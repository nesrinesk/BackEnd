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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "user", catalog = "easytransportdb")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByAccessLevel", query = "SELECT u FROM User u WHERE u.accessLevel = :accessLevel"),
    @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByTel", query = "SELECT u FROM User u WHERE u.tel = :tel"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByBirthDate", query = "SELECT u FROM User u WHERE u.birthDate = :birthDate"),
    @NamedQuery(name = "User.findByCin", query = "SELECT u FROM User u WHERE u.cin = :cin"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByCreationDate", query = "SELECT u FROM User u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "User.findByIsdeleted", query = "SELECT u FROM User u WHERE u.isdeleted = :isdeleted")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;

    @Size(max = 254)
    @Column(name = "USER_NAME")
    private String userName;

    @Size(max = 254)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCESS_LEVEL")
    private String accessLevel;
    
    @Size(max = 254)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 254)
    @Column(name = "LAST_NAME")
    private String lastName;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 254)
    @Column(name = "TEL")
    private String tel;

    @Size(max = 254)
    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Size(max = 254)
    @Column(name = "CIN")
    private String cin;

    @Column(name = "GENDER")
    private Short gender;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "ISDELETED")
    private Short isdeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "addedBy")
    private Collection<Line> lineCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "addedBy")
    private Collection<Transport> transportCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private Collection<Driverplanning> driverplanningCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "addedBy")
    private Collection<Alert> alertCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private Collection<Journeyplanning> journeyplanningCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "addedBy")
    private Collection<Station> stationCollection;

    @JsonIgnore
    @OneToMany(mappedBy = "addededBy")
    private Collection<Favorite> favoriteCollection;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

   
    
    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
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
    @XmlTransient
    public Collection<Line> getLineCollection() {
        return lineCollection;
    }

    @JsonIgnore
    public void setLineCollection(Collection<Line> lineCollection) {
        this.lineCollection = lineCollection;
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
    public Collection<Station> getStationCollection() {
        return stationCollection;
    }

    @JsonIgnore
    public void setStationCollection(Collection<Station> stationCollection) {
        this.stationCollection = stationCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.veganet.easytransport.mavenproject5.User[ userId=" + userId + " ]";
    }

}
