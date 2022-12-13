/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private static final AccountImpl database = new AccountImpl();
    private static final AccountServices databaseServices = new AccountServices();

    public static String getNameLabel() {
        return name;
    }

    public static void setNameLabel(String name) {
        AccountController.name = name;
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
            attempt.setMax(Double.valueOf(attempt.getAmount().multiply(BigDecimal.TEN).toString()));
            attempt.setStart(LocalDateTime.now());

            return database.create(attempt).isPresent();
        }
    }

    public static boolean deposit(Long id, BigDecimal value) {
        return databaseServices.deposit(id, AccountController.current.addAmount(value));
    }

    public static boolean withdraw(Long id, BigDecimal value) {
        if (AccountController.current.getAmount().compareTo(value) >= 0) {
            return databaseServices.withdraw(id, AccountController.current.subAmount(value));
        }
        return false;
    }

    public static boolean transfer(Long id, Long destiny, BigDecimal value) {
        Account accountDestiny = AccountController.search(destiny);
        if (AccountController.current.getAmount().compareTo(value) >= 0) {
            if (accountDestiny != null) {
                return databaseServices.transfer(id, destiny, current.subAmount(value), accountDestiny.addAmount(value));
            }
        }
        return false; 
    }
}
