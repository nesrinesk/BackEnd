/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Favorite;
import com.veganet.easytransport.service.FavoriteService;
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
@RequestMapping(value = "/favorites")
public class FavoriteController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(FavoriteController.class);
    @Autowired
    FavoriteService favoriteService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllFavorites", method = RequestMethod.GET)
    public List<Favorite> getFavorites() {
        logger.info("getting all favorites");

        List<Favorite> listOfFavorites = favoriteService.findAll();
        if (listOfFavorites == null || listOfFavorites.isEmpty()) {
            logger.info("no favorites found");
        }
        return listOfFavorites;
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getFavorite/{id}", method = RequestMethod.GET)
    public Favorite getFavoriteById(@PathVariable int id) {
        logger.info("getting favorite with id :"+id);
        
        return favoriteService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public void addFavorite(@RequestBody Favorite favorite) {
        logger.info("creating new favorite: {}");

        favoriteService.create(favorite);

    }
    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateFavorite/{id}", method = RequestMethod.POST)
    public void updateFavorite(@PathVariable int id,@RequestBody Favorite favorite) {
       
        Favorite currentFavorite = favoriteService.findOne(id);
         logger.info("updating favorite with id :"+id);
          if (currentFavorite==null) {
               logger.info("Favorite with id {} not found"+ id);
           }
          favoriteService.update(favorite);
          logger.info("updated favorite with id :"+id);
        }
    

    @RequestMapping(value = "/deleteFavorite/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteFavorite(@PathVariable("id") int id) {
        favoriteService.deleteById(id);
    }
}
