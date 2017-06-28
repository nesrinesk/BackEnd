/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Journeylocalisation;
import java.util.List;

/**
 *
 * @author asus
 */
public interface JourneylocalisationDao {

    public List<Journeylocalisation> getAllByType(short type);

    public List<Journeylocalisation> getAllByLine(int id);
}
