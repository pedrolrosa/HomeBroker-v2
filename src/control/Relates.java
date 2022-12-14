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
public class Relates {
    
    private static final RelatesAccountAssetImpl database = new RelatesAccountAssetImpl();
    private static final RelatesAccountAssetServices databaseServices = new RelatesAccountAssetServices();
    
    public static Long requestId(Long account){
        return databaseServices.requestId(account);
    }
    
    public static RelatesAccountAsset search(Long account){
        return databaseServices.target(requestId(account));
    }
    
    public static boolean create(RelatesAccountAsset attempt){
        return database.create(attempt).isPresent();
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
        return database.update(transaction).isPresent();
    }
    
    public static boolean delete(Long id){
        return database.delete(id);
    }
    
}
