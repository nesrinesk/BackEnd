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
@Table(name = "journey")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journey.findAll", query = "SELECT j FROM Journey j"),
    @NamedQuery(name = "Journey.findByJourneyId", query = "SELECT j FROM Journey j WHERE j.journeyId = :journeyId"),
    @NamedQuery(name = "Journey.findByJourneyName", query = "SELECT j FROM Journey j WHERE j.journeyName = :journeyName"),
    @NamedQuery(name = "Journey.findByDateEnd", query = "SELECT j FROM Journey j WHERE j.dateEnd = :dateEnd"),
    @NamedQuery(name = "Journey.findByDuration", query = "SELECT j FROM Journey j WHERE j.duration = :duration"),
    @NamedQuery(name = "Journey.findByDelay", query = "SELECT j FROM Journey j WHERE j.delay = :delay"),
    @NamedQuery(name = "Journey.findByDateStart", query = "SELECT j FROM Journey j WHERE j.dateStart = :dateStart"),
    @NamedQuery(name = "Journey.findByVisibility", query = "SELECT j FROM Journey j WHERE j.visibility = :visibility"),
    @NamedQuery(name = "Journey.findByIsdeleted", query = "SELECT j FROM Journey j WHERE j.isdeleted = :isdeleted")})
public class Journey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOURNEY_ID")
    private Integer journeyId;
    
    @Size(max = 254)
    @Column(name = "JOURNEY_NAME")
    private String journeyName;
    
    @Column(name = "DATE_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    
    @Size(max = 254)
    @Column(name = "DURATION")
    private String duration;
    
    @Size(max = 254)
    @Column(name = "DELAY")
    private String delay;
    
    @Column(name = "DATE_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    
    @Column(name = "VISIBILITY")
    private Short visibility;
    
    @Column(name = "ISDELETED")
    private Short isdeleted;
    
    @JsonIgnore
    @OneToMany(mappedBy = "journeyId")
    private Collection<Journeylocalisation> journeylocalisationCollection;
    
    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;
    
    @JoinColumn(name = "STATION_START_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationStartId;
    
    @JoinColumn(name = "STATION_END_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationEndId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "journeyId")
    private Collection<Driverplanning> driverplanningCollection;
    
    @JsonIgnore
    @OneToMany(mappedBy = "journeyId")
    private Collection<Alert> alertCollection;
    
    @JsonIgnore
    @OneToMany(mappedBy = "journeyId")
    private Collection<Journeyplanning> journeyplanningCollection;
    
    @JsonIgnore
    @OneToMany(mappedBy = "journeyId")
    private Collection<Favorite> favoriteCollection;

    public Journey() {
    }

    public Journey(Integer journeyId) {
        this.journeyId = journeyId;
    }

    public Integer getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Integer journeyId) {
        this.journeyId = journeyId;
    }

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Short getVisibility() {
        return visibility;
    }

    public void setVisibility(Short visibility) {
        this.visibility = visibility;
    }

    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Journeylocalisation> getJourneylocalisationCollection() {
        return journeylocalisationCollection;
    }

    @JsonIgnore
    public void setJourneylocalisationCollection(Collection<Journeylocalisation> journeylocalisationCollection) {
        this.journeylocalisationCollection = journeylocalisationCollection;
    }

    public Transport getTransportId() {
        return transportId;
    }

    public void setTransportId(Transport transportId) {
        this.transportId = transportId;
    }

    public Station getStationStartId() {
        return stationStartId;
    }

    public void setStationStartId(Station stationStartId) {
        this.stationStartId = stationStartId;
    }

    public Station getStationEndId() {
        return stationEndId;
    }

    public void setStationEndId(Station stationEndId) {
        this.stationEndId = stationEndId;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Driverplanning> getDriverplanningCollection() {
        return driverplanningCollection;
    }

    @JsonIgnore
    public void setDriverplanningCollection(Collection<Driverplanning> driverplanningCollection) {
        this.driverplanningCollection = driverplanningCollection;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Alert> getAlertCollection() {
        return alertCollection;
    }

    @JsonIgnore
    public void setAlertCollection(Collection<Alert> alertCollection) {
        this.alertCollection = alertCollection;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Journeyplanning> getJourneyplanningCollection() {
        return journeyplanningCollection;
    }

    @JsonIgnore
    public void setJourneyplanningCollection(Collection<Journeyplanning> journeyplanningCollection) {
        this.journeyplanningCollection = journeyplanningCollection;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Favorite> getFavoriteCollection() {
        return favoriteCollection;
    }

    @JsonIgnore
    public void setFavoriteCollection(Collection<Favorite> favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (journeyId != null ? journeyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Journey)) {
            return false;
        }
        Journey other = (Journey) object;
        if ((this.journeyId == null && other.journeyId != null) || (this.journeyId != null && !this.journeyId.equals(other.journeyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Journey[ journeyId=" + journeyId + " ]";
    }
    
}
