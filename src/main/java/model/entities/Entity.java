/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author pedro
 */
public abstract class Entity {
    private Long id;
    
    private LocalDate start;
    private LocalDate modify;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getModify() {
        return modify;
    }

    public void setModify(LocalDate modify) {
        this.modify = modify;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.start);
        hash = 67 * hash + Objects.hashCode(this.modify);
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
        final Entity other = (Entity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        return Objects.equals(this.modify, other.modify);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entity{");
        sb.append("id=").append(id);
        sb.append(", origem=").append(start);
        sb.append(", destino=").append(modify);
        sb.append('}');
        return sb.toString();
    }

   
}
