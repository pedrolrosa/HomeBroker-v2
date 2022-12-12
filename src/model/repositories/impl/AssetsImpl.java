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
import java.util.Optional;
import model.repositories.BaseRepository;
import model.entities.Assets; 

import model.database.ConnectionFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author silva.junior
 */
public class AssetsImpl implements BaseRepository<Assets, Long>{
     
 @Override
    public Optional<Assets> create(Assets element){
        String sql = "insert into assets "
                + "(company,ticker,amount,initialPrice)" + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, element.getCompany());
            stmt.setString(2, element.getTicker());
            stmt.setInt(3, element.getAmount());
            stmt.setBigDecimal(4, element.getInitialPrice());
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }
    
    @Override
    public List<Assets> read(){
        String sql = "select * from assets";

        List<Assets> assets = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String company = rs.getString("company");
                String ticker = rs.getString("ticker");
                int amount = rs.getInt("amount");
                BigDecimal initialPrice = rs.getBigDecimal("initialPrice");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                Assets asset = new Assets();
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
    public Optional<Assets> update(Assets element){
        
        String sql = "update assets set name = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, element.getCompany());
            stmt.setLong(2, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
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
