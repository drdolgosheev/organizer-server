package com.hse.organizer.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "take_date")
public class DateDrugs extends BaseEntity{
    @Column(name = "date")
    Date date;

    @Column(name = "drug_id")
    Long drugId;

    @ManyToMany(mappedBy = "dateDrugsList", fetch = FetchType.LAZY)
    List<Drug> drugListDate;

    public DateDrugs() {}

    public List<Drug> getDrugListDate() {
        return drugListDate;
    }

    public void setDrugListDate(List<Drug> drugListDate) {
        this.drugListDate = drugListDate;
    }

    public DateDrugs(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    @Override
    public String toString() {
        return "DateDrugs{" +
                "date=" + date +
                ", drugId=" + drugId +
                ", drugListDate=" + drugListDate +
                '}';
    }
}
