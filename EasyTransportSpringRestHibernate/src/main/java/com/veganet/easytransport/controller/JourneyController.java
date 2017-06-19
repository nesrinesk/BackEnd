/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Journey;
import com.veganet.easytransport.service.JourneyService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/journeys")
//@CrossOrigin(origins = "http://127.0.0.1:3000")
public class JourneyController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(JourneyController.class);
    @Autowired
    JourneyService journeyService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllJourneys", method = RequestMethod.GET)
    public List<Journey> getJourneys() {
        logger.info("getting all Journeys");

        List<Journey> list = journeyService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no journeys found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getJourney/{id}", method = RequestMethod.GET)
    public Journey getJourneyById(@PathVariable int id) {
        logger.info("getting journey with id :" + id);

        return journeyService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addJourney", method = RequestMethod.POST)
    public void addJourney(@RequestBody Journey journey) {
        logger.info("creating new journey: {}");

        journeyService.create(journey);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateJourney/{id}", method = RequestMethod.POST)
    public void updateJourney(@PathVariable int id, @RequestBody Journey journey) {

        Journey currentJourney = journeyService.findOne(id);
        logger.info("updating journey with id :" + id);
        if (currentJourney == null) {
            logger.info("Journey with id {} not found" + id);
        }
        journeyService.update2(journey);
        logger.info("updated journey with id :" + id);
    }

    @RequestMapping(value = "/deleteJourney/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteJourney(@PathVariable("id") int id) {
        journeyService.deleteById(id);
    }

    ////////////
    //not deleted object
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Journey> getAll() {
        logger.info("getting objects not deleted");

        List<Journey> list = journeyService.getAll((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no Journey found");
        }
        return list;
    }

    //add

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestBody Journey journey) {
        logger.info("creating new object: {}");

        journeyService.add(journey);
        return journey.getJourneyId();
    }

//delete setting isdeleted = 1
    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        journeyService.delete2(id);
    }

    //trains
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrainJourneys", method = RequestMethod.GET)
    public List<Journey> getTrainJourneys() {
        logger.info("getting all trains");

        List<Journey> list = journeyService.getAllByType((short) 0, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    //bus
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBusJourneys", method = RequestMethod.GET)
    public List<Journey> getBusJourneys() {
        logger.info("getting all trains");

        List<Journey> list = journeyService.getAllByType((short) 1, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }
    
  
    @RequestMapping(value = "/linesOfJourney/{journeyId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public String linesOfJourney(@PathVariable int journeyId) {
        logger.info("linesOfJourney");

        String list = journeyService.linesOfJourney(journeyId);
        
        return list;
    }
}
