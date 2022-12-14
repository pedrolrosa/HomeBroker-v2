/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import java.util.List;
import model.entities.RelatesAccountAsset;

/**
 *
 * @author pedro
 */
public interface RelatesAccountAssetRepository {
    
    Long requestId(Long account);
    
    List<RelatesAccountAsset> searchAssets(Long account);
    
    List<RelatesAccountAsset> searchAccounts(Long account);
    
}
