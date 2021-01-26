package com.hse.organizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Simple domain object that represents application doctor.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Entity
@Table(name = "doctors")
@Data
public class Doctor extends BaseEntity{

    @Column(name = "doctor_name")
    String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_diagnosis",
            joinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "diagnosis_id", referencedColumnName = "id")})
    private List<Diagnosis> diagnosisList;

}
