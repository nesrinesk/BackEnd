/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Relatedto;
import java.util.List;

/**
 *
 * @author asus
 */
public interface RelatedtoDao {

    public List<Relatedto> getAllByType(short type);

    public List<Relatedto> getAllByLine(int id);

    public Relatedto getlaststation(short tag, int id);
}
