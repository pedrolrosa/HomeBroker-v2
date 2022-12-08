/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;
import java.math.BigDecimal;
import java.util.Optional;

/**
 *
 * @author pedro
 */
public interface AccountRepository{
    
    Optional<BigDecimal> deposit(Long id, BigDecimal value);
    
    Optional<BigDecimal> withdraw(Long id, BigDecimal value);
    
    Optional<BigDecimal> transfer(Long id, Long destiny, BigDecimal value);
}