/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Journeyplanning;
import com.veganet.easytransport.service.JourneyplanningService;
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
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping(value = "/journeyplanning")
public class JourneyplanningController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(JourneyplanningController.class);
    @Autowired
    JourneyplanningService journeyplanningService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllJourneyplannings", method = RequestMethod.GET)
    public List<Journeyplanning> findAll() {
        logger.info("getting all Journeyplanning");

        List<Journeyplanning> list = journeyplanningService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no Journeyplanning found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getJourneyplanning/{id}", method = RequestMethod.GET)
    public Journeyplanning findOne(@PathVariable int id) {
        logger.info("getting Journeyplanning with id :" + id);

        return journeyplanningService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addJourneyplanning", method = RequestMethod.POST)
    public void create(@RequestBody Journeyplanning driverplanning) {
        logger.info("creating new Journeyplanning: {}");

        journeyplanningService.create(driverplanning);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateJourneyplanning/{id}", method = RequestMethod.POST)
    public void update(@PathVariable int id, @RequestBody Journeyplanning driverplanning) {

        Journeyplanning currentO = journeyplanningService.findOne(id);
        logger.info("updating Journeyplanning with id :" + id);
        if (currentO == null) {
            logger.info("Journeyplanning with id {} not found" + id);
        }
        journeyplanningService.update(driverplanning);
        logger.info("updated Journeyplanning with id :" + id);
    }

    @RequestMapping(value = "/deleteDriverplanning/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id) {
        journeyplanningService.deleteById(id);
    }
}
