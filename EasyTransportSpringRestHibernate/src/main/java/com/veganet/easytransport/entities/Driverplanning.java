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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "driverplanning")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Driverplanning.findAll", query = "SELECT d FROM Driverplanning d"),
    @NamedQuery(name = "Driverplanning.findByPlanningId", query = "SELECT d FROM Driverplanning d WHERE d.planningId = :planningId"),
    @NamedQuery(name = "Driverplanning.findByDay", query = "SELECT d FROM Driverplanning d WHERE d.day = :day"),
    @NamedQuery(name = "Driverplanning.findByDate", query = "SELECT d FROM Driverplanning d WHERE d.date = :date")})
public class Driverplanning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLANNING_ID")
    private Integer planningId;
    @Size(max = 256)
    @Column(name = "DAY")
    private String day;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "TYPE")
    private Short type;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;
    @JoinColumn(name = "JOURNEY_ID", referencedColumnName = "JOURNEY_ID")
    @ManyToOne
    private Journey journeyId;

    @Transient
    private Date from;

    @Transient
    private Date to;

    public Driverplanning() {
    }

    public Driverplanning(Integer planningId) {
        this.planningId = planningId;
    }

    public Integer getPlanningId() {
        return planningId;
    }

    public void setPlanningId(Integer planningId) {
        this.planningId = planningId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Journey journeyId) {
        this.journeyId = journeyId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planningId != null ? planningId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Driverplanning)) {
            return false;
        }
        Driverplanning other = (Driverplanning) object;
        if ((this.planningId == null && other.planningId != null) || (this.planningId != null && !this.planningId.equals(other.planningId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "x.Driverplanning[ planningId=" + planningId + " ]";
    }

}
