package com.hse.organizer.dto;

import com.hse.organizer.model.Drug;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class getMedKitDto {
    List<DrugForShare> drugs = new ArrayList<>();

    public  getMedKitDto(){}

    public List<DrugForShare> transferDrugs(List<Drug> drugList) {
        for (int i = 0; i < drugList.size(); i++) {
            Drug cur_dr = drugList.get(i);
            DrugForShare cur_drug = new DrugForShare(cur_dr.getName(), cur_dr.getId(), cur_dr.getBarcode(),
                    cur_dr.getDescription(), cur_dr.getProdDate(), cur_dr.getExpDate(), cur_dr.getNumOfPills(),
                    cur_dr.getNumOfPillsPerDay(), cur_dr.getStartTakePillsTime(), cur_dr.getTakePillsInterval());
            this.drugs.add(cur_drug);
        }
        return this.drugs;
    }
}
