package com.hse.organizer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hse.organizer.model.Status;
import com.hse.organizer.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugFullDto {
    Long id;
    Date created;
    Date updated;
    String status = Status.ACTIVE.toString();
    String name;
    String barcode;
    String description;
    Date prodDate;
    Date expDate;
    Integer numOfPills;
    Integer numOfPillsPerDay;
    Date startTakePillsTime;
    Integer takePillsInterval;

    public DrugFullDto(){}

    public DrugFullDto(Long id,Date created, Date updated, String status, String name, String barcode,
                       String description, Date prodDate, Date expDate,
                       Integer numOfPills, Integer numOfPillsPerDay, Date startTakePillsTime,
                       Integer takePillsInterval) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.status = status;
        this.name = name;
        this.barcode = barcode;
        this.description = description;
        this.prodDate = prodDate;
        this.expDate = expDate;
        this.numOfPills = numOfPills;
        this.numOfPillsPerDay = numOfPillsPerDay;
        this.startTakePillsTime = startTakePillsTime;
        this.takePillsInterval = takePillsInterval;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public void setNumOfPills(Integer numOfPills) {
        this.numOfPills = numOfPills;
    }

    public void setNumOfPillsPerDay(Integer numOfPillsPerDay) {
        this.numOfPillsPerDay = numOfPillsPerDay;
    }

    public void setStartTakePillsTime(Date startTakePillsTime) {
        this.startTakePillsTime = startTakePillsTime;
    }

    public void setTakePillsInterval(Integer takePillsInterval) {
        this.takePillsInterval = takePillsInterval;
    }
}
