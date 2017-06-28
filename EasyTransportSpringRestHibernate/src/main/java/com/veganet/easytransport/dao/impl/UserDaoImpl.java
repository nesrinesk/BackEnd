/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao.impl;

import com.veganet.easytransport.dao.UserDao;
import com.veganet.easytransport.dao.impl.AbstractHibernateDao;
import com.veganet.easytransport.entities.User;
import java.util.List;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public UserDaoImpl() {
        setClazz(User.class);
    }

    // users not deleted (isdeleted=0) by access level 
    @Override
    public List<User> getAllUsersByAccessLevel(String accessLevel, short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.isdeleted = :isdeleted and u.accessLevel = :accessLevel ")
                .setParameter("isdeleted", isdeleted)
                .setParameter("accessLevel", accessLevel).list();
        return userList;
    }

    //add+ set isdeleted =0
    @Override
    public User add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        user.setIsdeleted((short) 0);
        user.setStatus((short) 1);
        session.persist(user);
        return user;
    }

    //users not deleted (having isDeleted=0)
    @Override
    public List<User> getUsers(short isdeleted) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.isdeleted = :isdeleted")
                .setParameter("isdeleted", isdeleted).list();
        return userList;
    }

    // set isdeleted=1
    @Override
    public void delete2(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        user.setIsdeleted((short) 1);

    }

    //update 
    @Override
    public void update2(User object) {
        Session session = this.sessionFactory.getCurrentSession();

        session.update(object);
    }

    @Override
    public User findByUserName(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        User object = (User) session.createQuery("SELECT u FROM User u WHERE u.userName = :userName")
                .setParameter("userName", userName).uniqueResult();
        return object;
    }

    @Override
    public User passwordForgotten(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("SELECT u FROM User u WHERE u.userName = :userName")
                .setParameter("userName", userName)
                .list();
        User object = userList.get(0);
        //generate pssw
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        System.out.println("saltStr" + saltStr);
        //
        object.setPassword(saltStr);
        System.out.println("password" + object.getPassword());

        System.out.println("done");
        return object;
    }
}
