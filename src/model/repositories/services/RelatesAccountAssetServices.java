/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

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
import model.entities.RelatesAccountAsset;
import model.repositories.BaseRepository;
import model.repositories.RelatesAccountAssetRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class RelatesAccountAssetServices extends BaseImpl implements RelatesAccountAssetRepository, BaseRepository.Target<RelatesAccountAsset, Long> {

    @Override
    public RelatesAccountAsset target(Long id) {
        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = createPreparedStatement("relatesAccountAssets", connection, id); 
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long account = rs.getLong("account");
                Long asset = rs.getLong("asset");
                Integer quantity = rs.getInt("quantity");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if (rs.getTimestamp("modify") != null) {
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }

                RelatesAccountAsset related = new RelatesAccountAsset();
                related.setId(id);
                related.setAccount(account);
                related.setAsset(asset);
                related.setQuantity(quantity);

                related.setStart(start);
                related.setModify(modify);

                return related;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Long requestId(Long id) {
        String sql = "select id from relatesAccountAssets where account = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    return rs.getLong("id");
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
    public Long requestId(Long account, Long asset) {
        String sql = "select id from relatesAccountAssets where account = ? and asset = ?";

        try (Connection connection = new ConnectionFactory().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, account);
            stmt.setLong(2,asset);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("id");
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
    public List<RelatesAccountAsset> searchAssets(Long account) {
        String sql = "select * from relatesAccountAssets where account = ?";

        List<RelatesAccountAsset> relates = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, account);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long asset = rs.getLong("asset");
                    Integer quantity = rs.getInt("quantity");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                    LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                    LocalDateTime modify = null;
                    if (rs.getTimestamp("modify") != null) {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return relates;
    }

    @Override
    public List<RelatesAccountAsset> searchAccounts(Long asset) {
        String sql = "select * from relatesAccountAssets where account = ?";

        List<RelatesAccountAsset> relates = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, asset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    Long account = rs.getLong("account");
                    Integer quantity = rs.getInt("quantity");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                    LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                    LocalDateTime modify = null;
                    if (rs.getTimestamp("modify") != null) {
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return relates;
    }
    
    @Override
    public boolean updateAmount(Long id, Integer quantity) {
        String sql = "update relatesAccountAssets set quantity = ?, modify = ? where id = ?";

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
