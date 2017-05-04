/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Line;
import java.math.BigInteger;
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
public class LineDao extends AbstractHibernateDao<Line>{
     @Autowired
    private SessionFactory sessionFactory;

     @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    public LineDao() {
        setClazz(Line.class);
    }
    
     //add+ set isdeleted =0
    public Line add(Line line) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(line);
        line.setIsdeleted((short) 0);
        return line;
    }

    //users not deleted (having isDeleted=0)
    public List<Line> getAll(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Line> list = session.createQuery("SELECT l FROM Line l WHERE l.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return list;
    }
    // set isdeleted=1
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Line line = (Line) session.get(Line.class, id);
        line.setIsdeleted((short) 1);

    }
    //update 
    public void update2(Line object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
        object.setIsdeleted((short) 0);
    }
    
    // Lines not deleted (type=0) by  type
    public List<Line> getAllByType(short type, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Line> list = session.createQuery("SELECT l FROM Line l WHERE l.type = :type and l.isdeleted = :isdeleted")
                .setParameter("type", type)
                .setParameter("isdeleted", isdeleted).list();
        return list;
    }
    
    public BigInteger getLastInsertedId(){
                Session session = this.sessionFactory.getCurrentSession();

        BigInteger lastId= (BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
        return lastId;
    }
}
