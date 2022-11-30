/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <CLASS> Param for class entities
 * @param <ID> Param for IDs
 * 
 * @author pedro
 */
public interface BaseRepository<CLASS, ID> {
    
    /**
     * Create new object being passed as a parameter 
     *
     * @param obj
     */
    void create(CLASS obj);
    
    /**
     * Returns all the entities
     *
     * @return
     */
    List<CLASS> read();
    
    /**
     * Returns entity for ID
     *
     * @param id
     * @return
     */
    Optional<CLASS> read(ID id);
    
    /**
     * Update object by ID
     *
     * @param id
     * @return 
     */
    Optional<CLASS> update(ID id);
    
    /**
     * Delete object by ID
     *
     * @param id
     * @return 
     */
    Optional<CLASS> deleteById(ID id);
}
