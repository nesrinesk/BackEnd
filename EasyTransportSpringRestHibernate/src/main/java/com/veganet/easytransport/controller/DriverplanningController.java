/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.service.DriverplanningService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import static java.util.Date.from;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
@RequestMapping(value = "/driverplannings")
//@CrossOrigin(origins = "http://127.0.0.1:3000")
public class DriverplanningController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(DriverplanningController.class);
    @Autowired
    DriverplanningService driverplanningService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllDriverplannings", method = RequestMethod.GET)
    public List<Driverplanning> findAll() {
        logger.info("getting all Driverplannings");

        List<Driverplanning> list = driverplanningService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no transports found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getDriverplanning/{id}", method = RequestMethod.GET)
    public Driverplanning findOne(@PathVariable int id) {
        logger.info("getting Driverplanning with id :" + id);

        return driverplanningService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addDriverplanning", method = RequestMethod.POST)
    public void create(@RequestBody Driverplanning driverplanning) {
        logger.info("creating new driverplanning: {}");
        //for (Date d = driverplanning.getFrom(); !d.compareTo(driverplanning.getTo()); d = d.plusDays(1)) {
        //    driverplanningService.create (driverplanning);
        //driverplanning.setDate(d);
        // }
        driverplanningService.add(driverplanning);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateDriverplanning/{id}", method = RequestMethod.POST)
    public void update(@PathVariable int id, @RequestBody Driverplanning driverplanning) {

        Driverplanning currentO = driverplanningService.findOne(id);
        logger.info("updating driverplanning with id :" + id);
        if (currentO == null) {
            logger.info("driverplanning with id {} not found" + id);
        }
        driverplanningService.update(driverplanning);
        logger.info("updated transport with id :" + id);
    }

    @RequestMapping(value = "/deleteDriverplanning/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id) {
        driverplanningService.deleteById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByUserBus/{id}", method = RequestMethod.GET)
    public List<Driverplanning> getAllByUserBus(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Driverplanning> list = driverplanningService.getAllByUser(id, (short) 1);

        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByUserTrain/{id}", method = RequestMethod.GET)
    public List<Driverplanning> getAllByUserTrain(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Driverplanning> list = driverplanningService.getAllByUser(id, (short) 0);

        return list;
    }

    //    public List<Driverplanning> getAllByDistinctUser(int id) {

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByDistinctUserBus", method = RequestMethod.GET)
    public List<Driverplanning> getAllByDistinctUser() {
        //logger.info("getting transport with id :" + id);

        List<Driverplanning> list = driverplanningService.getAllByDistinctUser((short) 1);
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByDistinctUserTrain", method = RequestMethod.GET)
    public List<Driverplanning> getAllByDistinctUserTrain() {
        //logger.info("getting transport with id :" + id);

        List<Driverplanning> list = driverplanningService.getAllByDistinctUser((short) 0);
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getOneByUser/{id}", method = RequestMethod.GET)
    public List<Driverplanning> getOneByUser(@PathVariable int id) {
        logger.info("getting transport with id :" + id);

        List<Driverplanning> list1 = driverplanningService.getOneByUser(id);

        return list1;
    }

    //get programmed journeys from today
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByDateBus", method = RequestMethod.GET)
    public List<Driverplanning> getAllByDateBus() {
        logger.info("getAllByDateBus");

        List<Driverplanning> list1 = driverplanningService.getAllByDate((short) 1);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByDateTrain", method = RequestMethod.GET)
    public List<Driverplanning> getAllByDateTrain() {
        logger.info("getAllByDateTrain");

        List<Driverplanning> list1 = driverplanningService.getAllByDate((short) 0);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/search/{stationStart}/{stationEnd}/{date}/{hour}", method = RequestMethod.GET)
    public List<Driverplanning> search(@PathVariable String stationStart, @PathVariable String stationEnd,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date, @PathVariable @DateTimeFormat(pattern = "HH:mm:ss") Date hour) {
        logger.info("search");

        List<Driverplanning> list1 = driverplanningService.search(stationStart, stationEnd, date, hour);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/searchByTrain/{transportName}/{date}", method = RequestMethod.GET)
    public List<Driverplanning> searchByTrain(@PathVariable String transportName, @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        logger.info("searchByTrain");

        List<Driverplanning> list1 = driverplanningService.searchByTrain(transportName, date);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/searchStations/{stationStart}/{stationEnd}", method = RequestMethod.GET)
    public List<Station> searchStations(@PathVariable String stationStart, @PathVariable String stationEnd) {
        List<Station> list1 = driverplanningService.searchStations(stationStart, stationEnd);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/searchByStationName/{stationName}", method = RequestMethod.GET)
    public List<Driverplanning> searchByStationName(@PathVariable String stationName) {
        List<Driverplanning> list1 = driverplanningService.searchByStationName(stationName);

        return list1;
    }

}
