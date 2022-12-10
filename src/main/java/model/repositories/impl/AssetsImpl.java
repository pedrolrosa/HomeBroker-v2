/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import model.repositories.BaseRepository;
import model.entities.Assets; 

import model.database.ConnectionFactory;
import model.entities.User;
import model.enums.TypeUser;
import java.math.BigDecimal;

/**
 *
 * @author silva.junior
 */
public class AssetsImpl implements BaseRepository<Assets, Long>{
     
 @Override
    public Optional<Assets> create(Assets element){
        String sql = "insert into  "
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
                Date start = rs.getDate("start");
                Date modify = rs.getDate("modify");

                Assets asset = new Assets();
                asset.setId(id);
                asset.setCompany(company);
                asset.setTicker(ticker);
                asset.setAmount(amount);
                asset.setInitialPrice(initialPrice);
                
                asset.setStart(LocalDate.ofInstant(start.toInstant(), ZoneId.systemDefault()));
                asset.setModify(LocalDate.ofInstant(modify.toInstant(), ZoneId.systemDefault()));
                
                assets.add(asset); 
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return assets;
    }
    
    @Override
    public Optional<Assets> update(Assets element){
        
        String sql = "update users set name = ? where id = ?";

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
    public Optional<Assets> delete(Assets element){
        
        String sql = "delete from users where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento excluído com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
}
