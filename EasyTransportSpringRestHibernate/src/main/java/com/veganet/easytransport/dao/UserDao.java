/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.User;
import java.util.List;

/**
 *
 * @author asus
 */
public interface UserDao {
    public List<User> getAllUsersByAccessLevel(String accessLevel, short isdeleted);
    public User add(User user);
    public List<User> getUsers(short isdeleted);
    public void delete2(int id);
    public void update2(User object);
    public User findByUserName(String userName);
    public User passwordForgotten(String userName);
    
    
    
    
            
            
            }


