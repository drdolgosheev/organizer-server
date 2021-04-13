package com.hse.organizer.service.implementation;

import com.hse.organizer.dto.addDrugDto;
import com.hse.organizer.model.Drug;
import com.hse.organizer.model.Status;
import com.hse.organizer.model.User;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DrugServiceImplementation implements DrugService {

    private final DrugRepository drugRepository;
    private final UserRepository userRepository;

    @Autowired
    public DrugServiceImplementation(DrugRepository drugRepository, UserRepository userRepository) {
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    /**
     * Add drug to database
     * @param dto user input of drug info
     */
    @Override
    public void addDrug(addDrugDto dto) {
        Drug drug = new Drug();
        Date date = new Date();
        drug.setName(dto.getName());
        drug.setBarcode(dto.getBarcode());
        drug.setDescription(dto.getDescription());
        drug.setExpDate(dto.getExpDate());
        drug.setProdDate(dto.getProdDate());
        drug.setNumOfPills(dto.getNumOfPills());
        drug.setCreated(date);
        drug.setUpdated(date);
        drug.setStatus(Status.ACTIVE);

        drugRepository.save(drug);
        log.info("Drug added successfully");
    }

    /**
     * Method just for developers
     * @param drug drug
     * @param username username
     */
    @Override
    public void addDrugToMedKit(Drug drug, String username) {
        User user = userRepository.findByUsername(username);

        Drug drug_1 = drugRepository.findByBarcode(drug.getBarcode());

        if(drug_1 == null){
            drugRepository.save(drug);
        }
        drug_1 = drugRepository.findByBarcode(drug.getBarcode());

        List<Drug> userMedKit = user.getMedKit();

        if(userMedKit == null){
            userMedKit = new ArrayList<>();
        }

        userMedKit.add(drug_1);

        List<User> users = drug_1.getUsers();
        if(users == null) {
            users = new ArrayList<>();
        }

        user.setMedKit(userMedKit);
        users.add(user);
        drug_1.setUsers(users);

        userRepository.save(user);
        drugRepository.save(drug_1);

        log.info("MedKit updated successfully");
    }

    @Override
    public Drug findDrugByBarCode(String barcode) {
        Drug drug = drugRepository.findByBarcode(barcode);
        return drug;
    }
}
