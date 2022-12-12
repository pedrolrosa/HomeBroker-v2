/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.repositories.BaseRepository;

/**
 *
 * @author pedro
 */
public class AccountImpl extends BaseImpl implements BaseRepository<Account, Long> {

    @Override
    public Optional<Account> create(Account element) {
        String sql = "insert into accounts "
                + "(owner, amount, max, start)" + " values (?,?,?,?)";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, element.getOwner());
            stmt.setBigDecimal(2, element.getAmount());
            stmt.setDouble(3, element.getMax());
            stmt.setTimestamp(4, Timestamp.valueOf(element.getStart()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(element);
    }

    @Override
    public List<Account> read() {
        String sql = "select * from accounts";

        List<Account> accounts = new ArrayList<>();

        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long owner = rs.getLong("owner");
                BigDecimal amount = rs.getBigDecimal("amount");
                Double max = rs.getDouble("max");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Account account = new Account();
                account.setId(id);
                account.setOwner(owner);
                account.setAmount(amount);
                account.setMax(max);
                account.setStart(start);
                account.setModify(modify);

                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    @Override
    public Optional<Account> update(Account element) {

        String sql = "update accounts set limit = ? where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, element.getMax());
            stmt.setLong(2, element.getId());

            stmt.execute();

            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);

    }

    @Override
    public boolean delete(Long id) {

        String sql = "delete from accounts where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
