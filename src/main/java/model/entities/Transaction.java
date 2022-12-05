/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.math.BigDecimal;
import java.util.Objects;
import model.enums.TypeTransaction;

/**
 *
 * @author pedro
 */
public class Transaction extends Entity{
    
    private Long owner;
    private Long destiny;
    
    private TypeTransaction type;
    private String description;
    
    private BigDecimal value;

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getDestiny() {
        return destiny;
    }

    public void setDestiny(Long destiny) {
        this.destiny = destiny;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.owner);
        hash = 11 * hash + Objects.hashCode(this.destiny);
        hash = 11 * hash + Objects.hashCode(this.type);
        hash = 11 * hash + Objects.hashCode(this.description);
        hash = 11 * hash + Objects.hashCode(this.value);
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
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        if (!Objects.equals(this.destiny, other.destiny)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction{");
        sb.append("owner=").append(owner);
        sb.append(", destiny=").append(destiny);
        sb.append(", type=").append(type);
        sb.append(", description=").append(description);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
    
}
