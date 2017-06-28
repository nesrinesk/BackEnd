/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.JourneyDao;
import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import com.veganet.easytransport.entities.Journey;
import com.veganet.easytransport.entities.Journeylocalisation;
import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Relatedto;
import java.util.ArrayList;
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
public class JourneyDaoImpl extends AbstractHibernateDao<Journey> implements JourneyDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public JourneyDaoImpl() {
        setClazz(Journey.class);
    }

    //add+ set isdeleted =0
    @Override
    public Journey add(Journey journey) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(journey);
        session.flush();
        journey.setIsdeleted((short) 0);
        return journey;
    }

    //users not deleted (having isDeleted=0)
    @Override
    public List<Journey> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journey> list = session.createQuery("SELECT j FROM Journey j WHERE j.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1
    @Override
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Journey journey = (Journey) session.get(Journey.class, id);
        journey.setIsdeleted((short) 1);

    }
    //update 
    @Override
    public void update2(Journey object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }
    
    // Stations not deleted (type=0) by  type
    @Override
    public List<Journey> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Journey> list = session.createQuery("SELECT j FROM Journey j WHERE j.type = :type and j.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
    
    @Override
    public String linesOfJourney(int journeyId) {
        Journey journey = findOne(journeyId);
        Short isdeleted = 0;
        

        Session session = this.sessionFactory.getCurrentSession();
        Session session1 = this.sessionFactory.getCurrentSession();
        
        String listF = "";
        
        List<Line> listJourLoc = session.createQuery("SELECT r.lineId FROM Journeylocalisation r WHERE r.journeyId = :journey")
                .setParameter("journey", journey).list();

        for (Line lineId : listJourLoc) {
            listF= listF+"\n"+lineId.getName()+":";
             List<Relatedto> list = session.createQuery("SELECT r FROM Relatedto r WHERE r.lineId = :lineId")
                .setParameter("lineId", lineId).list();
            for (Relatedto r : list) {
                listF=listF+"\n"+r.getStationId().getStationName();
            }
        }

        return listF;
    }

}
