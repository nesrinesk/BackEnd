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
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
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
            ob = object;
            Date lDate = (Date) date;
            ob.setDate(lDate);
            System.out.println(" getdate ..." + ob.getDate());
            String ds = formatter.format(lDate);
            System.out.println(" Date is ..." + ds);
            create(ob);
            if (i % 20 == 0) { //20, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }

    }
    public List<Driverplanning> getAllByUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> list = session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId")
                .setParameter("userId", userId).list();
        return list;
    }
}
