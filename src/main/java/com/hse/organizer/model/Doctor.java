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

    public Doctor(){}

    public Doctor(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

}
