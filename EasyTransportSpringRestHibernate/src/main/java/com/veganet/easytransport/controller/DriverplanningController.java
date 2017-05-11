/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Driverplanning;
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
    @RequestMapping(value = "/getAllByUser/{id}", method = RequestMethod.GET)
    public List<Driverplanning> getAllByUser(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Driverplanning> list = driverplanningService.getAllByUser(id);

        return list;
    }
}
