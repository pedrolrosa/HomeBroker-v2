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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.repositories.AccountRepository;

/**
 *
 * @author pedro
 */
public class AccountRepositoryImpl implements AccountRepository{
    
    @Override
    public Optional<Account> create(Account element){
        String sql = "insert into accounts "
                + "(owner, amount, limit, start, modify)" + " values (?,?,?,?,null)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, element.getOwner());
            stmt.setBigDecimal(2, element.getAmount());
            stmt.setDouble(3, element.getLimit());
            stmt.setDate(4, java.sql.Date.valueOf(element.getStart()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }
    
    @Override
    public List<Account> read(){
        String sql = "select * from accounts";

        List<Account> accounts = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long owner = rs.getLong("owner");
                BigDecimal amount = rs.getBigDecimal("amount");
                Double limit = rs.getDouble("limit");
                Date start = rs.getDate("start");
                Date modify = rs.getDate("modify");

                Account account = new Account();
                account.setId(id);
                account.setOwner(owner);
                account.setAmount(amount);
                account.setLimit(limit);
                account.setStart(LocalDate.ofInstant(start.toInstant(), ZoneId.systemDefault()));
                account.setModify(LocalDate.ofInstant(modify.toInstant(), ZoneId.systemDefault()));
                
                accounts.add(account);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return accounts;
    }
    
    @Override
    public Optional<Account> read(Long id){
        
        return null;
        
    }
    
    @Override
    public Optional<Account> update(Account element){
        
        String sql = "update accounts set limit = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, element.getLimit());
            stmt.setLong(2, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
    
    @Override
    public Optional<Account> delete(Account element){
        
        String sql = "delete from accounts where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
    
    @Override
    public Optional<Account> deposit(Account current, BigDecimal value){
        
        return null;
    }
    
    @Override 
    public Optional<Account> withdraw(Account current, BigDecimal value){
        
        return null;
    }
    
    @Override
    public Optional<Account> transfer(Account current, Long idDestiny, BigDecimal value){
        
        return null;
    }
}
