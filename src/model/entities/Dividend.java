/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author pedro
 */
public class Dividend {
    
    private Long asset;
    
    private LocalDateTime base;
    private LocalDateTime payment;
    
    private BigDecimal value;

    public Long getAsset() {
        return asset;
    }

    public void setAsset(Long asset) {
        this.asset = asset;
    }

    public LocalDateTime getBase() {
        return base;
    }

    public void setBase(LocalDateTime base) {
        this.base = base;
    }

    public LocalDateTime getPayment() {
        return payment;
    }

    public void setPayment(LocalDateTime end) {
        this.payment = end;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.asset);
        hash = 89 * hash + Objects.hashCode(this.base);
        hash = 89 * hash + Objects.hashCode(this.payment);
        hash = 89 * hash + Objects.hashCode(this.value);
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
        final Dividend other = (Dividend) obj;
        if (!Objects.equals(this.asset, other.asset)) {
            return false;
        }
        if (!Objects.equals(this.base, other.base)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dividend{");
        sb.append("asset=").append(asset);
        sb.append(", base=").append(base);
        sb.append(", end=").append(payment);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    
    
}
