/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

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

    public static boolean verifyOrderExecution(Order attempt) {
        if (attempt.getType().equals(TypeOrder.BUY)) {
            return databaseServices.verifyOrderBuy(attempt);
        } else if (attempt.getType().equals(TypeOrder.SELL)) {
            return databaseServices.verifyOrderSell(attempt);
        }
        return false;
    }

    public boolean create() {
        return false;
    }

    public boolean read() {
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

}
