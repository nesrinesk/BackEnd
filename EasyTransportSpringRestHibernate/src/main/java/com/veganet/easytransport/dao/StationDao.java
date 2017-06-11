/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Station;
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
public class StationDao extends AbstractHibernateDao<Station> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public StationDao() {
        setClazz(Station.class);
    }

    //add+ set isdeleted =0
    public Station add(Station station) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(station);
        station.setIsdeleted((short) 0);
        return station;
    }

    //users not deleted (having isDeleted=0)
    public List<Station> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Station> list = session.createQuery("SELECT s FROM Station s WHERE s.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1

    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Station station = (Station) session.get(Station.class, id);
        station.setIsdeleted((short) 1);

    }
     //update 
    public void update2(Station object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }
    // Stations not deleted (type=0) by  type
    public List<Station> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Station> list = session.createQuery("SELECT s FROM Station s WHERE s.type = :type and s.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
    
}
