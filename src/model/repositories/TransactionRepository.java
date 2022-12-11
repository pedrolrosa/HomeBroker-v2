/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import java.util.Date;
import java.util.List;
import model.entities.Transaction;

/**
 *
 * @author pedro
 */
public interface TransactionRepository{
    
    List<Transaction> serchPeriod(Date start, Date end);
}
