/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Passage;
import com.veganet.easytransport.service.PassageService;
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
@RequestMapping(value = "/passages")
public class PassageController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(PassageController.class);
    @Autowired
    PassageService passageService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllPassages", method = RequestMethod.GET)
    public List<Passage> getPassagers() {
        logger.info("getting all passages");

        List<Passage> listOfPassages = passageService.findAll();
        if (listOfPassages == null || listOfPassages.isEmpty()) {
            logger.info("no passages found");
        }
        return listOfPassages;
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getPassage/{id}", method = RequestMethod.GET)
    public Passage getPassageById(@PathVariable int id) {
        logger.info("getting passage with id :"+id);
        
        return passageService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addPassage", method = RequestMethod.POST)
    public void addPassage(@RequestBody Passage passage) {
        logger.info("creating new passage: {}");

        passageService.create(passage);

    }
    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updatePassage/{id}", method = RequestMethod.POST)
    public void updatePassage(@PathVariable int id,@RequestBody Passage passage) {
       
        Passage currentPassage = passageService.findOne(id);
         logger.info("updating passage with id :"+id);
          if (currentPassage==null) {
               logger.info("Passage with id {} not found"+ id);
           }
          passageService.update(passage);
          logger.info("updated passage with id :"+id);
        }
    

    @RequestMapping(value = "/deletePassage/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deletePassage(@PathVariable("id") int id) {
        passageService.deleteById(id);
    }
}
