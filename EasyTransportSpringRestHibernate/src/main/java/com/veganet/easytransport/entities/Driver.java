/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "driver")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.findByDriverId", query = "SELECT d FROM Driver d WHERE d.driverId = :driverId")})
public class Driver implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DRIVER_ID")
    private Integer driverId;
    
    @JsonIgnore
    @OneToMany(mappedBy = "driverId")
    private Collection<Driverplanning> driverplanningCollection;
    
    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;

    public Driver() {
    }

    public Driver(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
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

    public Transport getTransportId() {
        return transportId;
    }

    public void setTransportId(Transport transportId) {
        this.transportId = transportId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (driverId != null ? driverId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.driverId == null && other.driverId != null) || (this.driverId != null && !this.driverId.equals(other.driverId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Driver[ driverId=" + driverId + " ]";
    }
    
}
