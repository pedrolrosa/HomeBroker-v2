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
import model.database.ConnectionFactory;
import model.entities.User;
import model.enums.TypeUser;
import model.repositories.BaseRepository;

/**
 *
 * @author pedro
 */
public class UserImpl implements BaseRepository<User, Long>{
    
    @Override
    public Optional<User> create(User element){
        String sql = "insert into users "
                + "(name,cpf,addres,phone,login,password,type)" + " values (?,?,?,?,?,HASH('SHA256', ?),?)";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, element.getName());
            stmt.setString(2, element.getCpf());
            stmt.setString(3, element.getAddress());
            stmt.setString(4, element.getPhone());
            stmt.setString(5, element.getLogin());
            stmt.setString(6, element.getPassword());
            stmt.setString(7, element.getType().toString());
            
            stmt.execute();
            
            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(element);
    }
    
    @Override
    public List<User> read(){
        String sql = "select * from users";

        List<User> users = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String type = rs.getString("type");
                Date start = rs.getDate("start");
                Date modify = rs.getDate("modify");

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setCpf(cpf);
                user.setAddress(address);
                user.setPhone(phone);
                user.setLogin(login);
                user.setPassword(password);
                user.setType(TypeUser.valueOf(type));
                user.setStart(LocalDate.ofInstant(start.toInstant(), ZoneId.systemDefault()));
                user.setModify(LocalDate.ofInstant(modify.toInstant(), ZoneId.systemDefault()));
                users.add(user);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        return users;
    }
    
    @Override
    public Optional<User> update(User element){
        
        String sql = "update users set name = ? where id = ?";

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, element.getName());
            stmt.setLong(2, element.getId());
            
            stmt.execute();
            
            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(element);
        
    }
    
    @Override
    public Optional<User> delete(User element){
        
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
