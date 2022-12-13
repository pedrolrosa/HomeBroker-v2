/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.entities.Account;
import model.entities.Transaction;
import model.repositories.impl.TransactionImpl;
import model.repositories.services.TransactionServices;

/**
 *
 * @author pedro
 */
public class TransactionController {
    
    private static Account current = new Account();
    
    private static final TransactionImpl database = new TransactionImpl();
    private static final TransactionServices databaseServices = new TransactionServices();

    public Account getCurrent() {
        return current;
    }

    public static void setCurrent(Account current) {
        TransactionController.current = current;
    }
    
    public static Transaction search(Long id){
        return databaseServices.target(id);
    }    
    
    public static boolean create(Transaction attempt){
        return database.create(attempt).isPresent();
    }
    
    public static List<Transaction> read(){
        return database.read();
    }
    
    public static List<Transaction> read(Long owner){
        return databaseServices.search(owner);
    }
    
    public static boolean update(Transaction transaction){
        return database.update(transaction).isPresent();
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
}
