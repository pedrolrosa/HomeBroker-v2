/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author silva.junior
 */
public class Asset extends Entity{
    
    private String company; 
    private String ticker;
    private int amount;
    private BigDecimal initialPrice; 

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.company);
        hash = 41 * hash + Objects.hashCode(this.ticker);
        hash = 41 * hash + this.amount;
        hash = 41 * hash + Objects.hashCode(this.initialPrice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asset other = (Asset) obj;
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.ticker, other.ticker)) {
            return false;
        }
        return Objects.equals(this.initialPrice, other.initialPrice);
    }

    @Override
    public String toString() {
        return "Assets{" + "company=" + company + ", ticker=" + ticker + ", amount=" + amount + ", initialPrice=" + initialPrice + '}';
    }
    
    
    
}
