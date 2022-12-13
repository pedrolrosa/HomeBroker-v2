/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.entities.User;
import model.repositories.impl.UserImpl;
import model.repositories.services.UserServices;

/**
 *
 * @author pedro
 */
public class UserController {
    
    public static User logued;
    
    private static final UserImpl database = new UserImpl();
    private static final UserServices databaseServices = new UserServices();
    
    public static boolean login(String user, String password){
        User attempt = databaseServices.authenticated(user, password);
        if(attempt != null){
            UserController.logued = attempt;
            return true;
        } 
        return false;
    }
    
    public static void refresh(){
        logued =  databaseServices.target(logued.getId());
    }
    
    public static boolean coupling(Long account, Long id){
        return databaseServices.coupling(account, id);
    }
    
    public static User search(Long id){
        return databaseServices.target(id);
    }
    
    public static boolean create(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.create(attempt).isPresent();
        }
    }
    
    public static List<User> read(){
        return database.read();
    }
    
    public static boolean update(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.update(attempt).isPresent();
        }
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
}
