/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class ConnectionFactory {
    public Connection getConnection() {
        
        Connection conexao = null;
        
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/homebroker","root","453923");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
        
        return conexao;
    }
}
