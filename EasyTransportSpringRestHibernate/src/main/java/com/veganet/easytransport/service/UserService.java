/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.UserDao;
import com.veganet.easytransport.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;

    

    @Transactional
    public List<User> getUsers(short isdeleted) {
        return userDao.getUsers(isdeleted);
    }

    @Transactional
    public List<User> getAllUsersByAccessLevel(short accessLevel,short isdeleted) {
        return userDao.getAllUsersByAccessLevel(accessLevel,isdeleted);
    }
////////////
    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Transactional
    public User findOne(int id) {
        return userDao.findOne(id);
    }

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void update2(User user) {
        userDao.update(user);

    }

    @Transactional
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Transactional
    public void delete2(int id) {
        userDao.delete2(id);
    }

}
