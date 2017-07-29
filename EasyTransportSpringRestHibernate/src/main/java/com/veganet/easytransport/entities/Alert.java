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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "alert")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alert.findAll", query = "SELECT a FROM Alert a"),
    @NamedQuery(name = "Alert.findByAlertId", query = "SELECT a FROM Alert a WHERE a.alertId = :alertId"),
    @NamedQuery(name = "Alert.findByTitle", query = "SELECT a FROM Alert a WHERE a.title = :title"),
    @NamedQuery(name = "Alert.findByStatus", query = "SELECT a FROM Alert a WHERE a.status = :status"),
    @NamedQuery(name = "Alert.findByCreationDate", query = "SELECT a FROM Alert a WHERE a.creationDate = :creationDate")})
public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ALERT_ID")
    private Integer alertId;
    @Size(max = 254)
    @Column(name = "TITLE")
    private String title;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private Short status;
     @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "TYPE")
    private Short type;
    @Column(name = "SEEN")
    private Short seen;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "VISIBILITY")
    private Short visibility;
    @JoinColumn(name = "JOURNEY_ID", referencedColumnName = "JOURNEY_ID")
    @ManyToOne
    private Journey journeyId;

    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationId;

    @JoinColumn(name = "ADDED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private User addedBy;

    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;

    @JoinColumn(name = "LINE_ID", referencedColumnName = "LINE_ID")
    @ManyToOne
    private Line lineId;

    public Alert() {
    }

    public Alert(Integer alertId) {
        this.alertId = alertId;
    }

    public Integer getAlertId() {
        return alertId;
    }

    public void setAlertId(Integer alertId) {
        this.alertId = alertId;
    }

    public Short getVisibility() {
        return visibility;
    }

    public void setVisibility(Short visibility) {
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getSeen() {
        return seen;
    }

    public void setSeen(Short seen) {
        this.seen = seen;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

      public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Journey journeyId) {
        this.journeyId = journeyId;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public Transport getTransportId() {
        return transportId;
    }

    public void setTransportId(Transport transportId) {
        this.transportId = transportId;
    }

    public Line getLineId() {
        return lineId;
    }

    public void setLineId(Line lineId) {
        this.lineId = lineId;
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
    

 

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alertId != null ? alertId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alert)) {
            return false;
        }
        Alert other = (Alert) object;
        if ((this.alertId == null && other.alertId != null) || (this.alertId != null && !this.alertId.equals(other.alertId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Alert[ alertId=" + alertId + " ]";
    }

}
