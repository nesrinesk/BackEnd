/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.AlertDao;
import com.veganet.easytransport.entities.Alert;
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

public class AlertDaoImpl extends AbstractHibernateDao<Alert> implements AlertDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public AlertDaoImpl() {
        setClazz(Alert.class);
    }

    public Alert add(Alert t) {
        Session session = this.sessionFactory.getCurrentSession();
        t.setVisibility((short) 0);
        session.persist(t);

        return t;
    }

    public Alert changeVisibility(Alert t) {
        Session session = this.sessionFactory.getCurrentSession();
        if(t.getVisibility() == (short) 1){
             t.setVisibility((short) 0);
        }
        else {
             t.setVisibility((short) 1);
        }
       // t.setVisibility((short) 1);
        session.update(t);

        return t;
    }
    
    
    public List<Alert> getAllVisible(Short visibility,Short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                + "WHERE t.visibility = :visibility and t.type = :type")
                .setParameter("visibility", visibility)
                .setParameter("type", type).list();
        return list;
    } 
     public List<Alert> getAllBytype(Short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                + "WHERE t.type = :type")
               
                .setParameter("type", type).list();
        return list;
    } 
}
