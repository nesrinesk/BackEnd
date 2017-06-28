/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.AlertDaoImpl;
import com.veganet.easytransport.entities.Alert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("alertService")

public class AlertService {

    @Autowired
    AlertDaoImpl alertDao;

    @Transactional
    public List<Alert> findAll() {
        return alertDao.findAll();
    }

    @Transactional
    public Alert findOne(int id) {
        return alertDao.findOne(id);
    }

    @Transactional
    public void create(Alert alert) {
        alertDao.create(alert);
    }

    @Transactional
    public void update(Alert alert) {
        alertDao.update(alert);

    }

    @Transactional
    public void deleteById(int id) {
        alertDao.deleteById(id);
    }

}
