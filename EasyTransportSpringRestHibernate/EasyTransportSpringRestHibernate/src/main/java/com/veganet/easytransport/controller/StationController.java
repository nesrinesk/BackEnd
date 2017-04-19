/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.service.StationService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/stations")
public class StationController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(StationController.class);
    @Autowired
    StationService stationService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllStations", method = RequestMethod.GET)
    public List<Station> getStations() {
        logger.info("getting all stations");

        List<Station> listOfStations = stationService.findAll();
        if (listOfStations == null || listOfStations.isEmpty()) {
            logger.info("no stations found");
        }
        return listOfStations;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getStation/{id}", method = RequestMethod.GET)
    public Station getStationById(@PathVariable int id) {
        logger.info("getting station with id :" + id);

        return stationService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
    public void addStation(@RequestBody Station station) {
        logger.info("creating new station: {}");

        stationService.create(station);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateStation/{id}", method = RequestMethod.POST)
    public void updateStation(@PathVariable int id, @RequestBody Station station) {

        Station currentStation = stationService.findOne(id);
        logger.info("updating station with id :" + id);
        if (currentStation == null) {
            logger.info("Station with id {} not found" + id);
        }
        stationService.update2(station);
        logger.info("updated station with id :" + id);
    }

    @RequestMapping(value = "/deleteStation/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteStation(@PathVariable("id") int id) {
        stationService.deleteById(id);
    }
    
    
    //not deleted users
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Station> getAll() {
        logger.info("getting  not deleted");

        List<Station> list = stationService.getAll((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no user found");
        }
        return list;
    }
    //add
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Station line) {
        logger.info("creating new : {}");

        stationService.add(line);

    }
    
//delete setting isdeleted = 1

    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        stationService.delete2(id);
    }
    
    
}
