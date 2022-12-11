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
import java.util.Optional;
import model.database.ConnectionFactory;
import model.entities.Account;
import model.repositories.AccountRepository;
import model.repositories.BaseRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author pedro
 */
public class AccountServices extends BaseImpl implements AccountRepository, BaseRepository.Target<Account, Long> {

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
    public Optional<BigDecimal> deposit(Long id, BigDecimal value) {
        String sql = "update accounts set amount = ? where id = ?";
        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, value);
            stmt.setLong(2, id);

            stmt.execute();

            System.out.println("Elemento alterado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<BigDecimal> withdraw(Long id, BigDecimal value) {
        return null;
    }

    @Override
    public Optional<BigDecimal> transfer(Long id, Long destiny, BigDecimal value) {
        return null;
    }
}
