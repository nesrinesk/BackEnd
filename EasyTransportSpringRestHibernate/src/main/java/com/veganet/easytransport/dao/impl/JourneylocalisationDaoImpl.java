/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.JourneylocalisationDao;
import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import com.veganet.easytransport.entities.Journey;
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
public class JourneylocalisationDaoImpl extends AbstractHibernateDao<Journeylocalisation> implements JourneylocalisationDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public JourneylocalisationDaoImpl() {
        setClazz(Journeylocalisation.class);
    }

    @Override
    public List<Journeylocalisation> getAllByType(short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journeylocalisation> list = session.createQuery("SELECT l FROM Journeylocalisation l WHERE l.type = :type")
                .setParameter("type", type).list();
        return list;
    }
     // by  lineId
    @Override
    public List<Journeylocalisation> getAllByLine(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Journey journeyId = (Journey) session.get(Journey.class, id);
        List<Journeylocalisation> list = session.createQuery("SELECT r FROM Journeylocalisation r WHERE r.journeyId = :journeyId")
                .setParameter("journeyId", journeyId).list();
        return list;
    }
}
