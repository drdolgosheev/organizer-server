package com.hse.organizer.rest;

import com.hse.organizer.dto.AddDrugToMedKitDto;
import com.hse.organizer.dto.DrugForShare;
import com.hse.organizer.dto.UserDto;
import com.hse.organizer.dto.getMedKitDto;
import com.hse.organizer.model.Diagnosis;
import com.hse.organizer.model.Drug;
import com.hse.organizer.model.User;
import com.hse.organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller user connected requests.
 *
 * @author Dolgosheev Dmitriy
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param id user ID
     * @return HTTP response: body UserDto
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @param username user username
     * @return List of Drug
     */
    @GetMapping(value="get/medKit/{username}")
    public ResponseEntity<List<DrugForShare>> getUserMedKitById(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        getMedKitDto dto = new getMedKitDto();

        return new ResponseEntity<>(dto.transferDrugs(user.getMedKit()), HttpStatus.OK);
    }

    /**
     * @param username user username
     * @return List of Diagnosis
     */
    @GetMapping(value="get/diagnosis/{username}")
    public ResponseEntity<List<Diagnosis>> getUserDiagnosisById(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user.getDiagnosisList(), HttpStatus.OK);
    }
}

