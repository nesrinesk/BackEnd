/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Journey;
import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.Transport;
import com.veganet.easytransport.entities.User;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository("driverplanningDao")

public class DriverplanningDaoImpl extends AbstractHibernateDao<Driverplanning> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public DriverplanningDaoImpl() {
        setClazz(Driverplanning.class);
    }

    public List<Driverplanning> getAllByDate() {
        Session session = this.sessionFactory.getCurrentSession();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //String dateNow = dateFormat.format(date);
        List<Driverplanning> list = session.createQuery("SELECT u FROM Driverplanning u WHERE u.date >= :date order by u.date asc")
                .setParameter("date", date)
                .list();
        return list;
    }

    public void add(Driverplanning object) {
        DateFormat formatter;

        Session session = this.sessionFactory.getCurrentSession();

        List<Date> dates = new ArrayList<Date>();
        Date startDate = object.getFrom();
        Date endDate = object.getTo();
        long interval = 24 * 1000 * 60 * 60 * 7; // 1 hour in millis
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
            ob.setType(object.getType());
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

    public List<Driverplanning> getAllByUser(int id,Short type) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> list = session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId and r.type = :type")
                .setParameter("userId", userId).setParameter("type", type).list();
        return list;
    }

   
    public List<Driverplanning> getAllByDistinctUser(Short type) {
         
        Session session = this.sessionFactory.getCurrentSession();
        List<Driverplanning> list = session.createQuery("SELECT DISTINCT userId FROM Driverplanning r WHERE r.type = :type")
                .setParameter("type", type).list();
        return list;
    }

    public List<Driverplanning> getOneByUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> list = session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId")
                .setParameter("userId", userId).list();
        return list;
    }

    public List<Driverplanning> search(String stationStart, String stationEnd, Date date, Date hour) {
        Station stationStartOb;
        Station stationEndOb;
        //Date hourD;
        stationStartOb = findStationByName(stationStart);
        stationEndOb = findStationByName(stationEnd);

        System.out.println("stationStartOb" + stationStartOb.getStationName());
        Session session = this.sessionFactory.getCurrentSession();
        Journey journey;
        List<Driverplanning> listF = new ArrayList<Driverplanning>();

        List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.stationStartId = :stationStartOb "
                + "and j.stationEndId = :stationEndOb and TIME(j.dateStart) >= :hour ")
                .setParameter("stationStartOb", stationStartOb)
                .setParameter("stationEndOb", stationEndOb)
                .setParameter("hour", hour)
                .list();
        //journey = listJ.get(0);
        for (Journey j : listJ) {
        List<Driverplanning> list = session.createQuery("SELECT j FROM Driverplanning j "
                + "WHERE j.journeyId = :j and j.date = :date")
                .setParameter("j", j)
                .setParameter("date", date)
                .list();
         listF.addAll(list);
        }

        return listF;
    }

    public Station findStationByName(String stationName) {
        Session session = this.sessionFactory.getCurrentSession();
        Station rs;
        List<Station> list = session.createQuery("SELECT s FROM Station s WHERE s.stationName = :stationName")
                .setParameter("stationName", stationName).list();
        rs = list.get(0);
        return rs;
    }

    public Transport findTransportByName(String transportName) {
        Session session = this.sessionFactory.getCurrentSession();
        Transport rs;
        List<Transport> list = session.createQuery("SELECT s FROM Transport s WHERE s.name = :transportName")
                .setParameter("transportName", transportName).list();
        rs = list.get(0);
        return rs;
    }


    /*
     recherche d'un trajet : 
     recherche des stations en indiquant le départ et l'arrivée
     */
    public List<Station> searchStations(String stationStart, String stationEnd) {
        Station stationStartOb;
        Station stationEndOb;

        Short isdeleted = 0;
        //Date hourD;
        stationStartOb = findStationByName(stationStart);
        stationEndOb = findStationByName(stationEnd);
//
        List<Station> stationsName = new ArrayList<Station>();
        //
        System.out.println("stationStartOb" + stationStartOb.getStationName());
        Session session = this.sessionFactory.getCurrentSession();
        Journey journey;
        List<Station> listStationF = null;

        List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.stationStartId = :stationStartOb "
                + "and j.stationEndId = :stationEndOb and j.isdeleted = :isdeleted")
                .setParameter("isdeleted", isdeleted)
                .setParameter("stationStartOb", stationStartOb)
                .setParameter("stationEndOb", stationEndOb)
                .list();
        journey = listJ.get(0);

        List<Line> listLine = session.createQuery("SELECT j.lineId FROM Journeylocalisation j WHERE j.journeyId = :journey")
                .setParameter("journey", journey)
                .list();
        for (Line l : listLine) {
            List<Station> listStation = session.createQuery("SELECT j.stationId FROM Relatedto j WHERE j.lineId = :l")
                    .setParameter("l", l)
                    .list();
            stationsName.addAll(listStation);

        }
        System.out.println("stations name" + stationsName);
        //System.out.println("final list"+ listStationF.get(0).getStationName());
        return stationsName;
    }

    /*
     recherche d'un bus : 
     recherche des stations en indiquant la date et le moyen de transport
     */
    public List<Driverplanning> searchByTrain(String transportName, Date date) {
        Transport transportId;
        Short isdeleted = 0;
        transportId = findTransportByName(transportName);

        Session session = this.sessionFactory.getCurrentSession();
        Session session1 = this.sessionFactory.getCurrentSession();
        Journey journey;
        List<Driverplanning> listF = new ArrayList<Driverplanning>();

        List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.transportId = :transportId and j.isdeleted = :isdeleted")
                .setParameter("isdeleted", isdeleted)
                .setParameter("transportId", transportId)
                .list();
        for (Journey j : listJ) {
            List<Driverplanning> list = session1.createQuery("SELECT j FROM Driverplanning j "
                    + "WHERE j.journeyId = :j and j.date = :date")
                    .setParameter("j", j)
                    .setParameter("date", date)
                    .list();
            listF.addAll(list);
        }

        return listF;
    }

    /*
     Recherche des horaires d'une station
     */
    public List<Driverplanning> searchByStationName(String stationName) {
        Station stationId;
        stationId = findStationByName(stationName);

        Short isdeleted = 0;
        System.out.println("station de départ" + stationId.getStationName());
        Date date = new Date();
        Session session = this.sessionFactory.getCurrentSession();
        // Journey journey;
        List<Driverplanning> listF = new ArrayList<Driverplanning>();

        List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.stationStartId = :stationId and j.isdeleted = :isdeleted")
                .setParameter("isdeleted", isdeleted)
                .setParameter("stationId", stationId)
                .list();
        System.out.println("size list journey" + listJ.size());
        for (Journey j : listJ) {
            List<Driverplanning> list = session.createQuery("SELECT j FROM Driverplanning j "
                    + "WHERE j.journeyId = :j and j.date = :date")
                    .setParameter("j", j)
                    .setParameter("date", date)
                    .list();
            System.out.println("size list driv pln" + list.size());

            listF.addAll(list);
        }

        return listF;
    }
}
