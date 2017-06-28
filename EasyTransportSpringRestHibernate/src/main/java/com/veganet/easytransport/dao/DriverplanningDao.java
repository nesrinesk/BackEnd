/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.Transport;
import java.util.Date;
import java.util.List;

/**
 *
 * @author asus
 */
public interface DriverplanningDao {

    public List<Driverplanning> getAllByDate();

    public void add(Driverplanning object);

    public List<Driverplanning> getAllByUser(int id);

    public List<Driverplanning> getAllByDistinctUser();

    public List<Driverplanning> getOneByUser(int id);

    public List<Driverplanning> search(String stationStart, String stationEnd, Date date, Date hour);

    public Station findStationByName(String stationName);

    public Transport findTransportByName(String transportName);

    public List<Station> searchStations(String stationStart, String stationEnd);

    public List<Driverplanning> searchByTrain(String transportName, Date date);

    public List<Driverplanning> searchByStationName(String stationName);
}
