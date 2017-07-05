/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.DriverplanningDaoImpl;
import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("driverplanningService")
public class DriverplanningService {

    @Autowired
    DriverplanningDaoImpl driverplanningDao;

    @Transactional
    public List<Driverplanning> findAll() {
        return driverplanningDao.findAll();
    }

    @Transactional
    public Driverplanning findOne(int id) {
        return driverplanningDao.findOne(id);
    }

    @Transactional
    public void add(Driverplanning driverplanning) {
        driverplanningDao.add(driverplanning);
    }

    @Transactional
    public void update(Driverplanning driverplanning) {
        driverplanningDao.update(driverplanning);

    }

    @Transactional
    public void deleteById(int id) {
        driverplanningDao.deleteById(id);
    }

    @Transactional
    public List<Driverplanning> getAllByUser(int id,Short type) {
        return driverplanningDao.getAllByUser(id,type);
    }

    @Transactional
    public List<User> getAllByDistinctUser(Short type, int id) {
        return driverplanningDao.getAllByDistinctUser(type,id);
    }

    @Transactional
    public List<Driverplanning> getOneByUser(int id) {
        return driverplanningDao.getOneByUser(id);
    }

    @Transactional
    public List<Driverplanning> getAllByDate(Short type) {
        return driverplanningDao.getAllByDate( type);
    }

    @Transactional
    public List<Driverplanning> search(String stationStart, String stationEnd, Date date, Date hour) {
        return driverplanningDao.search(stationStart, stationEnd, date, hour);
    }

    @Transactional
    public Station findByName(String stationName) {
        return driverplanningDao.findStationByName(stationName);
    }

    @Transactional
    public List<Driverplanning> searchByTrain(String transportName, Date date) {
        return driverplanningDao.searchByTrain(transportName, date);
    }
    
    @Transactional
     public List<Station> searchStations(String stationStart, String stationEnd) {
         return driverplanningDao.searchStations(stationStart, stationEnd);
     }
     
     @Transactional
     public List<Driverplanning> searchByStationName(String stationName) {
         return driverplanningDao.searchByStationName(stationName);
     }
}
