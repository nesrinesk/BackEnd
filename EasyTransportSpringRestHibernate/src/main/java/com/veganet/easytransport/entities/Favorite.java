/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "favorite")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f"),
    @NamedQuery(name = "Favorite.findByFavoriteId", query = "SELECT f FROM Favorite f WHERE f.favoriteId = :favoriteId"),
    @NamedQuery(name = "Favorite.findByTitle", query = "SELECT f FROM Favorite f WHERE f.title = :title"),
    @NamedQuery(name = "Favorite.findByCreationDate", query = "SELECT f FROM Favorite f WHERE f.creationDate = :creationDate")})
public class Favorite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FAVORITE_ID")
    private Integer favoriteId;
    
    @Size(max = 254)
    @Column(name = "TITLE")
    private String title;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    @ManyToOne
    private Station stationId;
    
    @JoinColumn(name = "ADDEDED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private User addededBy;
    
    @JoinColumn(name = "JOURNEY_ID", referencedColumnName = "JOURNEY_ID")
    @ManyToOne
    private Journey journeyId;
    
    @JoinColumn(name = "TRANSPORT_ID", referencedColumnName = "TRANSPORT_ID")
    @ManyToOne
    private Transport transportId;

    public Favorite() {
    }

    public Favorite(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public User getAddededBy() {
        return addededBy;
    }

    public void setAddededBy(User addededBy) {
        this.addededBy = addededBy;
    }

    public Journey getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Journey journeyId) {
        this.journeyId = journeyId;
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
        hash += (favoriteId != null ? favoriteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.favoriteId == null && other.favoriteId != null) || (this.favoriteId != null && !this.favoriteId.equals(other.favoriteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.Favorite[ favoriteId=" + favoriteId + " ]";
    }
    
}
