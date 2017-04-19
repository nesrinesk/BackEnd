/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.PositionDao;
import com.veganet.easytransport.entities.Position;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("positionService")
public class PositionService {

    @Autowired
    PositionDao positionDao;

    @Transactional
    public List<Position> findAll() {
        return positionDao.findAll();
    }

    @Transactional
    public Position findOne(int id) {
        return positionDao.findOne(id);
    }

    @Transactional
    public void create(Position position) {
        positionDao.create(position);
    }

    @Transactional
    public void update(Position position) {
        positionDao.update(position);

    }

    @Transactional
    public void deleteById(int id) {
        positionDao.deleteById(id);
    }

    /////////
//isdeleted
    @Transactional
    public void delete2(int id) {
        positionDao.delete2(id);
    }

    @Transactional
    public void add(Position position) {
        positionDao.add(position);
    }

    @Transactional
    public List<Position> getAll(short isdeleted) {
        return positionDao.getAll(isdeleted);
    }
}
