/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import static javafx.scene.input.KeyCode.K;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 * @param <T>
 */
//@SuppressWarnings("unchecked")
//@Repository
public class AbstractHibernateDao< T extends Serializable> {

    private Class<T> clazz;
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public final void setClazz(Class< T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public List<T> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<T> list = session.createQuery("from " + clazz.getName()).list();
        return list;
    }

    public T findOne(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        T object = (T) session.get(clazz, id);
        return object;
    }

    public T create(T object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(object);
        return object;
    }

    public void update(T object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
    }

    public void deleteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        T p = (T) session.load(clazz, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }
    
    
}
