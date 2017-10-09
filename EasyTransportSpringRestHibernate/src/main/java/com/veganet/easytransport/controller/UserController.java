/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.User;
import com.veganet.easytransport.service.UserService;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
//@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;

    private JavaMailSender mailSender;

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

    // public List<User> getDriversByCompany(int id)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getDriversByCompany/{id}", method = RequestMethod.GET)
    public List<User> getDriversByCompany(@PathVariable int id) {
        logger.info("getting all drivers");

        List<User> list = userService.getDriversByCompany(id);
        if (list == null || list.isEmpty()) {
            logger.info("no drivers found");
        }
        return list;
    }
// public User getCompanyAdmin(int id) 

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getCompanyAdmin/{id}", method = RequestMethod.GET)
    public User getCompanyAdmin(@PathVariable int id) {
        logger.info("getting all drivers");

        User list = userService.getCompanyAdmin(id);

        return list;
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

        String status = null;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("Administrateur");
            helper.setTo(user.getEmail());
            helper.setSubject("Votre compte EasyTransport");

            String text = "Bonjour " + user.getFirstName() + " " + user.getLastName() + ",<br />"
                    + "Merci pour votre inscription sur EasyTransport <br />"
                    + "Utilisez les paramètres d'accès suivants pour vous connecter au site: <br />"
                    + "Nom d'utilisateur:<b>" + user.getUserName() + "</b><br />"
                    + "Mot de passe:<b>" + user.getPassword() + "</b>";

            helper.setText(text, true);
            mailSender.send(message);
            status = "Confirmation email is sent to your address (" + user.getEmail() + ")";
        } catch (MessagingException e) {
            status = "There was an error in email sending. Please check your email address: " + user.getEmail();
        }

    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
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

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/passwordForgotten/{userName}", method = RequestMethod.GET)
    public User passwordForgotten(@PathVariable String userName) {

        logger.info("psw  {}");

        User user = userService.passwordForgotten(userName);

        String status = null;
        //logger.info("getting user with psw :" + user.getPassword()+" username"+user.getUserName());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("Administrator");
            helper.setTo(user.getEmail());
            helper.setSubject("Réinitialisation de votre mot de passe");

            String text = "Mot de passe réinitialisé. Vos détails de connexion sont:<br />"
                    + "Identifiant:<b>" + user.getUserName() + "</b><br />"
                    + "Mot de passe:<b>" + user.getPassword() + "</b>";

            helper.setText(text, true);
            mailSender.send(message);
            status = "Confirmation email is sent to your address (" + user.getEmail() + ")";
        } catch (MessagingException e) {
            status = "There was an error in email sending. Please check your email address: " + user.getEmail();
        }
        return user;

    }

}
