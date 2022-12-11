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
import model.entities.Transaction;
import model.enums.TypeTransaction;
import model.repositories.BaseRepository;

/**
 *
 * @author pedro
 */
public class TransactionImpl implements BaseRepository<Transaction, Long>{
    
    @Override
    public Optional<Transaction> create(Transaction element){
        String sql = "insert into transactions "
                + "(owner, destiny, type, description, value, start)" + " values (?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, element.getOwner());
            stmt.setLong(2, element.getDestiny());
            stmt.setString(3, element.getType().toString());
            stmt.setString(4, element.getDescription());
            stmt.setBigDecimal(5, element.getValue());
            stmt.setDate(4, java.sql.Date.valueOf(element.getStart()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }
    
    @Override
    public List<Transaction> read(){
        String sql = "select * from transactions";

        List<Transaction> users = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long owner = rs.getLong("owner");
                Long destiny = rs.getLong("destiny");
                String type = rs.getString("type");
                String description = rs.getString("description");
                BigDecimal value = rs.getBigDecimal("value");
                Date start = rs.getDate("start");
                Date modify = rs.getDate("modify");

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setOwner(owner);
                transaction.setDestiny(destiny);
                transaction.setType(TypeTransaction.valueOf(type));
                transaction.setDescription(description);
                transaction.setValue(value);
                transaction.setStart(LocalDate.ofInstant(start.toInstant(), ZoneId.systemDefault()));
                transaction.setModify(LocalDate.ofInstant(modify.toInstant(), ZoneId.systemDefault()));
                
                users.add(transaction);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return users;
    }
    
    @Override
    public Optional<Transaction> update(Transaction element){
        
        String sql = "update transactions set description = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, element.getDescription());
            stmt.setLong(2, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
    
    @Override
    public Optional<Transaction> delete(Transaction element){
        
        String sql = "delete from transactions where id = ?";

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
}
