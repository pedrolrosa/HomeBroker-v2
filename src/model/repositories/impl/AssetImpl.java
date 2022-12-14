/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.repositories.BaseRepository;
import model.entities.Asset; 

import model.database.ConnectionFactory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author silva.junior
 */
public class AssetImpl implements BaseRepository<Asset, Long>{
     
 @Override
    public boolean create(Asset element){
        String sql = "insert into assets "
                + "(company,ticker,amount,initial_price, start)" + " values (?,?,?,?, ?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, element.getCompany());
            stmt.setString(2, element.getTicker());
            stmt.setInt(3, element.getAmount());
            stmt.setBigDecimal(4, element.getInitialPrice());
            stmt.setTimestamp(5, Timestamp.valueOf(element.getStart()));
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public List<Asset> read(){
        String sql = "select * from assets";

        List<Asset> assets = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String company = rs.getString("company");
                String ticker = rs.getString("ticker");
                int amount = rs.getInt("amount");
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
                
                assets.add(asset); 
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return assets;
    }
    
    @Override
    public boolean update(Asset element){
        
        String sql = "update assets set company = ?, ticker = ?, amount = ?, modify = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, element.getCompany());
            stmt.setString(2, element.getTicker());
            stmt.setInt(3, element.getAmount());
            stmt.setTimestamp(4,Timestamp.valueOf(element.getModify()));
            stmt.setLong(5, element.getId());
            
            stmt.execute();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    @Override
    public boolean delete(Long id){
        
        String sql = "delete from assets where id = ?";

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
