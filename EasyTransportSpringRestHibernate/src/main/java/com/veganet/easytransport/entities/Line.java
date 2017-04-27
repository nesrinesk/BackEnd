/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "line")
@NamedQueries({
    @NamedQuery(name = "Line.findAll", query = "SELECT l FROM Line l"),
    @NamedQuery(name = "Line.findByLineId", query = "SELECT l FROM Line l WHERE l.lineId = :lineId"),
    @NamedQuery(name = "Line.findByType", query = "SELECT l FROM Line l WHERE l.type = :type"),
    @NamedQuery(name = "Line.findByIsdeleted", query = "SELECT l FROM Line l WHERE l.isdeleted = :isdeleted")})
public class Line implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LINE_ID")
    private Integer lineId;
    @Column(name = "TYPE")
    private Short type;
    @Column(name = "ISDELETED")
    private Short isdeleted;
    @OneToMany(mappedBy = "lineId")
    private Collection<Journeylocalisation> journeylocalisationCollection;
    @JoinColumn(name = "ADDED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private User addedBy;
    @OneToMany(mappedBy = "lineId")
    private Collection<Relatedto> relatedtoCollection;
    @OneToMany(mappedBy = "lineId")
    private Collection<Alert> alertCollection;

    public Line() {
    }

    public Line(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }

    @XmlTransient
    public Collection<Journeylocalisation> getJourneylocalisationCollection() {
        return journeylocalisationCollection;
    }

    public void setJourneylocalisationCollection(Collection<Journeylocalisation> journeylocalisationCollection) {
        this.journeylocalisationCollection = journeylocalisationCollection;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @XmlTransient
    public Collection<Relatedto> getRelatedtoCollection() {
        return relatedtoCollection;
    }

    public void setRelatedtoCollection(Collection<Relatedto> relatedtoCollection) {
        this.relatedtoCollection = relatedtoCollection;
    }

    @XmlTransient
    public Collection<Alert> getAlertCollection() {
        return alertCollection;
    }

    public void setAlertCollection(Collection<Alert> alertCollection) {
        this.alertCollection = alertCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineId != null ? lineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Line)) {
            return false;
        }
        Line other = (Line) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.Line[ lineId=" + lineId + " ]";
    }
    
}