package com.hse.organizer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Simple domain object that represents application diagnosis.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Entity
@Table(name = "diagnosis")
@Data
public class Diagnosis extends BaseEntity{

    @Column(name = "diagnosis_name")
    String name;

    @Column(name = "diagnosis_description")
    String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "diagnosis_drugs",
            joinColumns = {@JoinColumn(name = "diagnosis_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "drug_id", referencedColumnName = "id")})
    private List<Drug> drugList;

    @ManyToMany(mappedBy = "diagnosisList", fetch = FetchType.LAZY)
    private List<Doctor> doctorList;
}
