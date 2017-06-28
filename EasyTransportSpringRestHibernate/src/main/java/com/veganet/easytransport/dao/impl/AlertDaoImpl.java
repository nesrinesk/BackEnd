/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.AlertDao;
import com.veganet.easytransport.entities.Alert;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository

public class AlertDaoImpl extends AbstractHibernateDao<Alert> implements AlertDao{

    public AlertDaoImpl() {
        setClazz(Alert.class);
    }
}
