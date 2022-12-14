/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.util.Objects;

/**
 *
 * @author pedro
 */
public class RelatesAccountAsset extends Entity{
    
    private Long account;
    private Long asset;
    private Integer quantity;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public Long getAsset() {
        return asset;
    }

    public void setAsset(Long asset) {
        this.asset = asset;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.account);
        hash = 89 * hash + Objects.hashCode(this.asset);
        hash = 89 * hash + Objects.hashCode(this.quantity);
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
        final RelatesAccountAsset other = (RelatesAccountAsset) obj;
        if (!Objects.equals(this.account, other.account)) {
            return false;
        }
        if (!Objects.equals(this.asset, other.asset)) {
            return false;
        }
        return Objects.equals(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RelatesAccountAsset{");
        sb.append("account=").append(account);
        sb.append(", asset=").append(asset);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
    
}
