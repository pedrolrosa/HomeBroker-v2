/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import model.entities.AssetNegotiation;

/**
 *
 * @author pedro
 */
public interface AssetNegotiationRepository {
    
    Double totalSpend(Long asset, Long buyer);
    
    AssetNegotiation search(Long asset);
    
}
