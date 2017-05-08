/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Journeylocalisation;
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
public class JourneylocalisationDao extends AbstractHibernateDao<Journeylocalisation> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public JourneylocalisationDao() {
        setClazz(Journeylocalisation.class);
    }

    public List<Journeylocalisation> getAllByType(short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journeylocalisation> list = session.createQuery("SELECT l FROM Journeylocalisation l WHERE l.type = :type")
                .setParameter("type", type).list();
        return list;
    }
}
