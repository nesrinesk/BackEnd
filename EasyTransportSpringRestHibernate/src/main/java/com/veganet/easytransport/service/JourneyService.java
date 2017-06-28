/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.JourneyDaoImpl;
import com.veganet.easytransport.entities.Journey;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("journeyService")
public class JourneyService {

    @Autowired
    JourneyDaoImpl journeyDao;

    @Transactional
    public List<Journey> findAll() {
        return journeyDao.findAll();
    }

    @Transactional
    public Journey findOne(int id) {
        return journeyDao.findOne(id);
    }

    @Transactional
    public void create(Journey journey) {
        journeyDao.create(journey);
    }

    @Transactional
    public void update2(Journey journey) {
        journeyDao.update(journey);

    }

    @Transactional
    public void deleteById(int id) {
        journeyDao.deleteById(id);
    }
 
    //
    @Transactional
    public Journey add(Journey journey) {
        return journeyDao.add(journey);
    }
    
    @Transactional
    public List<Journey> getAll(short isdeleted) {
       return journeyDao.getAll(isdeleted);
    }
    
    @Transactional
    public void delete2(int id) {
        journeyDao.delete2(id);
    }
    
     //not deleted by type

    @Transactional
    public List<Journey> getAllByType(short type, short isdeleted) {
        return journeyDao.getAllByType(type, isdeleted);
    }
    
    @Transactional
     public String linesOfJourney(int journeyId) {
         return  journeyDao.linesOfJourney(journeyId);
     }
}
