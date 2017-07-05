/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.CompanyDaoImpl;
import com.veganet.easytransport.entities.Company;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("companyService")

public class CompanyService {

    @Autowired
    CompanyDaoImpl companyDao;

    @Transactional
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Transactional
    public Company findOne(int id) {
        return companyDao.findOne(id);
    }

    @Transactional
    public void create(Company company) {
        companyDao.create(company);
    }

    @Transactional
    public Company add(Company c) {
        return companyDao.add(c);
    }

    @Transactional
    public void update(Company company) {
        companyDao.update(company);

    }

    @Transactional
    public void deleteById(int id) {
        companyDao.deleteById(id);
    }

    @Transactional
    public void delete2(int id) {
        companyDao.delete2(id);
    }

    @Transactional
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }
}
