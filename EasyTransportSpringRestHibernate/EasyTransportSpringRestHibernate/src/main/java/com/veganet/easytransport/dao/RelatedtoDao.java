/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.dao;

import com.veganet.easytransport.entities.Relatedto;
import org.springframework.stereotype.Repository;

/**
 *
 * @author asus
 */
@Repository
public class RelatedtoDao extends AbstractHibernateDao<Relatedto>{

    public RelatedtoDao() {
        setClazz(Relatedto.class);
    }
}
