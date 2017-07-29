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
@Table(name = "positions")
//@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Positions.findAll", query = "SELECT p FROM Positions p"),
    @NamedQuery(name = "Positions.findById", query = "SELECT p FROM Positions p WHERE p.id = :id"),
    @NamedQuery(name = "Positions.findByLongitude", query = "SELECT p FROM Positions p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Positions.findByLatitude", query = "SELECT p FROM Positions p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Positions.findByAltitude", query = "SELECT p FROM Positions p WHERE p.altitude = :altitude"),
    @NamedQuery(name = "Positions.findByCourse", query = "SELECT p FROM Positions p WHERE p.course = :course"),
    @NamedQuery(name = "Positions.findByOther", query = "SELECT p FROM Positions p WHERE p.other = :other"),
    @NamedQuery(name = "Positions.findByPower", query = "SELECT p FROM Positions p WHERE p.power = :power"),
    @NamedQuery(name = "Positions.findBySpeed", query = "SELECT p FROM Positions p WHERE p.speed = :speed"),
    @NamedQuery(name = "Positions.findByTime", query = "SELECT p FROM Positions p WHERE p.time = :time"),
    @NamedQuery(name = "Positions.findByValid", query = "SELECT p FROM Positions p WHERE p.valid = :valid"),
    @NamedQuery(name = "Positions.findLastPosition", query = "SELECT p FROM Positions p WHERE p.deviceId = :deviceId ORDER BY p.id ASC")})
public class Positions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "altitude")
    private Double altitude;
    @Column(name = "course")
    private Double course;
    @Column(name = "other")
    private String other;
    @Column(name = "POWER")
    private Double power;
    @Column(name = "speed")
    private Double speed;
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "valid")
    private Short valid;
    @JoinColumn(name = "device_id", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport deviceId;
    @JsonIgnore
    @OneToMany(mappedBy = "positionId")
    private Collection<Transport> transportCollection;

    public Positions() {
    }

    public Positions(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    public Transport getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Transport deviceId) {
        this.deviceId = deviceId;
    }
@JsonIgnore
    @XmlTransient
    public Collection<Transport> getTransportCollection() {
        return transportCollection;
    }
@JsonIgnore

    public void setTransportCollection(Collection<Transport> transportCollection) {
        this.transportCollection = transportCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Positions)) {
            return false;
        }
        Positions other = (Positions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

 
    
}
