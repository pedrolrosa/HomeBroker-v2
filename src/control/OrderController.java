/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.entities.Order;
import model.enums.TypeOrder;
import model.repositories.impl.OrderImpl;
import model.repositories.services.OrderServices;

/**
 *
 * @author pedro
 */
public class OrderController {
    
    private static final OrderImpl database = new OrderImpl();
    
    private static final OrderServices databaseServices = new OrderServices();
    
    public static Order search(Long id){
        return databaseServices.target(id);
    }
    
    public static boolean create(Order attempt){
        if(attempt == null){
            return false;
        } else {
            if(AssetNegotiationController.verifyOrderExecution(attempt)){
                
            } else {
                return database.create(attempt);
            }
        }
        
        return false;
    }
    
    public static List<Order> read(){
        return database.read();
    }
    
    public static boolean update(Order attempt){
        if(attempt == null){
            return false;
        } else {
            return database.update(attempt);
        }
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
    
}
