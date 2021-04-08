package com.hse.organizer.rest;

import com.hse.organizer.dto.AddDrugToMedKitDto;
import com.hse.organizer.dto.AuthenticationRequestDto;
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


    @PostMapping("addDrugToMedKit")
    public ResponseEntity login(@RequestBody AddDrugToMedKitDto dto) {
        drugService.addDrugToMedKit(dto.getDrug(), dto.getUsername());
        return ResponseEntity.ok("OK");
    }
}
