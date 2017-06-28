/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.JourneylocalisationDaoImpl;
import com.veganet.easytransport.entities.Journeylocalisation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("journeylocalisationService")
public class JourneylocalisationService {

    @Autowired
    JourneylocalisationDaoImpl journeylocalisationDao;

    @Transactional
    public List<Journeylocalisation> findAll() {
        return journeylocalisationDao.findAll();
    }

    @Transactional
    public Journeylocalisation findOne(int id) {
        return journeylocalisationDao.findOne(id);
    }

    @Transactional
    public void create(Journeylocalisation journeylocalisation) {
        journeylocalisationDao.create(journeylocalisation);
    }

    @Transactional
    public void update(Journeylocalisation journeylocalisation) {
        journeylocalisationDao.update(journeylocalisation);

    }

    @Transactional
    public void deleteById(int id) {
        journeylocalisationDao.deleteById(id);
    }

    @Transactional
    public List<Journeylocalisation> getAllByType(short type) {
       return journeylocalisationDao.getAllByType(type);
    }
// by  lineId
    @Transactional
    public List<Journeylocalisation> getAllByLine(int id) {
        return journeylocalisationDao.getAllByLine(id);
    }
}
