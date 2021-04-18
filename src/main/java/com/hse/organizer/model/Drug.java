package com.hse.organizer.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Simple domain object that represents application drug.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Entity
@Table(name = "drugs")
public class Drug extends BaseEntity {

    @Column(name = "drugname")
    String name;

    @Column(name = "barcode")
    String barcode;

    @Column(name = "drug_description")
    String description;

    @Column(name = "drug_prod_date")
    Date prodDate;

    @Column(name = "drug_exp_date")
    Date expDate;

    @Column(name = "num_of_pills")
    Integer numOfPills;

    @Column(name = "num_of_pills_per_day")
    Integer numOfPillsPerDay;

    @Column(name = "start_take_pills_time")
    Date startTakePillsTime;

    // Number must take every day
    @Column(name = "take_pills_interval")
    Integer takePillsInterval;

    @Column(name = "user_group")
    private String userGroup;

    @ManyToMany(mappedBy = "medKit", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(mappedBy = "drugList", fetch = FetchType.LAZY)
    private List<Diagnosis> diagnosisList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "drugs_take_time",
            joinColumns = {@JoinColumn(name = "take_date_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "drug_id", referencedColumnName = "id")})
    private List<DateDrugs> dateDrugsList;

    public Drug() {}

    public Drug(String name, String barcode, String description, Date prodDate, Date expDate, Integer numOfPills, Integer numOfPillsPerDay, Date startTakePillsTime, Integer takePillsInterval, String userGroup, List<DateDrugs> dateDrugsList) {
        this.name = name;
        this.barcode = barcode;
        this.description = description;
        this.prodDate = prodDate;
        this.expDate = expDate;
        this.numOfPills = numOfPills;
        this.numOfPillsPerDay = numOfPillsPerDay;
        this.startTakePillsTime = startTakePillsTime;
        this.takePillsInterval = takePillsInterval;
        this.userGroup = userGroup;
        this.dateDrugsList = dateDrugsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Integer getNumOfPills() {
        return numOfPills;
    }

    public void setNumOfPills(Integer numOfPills) {
        this.numOfPills = numOfPills;
    }

    public Integer getNumOfPillsPerDay() {
        return numOfPillsPerDay;
    }

    public void setNumOfPillsPerDay(Integer numOfPillsPerDay) {
        this.numOfPillsPerDay = numOfPillsPerDay;
    }

    public Date getStartTakePillsTime() {
        return startTakePillsTime;
    }

    public void setStartTakePillsTime(Date startTakePillsTime) {
        this.startTakePillsTime = startTakePillsTime;
    }

    public Integer getTakePillsInterval() {
        return takePillsInterval;
    }

    public void setTakePillsInterval(Integer takePillsInterval) {
        this.takePillsInterval = takePillsInterval;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public List<DateDrugs> getDateDrugsList() {
        return dateDrugsList;
    }

    public void setDateDrugsList(List<DateDrugs> dateDrugsList) {
        this.dateDrugsList = dateDrugsList;
    }
}
