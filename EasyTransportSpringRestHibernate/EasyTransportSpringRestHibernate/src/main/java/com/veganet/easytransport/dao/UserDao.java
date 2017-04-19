/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public UserDao() {
        setClazz(User.class);
    }

    // users not deleted (isdeleted=0) by access level 
    public List<User> getAllUsersByAccessLevel(short accessLevel, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.isdeleted = :isdeleted and u.accessLevel = :accessLevel ")
                .setParameter("isdeleted", isdeleted)
                .setParameter("accessLevel", accessLevel).list();
        return userList;
    }

    //add+ set isdeleted =0
    public User add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        user.setIsdeleted((short) 0);
        return user;
    }

    //users not deleted (having isDeleted=0)
    public List<User> getUsers(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.isdeleted = :isdeleted").setParameter("isdeleted", isdeleted).list();
        return userList;
    }

    // set isdeleted=1

    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        user.setIsdeleted((short) 1);

    }

    //update 

    public void update2(User object) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(object);
    }
}
