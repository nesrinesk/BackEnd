/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.CompanyDao;
import com.veganet.easytransport.entities.Company;
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
public class CompanyDaoImpl extends AbstractHibernateDao<Company> implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public CompanyDaoImpl() {
        setClazz(Company.class);
    }

    public Company add(Company c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
        session.flush();

        return c;
    }

    public List<Company> getCompanies() {
        // short isdeleted = (short) 0;
        Session session = this.sessionFactory.getCurrentSession();
        List<Company> list = session.createQuery("SELECT u FROM Company u WHERE u.isdeleted = 0")
                .list();
        return list;
    }

    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Company company = (Company) session.get(Company.class, id);
        company.setIsdeleted((short) 1);

    }
    
}
