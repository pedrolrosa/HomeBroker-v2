/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.entities.AssetNegotiation;
import model.repositories.impl.AssetNegotiationImpl;
import model.repositories.services.AssetNegotiationServices;

/**
 *
 * @author pedro
 */
public class AssetNegotiationController {

    private static final AssetNegotiationImpl database = new AssetNegotiationImpl();

    private static final AssetNegotiationServices databaseServices = new AssetNegotiationServices();
    
    public static AssetNegotiation getPriceAsset(Long asset){
        return databaseServices.search(asset);
    }
    
    public static Double totalSpend(Long asset, Long buyer){
        return databaseServices.totalSpend(asset, buyer);
    }

    public static boolean create(AssetNegotiation attempt) {
        return database.create(attempt);
    }

    public static List<AssetNegotiation> read() {
        return database.read();
    }

    public static boolean update() {
        return false;
    }

    public static boolean delete(Long id) {
        return database.delete(id);
    }

}
