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
     * @return
     */
    Optional<CLASS> create(CLASS obj);

    /**
     * Returns all the entities
     *
     * @return
     */
    List<CLASS> read();

    /**
     * Update object by ID
     *
     * @param obj
     * @return
     */
    Optional<CLASS> update(CLASS obj);

    /**
     * Delete object by ID
     *
     * @param obj
     * @return
     */
    Optional<CLASS> delete(CLASS obj);

    public interface Target<CLASS, ID> {

        /**
         * Delete object by ID
         *
         * @param id
         * @return
         */
        CLASS target(ID id);
    }
}
