/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.entities.RelatesAccountAsset;
import model.repositories.impl.RelatesAccountAssetImpl;
import model.repositories.services.RelatesAccountAssetServices;

/**
 *
 * @author pedro
 */
public class RelatesController {
    
    private static final RelatesAccountAssetImpl database = new RelatesAccountAssetImpl();
    private static final RelatesAccountAssetServices databaseServices = new RelatesAccountAssetServices();
    
    public static Long requestId(Long account){
        return databaseServices.requestId(account);
    }
    
    public static Long requestId(Long account, Long asset){
        return databaseServices.requestId(account, asset);
    }
    
    public static RelatesAccountAsset search(Long account){
        return databaseServices.target(requestId(account));
    }
    
    public static RelatesAccountAsset searchPerId(Long id){
        if(id != null){
            return databaseServices.target(id);
        }
        return null;
    }
    
    public static boolean addAmount(Long id, Integer quantity){
        return databaseServices.updateAmount(id, searchPerId(id).getQuantity() + quantity);
    }
    
    public static boolean subAmount(Long id, Integer quantity){
        return databaseServices.updateAmount(id, searchPerId(id).getQuantity() - quantity);
    }
    
    public static boolean create(RelatesAccountAsset attempt){
        return database.create(attempt);
    }
    
    public static List<RelatesAccountAsset> read(){
        return database.read();
    }
    
    public static List<RelatesAccountAsset> readAssets(Long account){
        return databaseServices.searchAssets(account);
    }
    
    public static List<RelatesAccountAsset> readAccounts(Long asset){
        return databaseServices.searchAccounts(asset);
    }
    
    public static boolean update(RelatesAccountAsset transaction){
        return database.update(transaction);
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
    
}
