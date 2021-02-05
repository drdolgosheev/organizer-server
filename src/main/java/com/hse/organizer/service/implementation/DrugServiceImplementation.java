package com.hse.organizer.service.implementation;

import com.hse.organizer.model.Drug;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DrugServiceImplementation implements DrugService {

    private final DrugRepository drugRepository;

    @Autowired
    public DrugServiceImplementation(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }
}
