/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import com.veganet.easytransport.entities.Positions;
import com.veganet.easytransport.entities.Transport;
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
public class PositionDao extends AbstractHibernateDao<Positions> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public PositionDao() {
        setClazz(Positions.class);
    }

    //add+ set isdeleted =0
    public Positions add(Positions position) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(position);
        return position;
    }

    //users not deleted (having isDeleted=0)
    public List<Positions> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Positions> list = session.createQuery("SELECT p FROM Positions p WHERE p.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Positions line = (Positions) session.get(Positions.class, id);

    }

    public Transport findTransportByName(String transportName) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport rs = (Transport) session.createQuery("SELECT s FROM Transport s WHERE s.name = :transportName")
                .setParameter("transportName", transportName).uniqueResult();

        return rs;
    }

    public List<Positions> getAllByTransport(String transportName) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport deviceId = findTransportByName(transportName);

        List<Positions> list = session.createQuery("SELECT u FROM Positions u WHERE u.deviceId = :deviceId")
                .setParameter("deviceId", deviceId)
                .list();

        return list;
    }
    
    public Positions getLastPositionByTransport(String transportName) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport deviceId = findTransportByName(transportName);

        Positions p = (Positions) session.createQuery("SELECT u FROM Positions u WHERE u.deviceId = :deviceId ORDER BY u.id DESC")
                .setParameter("deviceId", deviceId)
                .setMaxResults(1).setFirstResult(0)
                .list().get(0);
       // int size= list.size();
        //System.out.println("size "+ size);
       // Positions p = (Positions) session.get(Positions.class, size);
        System.out.println("id pos "+p.getId());
        System.out.println("long "+p.getLongitude());
        System.out.println("lat "+p.getLatitude());
        return p;
    }
}
