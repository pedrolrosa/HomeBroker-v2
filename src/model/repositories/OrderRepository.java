/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import model.entities.Order;
import model.enums.StateOrder;

/**
 *
 * @author pedro
 */
public interface OrderRepository {
    
    void updateQuantity(Long id, Integer quantity);
    
    void updateState(Long id, StateOrder state);
    
    Order verifyOrderBuy(Order attempt);
    
    Order verifyOrderSell(Order attempt);
    
}
