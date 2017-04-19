/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Position;
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
public class PositionDao  extends AbstractHibernateDao<Position>{
  @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    public PositionDao() {
        setClazz(Position.class);
    }
    
      //add+ set isdeleted =0
    public Position add(Position position) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(position);
        position.setIsdeleted((short) 0);
        return position;
    }

    //users not deleted (having isDeleted=0)
    public List<Position> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Position> list = session.createQuery("SELECT p FROM Position p WHERE p.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }
    // set isdeleted=1
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Position line = (Position) session.get(Position.class, id);
        line.setIsdeleted((short) 1);

    }
}

