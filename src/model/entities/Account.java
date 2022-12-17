/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author pedro
 */
public class Account extends Entity{
    
    private Long owner;
    private BigDecimal amount;
    private BigDecimal max;

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }
    
    public BigDecimal addAmount(BigDecimal value){
        return this.amount.add(value);
    }
    
    public BigDecimal subAmount(BigDecimal value){
        if(this.getAmount().compareTo(value) >= 0){
            return this.amount.subtract(value);
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.owner);
        hash = 83 * hash + Objects.hashCode(this.amount);
        hash = 83 * hash + Objects.hashCode(this.max);
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
        final Account other = (Account) obj;
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.max, other.max);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account{");
        sb.append("owner=").append(owner);
        sb.append(", amount=").append(amount);
        sb.append(", max=").append(max);
        sb.append('}');
        return sb.toString();
    }

    
}
