/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.entities.OrderExecution;
import model.repositories.impl.OrderExecutionImpl;
import model.repositories.services.OrderExecutionServices;

/**
 *
 * @author Aluno
 */
public class OrderExecutionController {
    
    private static final OrderExecutionImpl database = new OrderExecutionImpl();
    
    private static final OrderExecutionServices databaseServices = new OrderExecutionServices();
    
    public static Integer quantityOrigin(Long order){
        return databaseServices.quantityOrigin(order);
    }
    
   public static boolean create(OrderExecution attempt){
        if(attempt == null){
            return false;
        } else {
            return database.create(attempt);
        }
    }
    
    public static List<OrderExecution> read(){
        return database.read();
    }
    
    public static boolean update(OrderExecution attempt){
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
