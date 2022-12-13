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
import model.entities.Order;
import model.enums.StateOrder;
import model.enums.TypeOrder;
import model.repositories.BaseRepository;



/**
 *
 * @author pedro
 */
public class OrderImpl implements BaseRepository<Order, Long>{

    @Override
    public Optional<Order> create(Order element) {
        String sql = "insert into orders "
                + "(account,type,ticker,quantity,value,total_value,state,start)" + " values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, element.getAccount());
            stmt.setString(2, element.getType().name());
            stmt.setString(3, element.getTicker());
            stmt.setInt(4, element.getQuantity());
            stmt.setDouble(5, element.getValue().doubleValue());
            stmt.setDouble(6, element.getTotalValue().doubleValue());
            stmt.setString(7, element.getState().toString());
            stmt.setTimestamp(8, Timestamp.valueOf(element.getStart()));
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }

    @Override
    public List<Order> read() {
        String sql = "select * from orders";

        List<Order> orders = new ArrayList<>();

        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long account = rs.getLong("account");
                TypeOrder type = TypeOrder.valueOf(rs.getString("type"));
                String ticker = rs.getString("ticker");
                Integer quantity = rs.getInt("quantity");
                BigDecimal value = rs.getBigDecimal("value");
                BigDecimal valueTotal = rs.getBigDecimal("value_total");
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

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public Optional<Order> update(Order element) {
        String sql = "update orders set value = ? where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, element.getValue());
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
        String sql = "delete from orders where id = ?";

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
