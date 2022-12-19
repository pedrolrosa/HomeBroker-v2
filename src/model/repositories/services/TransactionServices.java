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
import java.util.ArrayList;
import java.util.List;
import model.database.ConnectionFactory;
import model.entities.Transaction;
import model.enums.TypeTransaction;
import model.repositories.BaseRepository;
import model.repositories.TransactionRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class TransactionServices extends BaseImpl implements TransactionRepository, BaseRepository.Target<Transaction, Long> {
    
    @Override
    public Transaction target(Long id) {
        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = createPreparedStatement("transactions", connection, id);  
              ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
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

                return transaction;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    @Override
    public List<Transaction> search(Long owner){
        String sql = "select * from transactions where owner = ? or destiny = ? order by id desc";

        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql) ) {
            
                stmt.setLong(1, owner);
                stmt.setLong(2, owner);

                try(ResultSet rs = stmt.executeQuery()){
                    while (rs.next()) {
                    Long id = rs.getLong("id");
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
            } catch(SQLException e) {
                throw new RuntimeException(e);
            }
            
            
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return transactions;
    }
    
}
