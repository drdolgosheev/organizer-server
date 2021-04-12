package com.hse.organizer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class addDrugDto {
    String name;
    String barcode;
    String description;
    Date prodDate;
    Date expDate;
    Integer numOfPills;

    public addDrugDto(){}

    public addDrugDto(String name, String barcode, String description, Date prodDate,
                      Date expDate, Integer numOfPills) {
        this.name = name;
        this.barcode = barcode;
        this.description = description;
        this.prodDate = prodDate;
        this.expDate = expDate;
        this.numOfPills = numOfPills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
}
