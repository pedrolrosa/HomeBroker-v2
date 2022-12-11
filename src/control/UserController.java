/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.swing.JOptionPane;
import model.entities.User;

/**
 *
 * @author pedro
 */
public class UserController {
    
    public User logued;
    
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
}
