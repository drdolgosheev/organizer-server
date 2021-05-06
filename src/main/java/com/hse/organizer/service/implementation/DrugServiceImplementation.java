package com.hse.organizer.service.implementation;

import com.hse.organizer.dto.addDrugDto;
import com.hse.organizer.model.DateDrugs;
import com.hse.organizer.model.Drug;
import com.hse.organizer.model.Status;
import com.hse.organizer.model.User;
import com.hse.organizer.repository.DateDrugRepository;
import com.hse.organizer.repository.DrugRepository;
import com.hse.organizer.repository.RoleRepository;
import com.hse.organizer.repository.UserRepository;
import com.hse.organizer.service.DrugService;
import com.hse.organizer.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Implementation of {@link DrugService} interface.
 * Wrapper for {@link DrugRepository} + business logic.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@Service
@Slf4j
public class DrugServiceImplementation implements DrugService {

    private final DrugRepository drugRepository;
    private final UserRepository userRepository;
    private final DateDrugRepository dateDrugRepository;

    @Autowired
    public DrugServiceImplementation(DrugRepository drugRepository, UserRepository userRepository, DateDrugRepository dateDrugRepository) {
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
        this.dateDrugRepository = dateDrugRepository;
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
        drug_1.setNumOfPillsPerDay(drug.getNumOfPillsPerDay());
        drug_1.setTakePillsInterval(drug.getNumOfPillsPerDay());
        drug_1.setUserGroup(drug.getUserGroup());
        drug_1.setStartTakePillsTime(drug.getStartTakePillsTime());
        drug_1.setExpDate(drug.getExpDate());

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
        drug_1.setUserGroup(drug.getUserGroup());

        userRepository.save(user);
        drugRepository.save(drug_1);

        log.info("MedKit updated successfully");
    }

    /**
     * Method to get drug from DB
     * @param barcode drug barcode
     * @return Drug
     */
    @Override
    public Drug findDrugByBarCode(String barcode) {
        Drug drug = drugRepository.findByBarcode(barcode);
        return drug;
    }

    /**
     * Method to get drug take date, time
     * @param barcode drug barcode
     * @return List of dates
     */
    @SuppressWarnings("deprecation")
    @Override
    public List<DateDrugs> getDrugTakeTime(String barcode) {
        Drug drug = drugRepository.findByBarcode(barcode);

        List<DateDrugs> result = new ArrayList<>();
        int startTime = 8;
        int endTime = 22;

        int hoursInADay = endTime - startTime;

        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        df2.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));

        for (int i = 0; i < drug.getNumOfPillsPerDay(); i++) {
            Date locDate = new Date();
            DateDrugs dateDrugs = new DateDrugs();
            int step = hoursInADay/drug.getNumOfPillsPerDay();
            locDate.setHours(8+step*(i+1));
            dateDrugs.setDate(locDate);
            result.add(dateDrugs);
            log.info("Number: " + i + " Time: " + df2.format(dateDrugs.getDate()));
        }

        List<Drug> drugList = new ArrayList<>();
        drugList.add(drug);
        List<Long> idList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            result.get(i).setDrugListDate(drugList);
            result.get(i).setDrugId(drug.getId());
        }

        List<DateDrugs> tmp = dateDrugRepository.getAllByDrugId(drug.getId());
        if(tmp.isEmpty()) {
            for (int i = 0; i < result.size(); i++) {
                dateDrugRepository.save(result.get(i));
            }
        }

        List<DateDrugs> res = dateDrugRepository.getAllByDrugId(drug.getId());

        drug.setDateDrugsList(res);
        drugRepository.save(drug);

        log.info("IN DRUG SERVICE: " + drug.toString() + "\n WAS SAVED SUCCESSFULLY");
        return result;
    }

    /**
     * Recount number of pills left
     * @param barcode drug barcode
     * @return number of pills left
     */
    @SuppressWarnings("deprecation")
    @Override
    public Integer recountNumberOfPills(String barcode) {
        Drug drug = drugRepository.findByBarcode(barcode);

        if(drug == null)
            return null;

        List<DateDrugs> dateDrugs = getDrugTakeTime(barcode);

        int curNumOfPills = drug.getNumOfPills();

        Date curDate = new Date();

        for (int i = 0; i < dateDrugs.size(); i++) {
            if(drug.getUpdated().getDay() == curDate.getDay()) {
                return curNumOfPills;
            }else {
                Date takeDate = dateDrugs.get(i).getDate();
                if (curDate.after(takeDate)) {
                    curNumOfPills--;
                }
            }
        }

        drug.setNumOfPills(curNumOfPills);
        drug.setUpdated(curDate);

        drugRepository.save(drug);

        return curNumOfPills;
    }

    @Override
    public String getBarCodeByName(String name) {
        Drug drug = drugRepository.findByName(name);

        if(drug == null)
            return null;

        return drug.getBarcode();
    }
}
