/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Alert;
import com.veganet.easytransport.service.AlertService;
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
@RequestMapping(value = "/alerts")

public class AlertController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(AlertController.class);
    @Autowired
    AlertService alertService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllAlerts", method = RequestMethod.GET)
    public List<Alert> getAlerts() {
        logger.info("getting all alerts");

        List<Alert> listOfAlerts = alertService.findAll();
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAlert/{id}", method = RequestMethod.GET)
    public Alert getAlertById(@PathVariable int id) {
        logger.info("getting alert with id :" + id);

        return alertService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addAlert", method = RequestMethod.POST)
    public void addAlert(@RequestBody Alert alert) {
        logger.info("creating new alert: {}");

        alertService.create(alert);

    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/updateAlert/{id}", method = RequestMethod.POST)
    public void updateAlert(@PathVariable int id, @RequestBody Alert alert) {

        Alert currentAlert = alertService.findOne(id);
        logger.info("updating alert with id :" + id);
        if (currentAlert == null) {
            logger.info("Alert with id {} not found" + id);
        }
        alertService.update(alert);
        logger.info("updated aler with id :" + id);
    }

    @RequestMapping(value = "/deleteAlert/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteAlert(@PathVariable("id") int id) {
        alertService.deleteById(id);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllVisibleTrain", method = RequestMethod.GET)
    public List<Alert> getAllVisibleTrain() {
        logger.info("getting all alerts");

        List<Alert> listOfAlerts = alertService.getAllVisible((short) 1, (short) 0);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllInvisibleTrain", method = RequestMethod.GET)
    public List<Alert> getAllInvisibleTrain() {
        logger.info("getting all alerts");

        List<Alert> listOfAlerts = alertService.getAllVisible((short) 0, (short) 0);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllVisibleBus", method = RequestMethod.GET)
    public List<Alert> getAllVisible() {
        logger.info("getting all alerts");

        List<Alert> listOfAlerts = alertService.getAllVisible((short) 1, (short) 1);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllInvisibleBus", method = RequestMethod.GET)
    public List<Alert> getAllInvisible() {
        logger.info("getting all alerts");

        List<Alert> listOfAlerts = alertService.getAllVisible((short) 0, (short) 1);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/changeVisibility/{id}", method = RequestMethod.POST)
    public void changeVisibility(@PathVariable int id, @RequestBody Alert alert) {

        Alert currentAlert = alertService.findOne(id);
        logger.info("updating alert with id :" + id);
        if (currentAlert == null) {
            logger.info("Alert with id {} not found" + id);
        }
        alertService.changeVisibility(currentAlert);
        logger.info("updated aler with id :" + id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/alertsBus", method = RequestMethod.GET)

    public List<Alert> getAllBytype(Short type) {

        List<Alert> listOfAlerts = alertService.getAllBytype((short) 1);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }
    
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/alertsTrain", method = RequestMethod.GET)

    public List<Alert> getAllBytypeT(Short type) {

        List<Alert> listOfAlerts = alertService.getAllBytype((short) 0);
        if (listOfAlerts == null || listOfAlerts.isEmpty()) {
            logger.info("no alerts found");
        }
        return listOfAlerts;
    }
}
