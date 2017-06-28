/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Transport;
import java.util.List;

/**
 *
 * @author asus
 */
public interface TransportDao {

    public Transport add(Transport t);

    public List<Transport> getAll(short isdeleted);

    public void delete2(int id);

    public void update2(Transport object);

    public List<Transport> getAllByType(short type, short isdeleted);
}
