package com.hse.organizer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hse.organizer.model.DateDrugs;
import com.hse.organizer.model.Drug;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TakeTimeDto {
    List<Date> date = new ArrayList<>();

    public TakeTimeDto(){}

    public TakeTimeDto(List<DateDrugs> date) {
        for (int i = 0; i < date.size(); i++) {
            this.date.add(date.get(i).getDate());
        }
    }

    public List<Date> getDate() {
        return date;
    }

    public void setDate(List<Date> date) {
        this.date = date;
    }
}
