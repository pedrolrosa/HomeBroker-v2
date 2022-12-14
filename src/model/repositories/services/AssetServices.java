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
import model.database.ConnectionFactory;
import model.entities.Asset;
import model.repositories.AssetRepository;
import model.repositories.BaseRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class AssetServices extends BaseImpl implements AssetRepository, BaseRepository.Target<Asset, Long> {

    @Override
    public Asset target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = createPreparedStatement("assets", connection, id); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                String company = rs.getString("company");
                String ticker = rs.getString("ticker");
                Integer amount = rs.getInt("amount");
                BigDecimal initialPrice = rs.getBigDecimal("initial_price");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Asset asset = new Asset();
                asset.setId(id);
                asset.setCompany(company);
                asset.setTicker(ticker);
                asset.setAmount(amount);
                asset.setInitialPrice(initialPrice);
                asset.setStart(start);
                asset.setModify(modify);


                return asset;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean updateAmount(Long id, Integer quantity) {
        String sql = "update assets set amount = ?, modify = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, id);
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
