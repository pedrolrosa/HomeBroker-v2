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
import model.entities.AssetNegotiation;
import model.repositories.AssetNegotiationRepository;
import model.repositories.BaseRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class AssetNegotiationServices extends BaseImpl implements AssetNegotiationRepository, BaseRepository.Target<AssetNegotiation, Long> {

    @Override
    public AssetNegotiation target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = createPreparedStatement("assetNegotiation", connection, id); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
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

                return negotiation;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public AssetNegotiation search(Long asset){
        String sql = "select id from assetNegotiation where asset = ? order by id desc";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,asset);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return target(rs.getLong("id"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
        
    }
    
    @Override
    public Double totalSpend(Long asset, Long buyer){
        
        Double total = 0.0;
        
        String sql = "select id from assetNegotiation where asset = ? and buyer = ?";
        
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1,asset);
            stmt.setLong(2,buyer);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    total += Double.valueOf(target(rs.getLong("id")).getValueTotal().toString());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return total;
        
    }

}
