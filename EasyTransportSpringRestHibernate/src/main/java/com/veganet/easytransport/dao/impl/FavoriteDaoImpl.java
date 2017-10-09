/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.FavoriteDao;
import com.veganet.easytransport.entities.Company;
import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Favorite;
import com.veganet.easytransport.entities.User;
import java.util.ArrayList;
import java.util.Date;
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
public class FavoriteDaoImpl extends AbstractHibernateDao<Favorite> implements FavoriteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public FavoriteDaoImpl() {
        setClazz(Favorite.class);
    }

    public List<Driverplanning> getAllByUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User userId = (User) session.get(User.class, id);
        List<Driverplanning> finalList = new ArrayList<Driverplanning>();
        List<Driverplanning> listPlannings = getAllByDate((short) 1, userId.getCompanyId().getCompanyId());
        Date date = new Date();
        List<Favorite> list = session.createQuery("SELECT r FROM Favorite r WHERE r.addededBy = :userId ")
                .setParameter("userId", userId).list();
        for (Favorite f : list) {
            for (Driverplanning d : listPlannings) {
                if ((f.getJourneyId().equals(d.getJourneyId()))&& (d.getDate().equals(d.getDate()))) {
                    finalList.add(d);
                }
            }
        }
        return finalList;
    }

    public List<Driverplanning> getAllByDate(Short type, int id) {
        List<Driverplanning> finalList = new ArrayList<Driverplanning>();
        Session session = this.sessionFactory.getCurrentSession();
        Company companyId = (Company) session.get(Company.class, id);

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        //String dateNow = dateFormat.format(date);
        List<Driverplanning> list = session.createQuery("SELECT u FROM Driverplanning u WHERE u.type = :type "
                + "and u.date >= :date order by u.date asc")
                .setParameter("date", date).setParameter("type", type)
                .list();
        for (Driverplanning u : list) {
            if (u.getUserId().getCompanyId().equals(companyId)) {
                finalList.add(u);
            }
        }
        return finalList;
    }
    //list get fav by userid for the current date
}
