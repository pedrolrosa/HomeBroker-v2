/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import java.util.List;
import java.util.Optional;
import model.entities.User;

/**
 *
 * @author pedro
 */
public interface UserRepository extends BaseRepository<User, Long>{
    
    List<User> search(String term);
    
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    Optional<User> findByUsername(String username);
}
