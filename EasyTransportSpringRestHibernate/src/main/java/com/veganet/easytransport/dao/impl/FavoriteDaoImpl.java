/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.FavoriteDao;
import com.veganet.easytransport.entities.Favorite;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public class FavoriteDaoImpl extends AbstractHibernateDao<Favorite> implements FavoriteDao {

    public FavoriteDaoImpl() {
        setClazz(Favorite.class);
    }
}
