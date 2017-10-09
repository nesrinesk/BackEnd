/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.controller;

import com.veganet.easytransport.entities.Company;
import com.veganet.easytransport.service.CompanyService;
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
@RequestMapping(value = "/company")
public class CompanyController {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(CompanyController.class);
    @Autowired
    CompanyService companyService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public List<Company> findAll() {
        logger.info("getting all ");

        List<Company> list = companyService.findAll();
        if (list == null || list.isEmpty()) {
            logger.info("no found");
        }
        return list;
    }

//public List<Company> getCompanies()
    
     @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getCompanies", method = RequestMethod.GET)
    public List<Company> getCompanies() {
        logger.info("getting all ");

        List<Company> list = companyService.getCompanies();
        if (list == null || list.isEmpty()) {
            logger.info("no found");
        }
        return list;
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getCompany/{id}", method = RequestMethod.GET)
    public Company getCompanyById(@PathVariable int id) {
        logger.info("getting company with id :" + id);

        return companyService.findOne(id);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public Integer addCompany(@RequestBody Company company) {
        logger.info("creating new company: {}");

        companyService.add(company);
        return company.getCompanyId();
    }

    @Consumes(MediaType.APPLICATION_JSON)

    @RequestMapping(value = "/updateCompany/{id}", method = RequestMethod.POST)
    public void updateCompany(@PathVariable int id, @RequestBody Company company) {

        Company currentCompany = companyService.findOne(id);
        logger.info("updating company with id :" + id);
        if (currentCompany == null) {
            logger.info("Company with id {} not found" + id);
        }
        companyService.update(company);
        logger.info("updated company with id :" + id);
    }

    @RequestMapping(value = "/deleteCompany/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteCompany(@PathVariable("id") int id) {
        companyService.deleteById(id);
    }

    @RequestMapping(value = "/deleteHide/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
    public void delete2(@PathVariable("id") int id) {
        companyService.delete2(id);
    }
    
       @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @RequestMapping(value = "/getCompanyByName/{name}", method = RequestMethod.GET)
    public Company getCompanyByName(@PathVariable String name) {
       // logger.info("getting company with id :" + id);

        return companyService.findCompanyByName(name);
    }
}
