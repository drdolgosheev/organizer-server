package com.hse.organizer.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugForShare {
    String name;
    Long id;
    String barcode;
    String description;
    Date prodDate;
    Date expDate;
    Integer numOfPills;
    Integer numOfPillsPerDay;
    Date startTakePillsTime;
    Integer takePillsInterval;

    public DrugForShare(){}

    public DrugForShare(String name, Long id, String barcode, String description, Date prodDate, Date expDate,
                        Integer numOfPills, Integer numOfPillsPerDay, Date startTakePillsTime, Integer takePillsInterval) {
        this.name = name;
        this.id = id;
        this.barcode = barcode;
        this.description = description;
        this.prodDate = prodDate;
        this.expDate = expDate;
        this.numOfPills = numOfPills;
        this.numOfPillsPerDay = numOfPillsPerDay;
        this.startTakePillsTime = startTakePillsTime;
        this.takePillsInterval = takePillsInterval;
    }
}
