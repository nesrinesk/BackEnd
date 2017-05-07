/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Relatedto;
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
public class RelatedtoDao extends AbstractHibernateDao<Relatedto> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public RelatedtoDao() {
        setClazz(Relatedto.class);
    }

    // by  type
    public List<Relatedto> getAllByType(short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Relatedto> list = session.createQuery("SELECT r FROM Relatedto r WHERE r.type = :type")
                .setParameter("type", type).list();
        return list;
    }

    // by  lineId
    public List<Relatedto> getAllByLine(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Line lineId = (Line) session.get(Line.class, id);
        List<Relatedto> list = session.createQuery("SELECT r FROM Relatedto r WHERE r.lineId = :lineId")
                .setParameter("lineId", lineId).list();
        return list;
    }
    /*
     Session session = this.sessionFactory.getCurrentSession();
     T object = (T) session.get(clazz, id);
     return object;
     */

    public Relatedto getlaststation(short tag, int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Line lineId = (Line) session.get(Line.class, id);
        Relatedto object = (Relatedto) session.createQuery("SELECT r FROM Relatedto r WHERE r.tag = :tag and r.lineId = :lineId ")
                .setParameter("tag", tag).setParameter("lineId", lineId).uniqueResult();
        return object;
    }
}
