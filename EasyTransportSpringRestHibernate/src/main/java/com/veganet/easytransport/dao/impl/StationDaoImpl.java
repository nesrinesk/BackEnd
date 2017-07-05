/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.StationDao;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.User;
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
public class StationDaoImpl extends AbstractHibernateDao<Station> implements StationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public StationDaoImpl() {
        setClazz(Station.class);
    }

    //add+ set isdeleted =0
    @Override
    public Station add(Station station) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(station);
        station.setIsdeleted((short) 0);
        return station;
    }

    //users not deleted (having isDeleted=0)
    @Override
    public List<Station> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Station> list = session.createQuery("SELECT s FROM Station s WHERE s.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1
    @Override
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Station station = (Station) session.get(Station.class, id);
        station.setIsdeleted((short) 1);

    }

    //update 
    @Override
    public void update2(Station object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }

    // Stations not deleted (type=0) by  type
//    @Override
    public List<Station> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Station> list = session.createQuery("SELECT s FROM Station s WHERE s.type = :type and s.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }

    public List<Station> getAllByAdmin(short type, int adminId) {
        Session session = this.sessionFactory.getCurrentSession();
        User addedBy = (User) session.get(User.class, adminId);
        List<Station> list = session.createQuery("SELECT t FROM Station t WHERE t.isdeleted = 0 and t.type = :type and t.addedBy = :addedBy")
                .setParameter("type", type)
                .setParameter("addedBy", addedBy).list();
        return list;
    }
}
