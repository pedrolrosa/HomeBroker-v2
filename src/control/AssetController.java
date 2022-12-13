/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.swing.JOptionPane;
import java.util.List;
import model.entities.Asset;
import model.repositories.impl.AssetImpl; 
import model.repositories.services.AssetServices;

/**
 *
 * @author silva.junior
 */
public class AssetController {
    
 
    private static final AssetImpl database = new AssetImpl();
    
    private static final AssetServices databaseServices = new AssetServices();
    
    public static Asset search(Long id){
        return databaseServices.target(id);
    }
    
    public static boolean create(Asset asset){
        if(asset == null){ 
            JOptionPane.showMessageDialog(null, "Invalid inserts");
            return false; 
        }else{
             
            return database.create(asset).isPresent(); 
        }
    } 
    
    public static List<Asset> read(){
        return database.read(); 
    }
    
    public static boolean update (Asset asset){
      if(asset == null){
       JOptionPane.showMessageDialog(null, "Invalid updates");
       return false; 
      }else{
       return database.update(asset).isPresent(); 
      } 
      
    }
    
    public static boolean delete(Long id){
     return database.delete(id); 
    }
    
}
