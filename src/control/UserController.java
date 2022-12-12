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
    
    public User logued;
    
    private UserImpl database = new UserImpl();
    private UserServices databaseServices = new UserServices();
    
    public boolean login(String user, String password){
        User attempt = new User().authenticated(user, password);
        if(attempt != null){
            this.logued = attempt;
            return true;
        } 
        return false;
    }
    
    public void refresh(){
        logued =  databaseServices.target(logued.getId());
    }
    
    public boolean coupling(Long account, Long id){
        return databaseServices.coupling(account, id);
    }
    
    public User search(Long id){
        return databaseServices.target(id);
    }
    
    public boolean create(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.create(attempt).isPresent();
        }
    }
    
    public List<User> read(){
        return database.read();
    }
    
    public boolean update(User attempt){
        if(attempt == null){
            return false;
        } else {
            return database.update(attempt).isPresent();
        }
    }
    
    public boolean delete(Long id){
        return database.delete(id);
    }
}
