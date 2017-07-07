/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Transport;
import com.veganet.easytransport.service.TransportService;
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
@RequestMapping(value = "/transports")
public class TransportController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(TransportController.class);
    @Autowired
    TransportService transportService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllTransports", method = RequestMethod.GET)
    public List<Transport> getTransports() {
        logger.info("getting all transports");

        List<Transport> listOfTransports = transportService.findAll();
        if (listOfTransports == null || listOfTransports.isEmpty()) {
            logger.info("no transports found");
        }
        return listOfTransports;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTransport/{id}", method = RequestMethod.GET)
    public Transport getTransportById(@PathVariable int id) {
        logger.info("getting transport with id :" + id);

        return transportService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addTransport", method = RequestMethod.POST)
    public void addTransport(@RequestBody Transport transport) {
        logger.info("creating new transport: {}");

        transportService.create(transport);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateTransport/{id}", method = RequestMethod.POST)
    public void updateTransport(@PathVariable int id, @RequestBody Transport transport) {

        Transport currentTransport = transportService.findOne(id);
        logger.info("updating transport with id :" + id);
        if (currentTransport == null) {
            logger.info("Transport with id {} not found" + id);
        }
        transportService.update2(transport);
        logger.info("updated transport with id :" + id);
    }

    @RequestMapping(value = "/deleteTransport/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteTransport(@PathVariable("id") int id) {
        transportService.deleteById(id);
    }

    //not deleted 
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Transport> getAll() {
        logger.info("getting  not deleted");

        List<Transport> list = transportService.getAll((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no user found");
        }
        return list;
    }

    //add
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Transport t) {
        logger.info("creating new : {}");

        transportService.add(t);

    }

//delete setting isdeleted = 1
    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        transportService.delete2(id);
    }

    //trains
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrains", method = RequestMethod.GET)
    public List<Transport> getTrains() {
        logger.info("getting all trains");

        List<Transport> list = transportService.getAllByType((short) 0, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }
//    

    //bus
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBus", method = RequestMethod.GET)
    public List<Transport> getBus() {
        logger.info("getting all trains");

        List<Transport> list = transportService.getAllByType((short) 1, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    // public List<Transport> getAllByAdmin(short type, int adminId) {
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBusByAdmin/{adminId}", method = RequestMethod.GET)
    public List<Transport> getAllByAdmin(@PathVariable int adminId) {
        logger.info("getting all ");

        List<Transport> list = transportService.getAllByAdmin((short) 1, adminId);
        if (list == null || list.isEmpty()) {
            logger.info("no  found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrainsByAdmin/{adminId}", method = RequestMethod.GET)
    public List<Transport> getTrainsByAdmin(@PathVariable int adminId) {
        logger.info("getting all trains");

        List<Transport> list = transportService.getAllByAdmin((short) 0, adminId);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    //bycompany

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBusByCompany/{id}", method = RequestMethod.GET)
    public List<Transport> getAllByCompany(@PathVariable int id) {
        logger.info("getting all ");

        List<Transport> list = transportService.getAllByAdmin((short) 1, id);
        if (list == null || list.isEmpty()) {
            logger.info("no  found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrainsByCompany/{id}", method = RequestMethod.GET)
    public List<Transport> getTrainsByCompany(@PathVariable int id) {
        logger.info("getting all trains");

        List<Transport> list = transportService.getAllByAdmin((short) 0, id);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }
}
