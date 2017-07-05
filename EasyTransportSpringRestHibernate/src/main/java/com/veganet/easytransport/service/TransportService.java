/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.TransportDaoImpl;
import com.veganet.easytransport.entities.Transport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("transportService")
public class TransportService {

    @Autowired
    TransportDaoImpl transportDao;

    @Transactional
    public List<Transport> findAll() {
        return transportDao.findAll();
    }

    @Transactional
    public Transport findOne(int id) {
        return transportDao.findOne(id);
    }

    @Transactional
    public void create(Transport transport) {
        transportDao.create(transport);
    }

    @Transactional
    public void update2(Transport transport) {
        transportDao.update(transport);

    }

    @Transactional
    public void deleteById(int id) {
        transportDao.deleteById(id);
    }

//isdeleted
    @Transactional
    public void delete2(int id) {
        transportDao.delete2(id);
    }

    @Transactional
    public void add(Transport t) {
        transportDao.add(t);
    }

    @Transactional
    public List<Transport> getAll(short isdeleted) {
        return transportDao.getAll(isdeleted);
    }

    //not deleted by type
    @Transactional
    public List<Transport> getAllByType(short type, short isdeleted) {
        return transportDao.getAllByType(type, isdeleted);
    }
    
    @Transactional
     public List<Transport> getAllByAdmin(short type, int adminId) {
       return transportDao.getAllByAdmin(type, adminId);
    }
}
