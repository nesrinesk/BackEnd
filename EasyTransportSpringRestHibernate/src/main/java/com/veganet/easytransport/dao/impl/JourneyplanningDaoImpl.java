/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.JourneyplanningDao;
import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import com.veganet.easytransport.entities.Journeyplanning;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public class JourneyplanningDaoImpl extends AbstractHibernateDao<Journeyplanning> implements JourneyplanningDao{

    public JourneyplanningDaoImpl() {
        setClazz(Journeyplanning.class);
    }
}
