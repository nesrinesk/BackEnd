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
        alertDao.add(alert);
    }
    
    @Transactional
    public void update(Alert alert) {
        alertDao.update(alert);
        
    }
    
    @Transactional
    public void deleteById(int id) {
        alertDao.deleteById(id);
    }
    
    @Transactional
    public List<Alert> getAllVisible(Short visibility, Short type, int id) {
        return alertDao.getAllVisible(visibility, type, id);
    }
    
    @Transactional
    public Alert changeVisibility(Alert t) {
        return alertDao.changeVisibility(t);
    }

    @Transactional
    public List<Alert> getAllBytype(Short type) {
        return alertDao.getAllBytype(type);
    }    
    
    @Transactional
    public List<Alert> getAllByCompany(short type, int id) {
        return alertDao.getAllByCompany(type, id);
    }
    
    @Transactional
    public List<Alert> alertNotificationForAdmin(Short visibility, Short type, int id) {
        return alertDao.alertNotificationForAdmin(visibility, type, id);
    }
    
    @Transactional
    public List<Alert> getNotSeenAlerts(int id) {
        return alertDao.getNotSeenAlerts(id);
    }
    
    @Transactional
    public Alert seen(Alert t) {
        return alertDao.seen(t);
    }
}
