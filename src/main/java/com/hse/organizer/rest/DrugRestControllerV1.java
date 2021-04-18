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

@RestController
@RequestMapping(value = "/api/v1/drug/")
public class DrugRestControllerV1 {
    private final DrugService drugService;

    @Autowired
    public DrugRestControllerV1(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping(value = "get/all")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        List<Drug> result = drugService.getAllDrugs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "validate/{code}")
    public ResponseEntity<Boolean> validateDrug(@PathVariable(name = "code") String code) {
        DrugCodeValidatorImplementation validator = new DrugCodeValidatorImplementation();
        Boolean result = validator.validate(code);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("addDrug")
    public ResponseEntity addDrug(@RequestBody addDrugDto dto){
        drugService.addDrug(dto);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("addDrugToMedKit")
    public ResponseEntity addDrugToMedKit(@RequestBody AddDrugToMedKitDto dto) {
        drugService.addDrugToMedKit(dto.getDrug(), dto.getUsername());
        return ResponseEntity.ok("OK");
    }

    @GetMapping("getDrugByBarcode/{code}")
    public ResponseEntity<DrugFullDto> getDrugByBarCode(@PathVariable(name = "code") String barcode){
        Drug drug = drugService.findDrugByBarCode(barcode);
        DrugFullDto dto = new DrugFullDto(drug.getId(),drug.getCreated(), drug.getUpdated(), drug.getStatus().toString(),
                drug.getName(),drug.getBarcode(), drug.getDescription(),
                drug.getProdDate(), drug.getExpDate(), drug.getNumOfPills(), drug.getNumOfPillsPerDay(),
                drug.getStartTakePillsTime(),drug.getTakePillsInterval());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("getDrugsTakeTime/{code}")
    public  ResponseEntity<TakeTimeDto> gerDrugsTakeTime(@PathVariable(name = "code") String barcode){
        List<DateDrugs> result = drugService.getDrugTakeTime(barcode);
        TakeTimeDto dto = new TakeTimeDto(result);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
