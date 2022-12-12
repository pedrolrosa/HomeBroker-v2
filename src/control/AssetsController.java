/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.swing.JOptionPane;
import java.util.List;
import model.entities.Assets;
import model.entities.Assets;
import model.repositories.impl.AssetsImpl; 

/**
 *
 * @author silva.junior
 */
public class AssetsController {
    
 
    private AssetsImpl database = new AssetsImpl();
    
    public boolean create(Assets asset){
        if(asset == null){ 
            JOptionPane.showMessageDialog(null, "Invalid inserts");
            return false; 
        }else{
             
            return database.create(asset).isPresent(); 
        }
    } 
    
    public List<Assets> read(){
        return database.read(); 
    }
    
    public boolean update (Assets asset){
      if(asset == null){
       JOptionPane.showMessageDialog(null, "Invalid updates");
       return false; 
      }else{
       return database.update(asset).isPresent(); 
      } 
      
    }
    
    public boolean delete(Long id){
     return database.delete(id); 
    }
    
}
