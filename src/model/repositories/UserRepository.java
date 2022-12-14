/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.repositories;

import model.entities.User;

/**
 *
 * @author pedro
 */
public interface UserRepository{
    
    boolean coupling(Long account, Long id);
    
    User authenticated(String login, String password);
    
    boolean searchName(String name);
}
