package com.hse.organizer.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Simple domain object that represents application user.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Entity
@Table(name = "drugs")
@Data
public class Drug extends BaseEntity{

    @Column(name = "drugName")
    String name;

    @Column(name = "drugDescription")
    String description;

    @Column(name = "drugProdDate")
    Date prodDate;

    @Column(name = "drugExpDate")
    Date expDate;

    @Column(name = "numOfPills")
    Integer numOfPills;

    @Column(name = "numOfPillsPerDay")
    Integer numOfPillsPerDay;

    @Column(name = "startTakePillsTime")
    Date startTakePillsTime;

    // Number must take every day
    @Column(name = "takePillsInterval")
    Integer takePillsInterval;

    @ManyToMany(mappedBy = "medKit", fetch = FetchType.LAZY)
    private List<User> users;
}
