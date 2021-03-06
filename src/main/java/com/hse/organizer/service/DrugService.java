package com.hse.organizer.service;

import com.hse.organizer.dto.addDrugDto;
import com.hse.organizer.model.DateDrugs;
import com.hse.organizer.model.Drug;

import java.util.List;

public interface DrugService {
    List<Drug> getAllDrugs();

    void addDrug(addDrugDto dto);

    void addDrugToMedKit(Drug drug, String username);

    Drug findDrugByBarCode(String barcode);

    List<DateDrugs> getDrugTakeTime(String barcode);

    Integer recountNumberOfPills(String barcode);

    String getBarCodeByName(String name);
}
