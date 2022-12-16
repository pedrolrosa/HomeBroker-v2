/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.database.ConnectionFactory;
import model.entities.OrderExecution;
import model.repositories.BaseRepository;

/**
 *
 * @author Aluno
 */
public class OrderExecutionImpl implements BaseRepository<OrderExecution, Long> {

    @Override
    public boolean create(OrderExecution element) {
        String sql = "insert into ordersExecution "
                + "(order, buyer, seller, quantity, start)" + " values (?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, element.getOrder());
            stmt.setLong(2, element.getBuyer());
            stmt.setLong(3, element.getSeller());
            stmt.setInt(4, element.getQuantity());
            stmt.setTimestamp(5, Timestamp.valueOf(element.getStart()));
            
            stmt.execute();
            
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderExecution> read() {
        String sql = "select * from orders";

        List<OrderExecution> executions = new ArrayList<>();

        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long order = rs.getLong("order");
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

                executions.add(execution);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return executions;
    }

    @Override
    public boolean update(OrderExecution obj) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from ordersExecutions where id = ?";

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
