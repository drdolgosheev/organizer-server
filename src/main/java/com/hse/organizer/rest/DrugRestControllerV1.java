package com.hse.organizer.rest;

import com.hse.organizer.model.Drug;
import com.hse.organizer.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/drug/")
public class DrugRestControllerV1 {
    private final DrugService drugService;

    @Autowired
    public DrugRestControllerV1(DrugService drugService) {
        this.drugService = drugService;
    }
    @GetMapping(value="get/all")
    public ResponseEntity<List<Drug>> getAllDrugs(){
        List<Drug> result = drugService.getAllDrugs();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
