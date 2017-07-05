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
@Table(name = "passage")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passage.findAll", query = "SELECT p FROM Passage p"),
    @NamedQuery(name = "Passage.findByPassageId", query = "SELECT p FROM Passage p WHERE p.passageId = :passageId"),
    @NamedQuery(name = "Passage.findByDate", query = "SELECT p FROM Passage p WHERE p.date = :date")})
public class Passage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PASSAGE_ID")
    private Integer passageId;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationId;
    @Column(name = "TYPE")
    private Short type;
    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;

    public Passage() {
    }

    public Passage(Integer passageId) {
        this.passageId = passageId;
    }

    public Integer getPassageId() {
        return passageId;
    }

    public void setPassageId(Integer passageId) {
        this.passageId = passageId;
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

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public Transport getTransportId() {
        return transportId;
    }

    public void setTransportId(Transport transportId) {
        this.transportId = transportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passageId != null ? passageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passage)) {
            return false;
        }
        Passage other = (Passage) object;
        if ((this.passageId == null && other.passageId != null) || (this.passageId != null && !this.passageId.equals(other.passageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Passage[ passageId=" + passageId + " ]";
    }

}
