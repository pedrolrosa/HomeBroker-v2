/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.database.ConnectionFactory;
import model.entities.OrderExecution;
import model.repositories.BaseRepository;
import model.repositories.OrderExecutionRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author Aluno
 */
public class OrderExecutionServices extends BaseImpl implements OrderExecutionRepository, BaseRepository.Target<OrderExecution, Long>{

    @Override
    public OrderExecution target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = createPreparedStatement("ordersExecution", connection, id); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long order = rs.getLong("order_id");
                Long buyer = rs.getLong("buyer");
                Long seller = rs.getLong("seller");
                Integer quantity = rs.getInt("quantity");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                OrderExecution execution = new OrderExecution();
                execution.setId(id);
                execution.setOrder(order);
                execution.setBuyer(buyer);
                execution.setSeller(seller);
                execution.setQuantity(quantity);

                execution.setStart(start);
                execution.setModify(modify);

                return execution;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Integer quantityOrigin(Long order) {
        
        Integer quantity = 0;
        
        String sql = "select quantity from ordersExecution where order_id = ?";
        
        try(Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setLong(1, order);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    quantity += rs.getInt("quantity");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return quantity;
    }
    
    
    
}
