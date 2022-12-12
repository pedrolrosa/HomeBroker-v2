/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;
import java.math.BigDecimal;
import java.util.Optional;
import model.entities.Account;

/**
 *
 * @author pedro
 */
public interface AccountRepository{
    
    Account acess(Long id);
    
    boolean deposit(Long id, BigDecimal value);
    
    boolean withdraw(Long id, BigDecimal value);
    
    boolean transfer(Long id, Long destiny, BigDecimal value);
}