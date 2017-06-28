/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Line;
import java.util.List;

/**
 *
 * @author asus
 */
public interface LineDao {

    public Line add(Line line);

    public List<Line> getAll(short isdeleted);

    public void delete2(int id);

    public void update2(Line object);

    public List<Line> getAllByType(short type, short isdeleted);

}
