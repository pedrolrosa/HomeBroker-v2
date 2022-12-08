/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.entities.User;
import model.repositories.BaseRepository;
import model.repositories.UserRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class UserServices extends BaseImpl implements UserRepository, BaseRepository.Target<Account, Long> {
    
    @Override
    public Account target(Long id) {
        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = createPreparedStatement("accounts", connection, id);  
              ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    @Override
    public Optional<User> searchName(String name){
        return null;
    }
}
