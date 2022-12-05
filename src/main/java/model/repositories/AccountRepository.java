/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;
import java.math.BigDecimal;
import model.entities.Account;
import java.util.Optional;

/**
 *
 * @author pedro
 */
public interface AccountRepository extends BaseRepository<Account, Long>{
    
    Optional<Account> deposit(Account current, BigDecimal value);
    
    Optional<Account> withdraw(Account current, BigDecimal value);
    
    Optional<Account> transfer(Account origin, Long destiny, BigDecimal value);
}