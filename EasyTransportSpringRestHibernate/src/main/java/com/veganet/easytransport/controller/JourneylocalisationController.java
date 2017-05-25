/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Journeylocalisation;
import com.veganet.easytransport.service.JourneylocalisationService;
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
//@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping(value = "/journeylocalisation")
public class JourneylocalisationController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(JourneylocalisationController.class);
    @Autowired
    JourneylocalisationService journeylocalisationService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllJourneylocalisations", method = RequestMethod.GET)
    public List<Journeylocalisation> findAll() {
        logger.info("getting all Journeylocalisations");

        List<Journeylocalisation> list = journeylocalisationService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no transports found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getJourneylocalisation/{id}", method = RequestMethod.GET)
    public Journeylocalisation findOne(@PathVariable int id) {
        logger.info("getting jurneylocalisation with id :" + id);

        return journeylocalisationService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addJourneylocalisation", method = RequestMethod.POST)
    public Integer create(@RequestBody Journeylocalisation journeylocalisation) {
        logger.info("creating new transport: {}");

        journeylocalisationService.create(journeylocalisation);
        return journeylocalisation.getJourneyLocalisationId();
    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateJourneylocalisation/{id}", method = RequestMethod.POST)
    public void update(@PathVariable int id, @RequestBody Journeylocalisation journeylocalisation) {

        Journeylocalisation currentO = journeylocalisationService.findOne(id);
        logger.info("updating journeylocalisation with id :" + id);
        if (currentO == null) {
            logger.info("Transport with id {} not found" + id);
        }
        journeylocalisationService.update(journeylocalisation);
        logger.info("updated transport with id :" + id);
    }

    @RequestMapping(value = "/deleteJourneylocalisation/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id) {
        journeylocalisationService.deleteById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllBus", method = RequestMethod.GET)
    public List<Journeylocalisation> getAllBus() {
        logger.info("getting all trains");

        List<Journeylocalisation> list = journeylocalisationService.getAllByType((short) 1);

        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByJourney/{id}", method = RequestMethod.GET)
    public List<Journeylocalisation> getAllByLine(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Journeylocalisation> list = journeylocalisationService.getAllByLine(id);

        return list;
    }
}
