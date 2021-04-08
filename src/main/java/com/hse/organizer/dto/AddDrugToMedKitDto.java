package com.hse.organizer.dto;

import com.hse.organizer.model.Drug;

public class AddDrugToMedKitDto {
    String username;
    Drug drug;

    public AddDrugToMedKitDto(){}

    public AddDrugToMedKitDto(String username, Drug drug) {
        this.username = username;
        this.drug = drug;
    }

    public String getUsername() {
        return username;
    }

    public Drug getDrug() {
        return drug;
    }
}
