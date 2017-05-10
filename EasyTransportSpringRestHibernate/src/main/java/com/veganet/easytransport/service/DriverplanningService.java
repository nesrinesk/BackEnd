/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.DriverplanningDao;
import com.veganet.easytransport.entities.Driverplanning;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
 @Service("driverplanningService")
public class DriverplanningService {

    @Autowired
    DriverplanningDao driverplanningDao;

    
   
    @Transactional
    public List<Driverplanning> findAll() {
        return driverplanningDao.findAll();
    }
    @Transactional
    public Driverplanning findOne(int id) {
        return driverplanningDao.findOne(id);
    }

    @Transactional
    public void add(Driverplanning driverplanning) {
        driverplanningDao.add(driverplanning);
    }

    @Transactional
    public void update(Driverplanning driverplanning) {
        driverplanningDao.update(driverplanning);

    }

    @Transactional
    public void deleteById(int id) {
        driverplanningDao.deleteById(id);
    }

    

}