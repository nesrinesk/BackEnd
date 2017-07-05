/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Journey;
import java.util.List;

/**
 *
 * @author asus
 */
public interface JourneyDao {

    public Journey add(Journey journey);

    public List<Journey> getAll(short isdeleted);

    public void delete2(int id);

    public void update2(Journey object);

   // public List<Journey> getAllByType(short type, short isdeleted);

    public String linesOfJourney(int journeyId);
}
