/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.entities.Company;
import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Journey;
import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.Transport;
import com.veganet.easytransport.entities.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
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

    public List<Driverplanning> getAllByDate(Short type, int id) {
        List<Driverplanning> finalList = new ArrayList<Driverplanning>();
        Session session = this.sessionFactory.getCurrentSession();
        Company companyId = (Company) session.get(Company.class, id);

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //String dateNow = dateFormat.format(date);
        List<Driverplanning> list = session.createQuery("SELECT u FROM Driverplanning u WHERE u.type = :type "
                + "and u.date >= :date order by u.date asc")
                .setParameter("date", date).setParameter("type", type)
                .list();
        for (Driverplanning u : list) {
            if (u.getUserId().getCompanyId().equals(companyId)) {
                finalList.add(u);
            }
        }
        return finalList;
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

    public List<Driverplanning> getAllByUser(int id, Short type) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        Date date = new Date();
        List<Driverplanning> list = session.createQuery("SELECT r FROM Driverplanning r WHERE r.userId = :userId "
                + "and r.type = :type and r.date >= :date order by r.date asc")
                .setParameter("date", date)
                .setParameter("userId", userId).setParameter("type", type).list();
        return list;
    }

    public List<User> getAllByDistinctUser(Short type, int id) {
        List<User> finalList = new ArrayList<User>();

        Session session = this.sessionFactory.getCurrentSession();
        List<User> list = session.createQuery("SELECT DISTINCT userId FROM Driverplanning r WHERE r.type = :type")
                .setParameter("type", type).list();

        Company companyId = (Company) session.get(Company.class, id);
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.isdeleted = 0 and u.accessLevel = 'ROLE_DRIVER' and u.companyId = :companyId")
                .setParameter("companyId", companyId).list();

        for (User u : userList) {
            if (u.getCompanyId().equals(companyId)) {
                finalList.add(u);
            }
        }
        return finalList;
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
        stationEndOb = findStationByName(stationEnd);
        System.out.println("station end" + stationEndOb.getStationName());
        stationStartOb = findStationByName(stationStart);

        Session session = this.sessionFactory.getCurrentSession();

        System.out.println("stationStartOb" + stationStartOb.getStationName());
        //   Company companyId = (Company) session.get(Company.class, id);

        List<Driverplanning> listF = new ArrayList<Driverplanning>();
        // List<Driverplanning> listFinal = new ArrayList<Driverplanning>();

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
        /*    for (Driverplanning s : listF) {
         if (s.getUserId().getCompanyId().equals(companyId)) {
         listFinal.add(s);
         }
         }
         */
        return listF;
    }

    public Station findStationByName(String stationName) {
        Session session = this.sessionFactory.getCurrentSession();

         List<Station> list =   session.createQuery("SELECT s FROM Station s WHERE s.stationName = :stationName")
                .setParameter("stationName", stationName).list();
        Station rs =list.get(0);
        System.out.println("rs.getStationName()" + rs.getStationName());
        return rs;
    }

    public Transport findTransportByName(String transportName) {
        Session session = this.sessionFactory.getCurrentSession();
       List<Transport> list =  session.createQuery("SELECT s FROM Transport s WHERE s.name = :transportName")
                .setParameter("transportName", transportName).list();
Transport rs = list.get(0);
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
    public Driverplanning searchByTrain(String transportName) {

        List<Driverplanning> listF = new ArrayList<Driverplanning>();
        Transport transportId;
            Short isdeleted = 0;
            transportId = findTransportByName(transportName);

            Session session = this.sessionFactory.getCurrentSession();
            Session session1 = this.sessionFactory.getCurrentSession();
            Journey journey;
        try {
            //current time
            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");

            Date now = new Date();
            Date date = new Date();
            String strTime = sdfTime.format(now);

            System.out.println("Time: " + strTime);

            Date date1;

            date1 = new SimpleDateFormat("HH:mm:ss").parse(strTime);
            System.out.println(strTime + "\t" + date1);

            

            List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.transportId = :transportId"
                    + " and TIME(j.dateStart) >= :date1 and j.isdeleted = :isdeleted order by j.dateStart DESC")
                    .setParameter("isdeleted", isdeleted)
                    .setParameter("date1", date1)
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

            return listF.get(0);
        } catch (ParseException ex) {
            Logger.getLogger(DriverplanningDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listF.get(0);
    }

    /*
     Recherche des horaires d'une station
     */
    public List<Driverplanning> searchByStationName(String stationName) {
        Station stationId;
        stationId = findStationByName(stationName);
//current time
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String strTime = sdfTime.format(now);
        
        List<Driverplanning> listF = new ArrayList<Driverplanning>();

        System.out.println("Time: " + strTime);

        Date date1;
        try {
            date1 = new SimpleDateFormat("HH:mm:ss").parse(strTime);
            System.out.println(strTime + "\t" + date1);

//        
            Short isdeleted = 0;
            System.out.println("station de départ" + stationId.getStationName());
            Date date = new Date();
            Session session = this.sessionFactory.getCurrentSession();
            // Journey journey;

            List<Journey> listJ = session.createQuery("SELECT j FROM Journey j WHERE j.stationStartId = :stationId"
                    + " and TIME(j.dateStart) >= :date1 and j.isdeleted = :isdeleted order by j.dateStart DESC")
                    .setParameter("isdeleted", isdeleted)
                    .setParameter("date1", date1)
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
        } catch (ParseException ex) {
            Logger.getLogger(DriverplanningDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listF;
    }

    public ArrayList<String> latlong(int id) {
        //ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
        ArrayList<String> singleList = new ArrayList<String>();
        System.out.println("        ArrayList<String> singleList = new ArrayList<String>();\n");
        Session session = this.sessionFactory.getCurrentSession();
        Driverplanning d = (Driverplanning) session.get(Driverplanning.class, id);
        System.out.println("d" + d.getPlanningId());
        singleList.add(String.valueOf(d.getJourneyId().getStationStartId().getLatitude()));
        singleList.add(String.valueOf(d.getJourneyId().getStationStartId().getLongitude()));
        singleList.add(String.valueOf(d.getJourneyId().getStationEndId().getLatitude()));
        singleList.add(String.valueOf(d.getJourneyId().getStationEndId().getLongitude()));

        return singleList;
    }

}
