/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.repositories.impl.OrderExecutionImpl;
import model.repositories.services.OrderExecutionServices;

/**
 *
 * @author Aluno
 */
public class OrderExecutionController {
    
    private static final OrderExecutionImpl database = new OrderExecutionImpl();
    
    private static final OrderExecutionServices databaseServices = new OrderExecutionServices();
    
    public boolean create(){
        return false;
    }
    
    public boolean read(){
        return false;
    }
    
    public boolean update(){
        return false;
    }
    
    public boolean delete(){
        return false;
    }
    
}
