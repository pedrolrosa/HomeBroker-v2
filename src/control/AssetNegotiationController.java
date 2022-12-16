/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.math.BigDecimal;
import model.entities.AssetNegotiation;
import model.entities.Order;
import model.enums.TypeOrder;
import model.repositories.impl.AssetNegotiationImpl;
import model.repositories.services.AssetNegotiationServices;

/**
 *
 * @author pedro
 */
public class AssetNegotiationController {

    private static final AssetNegotiationImpl database = new AssetNegotiationImpl();

    private static final AssetNegotiationServices databaseServices = new AssetNegotiationServices();
    
    public static boolean attPriceAsset(Long asset, BigDecimal value){
        return false;
    }

    public static boolean create(AssetNegotiation attempt) {
        return database.create(attempt);
    }

    public static boolean read() {
        return false;
    }

    public static boolean update() {
        return false;
    }

    public static boolean delete() {
        return false;
    }

}
