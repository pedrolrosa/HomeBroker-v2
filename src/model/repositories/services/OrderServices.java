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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.database.ConnectionFactory;
import model.entities.Order;
import model.enums.StateOrder;
import model.enums.TypeOrder;
import model.repositories.BaseRepository;
import model.repositories.OrderRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class OrderServices extends BaseImpl implements OrderRepository, BaseRepository.Target<Order, Long> {

    @Override
    public Order target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = createPreparedStatement("orders", connection, id); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long account = rs.getLong("account");
                TypeOrder type = TypeOrder.valueOf(rs.getString("type"));
                Long asset = rs.getLong("asset");
                Integer quantity = rs.getInt("quantity");
                BigDecimal value = rs.getBigDecimal("value");
                BigDecimal valueTotal = rs.getBigDecimal("total_value");
                StateOrder state = StateOrder.valueOf(rs.getString("state"));
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Order order = new Order();
                order.setId(id);
                order.setAccount(account);
                order.setType(type);
                order.setQuantity(quantity);
                order.setAsset(asset);
                order.setValue(value);
                order.setTotalValue(valueTotal);
                order.setState(state);

                order.setStart(start);
                order.setModify(modify);

                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void updateState(Long id, StateOrder state) {
        String sql = "update orders set state = ?, modify = ? where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,state.name());
            stmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3,id);

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void updateQuantity(Long id, Integer quantity){
        String sql = "update orders set quantity = ?, modify = ? where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1,quantity);
            stmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3,id);

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<Order> verifyOrderBuy(Order attempt) {
        
        List<Order> orders = new ArrayList<>();

        String sql = "select id from orders where type = ? and value <= ? and (state = ? or state = ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,"SELL");
            stmt.setBigDecimal(2,attempt.getValue());
            stmt.setString(3,"OPEN");
            stmt.setString(4,"PARCIAL");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(target(rs.getLong("id")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AssetNegotiationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;

    }

    @Override
    public List<Order> verifyOrderSell(Order attempt) {
        
        List<Order> orders = new ArrayList<>();

        String sql = "select id from orders where type = ? and value >= ? and (state = ? or state = ?)";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,"BUY");
            stmt.setBigDecimal(2,attempt.getValue());
            stmt.setString(3,"OPEN");
            stmt.setString(4,"PARCIAL");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(target(rs.getLong("id")));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AssetNegotiationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orders;

    }

    
    
}
