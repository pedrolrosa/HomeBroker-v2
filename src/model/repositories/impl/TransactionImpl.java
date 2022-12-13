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
            if(element.getType() == TypeTransaction.DEPOSIT || element.getType() == TypeTransaction.WITHDRAW){
                stmt.setNull(2,0);
            } else {
                stmt.setLong(2, element.getDestiny());
            }
            
            stmt.setString(3, element.getType().toString());
            stmt.setString(4, element.getDescription());
            stmt.setBigDecimal(5, element.getValue());
            stmt.setTimestamp(6, Timestamp.valueOf(element.getStart()));
            
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

        List<Transaction> transactions = new ArrayList<>();

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
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setOwner(owner);
                transaction.setDestiny(destiny);
                transaction.setType(TypeTransaction.valueOf(type));
                transaction.setDescription(description);
                transaction.setValue(value);
                transaction.setStart(start);
                transaction.setModify(modify);
                
                transactions.add(transaction);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return transactions;
    }
    
    @Override
    public Optional<Transaction> update(Transaction element){
        
        String sql = "update transactions set description = ?, modify = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, element.getDescription());
            stmt.setTimestamp(2,Timestamp.valueOf(element.getModify()));
            stmt.setLong(3, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
    
    @Override
    public boolean delete(Long id){
        
        String sql = "delete from transactions where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}
