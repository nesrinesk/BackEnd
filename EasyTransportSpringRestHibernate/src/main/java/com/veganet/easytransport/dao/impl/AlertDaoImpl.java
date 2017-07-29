/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.AlertDao;
import com.veganet.easytransport.entities.Alert;
import com.veganet.easytransport.entities.Company;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository

public class AlertDaoImpl extends AbstractHibernateDao<Alert> implements AlertDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    
    public AlertDaoImpl() {
        setClazz(Alert.class);
    }
    
    public Alert add(Alert t) {
        Session session = this.sessionFactory.getCurrentSession();
        t.setVisibility((short) 0);
        t.setSeen((short) 0);
        t.setType((short) 1);
        session.persist(t);
        
        return t;
    }
    
    public Alert changeVisibility(Alert t) {
        Session session = this.sessionFactory.getCurrentSession();
        if (t.getVisibility() == (short) 1) {
            t.setVisibility((short) 0);
        } else {
            t.setVisibility((short) 1);
        }
        // t.setVisibility((short) 1);
        session.update(t);
        
        return t;
    }
    
    public Alert seen(Alert t) {
        Session session = this.sessionFactory.getCurrentSession();
        t.setSeen((short) 1);
        // t.setVisibility((short) 1);
        session.update(t);
        
        return t;
    }
    
    public List<Alert> getAllBytype(Short type) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                + "WHERE t.type = :type")
                .setParameter("type", type).list();
        return list;
    }
    
    public List<Alert> getAllByCompany(short type, int id) {
        List<Alert> finalList = new ArrayList<Alert>();
        Session session = this.sessionFactory.getCurrentSession();
        Company companyId = (Company) session.get(Company.class, id);
        List<Alert> list = session.createQuery("SELECT t FROM Alert t WHERE t.type = :type")
                .setParameter("type", type)
                .list();
        for (Alert u : list) {
            if (u.getAddedBy().getCompanyId().equals(companyId)) {
                finalList.add(u);
            }
        }
        return finalList;
    }
    
    public List<Alert> getAllVisible(Short visibility, Short type, int id) {
        List<Alert> finalList = new ArrayList<Alert>();
        
        Session session = this.sessionFactory.getCurrentSession();
        Company companyId = (Company) session.get(Company.class, id);
        
        List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                + "WHERE t.visibility = :visibility and t.type = :type")
                .setParameter("visibility", visibility)
                .setParameter("type", type).list();
        for (Alert u : list) {
            if (u.getAddedBy().getCompanyId().equals(companyId)) {
                finalList.add(u);
            }
        }
        return finalList;
    }
    
    public List<Alert> alertNotificationForAdmin(Short visibility, Short type, int id) {
        List<Alert> finalList = new ArrayList<Alert>();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Date today = new Date();
        
        Date todayWithZeroTime;
        try {
            todayWithZeroTime = formatter.parse(formatter.format(today));
            
            System.out.println("date" + today);
            System.out.println("todayWithZeroTime" + todayWithZeroTime);
            
            Session session = this.sessionFactory.getCurrentSession();
            Company companyId = (Company) session.get(Company.class, id);
            List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                    + "WHERE t.visibility = :visibility and t.type = :type and Date(t.creationDate) = :todayWithZeroTime")
                    .setParameter("visibility", visibility)
                    .setParameter("todayWithZeroTime", todayWithZeroTime)
                    .setParameter("type", type).list();
            for (Alert u : list) {
                if ((u.getAddedBy().getCompanyId().equals(companyId)) && (u.getAddedBy().getAccessLevel().equals("ROLE_ADMIN"))) {
                    finalList.add(u);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(AlertDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return finalList;
    }
    
    public List<Alert> getNotSeenAlerts(int id) {
        List<Alert> finalList = new ArrayList<Alert>();
        
        Session session = this.sessionFactory.getCurrentSession();
        Company companyId = (Company) session.get(Company.class, id);
        
        List<Alert> list = session.createQuery("SELECT t FROM Alert t "
                + "WHERE t.seen = 0")
                .list();
        for (Alert u : list) {
            if ((u.getAddedBy().getCompanyId().equals(companyId))) {
                finalList.add(u);
            }
        }
        return finalList;
    }
}
