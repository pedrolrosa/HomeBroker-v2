/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.swing.JOptionPane;
import model.entities.User;
import model.repositories.impl.UserImpl;

/**
 *
 * @author pedro
 */
public class UserController {
    
    public User logued;
    
    private UserImpl database = new UserImpl();
    
    public boolean login(String user, String password){
        User attempt = new User().authenticated(user, password);
        if(attempt == null){
            JOptionPane.showMessageDialog(null, "User Not Found");
            return false;
        } else {
            this.logued = attempt;
            return true;
        }
    }
    
    public boolean create(User attempt){
        if(attempt == null){
            JOptionPane.showMessageDialog(null, "Invalid Inserts");
            return false;
        } else {
            return database.create(attempt).isPresent();
        }
    }
}
