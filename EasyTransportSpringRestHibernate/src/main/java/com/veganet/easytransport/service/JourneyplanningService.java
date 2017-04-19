/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.JourneyplanningDao;
import com.veganet.easytransport.entities.Journeyplanning;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("journeyplanningService")
public class JourneyplanningService {

    @Autowired
    JourneyplanningDao journeyplanningDao;

    @Transactional
    public List<Journeyplanning> findAll() {
        return journeyplanningDao.findAll();
    }

    @Transactional
    public Journeyplanning findOne(int id) {
        return journeyplanningDao.findOne(id);
    }

    @Transactional
    public void create(Journeyplanning  journeyplanning) {
        journeyplanningDao.create(journeyplanning);
    }

    @Transactional
    public void update(Journeyplanning journeyplanning) {
        journeyplanningDao.update(journeyplanning);

    }

    @Transactional
    public void deleteById(int id) {
        journeyplanningDao.deleteById(id);
    }

}
