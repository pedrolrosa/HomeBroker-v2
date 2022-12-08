/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public abstract class BaseImpl {
    
    protected PreparedStatement createPreparedStatement(String table, Connection con, long id) throws SQLException {
        String sql = "select * from "+ table +" where id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        return ps;
    }
}
