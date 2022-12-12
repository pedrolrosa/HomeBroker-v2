/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
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
        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = createPreparedStatement("accounts", connection, id);  
              ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
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
                
                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    @Override
    public Account acess(Long owner){
        
        String sql = "select id from accounts where owner = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ) {
            
            stmt.setLong(1, owner);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return target(rs.getLong("id"));
                }
                return null;                
            } catch(SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<BigDecimal> deposit(Long id, BigDecimal value) {
        String sql = "update accounts set amount = ? where id = ?";
        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, value);
            stmt.setLong(2, id);

            stmt.execute();

            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<BigDecimal> withdraw(Long id, BigDecimal value) {
        return null;
    }

    @Override
    public Optional<BigDecimal> transfer(Long id, Long destiny, BigDecimal value) {
        return null;
    }
}
