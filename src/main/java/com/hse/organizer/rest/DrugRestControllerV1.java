package com.hse.organizer.rest;

import com.hse.organizer.dto.*;
import com.hse.organizer.model.DateDrugs;
import com.hse.organizer.model.Drug;
import com.hse.organizer.modules.implementation.DrugCodeValidatorImplementation;
import com.hse.organizer.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller drug connected requests.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api/v1/drug/")
public class DrugRestControllerV1 {
    private final DrugService drugService;

    @Autowired
    public DrugRestControllerV1(DrugService drugService) {
        this.drugService = drugService;
    }

    /**
     * @return Return all drugs which are in DB
     */
    @GetMapping(value = "get/all")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        List<Drug> result = drugService.getAllDrugs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @param code drug barcode
     * @return Boolean validation result
     */
    @GetMapping(value = "validate/{code}")
    public ResponseEntity<Boolean> validateDrug(@PathVariable(name = "code") String code) {
        DrugCodeValidatorImplementation validator = new DrugCodeValidatorImplementation();
        Boolean result = validator.validate(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @param dto Drug information
     * @return String "OK"
     */
    @PostMapping("addDrug")
    public ResponseEntity addDrug(@RequestBody addDrugDto dto){
        drugService.addDrug(dto);
        return ResponseEntity.ok("OK");
    }

    /**
     * @param dto Drug information and user Username
     * @return String "OK"
     */
    @PostMapping("addDrugToMedKit")
    public ResponseEntity addDrugToMedKit(@RequestBody AddDrugToMedKitDto dto) {
        drugService.addDrugToMedKit(dto.getDrug(), dto.getUsername());
        return ResponseEntity.ok("OK");
    }

    /**
     * @param barcode drug barcode
     * @return Drug
     */
    @GetMapping("getDrugByBarcode/{code}")
    public ResponseEntity<DrugFullDto> getDrugByBarCode(@PathVariable(name = "code") String barcode){
        Drug drug = drugService.findDrugByBarCode(barcode);
        DrugFullDto dto = new DrugFullDto(drug.getId(),drug.getCreated(), drug.getUpdated(), drug.getStatus().toString(),
                drug.getName(),drug.getBarcode(), drug.getDescription(),
                drug.getProdDate(), drug.getExpDate(), drug.getNumOfPills(), drug.getNumOfPillsPerDay(),
                drug.getStartTakePillsTime(),drug.getTakePillsInterval());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * @param barcode drug barcode
     * @return List of DataTime, to take pills
     */
    @GetMapping("getDrugsTakeTime/{code}")
    public  ResponseEntity<TakeTimeDto> gerDrugsTakeTime(@PathVariable(name = "code") String barcode){
        List<DateDrugs> result = drugService.getDrugTakeTime(barcode);
        TakeTimeDto dto = new TakeTimeDto(result);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Recount number of pills
     * @param barcode drug barcode
     * @return Amount of pills left
     */
    @GetMapping("recountNumberOfPills/{code}")
    public  ResponseEntity<IntegerDto> recountNumberOfPills(@PathVariable(name = "code") String barcode){
        Integer result = drugService.recountNumberOfPills(barcode);
        IntegerDto dto = new IntegerDto();
        dto.setNumber(result);
        return ResponseEntity.ok(dto);
    }
}
