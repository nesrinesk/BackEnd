/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.DriverDao;
import com.veganet.easytransport.entities.Driver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("driverService")
public class DriverService {

    @Autowired
    DriverDao driverDao;

    @Transactional
    public List<Driver> findAll() {
        return driverDao.findAll();
    }

    @Transactional
    public Driver findOne(int id) {
        return driverDao.findOne(id);
    }

    @Transactional
    public void create(Driver driver) {
        driverDao.create(driver);
    }

    @Transactional
    public void update(Driver driver) {
        driverDao.update(driver);

    }

    @Transactional
    public void deleteById(int id) {
        driverDao.deleteById(id);
    }

}
