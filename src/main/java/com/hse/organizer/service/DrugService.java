package com.hse.organizer.service;

import com.hse.organizer.model.Drug;

import java.util.List;

public interface DrugService {
    List<Drug> getAllDrugs();

    void addDrug(Drug drug);

    void addDrugToMedKit(Drug drug, String username);
}
