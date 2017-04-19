/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.FavoriteDao;
import com.veganet.easytransport.entities.Favorite;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("favoriteService")

public class FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    @Transactional
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }

    @Transactional
    public Favorite findOne(int id) {
        return favoriteDao.findOne(id);
    }

    @Transactional
    public void create(Favorite favorite) {
        favoriteDao.create(favorite);
    }

    @Transactional
    public void update(Favorite favorite) {
        favoriteDao.update(favorite);

    }

    @Transactional
    public void deleteById(int id) {
        favoriteDao.deleteById(id);
    }

}
