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
import model.database.ConnectionFactory;
import model.entities.AssetNegotiation;
import model.repositories.BaseRepository;

/**
 *
 * @author pedro
 */
public class AssetNegotiationImpl implements BaseRepository<AssetNegotiation, Long>{

    @Override
    public boolean create(AssetNegotiation element) {
        
        String sql = "insert into assetNegotiation "
                + "(asset,buyer,seller,quantity,value,value_total,start)" + " values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setLong(1, element.getAsset());
            stmt.setLong(2,element.getBuyer());
            stmt.setLong(3,element.getSeller());
            stmt.setInt(4,element.getQuantity());
            stmt.setBigDecimal(5,element.getValue());
            stmt.setBigDecimal(6,element.getValueTotal());
            stmt.setTimestamp(7, Timestamp.valueOf(element.getStart()));
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AssetNegotiation> read() {
        
        String sql = "select * from assetNegotiation";

        List<AssetNegotiation> negotiations = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long asset = rs.getLong("asset");
                Long buyer = rs.getLong("buyer");
                Long seller = rs.getLong("seller");
                Integer quantity = rs.getInt("quantity");
                BigDecimal value = rs.getBigDecimal("value");
                BigDecimal valueTotal = rs.getBigDecimal("value_total");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                AssetNegotiation negotiation = new AssetNegotiation();
                negotiation.setId(id);
                negotiation.setAsset(asset);
                negotiation.setBuyer(buyer);
                negotiation.setSeller(seller);
                negotiation.setQuantity(quantity);
                negotiation.setValue(value);
                negotiation.setValueTotal(valueTotal);
                
                negotiation.setStart(start);
                negotiation.setModify(modify);
                
                negotiations.add(negotiation); 
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return negotiations;
        
    }

    @Override
    public boolean update(AssetNegotiation element) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "delete from assetNegotiation where id = ?";

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
