/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "relatedto")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relatedto.findAll", query = "SELECT r FROM Relatedto r"),
    @NamedQuery(name = "Relatedto.findByRelatedToId", query = "SELECT r FROM Relatedto r WHERE r.relatedToId = :relatedToId"),
    @NamedQuery(name = "Relatedto.findByType", query = "SELECT r FROM Relatedto r WHERE r.type = :type"),
    @NamedQuery(name = "Relatedto.findByDatePassage", query = "SELECT r FROM Relatedto r WHERE r.datePassage = :datePassage")})
public class Relatedto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RELATED_TO_ID")
    private Integer relatedToId;
    
    @Column(name = "TYPE")
    private Short type;
    
    @Column(name = "DATE_PASSAGE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePassage;
    
    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationId;
    
    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    @ManyToOne
    private Line lineId;

    public Relatedto() {
    }

    public Relatedto(Integer relatedToId) {
        this.relatedToId = relatedToId;
    }

    public Integer getRelatedToId() {
        return relatedToId;
    }

    public void setRelatedToId(Integer relatedToId) {
        this.relatedToId = relatedToId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relatedToId != null ? relatedToId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relatedto)) {
            return false;
        }
        Relatedto other = (Relatedto) object;
        if ((this.relatedToId == null && other.relatedToId != null) || (this.relatedToId != null && !this.relatedToId.equals(other.relatedToId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Relatedto[ relatedToId=" + relatedToId + " ]";
    }
    
}

