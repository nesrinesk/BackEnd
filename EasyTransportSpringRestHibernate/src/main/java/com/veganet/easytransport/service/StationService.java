/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.StationDaoImpl;
import com.veganet.easytransport.entities.Station;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("stationService")
public class StationService {

    @Autowired
    StationDaoImpl stationDao;

    @Transactional
    public List<Station> findAll() {
        return stationDao.findAll();
    }

    @Transactional
    public Station findOne(int id) {
        return stationDao.findOne(id);
    }

    @Transactional
    public void create(Station station) {
        stationDao.create(station);
    }

    @Transactional
    public void update2(Station station) {
        stationDao.update(station);

    }

    @Transactional
    public void deleteById(int id) {
        stationDao.deleteById(id);
    }

    //isdeleted
    @Transactional
    public void delete2(int id) {
        stationDao.delete2(id);
    }

    @Transactional
    public void add(Station station) {
        stationDao.add(station);
    }
//not deleted

    @Transactional
    public List<Station> getAll(short isdeleted) {
        return stationDao.getAll(isdeleted);
    }

    //not deleted by type
//
    @Transactional
    public List<Station> getAllByType(short type, short isdeleted) {
        return stationDao.getAllByType(type, isdeleted);
    }

    @Transactional
    public List<Station> getAllByAdmin(short type, int adminId) {
        return stationDao.getAllByAdmin(type, adminId);
    }

    @Transactional
    public List<Station> getAllByCompany(short type, int id) {
        return stationDao.getAllByCompany(type, id);
    }
    
}
