/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.impl;

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
import model.entities.RelatesAccountAsset;
import model.repositories.BaseRepository;


/**
 *
 * @author pedro
 */
public class RelatesAccountAssetImpl extends BaseImpl implements BaseRepository<RelatesAccountAsset, Long>{

    @Override
    public Optional<RelatesAccountAsset> create(RelatesAccountAsset element) {
        String sql = "insert into relatesAccountAssets "
                + "(account, asset, quantity, start)" + " values (?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
                stmt.setLong(1,element.getAccount());
                stmt.setLong(2,element.getAsset());
                stmt.setInt(3,element.getQuantity());
                stmt.setTimestamp(4, Timestamp.valueOf(element.getStart()));
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }

    @Override
    public List<RelatesAccountAsset> read() {
        String sql = "select * from relatesAccountAssets";

        List<RelatesAccountAsset> relates = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long account = rs.getLong("account");
                Long asset = rs.getLong("asset");
                Integer quantity = rs.getInt("quantity");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                RelatesAccountAsset related = new RelatesAccountAsset();
                related.setId(id);
                related.setAccount(account);
                related.setAsset(asset);
                related.setQuantity(quantity);
                related.setStart(start);
                related.setModify(modify);
                
                relates.add(related);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return relates;
    }

    @Override
    public Optional<RelatesAccountAsset> update(RelatesAccountAsset element) {
        String sql = "update relatesAccountAssets set quantity = ?, modify = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, element.getQuantity());
            stmt.setTimestamp(2,Timestamp.valueOf(element.getModify()));
            stmt.setLong(3, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
    }

    @Override
    public boolean delete(Long id) {
        
        String sql = "delete from relatesAccountAssets where id = ?";

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
