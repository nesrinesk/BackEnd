/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.RelatedtoDao;
import com.veganet.easytransport.entities.Relatedto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
    
@Service("relatedtoService")
public class RelatedtoService {

    @Autowired
    RelatedtoDao relatedtoDao;

    
   
    @Transactional
    public List<Relatedto> findAll() {
        return relatedtoDao.findAll();
    }
    @Transactional
    public Relatedto findOne(int id) {
        return relatedtoDao.findOne(id);
    }

    @Transactional
    public void create(Relatedto relatedto) {
        relatedtoDao.create(relatedto);
    }

    @Transactional
    public void update(Relatedto relatedto) {
        relatedtoDao.update(relatedto);

    }

    @Transactional
    public void deleteById(int id) {
        relatedtoDao.deleteById(id);
    }

@Transactional
    public List<Relatedto> getAllByType(short type) {
        return relatedtoDao.getAllByType(type);
    }    
}
