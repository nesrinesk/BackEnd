/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.User;
import com.veganet.easytransport.service.UserService;
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
import org.springframework.http.HttpStatus;

/**
 *
 * @author asus
 */
@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;

    //all users
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        logger.info("getting all users");

        List<User> listOfUsers = userService.findAll();
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            logger.info("no users found");
        }
        return listOfUsers;
    }

    //admins
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllAdmins", method = RequestMethod.GET)
    public List<User> getAllAdmins() {
        logger.info("getting all admins");

        List<User> listOfUsers = userService.getAllUsersByAccessLevel("ROLE_ADMIN", (short) 0);
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            logger.info("no admins found");
        }
        return listOfUsers;
    }
//drivers

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllDrivers", method = RequestMethod.GET)
    public List<User> getAllDrivers() {
        logger.info("getting all drivers");

        List<User> listOfUsers = userService.getAllUsersByAccessLevel("ROLE_DRIVER", (short) 0);
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            logger.info("no drivers found");
        }
        return listOfUsers;
    }
//travelers

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllTravelers", method = RequestMethod.GET)
    public List<User> getAllTravelers() {
        logger.info("getting all travelers");

        List<User> listOfUsers = userService.getAllUsersByAccessLevel("ROLE_TRAVELER", (short) 0);
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            logger.info("no traveller found");
        }
        return listOfUsers;
    }
//superAdmins

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllSuperAdmins", method = RequestMethod.GET)
    public List<User> getAllSuperAdmins() {
        logger.info("getting all superAdmins");

        List<User> listOfUsers = userService.getAllUsersByAccessLevel("ROLE_SADMIN", (short) 0);
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            logger.info("no superAdmin found");
        }
        return listOfUsers;
    }

    //findOne
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        logger.info("getting user with id :" + id);
        return userService.findOne(id);
    }

    //update
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public void updateUser(@PathVariable int id, @RequestBody User user) {

        User currentUser = userService.findOne(id);
        logger.info("updating user with id :" + id);
        if (currentUser == null) {
            logger.info("User with id {} not found" + id);
        }
        userService.update2(user);
        logger.info("updated user with id :" + id);
    }

//delete
    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

    //not deleted users
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        logger.info("getting users not deleted");

        List<User> list = userService.getUsers((short) 0);
        if (list == null || list.isEmpty()) {
            logger.info("no user found");
        }
        return list;
    }

    //add
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void add(@RequestBody User user) {
        logger.info("creating new user: {}");

        userService.add(user);

    }

//delete setting isdeleted = 1
    @RequestMapping(value = "/deleteUser2/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        userService.delete2(id);
    }

    //findOne by username
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/findByUserName/{userName}", method = RequestMethod.GET)
    public User findByUserName(@PathVariable String userName) {
        logger.info("getting user with id :" + userName);
        return userService.findByUserName(userName);
    }

    
}
