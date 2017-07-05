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
@Table(name = "station")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Station.findAll", query = "SELECT s FROM Station s"),
    @NamedQuery(name = "Station.findByStationId", query = "SELECT s FROM Station s WHERE s.stationId = :stationId"),
    @NamedQuery(name = "Station.findByStationName", query = "SELECT s FROM Station s WHERE s.stationName = :stationName"),
    @NamedQuery(name = "Station.findByAddress", query = "SELECT s FROM Station s WHERE s.address = :address"),
    @NamedQuery(name = "Station.findByAltitude", query = "SELECT s FROM Station s WHERE s.altitude = :altitude"),
    @NamedQuery(name = "Station.findByLongitude", query = "SELECT s FROM Station s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "Station.findByLatitude", query = "SELECT s FROM Station s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "Station.findByCreationDate", query = "SELECT s FROM Station s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "Station.findByIsdeleted", query = "SELECT s FROM Station s WHERE s.isdeleted = :isdeleted")})
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STATION_ID")
    private Integer stationId;
    @Size(max = 254)
    @Column(name = "STATION_NAME")
    private String stationName;
    @Size(max = 254)
    @Column(name = "ADDRESS")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALTITUDE")
    private Double altitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "ISDELETED")
    private Short isdeleted;

    @Column(name = "TYPE")
    private Short type;

    @JsonIgnore
    @OneToMany(mappedBy = "stationStartId")
    private Collection<Journey> journeyCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "stationEndId")
    private Collection<Journey> journeyCollection1;

    @JsonIgnore
    @OneToMany(mappedBy = "stationId")
    private Collection<Passage> passageCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "stationId")
    private Collection<Relatedto> relatedtoCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "stationId")
    private Collection<Alert> alertCollection;

    @JoinColumn(name = "ADDED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private User addedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "stationId")
    private Collection<Favorite> favoriteCollection;

    public Station() {
    }

    public Station(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }

    @JsonIgnore
    public Collection<Journey> getJourneyCollection() {
        return journeyCollection;
    }

    public void setJourneyCollection(Collection<Journey> journeyCollection) {
        this.journeyCollection = journeyCollection;
    }

    @JsonIgnore
    public Collection<Journey> getJourneyCollection1() {
        return journeyCollection1;
    }

    @JsonIgnore
    public void setJourneyCollection1(Collection<Journey> journeyCollection1) {
        this.journeyCollection1 = journeyCollection1;
    }

    @JsonIgnore
    public Collection<Passage> getPassageCollection() {
        return passageCollection;
    }

    @JsonIgnore
    public void setPassageCollection(Collection<Passage> passageCollection) {
        this.passageCollection = passageCollection;
    }

    @JsonIgnore
    public Collection<Relatedto> getRelatedtoCollection() {
        return relatedtoCollection;
    }

    @JsonIgnore
    public void setRelatedtoCollection(Collection<Relatedto> relatedtoCollection) {
        this.relatedtoCollection = relatedtoCollection;
    }

    @JsonIgnore
    public Collection<Alert> getAlertCollection() {
        return alertCollection;
    }

    @JsonIgnore
    public void setAlertCollection(Collection<Alert> alertCollection) {
        this.alertCollection = alertCollection;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @JsonIgnore
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
        hash += (stationId != null ? stationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Station)) {
            return false;
        }
        Station other = (Station) object;
        if ((this.stationId == null && other.stationId != null) || (this.stationId != null && !this.stationId.equals(other.stationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.Station[ stationId=" + stationId + " ]";
    }

}
