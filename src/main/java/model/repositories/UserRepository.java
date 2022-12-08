/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import java.util.Optional;
import model.entities.User;

/**
 *
 * @author pedro
 */
public interface UserRepository{
    
    User authenticate(String login, String password);
    
    Optional<User> searchName(String name);
}
