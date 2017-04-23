/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

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
public class TransportDao extends AbstractHibernateDao<Transport>{
 @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    public TransportDao() {
        setClazz(Transport.class);
    }
    //add+ set isdeleted =0
    public Transport add(Transport t) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(t);
        t.setIsdeleted((short) 0);
        return t;
    }

    //users not deleted (having isDeleted=0)
    public List<Transport> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Transport> list = session.createQuery("SELECT t FROM Transport t WHERE t.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }
    // set isdeleted=1
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport t = (Transport) session.get(Transport.class, id);
        t.setIsdeleted((short) 1);

    }
    
     //update 
    public void update2(Transport object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }
    
    //get all by type
    
    //SELECT t FROM Transport t WHERE t.type = :type
    
    // Transports not deleted (type=0) by  type
    public List<Transport> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Transport> list = session.createQuery("SELECT t FROM Transport t WHERE t.type = :type and t.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
}
