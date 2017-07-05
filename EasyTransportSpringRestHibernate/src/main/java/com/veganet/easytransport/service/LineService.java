/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.LineDaoImpl;
import com.veganet.easytransport.entities.Line;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("lineService")
public class LineService {

    @Autowired
    LineDaoImpl lineDao;

    @Transactional
    public List<Line> findAll() {
        return lineDao.findAll();
    }

    @Transactional
    public Line findOne(int id) {
        return lineDao.findOne(id);
    }

    @Transactional
    public void create(Line line) {
        lineDao.create(line);
    }

    @Transactional
    public void update2(Line line) {
        lineDao.update(line);

    }

    @Transactional
    public void deleteById(int id) {
        lineDao.deleteById(id);
    }

    //isdeleted
    @Transactional
    public void delete2(int id) {
        lineDao.delete2(id);
    }

    @Transactional
    public void add(Line line) {
        lineDao.add(line);
    }

    @Transactional
    public List<Line> getAll(short isdeleted) {
        return lineDao.getAll(isdeleted);
    }

    @Transactional
    public List<Line> getAllByType(short type, short isdeleted) {
        return lineDao.getAllByType(type, isdeleted);
    }

    @Transactional
    public List<Line> getAllByAdmin(short type, int adminId) {
        return lineDao.getAllByAdmin(type, adminId);
    }
    // public int getLastInsertedId(){
    /*@Transactional
     public BigInteger getLastInsertedId() {
     return lineDao.getLastInsertedId();
     }*/
}
