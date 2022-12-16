/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repositories.services;

import model.entities.AssetNegotiation;
import model.entities.Order;
import model.repositories.AssetNegotiationRepository;
import model.repositories.BaseRepository;
import model.repositories.impl.BaseImpl;



/**
 *
 * @author pedro
 */
public class AssetNegotiationServices extends BaseImpl implements AssetNegotiationRepository, BaseRepository.Target<AssetNegotiation, Long> {
    
    @Override
    public AssetNegotiation target(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean verifyOrderBuy(Order attempt){
        
        
        
        return false;
        
    }
    
    @Override
    public boolean verifyOrderSell(Order attempt){
        
        
        
        return false;
        
    }

    
}
