/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "journeyplanning")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Journeyplanning.findAll", query = "SELECT j FROM Journeyplanning j"),
    @NamedQuery(name = "Journeyplanning.findByPlanningId", query = "SELECT j FROM Journeyplanning j WHERE j.planningId = :planningId")})
public class Journeyplanning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLANNING_ID")
    private Integer planningId;

    @JoinColumn(name = "JOURNEY_ID", referencedColumnName = "JOURNEY_ID")
    @ManyToOne
    private Journey journeyId;

    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private User userId;

    @Column(name = "TYPE")
    private Short type;

    public Journeyplanning() {
    }

    public Journeyplanning(Integer planningId) {
        this.planningId = planningId;
    }

    public Integer getPlanningId() {
        return planningId;
    }

    public void setPlanningId(Integer planningId) {
        this.planningId = planningId;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Journey journeyId) {
        this.journeyId = journeyId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
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
        if (!(object instanceof Journeyplanning)) {
            return false;
        }
        Journeyplanning other = (Journeyplanning) object;
        if ((this.planningId == null && other.planningId != null) || (this.planningId != null && !this.planningId.equals(other.planningId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Journeyplanning[ planningId=" + planningId + " ]";
    }

}
