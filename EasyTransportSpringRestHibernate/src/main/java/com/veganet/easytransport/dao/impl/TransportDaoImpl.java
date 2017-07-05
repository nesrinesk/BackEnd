/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.TransportDao;
import com.veganet.easytransport.entities.Transport;
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
public class TransportDaoImpl extends AbstractHibernateDao<Transport> implements TransportDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public TransportDaoImpl() {
        setClazz(Transport.class);
    }

    //add+ set isdeleted =0
    @Override
    public Transport add(Transport t) {
        Session session = this.sessionFactory.getCurrentSession();
        t.setIsdeleted((short) 0);
        session.persist(t);

        return t;
    }

    //users not deleted (having isDeleted=0)
    @Override
    public List<Transport> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Transport> list = session.createQuery("SELECT t FROM Transport t WHERE t.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }

    // set isdeleted=1
    @Override
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport t = (Transport) session.get(Transport.class, id);
        t.setIsdeleted((short) 1);

    }

    //update 
    @Override
    public void update2(Transport object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }

    //get all by type
    //SELECT t FROM Transport t WHERE t.type = :type
    // Transports not deleted (type=0) by  type
//    @Override
    public List<Transport> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Transport> list = session.createQuery("SELECT t FROM Transport t WHERE t.type = :type and t.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
    
       public List<Transport> getAllByAdmin(short type, int adminId) {
        Session session = this.sessionFactory.getCurrentSession();
        User addedBy = (User) session.get(User.class, adminId);
        List<Transport> list = session.createQuery("SELECT t FROM Transport t WHERE t.isdeleted = 0 and t.type = :type and t.addedBy = :addedBy")
                .setParameter("type", type)
                .setParameter("addedBy", addedBy).list();
        return list;
    }
}
