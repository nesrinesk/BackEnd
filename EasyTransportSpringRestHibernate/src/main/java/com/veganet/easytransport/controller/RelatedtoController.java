/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Relatedto;
import com.veganet.easytransport.service.LineService;
import com.veganet.easytransport.service.RelatedtoService;
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
@RequestMapping(value = "/relatedto")

public class RelatedtoController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(RelatedtoController.class);
    @Autowired
    RelatedtoService relatedtoService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllRelatedto", method = RequestMethod.GET)
    public List<Relatedto> findAll() {
        logger.info("getting all Relatedto");

        List<Relatedto> list = relatedtoService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no Relatedto found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getRelatedto/{id}", method = RequestMethod.GET)
    public Relatedto findOne(@PathVariable int id) {
        logger.info("getting transport with id :" + id);

        return relatedtoService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addRelatedto", method = RequestMethod.POST)
    public void create(@RequestBody Relatedto relatedto) {
        logger.info("creating new transport: {}");

        relatedtoService.create(relatedto);

    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateRelatedto/{id}", method = RequestMethod.POST)
    public void update(@PathVariable int id, @RequestBody Relatedto r) {

        Relatedto currentO = relatedtoService.findOne(id);
        logger.info("updating Relatedto with id :" + id);
        if (currentO == null) {
            logger.info("Relatedto with id {} not found" + id);
        }
        relatedtoService.update(r);
        logger.info("updated Relatedto with id :" + id);
    }

    @RequestMapping(value = "/deleteRelatedto/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id) {
        relatedtoService.deleteById(id);
    }

    //trains
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllTrains", method = RequestMethod.GET)
    public List<Relatedto> getAll() {
        logger.info("getting all trains");

        List<Relatedto> list = relatedtoService.getAllByType((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    //bus
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllBus", method = RequestMethod.GET)
    public List<Relatedto> getAllBus() {
        logger.info("getting all trains");

        List<Relatedto> list = relatedtoService.getAllByType((short) 1);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllByLine/{id}", method = RequestMethod.GET)
    public List<Relatedto> getAllByLine(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Relatedto> list = relatedtoService.getAllByLine(id);

        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getLastTagLine/{id}", method = RequestMethod.GET)
    public Relatedto getLastTagLine(@PathVariable int id) {
        logger.info("getting transport with id :" + id);
        List<Relatedto> list = relatedtoService.getAllByLine(id);
        int lastTag = list.size() - 1;
        Relatedto list1 = relatedtoService.getlaststation((short) lastTag, id);

        return list1;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getLastTag/{id}", method = RequestMethod.GET)
    public int getLastTag(@PathVariable int id) {
        //logger.info("getting transport with id :" + id);

        List<Relatedto> list = relatedtoService.getAllByLine(id);
        int lastTag = list.size() - 1;
        return lastTag;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getFirstTagLine/{id}", method = RequestMethod.GET)
    public Relatedto getFirstTagLine(@PathVariable int id) {
        logger.info("getting transport with id :" + id);

        Relatedto list1 = relatedtoService.getlaststation((short) 0, id);

        return list1;
    }
}
