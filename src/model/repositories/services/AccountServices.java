/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import control.AccountController;
import control.DateControl;
import control.TransactionController;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.entities.Transaction;
import model.enums.TypeTransaction;
import model.repositories.AccountRepository;
import model.repositories.BaseRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class AccountServices extends BaseImpl implements AccountRepository, BaseRepository.Target<Account, Long> {

    @Override
    public Account target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = createPreparedStatement("accounts", connection, id); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long owner = rs.getLong("owner");
                BigDecimal amount = rs.getBigDecimal("amount");
                BigDecimal max = rs.getBigDecimal("max");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if (rs.getTimestamp("modify") != null) {
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Account account = new Account();
                account.setId(id);
                account.setOwner(owner);
                account.setAmount(amount);
                account.setMax(max);

                account.setStart(start);
                account.setModify(modify);

                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Long searchPerType(String type) {
        String sql = "select accounts.id from accounts inner join users on accounts.id = users.account where users.type = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, type);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Integer feeMonth() {

        Integer nTurn = 0;

        String sql = "select accounts.id from accounts inner join users on accounts.id = users.account where users.type = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setString(1, "COMMOM");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");

                    BigDecimal amount = target(id).getAmount().subtract(new BigDecimal(20));

                    if (withdraw(id, amount)) {
                        Transaction transaction = new Transaction();
                        transaction.setDescription("fee");
                        transaction.setDestiny(AccountController.searchAdm());
                        transaction.setOwner(target(id).getId());
                        transaction.setType(TypeTransaction.TRANSFER);
                        transaction.setValue(new BigDecimal(20));
                        transaction.setStart(DateControl.now());
                        
                        TransactionController.create(transaction);
                    }
                    nTurn++;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return nTurn;
    }

    @Override
    public Account acess(Long owner) {

        String sql = "select id from accounts where owner = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql);) {

            stmt.setLong(1, owner);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return target(rs.getLong("id"));
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deposit(Long id, BigDecimal value) {
        String sql = "update accounts set amount = ? where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, value);
            stmt.setLong(2, id);

            stmt.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean withdraw(Long id, BigDecimal value) {
        String sql = "update accounts set amount = ? where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, value);
            stmt.setLong(2, id);

            stmt.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean transfer(Long id, Long destiny, BigDecimal origin, BigDecimal dest) {
        String sql = "update accounts set amount = ? where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, origin);
            stmt.setLong(2, id);

            stmt.execute();

            try (PreparedStatement stmtB = connection.prepareStatement(sql)) {

                stmtB.setBigDecimal(1, dest);
                stmtB.setLong(2, destiny);

                stmtB.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Long> accountsDividend(Long asset) {
        List<Long> accounts = new ArrayList<>();

        String sql = "select id from relatesaccountassets where asset = ? and account != 1";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, asset);

            stmt.execute();

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    accounts.add(rs.getLong("id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return accounts;
    }
}
