/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Journey;
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
public class JourneyDao extends AbstractHibernateDao<Journey> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public JourneyDao() {
        setClazz(Journey.class);
    }

    //add+ set isdeleted =0
    public Journey add(Journey journey) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(journey);
        journey.setIsdeleted((short) 0);
        return journey;
    }

    //users not deleted (having isDeleted=0)
    public List<Journey> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journey> list = session.createQuery("SELECT j FROM Journey j WHERE j.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Journey journey = (Journey) session.get(Journey.class, id);
        journey.setIsdeleted((short) 1);

    }
    //update 
    public void update2(Journey object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }
    
    // Stations not deleted (type=0) by  type
    public List<Journey> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journey> list = session.createQuery("SELECT j FROM Journey j WHERE j.type = :type and j.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
}
