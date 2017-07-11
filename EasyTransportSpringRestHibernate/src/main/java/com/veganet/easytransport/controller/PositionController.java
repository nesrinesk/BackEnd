/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Positions;
import com.veganet.easytransport.service.PositionService;
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
@RequestMapping(value = "/positions")
public class PositionController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(PositionController.class);
    @Autowired
    PositionService positionService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllPositions", method = RequestMethod.GET)
    public List<Positions> getPositions() {
        logger.info("getting all positions");

        List<Positions> listOfPositions = positionService.findAll();
        if (listOfPositions == null || listOfPositions.isEmpty()) {
            logger.info("no positions found");
        }
        return listOfPositions;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getPosition/{id}", method = RequestMethod.GET)
    public Positions getPositionById(@PathVariable int id) {
        logger.info("getting position with id :" + id);

        return positionService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addPosition", method = RequestMethod.POST)
    public void addPosition(@RequestBody Positions position) {
        logger.info("creating new position: {}");

        positionService.create(position);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updatePosition/{id}", method = RequestMethod.POST)
    public void updatePosition(@PathVariable int id, @RequestBody Positions position) {

        Positions currentPosition = positionService.findOne(id);
        logger.info("updating position with id :" + id);
        if (currentPosition == null) {
            logger.info("Position with id {} not found" + id);
        }
        positionService.update(position);
        logger.info("updated position with id :" + id);
    }

    @RequestMapping(value = "/deletePosition/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deletePosition(@PathVariable("id") int id) {
        positionService.deleteById(id);
    }
    ///////////

    //not deleted 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Positions> getAll() {
        logger.info("getting Positions not deleted");

        List<Positions> list = positionService.getAll((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no  found");
        }
        return list;
    }

    //add

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Positions position) {
        logger.info("creating new Position: {}");

        positionService.add(position);

    }

//delete setting isdeleted = 1
    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        positionService.delete2(id);
    }

    //

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByTransport/{transportName}", method = RequestMethod.GET)
    public List<Positions> getAllByTransport(@PathVariable String transportName) {
        logger.info("getting position with id :" + transportName);

        return positionService.getAllByTransport(transportName);
    }

    //

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getLastPositionByTransport/{transportName}", method = RequestMethod.GET)
    public Positions getLastPositionByTransport(@PathVariable String transportName) {
        return positionService.getLastPositionByTransport(transportName);
    }
}
