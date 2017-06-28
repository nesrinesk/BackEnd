/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Station;
import java.util.List;

/**
 *
 * @author asus
 */
public interface StationDao {

    public Station add(Station station);

    public List<Station> getAll(short isdeleted);

    public void delete2(int id);

    public void update2(Station object);

    public List<Station> getAllByType(short type, short isdeleted);
}
