/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.repositories.services;

import model.entities.OrderExecution;
import model.repositories.BaseRepository;
import model.repositories.OrderExecutionRepository;
import model.repositories.impl.BaseImpl;

/**
 *
 * @author Aluno
 */
public class OrderExecutionServices extends BaseImpl implements OrderExecutionRepository, BaseRepository.Target<OrderExecution, Long>{

    @Override
    public OrderExecution target(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
