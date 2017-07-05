/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.service.LineService;
import java.math.BigInteger;
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
@RequestMapping(value = "/lines")
public class LineController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(LineController.class);
    @Autowired
    LineService lineService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllLines", method = RequestMethod.GET)
    public List<Line> getLines() {
        logger.info("getting all lines");

        List<Line> listOfLines = lineService.findAll();
        if (listOfLines == null || listOfLines.isEmpty()) {
            logger.info("no lines found");
        }
        return listOfLines;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getLine/{id}", method = RequestMethod.GET)
    public Line getLineById(@PathVariable int id) {
        logger.info("getting line with id :" + id);

        return lineService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addLine", method = RequestMethod.POST)
    public void addLine(@RequestBody Line line) {
        logger.info("creating new line: {}");

        lineService.create(line);

    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/updateLine/{id}", method = RequestMethod.POST)
    public void updateLine(@PathVariable int id, @RequestBody Line line) {

        Line currentLine = lineService.findOne(id);
        logger.info("updating line with id :" + id);
        if (currentLine == null) {
            logger.info("Line with id {} not found" + id);
        }
        lineService.update2(line);
        logger.info("updated line with id :" + id);
    }

    @RequestMapping(value = "/deleteLine/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteLine(@PathVariable("id") int id) {
        lineService.deleteById(id);
    }

    //not deleted users
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Line> getAll() {
        logger.info("getting  not deleted");

        List<Line> list = lineService.getAll((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no user found");
        }
        return list;
    }

    //add
    @Produces(MediaType.APPLICATION_JSON)

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer add(@RequestBody Line line) {
        logger.info("creating new : {}");
        lineService.add(line);
        logger.info("id : {}" + line.getLineId());
        return line.getLineId();
    }

//delete setting isdeleted = 1
    @RequestMapping(value = "/delete2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        lineService.delete2(id);
    }

    //trains
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrainLines", method = RequestMethod.GET)
    public List<Line> getTrainLines() {
        logger.info("getting all trains");

        List<Line> list = lineService.getAllByType((short) 0, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }

    //bus
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBusLines", method = RequestMethod.GET)
    public List<Line> getBusLines() {
        logger.info("getting all trains");

        List<Line> list = lineService.getAllByType((short) 1, (short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }
   @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getBusByAdmin/{adminId}", method = RequestMethod.GET)
    public List<Line> getAllByAdmin( @PathVariable int adminId) {
        logger.info("getting all ");

        List<Line> list = lineService.getAllByAdmin((short) 1, adminId);
        if (list == null || list.isEmpty()) {
            logger.info("no  found");
        }
        return list;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getTrainsByAdmin/{adminId}", method = RequestMethod.GET)
    public List<Line> getTrainsByAdmin(@PathVariable int adminId) {
        logger.info("getting all trains");

        List<Line> list = lineService.getAllByAdmin((short) 0, adminId);
        if (list == null || list.isEmpty()) {
            logger.info("no train found");
        }
        return list;
    }
    //getLastInsertedId
    /*@Produces(MediaType.APPLICATION_JSON)
     @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     @RequestMapping(value = "/getLastInsertedId", method = RequestMethod.GET)
     public BigInteger getLastInsertedId() {
     logger.info("getting last inseted id");

     BigInteger id = lineService.getLastInsertedId();

     return id;
     }*/
}
