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
 * Simple domain object that represents application drug.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Entity
@Table(name = "drugs")
@Data
public class Drug extends BaseEntity{

    @Column(name = "drugname")
    String name;

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

    @ManyToMany(mappedBy = "medKit", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(mappedBy = "drugList", fetch = FetchType.LAZY)
    private List<Diagnosis> diagnosisList;

}
