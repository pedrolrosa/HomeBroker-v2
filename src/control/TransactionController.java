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
    
    private Account current = new Account();
    
    private TransactionImpl database = new TransactionImpl();
    private TransactionServices databaseServices = new TransactionServices();

    public Account getCurrent() {
        return current;
    }

    public void setCurrent(Account current) {
        this.current = current;
    }
    
    public Transaction search(Long id){
        return databaseServices.target(id);
    }    
    
    public boolean create(Transaction attempt){
        return database.create(attempt).isPresent();
    }
    
    public List<Transaction> read(){
        return database.read();
    }
    
    public List<Transaction> read(Long owner){
        return databaseServices.search(owner);
    }
    
    public boolean update(Transaction transaction){
        return database.update(transaction).isPresent();
    }
    
    public boolean delete(Long id){
        return database.delete(id);
    }
}
