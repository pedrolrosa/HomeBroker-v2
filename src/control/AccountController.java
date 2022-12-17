/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.math.BigDecimal;
import java.util.List;
import model.entities.Account;
import model.repositories.impl.AccountImpl;
import model.repositories.services.AccountServices;

/**
 *
 * @author pedro
 */
public class AccountController {

    public static Account current;
    private static String name;
    private static Integer orderZero = 0;

    private static final AccountImpl database = new AccountImpl();
    private static final AccountServices databaseServices = new AccountServices();

    public static String getNameLabel() {
        return name;
    }

    public static void setNameLabel(String name) {
        AccountController.name = name;
    }
    
    public static void resetOrderZero(){
        orderZero = 0;
    }
    
    public static boolean addOrderZero(){
        
        if(orderZero < 3){
            orderZero += 1;
            return true;
        } 
        return false;
        
    }
    
    public static Long searchAdm(){
        return databaseServices.searchPerType("ADM");
    }
    
    public static boolean fee(BigDecimal value){
        return transfer(searchAdm(), value);
    }
    
    public static void feeMonth(){
        Integer nTurn = databaseServices.feeMonth();
        databaseServices.deposit(searchAdm(), databaseServices.target(searchAdm()).getAmount().add(new BigDecimal(20).multiply(new BigDecimal(nTurn))));
    }
    
    public static boolean hasBalance(BigDecimal value){
        
        return current.getAmount().compareTo(value) >= 0;
    }

    public static void refresh() {
        current = databaseServices.target(current.getId());
    }

    public static Account search(Long id) {
        return databaseServices.target(id);
    }

    public static boolean acess(Long owner) {
        Account attempt = databaseServices.acess(owner);

        if (attempt != null) {
            AccountController.current = attempt;
            return true;
        }
        return false;
    }

    public static boolean create(Long owner) {
        if (owner == null) {
            return false;
        } else {

            Account attempt = new Account();

            attempt.setOwner(owner);
            attempt.setAmount(new BigDecimal(20000));
            attempt.setMax(attempt.getAmount().multiply(BigDecimal.TEN));
            attempt.setStart(DateControl.now());

            return database.create(attempt);
        }
    }
    
    public static List<Account> read(){
        return database.read();
    }
    
    public static boolean update(Account attempt){
        return database.update(attempt);
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }

    public static boolean deposit(BigDecimal value) {
        current.addAmount(value);
        return databaseServices.deposit(current.getId(), AccountController.current.addAmount(value));
    }

    public static boolean withdraw(BigDecimal value) {
        if (AccountController.hasBalance(value)) {
            current.subAmount(value);
            return databaseServices.withdraw(current.getId(), AccountController.current.subAmount(value));
        }
        return false;
    }

    public static boolean transfer(Long destiny, BigDecimal value) {
        Account accountDestiny = AccountController.search(destiny);
        if (AccountController.current.getAmount().compareTo(value) >= 0) {
            if (accountDestiny != null) {
                current.subAmount(value);
                return databaseServices.transfer(current.getId(), destiny, current.subAmount(value), accountDestiny.addAmount(value));
            }
        }
        return false; 
    }
    
    public static boolean transferToMe(Long origin, BigDecimal value){
        Account accountOrigin = AccountController.search(origin);
        if (accountOrigin.getAmount().compareTo(value) >= 0) {
            current.addAmount(value);
            return databaseServices.transfer(origin, current.getId(), accountOrigin.subAmount(value), current.addAmount(value));
        }
        return false; 
    }
}
