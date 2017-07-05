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
@Table(name = "transport")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transport.findAll", query = "SELECT t FROM Transport t"),
    @NamedQuery(name = "Transport.findByTransportId", query = "SELECT t FROM Transport t WHERE t.transportId = :transportId"),
    @NamedQuery(name = "Transport.findByName", query = "SELECT t FROM Transport t WHERE t.name = :name"),
    @NamedQuery(name = "Transport.findByStationStart", query = "SELECT t FROM Transport t WHERE t.stationStart = :stationStart"),
    @NamedQuery(name = "Transport.findByDelay", query = "SELECT t FROM Transport t WHERE t.delay = :delay"),
    @NamedQuery(name = "Transport.findByCreationDate", query = "SELECT t FROM Transport t WHERE t.creationDate = :creationDate"),
    @NamedQuery(name = "Transport.findByNumber", query = "SELECT t FROM Transport t WHERE t.number = :number"),
    @NamedQuery(name = "Transport.findBySpeedMax", query = "SELECT t FROM Transport t WHERE t.speedMax = :speedMax"),
    @NamedQuery(name = "Transport.findBySpeedAverage", query = "SELECT t FROM Transport t WHERE t.speedAverage = :speedAverage"),
    @NamedQuery(name = "Transport.findByCapacity", query = "SELECT t FROM Transport t WHERE t.capacity = :capacity"),
    @NamedQuery(name = "Transport.findByIsdeleted", query = "SELECT t FROM Transport t WHERE t.isdeleted = :isdeleted")})
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TRANSPORT_ID")
    private Integer transportId;

    @Size(max = 254)
    @Column(name = "NAME")
    private String name;

    @Size(max = 254)
    @Column(name = "STATION_START")
    private String stationStart;

    @Size(max = 254)
    @Column(name = "DELAY")
    private String delay;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Size(max = 50)
    @Column(name = "NUMBER")
    private String number;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SPEED_MAX")
    private Double speedMax;

    @Column(name = "SPEED_AVERAGE")
    private Double speedAverage;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "ISDELETED")
    private Short isdeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "transportId")
    private Collection<Journey> journeyCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "transportId")
    private Collection<Passage> passageCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "deviceId")
    private Collection<Positions> positionsCollection;

    @Column(name = "TYPE")
    private Short type;
    @JoinColumn(name = "ADDED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private User addedBy;
    @JoinColumn(name = "POSITION_ID", referencedColumnName = "id")
    @ManyToOne
    private Positions positionId;

    @JsonIgnore
    @OneToMany(mappedBy = "transportId")
    private Collection<Alert> alertCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "transportId")
    private Collection<Favorite> favoriteCollection;

    public Transport() {
    }

    public Transport(Integer transportId) {
        this.transportId = transportId;
    }

    public Integer getTransportId() {
        return transportId;
    }

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getStationStart() {
        return stationStart;
    }

    public void setStationStart(String stationStart) {
        this.stationStart = stationStart;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(Double speedMax) {
        this.speedMax = speedMax;
    }

    public Double getSpeedAverage() {
        return speedAverage;
    }

    public void setSpeedAverage(Double speedAverage) {
        this.speedAverage = speedAverage;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Journey> getJourneyCollection() {
        return journeyCollection;
    }

    @JsonIgnore
    public void setJourneyCollection(Collection<Journey> journeyCollection) {
        this.journeyCollection = journeyCollection;
    }

    @JsonIgnore
    @XmlTransient
    public Collection<Passage> getPassageCollection() {
        return passageCollection;
    }

    @JsonIgnore
    public void setPassageCollection(Collection<Passage> passageCollection) {
        this.passageCollection = passageCollection;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
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
    public Collection<Favorite> getFavoriteCollection() {
        return favoriteCollection;
    }

    @JsonIgnore
    public void setFavoriteCollection(Collection<Favorite> favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }

    @JsonIgnore
    public Collection<Positions> getPositionsCollection() {
        return positionsCollection;
    }

    @JsonIgnore
    public void setPositionsCollection(Collection<Positions> positionsCollection) {
        this.positionsCollection = positionsCollection;
    }

    public Positions getPositionId() {
        return positionId;
    }

    public void setPositionId(Positions positionId) {
        this.positionId = positionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transportId != null ? transportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transport)) {
            return false;
        }
        Transport other = (Transport) object;
        if ((this.transportId == null && other.transportId != null) || (this.transportId != null && !this.transportId.equals(other.transportId))) {
            return false;
        }
        return true;
    }

}
