/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.time.LocalDateTime;

/**
 *
 * @author pedro
 */
public class DateControl {

    private static LocalDateTime date = LocalDateTime.now();

    public static LocalDateTime now() {
        return date;
    }

    public static void plusDays(Integer days) {

        int past = 0;

        while (past < days) {
            if (date.getDayOfMonth() == 15) {
                AccountController.feeMonth();
            }
            date = date.plusDays(1);
            past++;
        }

    }

}
