/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service
public abstract class AbstractService<T extends Serializable> {

    private AbstractHibernateDao<T> abstractHibernateDao;

    public AbstractService(AbstractHibernateDao<T> abstractHibernateDao) {
        this.abstractHibernateDao = abstractHibernateDao;
    }

    public AbstractService() {
    }

   

    @Transactional
    public List<T> findAll() {
        return abstractHibernateDao.findAll();
    }

    @Transactional
    public T findOne(Integer id) {
        return abstractHibernateDao.findOne(id);
    }

    @Transactional
    public T create(T object) {
        return abstractHibernateDao.create(object);
    }

    @Transactional
    public void update(T object) {
        abstractHibernateDao.update(object);
    }

    @Transactional
    public void deleteById(int id) {
        abstractHibernateDao.deleteById(id);
    }
}
