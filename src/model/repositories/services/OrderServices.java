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
import model.database.ConnectionFactory;
import model.entities.Account;
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
                String ticker = rs.getString("ticker");
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
                order.setTicker(ticker);
                order.setValue(value);
                order.setTotalValue(valueTotal);
                order.setState(state);
                order.setStart(start);
                order.setModify(modify);

                order.setStart(start);
                order.setModify(modify);

                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
