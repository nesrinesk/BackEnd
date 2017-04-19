/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Relatedto;
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
    public void update(@PathVariable int id, @RequestBody Relatedto transport) {

        Relatedto currentO = relatedtoService.findOne(id);
        logger.info("updating Relatedto with id :" + id);
        if (currentO == null) {
            logger.info("Relatedto with id {} not found" + id);
        }
        relatedtoService.update(transport);
        logger.info("updated Relatedto with id :" + id);
    }

    @RequestMapping(value = "/deleteRelatedto/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteById(@PathVariable("id") int id) {
        relatedtoService.deleteById(id);
    }
}
