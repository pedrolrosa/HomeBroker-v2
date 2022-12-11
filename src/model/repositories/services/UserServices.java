/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.entities.User;
import model.enums.TypeUser;
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
        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = createPreparedStatement("accounts", connection, id);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User authenticate(String login, String password) {

        String sql = "select * from users where login = " + login + " and password = " + password;

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
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
                
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Optional<User> searchName(String name) {
        return null;
    }
}
