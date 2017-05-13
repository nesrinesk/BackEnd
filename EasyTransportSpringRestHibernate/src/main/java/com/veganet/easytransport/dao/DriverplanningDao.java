/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository("driverplanningDao")

public class DriverplanningDao extends AbstractHibernateDao<Driverplanning> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public DriverplanningDao() {
        setClazz(Driverplanning.class);
    }

    public void add(Driverplanning object) {
        DateFormat formatter;

        Session session = this.sessionFactory.getCurrentSession();

        List<Date> dates = new ArrayList<Date>();
        Date startDate = object.getFrom();
        Date endDate = object.getTo();
        long interval = 24 * 1000 * 60 * 60*7; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        int i = 0;
        for (Date date : dates) {
            i++;
            Driverplanning ob = new Driverplanning();
            ob.setJourneyId(object.getJourneyId());
            ob.setUserId(object.getUserId());
            ob.setDay(object.getDay());
            Date lDate = (Date) date;
            ob.setDate(lDate);
            System.out.println(" getdate ..." + ob.getDate());
            String ds = formatter.format(lDate);
            System.out.println(" Date is ..." + ds);
            ob.setPlanningId(null);
            create(ob);

        }

    }

    public List<Driverplanning> getAllByUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> list = session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId")
                .setParameter("userId", userId).list();
        return list;
    }
    
     public List<Driverplanning> getAllByDistinctUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Driverplanning> list = session.createQuery("SELECT DISTINCT userId FROM Driverplanning r")
                .list();
        return list;
    }
     
     public List<Driverplanning> getOneByUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> list =  session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId")
                .setParameter("userId", userId).list();
        return list;
    }
     /*
      public Relatedto getlaststation(short tag, int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Line lineId = (Line) session.get(Line.class, id);
        Relatedto object = (Relatedto) session.createQuery("SELECT r FROM Relatedto r WHERE r.tag = :tag and r.lineId = :lineId ")
                .setParameter("tag", tag).setParameter("lineId", lineId).uniqueResult();
        return object;
    }
     */
}
