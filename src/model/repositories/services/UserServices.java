/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.database.ConnectionFactory;
import model.entities.User;
import model.enums.TypeUser;
import model.repositories.BaseRepository;
import model.repositories.UserRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class UserServices extends BaseImpl implements UserRepository, BaseRepository.Target<User, Long> {

    @Override
    public User target(Long code) {
        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = createPreparedStatement("users", connection, code);  
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String cpf = rs.getString("cpf");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String type = rs.getString("type");
                Long account = rs.getLong("account");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S");
                LocalDateTime start = LocalDateTime.parse(rs.getTimestamp("start").toString(), formatter);
                LocalDateTime modify = null;
                if(rs.getTimestamp("modify") != null){
                    modify = LocalDateTime.parse(rs.getTimestamp("modify").toString(), formatter);
                }
                

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setCpf(cpf);
                user.setAddress(address);
                user.setPhone(phone);
                user.setLogin(login);
                user.setPassword(password);
                user.setType(TypeUser.valueOf(type));
                user.setAccount(account);
                user.setStart(start);
                user.setModify(modify);
                
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    @Override
    public boolean coupling(Long account, Long id){
        String sql = "update users set account = ? where id = ?";

        try ( Connection connection = new ConnectionFactory().getConnection();  
              PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, account);
            stmt.setLong(2, id);

            stmt.execute();

            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User authenticated(String login, String password) {

        String sql = "select id from users where login = ? and password = ?;";

        try ( Connection connection = new ConnectionFactory().getConnection();  
                PreparedStatement stmt = connection.prepareStatement(sql);  
                ) {
            
            stmt.setString(1, login);
            stmt.setString(2, password);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return target(rs.getLong("id"));
                }
                return null;                
            } catch(SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
