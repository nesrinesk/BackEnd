/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.PassageDao;
import com.veganet.easytransport.entities.Passage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("passageService")
public class PassageService {

    @Autowired
    PassageDao passageDao;

    @Transactional
    public List<Passage> findAll() {
        return passageDao.findAll();
    }

    @Transactional
    public Passage findOne(int id) {
        return passageDao.findOne(id);
    }

    @Transactional
    public void create(Passage passage) {
        passageDao.create(passage);
    }

    @Transactional
    public void update(Passage passage) {
        passageDao.update(passage);

    }

    @Transactional
    public void deleteById(int id) {
        passageDao.deleteById(id);
    }

}
