/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Driver;
import com.veganet.easytransport.service.DriverService;
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
@RequestMapping(value = "/drivers")
public class DriverController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(DriverController.class);
    @Autowired
    DriverService driverService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllDrivers", method = RequestMethod.GET)
    public List<Driver> getDrivers() {
        logger.info("getting all drivers");

        List<Driver> listOfDrivers = driverService.findAll();
        if (listOfDrivers == null || listOfDrivers.isEmpty()) {
            logger.info("no drivers found");
        }
        return listOfDrivers;
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getDriver/{id}", method = RequestMethod.GET)
    public Driver getDriverById(@PathVariable int id) {
        logger.info("getting driver with id :"+id);
        
        return driverService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addDriver", method = RequestMethod.POST)
    public void addDriver(@RequestBody Driver driver) {
        logger.info("creating new driver: {}");

        driverService.create(driver);

    }
    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateDriver/{id}", method = RequestMethod.POST)
    public void updateDriver(@PathVariable int id,@RequestBody Driver driver) {
       
        Driver currentDriver = driverService.findOne(id);
         logger.info("updating driver with id :"+id);
          if (currentDriver==null) {
               logger.info("Driver with id {} not found"+ id);
           }
          driverService.update(driver);
          logger.info("updated driver with id :"+id);
        }
    

    @RequestMapping(value = "/deleteDriver/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteDriver(@PathVariable("id") int id) {
        driverService.deleteById(id);
    }
}
