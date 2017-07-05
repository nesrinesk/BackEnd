
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
@Table(name = "journeylocalisation")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journeylocalisation.findAll", query = "SELECT j FROM Journeylocalisation j"),
    @NamedQuery(name = "Journeylocalisation.findByJourneyLocalisationId", query = "SELECT j FROM Journeylocalisation j WHERE j.journeyLocalisationId = :journeyLocalisationId"),
    @NamedQuery(name = "Journeylocalisation.findByDate", query = "SELECT j FROM Journeylocalisation j WHERE j.date = :date")})
public class Journeylocalisation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOURNEY_LOCALISATION_ID")
    private Integer journeyLocalisationId;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    @ManyToOne
    private Line lineId;
    @Column(name = "TYPE")
    private Short type;
    @JoinColumn(name = "JOURNEY_ID", referencedColumnName = "JOURNEY_ID")
    @ManyToOne
    private Journey journeyId;

    public Journeylocalisation() {
    }

    public Journeylocalisation(Integer journeyLocalisationId) {
        this.journeyLocalisationId = journeyLocalisationId;
    }

    public Integer getJourneyLocalisationId() {
        return journeyLocalisationId;
    }

    public void setJourneyLocalisationId(Integer journeyLocalisationId) {
        this.journeyLocalisationId = journeyLocalisationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Journey journeyId) {
        this.journeyId = journeyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (journeyLocalisationId != null ? journeyLocalisationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Journeylocalisation)) {
            return false;
        }
        Journeylocalisation other = (Journeylocalisation) object;
        if ((this.journeyLocalisationId == null && other.journeyLocalisationId != null) || (this.journeyLocalisationId != null && !this.journeyLocalisationId.equals(other.journeyLocalisationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Journeylocalisation[ journeyLocalisationId=" + journeyLocalisationId + " ]";
    }

}
