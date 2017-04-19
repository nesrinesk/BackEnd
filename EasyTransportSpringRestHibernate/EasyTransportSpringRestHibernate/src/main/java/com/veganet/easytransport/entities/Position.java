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
@Table(name = "position")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findByPositionId", query = "SELECT p FROM Position p WHERE p.positionId = :positionId"),
    @NamedQuery(name = "Position.findByLongitude", query = "SELECT p FROM Position p WHERE p.longitude = :longitude"),
    @NamedQuery(name = "Position.findByLatitude", query = "SELECT p FROM Position p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Position.findByAltitude", query = "SELECT p FROM Position p WHERE p.altitude = :altitude"),
    @NamedQuery(name = "Position.findByCourse", query = "SELECT p FROM Position p WHERE p.course = :course"),
    @NamedQuery(name = "Position.findByOther", query = "SELECT p FROM Position p WHERE p.other = :other"),
    @NamedQuery(name = "Position.findByPower", query = "SELECT p FROM Position p WHERE p.power = :power"),
    @NamedQuery(name = "Position.findBySpeed", query = "SELECT p FROM Position p WHERE p.speed = :speed"),
    @NamedQuery(name = "Position.findByTime", query = "SELECT p FROM Position p WHERE p.time = :time"),
    @NamedQuery(name = "Position.findByValid", query = "SELECT p FROM Position p WHERE p.valid = :valid"),
    @NamedQuery(name = "Position.findByIsdeleted", query = "SELECT p FROM Position p WHERE p.isdeleted = :isdeleted")})
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "POSITION_ID")
    private Integer positionId;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LONGITUDE")
    private Double longitude;
    
    @Column(name = "LATITUDE")
    private Double latitude;
    
    @Column(name = "ALTITUDE")
    private Double altitude;
    
    @Column(name = "COURSE")
    private Double course;
    
    @Size(max = 255)
    @Column(name = "OTHER")
    private String other;
    
    @Column(name = "POWER")
    private Double power;
    
    @Column(name = "SPEED")
    private Double speed;
    
    @Column(name = "TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    
    @Column(name = "VALID")
    private Short valid;
    
    @Column(name = "ISDELETED")
    private Short isdeleted;
    
    @JsonIgnore
    @OneToMany(mappedBy = "positionId")
    private Collection<Transport> transportCollection;
    
    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;

    public Position() {
    }

    public Position(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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

    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
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

    public Transport getTransportId() {
        return transportId;
    }

    public void setTransportId(Transport transportId) {
        this.transportId = transportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Position[ positionId=" + positionId + " ]";
    }
    
}
