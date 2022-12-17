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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.database.ConnectionFactory;
import model.entities.Order;
import model.enums.StateOrder;
import model.enums.TypeOrder;
import model.repositories.BaseRepository;

/**
 *
 * @author pedro
 */
public class OrderImpl implements BaseRepository<Order, Long> {

    @Override
    public boolean create(Order element) {
        String sql = "insert into orders "
                + "(account,type,ticker,quantity,value,total_value,state,start)" + " values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, element.getAccount());
            stmt.setString(2, element.getType().name());
            stmt.setLong(3, element.getAsset());
            stmt.setInt(4, element.getQuantity());
            stmt.setDouble(5, element.getValue().doubleValue());
            stmt.setDouble(6, element.getTotalValue().doubleValue());
            stmt.setString(7, element.getState().toString());
            stmt.setTimestamp(8, Timestamp.valueOf(element.getStart()));

            stmt.execute();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long createReturnID(Order element) {
        String sql = "insert into orders "
                + "(account,type,asset,quantity,value,total_value,state,start)" + " values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, element.getAccount());
            stmt.setString(2, element.getType().name());
            stmt.setLong(3, element.getAsset());
            stmt.setInt(4, element.getQuantity());
            stmt.setDouble(5, element.getValue().doubleValue());
            stmt.setDouble(6, element.getTotalValue().doubleValue());
            stmt.setString(7, element.getState().toString());
            stmt.setTimestamp(8, Timestamp.valueOf(element.getStart()));

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();

            Long id = null;
            if (rs.next()) {
                id = rs.getLong(1);
            }

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> read() {
        String sql = "select * from orders";

        List<Order> orders = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
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
                if (rs.getTimestamp("modify") != null) {
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

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    public List<Order> read(Long asset) {
        String sql = "select * from orders where asset = ?";

        List<Order> orders = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, asset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long account = rs.getLong("account");
                    TypeOrder type = TypeOrder.valueOf(rs.getString("type"));
                    Integer quantity = rs.getInt("quantity");
                    BigDecimal value = rs.getBigDecimal("value");
                    BigDecimal valueTotal = rs.getBigDecimal("total_value");
                    StateOrder state = StateOrder.valueOf(rs.getString("state"));

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                    LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                    LocalDateTime modify = null;
                    if (rs.getTimestamp("modify") != null) {
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

                    orders.add(order);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    @Override
    public boolean update(Order element) {
        String sql = "update orders set value = ?, quantity = ?, total_value = ?, modify = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, element.getValue());
            stmt.setInt(2, element.getQuantity());
            stmt.setBigDecimal(3, element.getTotalValue());
            stmt.setTimestamp(4, Timestamp.valueOf(element.getModify()));
            stmt.setLong(5, element.getId());

            stmt.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from orders where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.execute();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
