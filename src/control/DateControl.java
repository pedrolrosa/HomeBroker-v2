/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.entities.Dividend;

/**
 *
 * @author pedro
 */
public class DateControl {

    private static LocalDateTime date = LocalDateTime.now();
    
    private static final List<Dividend> dividends = new ArrayList<>();

    public static boolean addDividend(Dividend attempt){
        return dividends.add(attempt);
    }

    public static LocalDateTime now() {
        return date;
    }

    public static void plusDays(Integer days) {

        int past = 0;

        while (past < days) {
            
            for(Dividend dividend : dividends){
                if(dividend.getPayment().toLocalDate().isEqual(date.toLocalDate())){
                    AccountController.dividend(dividend.getAsset(), dividend.getBase(), dividend.getValue());
                }
            }
            
            if (date.getDayOfMonth() == 15) {
                AccountController.feeMonth();
            }
            date = date.plusDays(1);
            past++;
        }

    }

}
