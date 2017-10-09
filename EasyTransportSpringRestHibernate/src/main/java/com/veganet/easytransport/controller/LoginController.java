/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.User;
import com.veganet.easytransport.insertPosition.InsertThread;
import com.veganet.easytransport.insertPosition.InsertionExecutorService;
import com.veganet.easytransport.service.DriverplanningService;
import com.veganet.easytransport.service.TransportService;
import com.veganet.easytransport.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
//@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(value = "/security")
public class LoginController {
//@Qualifier

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager; // specific for Spring Security

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;
    @Autowired
    TransportService transportService;
    @Autowired
    DriverplanningService driverplanningService;
    @Autowired
    InsertionExecutorService insertionExecutorService;

    public static String device_name;
    public static Driverplanning journey;
    public static String startStation;
    public static String endStation;
    public static ArrayList<ArrayList<Double>> listOLists;
    public static List<Station> linesStation;
    public static List<Integer> linesStationDelay;
    public static Double stationStartLong;
    public static Double stationStartLat;
    public static Double stationEndLong;
    public static Double stationEndLat;
    public static Date startDate;
    public static Date endDate;
    String connectedUserName;
    int deice_id = 1;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.GET)
    private void doAutoLogin(@PathVariable String username, @PathVariable String password, HttpServletRequest request) {
        try {
            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = this.authenticationManager.authenticate(token);
            logger.info("Logging in with [{}]" + authentication.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            connectedUserName = username;

            logger.info("connectedUserName " + connectedUserName);
            //thread preparation

            device_name = transportService.findOne(deice_id).getName();
            System.out.println("device_name " + device_name);
            journey = driverplanningService.searchByTrain(device_name);
            //start 
            startStation = journey.getJourneyId().getStationStartId().getStationName();
            System.out.println("startStation " + startStation);
            stationStartLong = journey.getJourneyId().getStationStartId().getLongitude();
            stationStartLat = journey.getJourneyId().getStationStartId().getLatitude();
            //end
            endStation = journey.getJourneyId().getStationEndId().getStationName();
            System.out.println("endStation " + endStation);
            stationEndLong = journey.getJourneyId().getStationEndId().getLongitude();
            stationEndLat = journey.getJourneyId().getStationEndId().getLatitude();
            //date 
            startDate = journey.getJourneyId().getDateStart();
           
            new Thread(new InsertThread()).start();

        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            logger.error("Failure in autoLogin", e);
        }
    }
    /*   ExecutorService executor = Executors.newFixedThreadPool(5);
            
     for (int i = 0; i < 10; i++) {
     InsertionExecutorService work = new InsertionExecutorService();
     executor.execute(work);
     }
     executor.shutdown();

     try {
     if (executor.awaitTermination(60, TimeUnit.SECONDS)) {
     System.out.println("Completed units of work successfully.");
     } else {
     System.err.println("Failed to execute in allowed time period.");
     }
     } catch (InterruptedException e) {
     e.printStackTrace(System.err);
     }
     // new Thread(new InsertionExecutorService()).start();
     // insertionExecutorService.run();
        
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            connectedUserName = "";
        }
        logger.info("logout");
    }

//findOne by username
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/findConnectedUser", method = RequestMethod.GET)
    public User findByUserName() {
        logger.info("getting user with id :" + connectedUserName);

        userService.findByUserName(connectedUserName);

        return userService.findByUserName(connectedUserName);
    }

}
