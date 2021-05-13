package com.hse.organizer.rest;

import com.hse.organizer.dto.*;
import com.hse.organizer.model.Diagnosis;
import com.hse.organizer.model.User;
import com.hse.organizer.service.DrugService;
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
    private final DrugService drugService;

    @Autowired
    public UserRestControllerV1(UserService userService, DrugService drugService) {
        this.userService = userService;
        this.drugService = drugService;
    }

    /**
     * @param id user ID
     * @return HTTP response: body UserDto
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * @param username user username
     * @return List of Drug
     */
    @GetMapping(value = "get/medKit/{username}")
    public ResponseEntity<List<DrugForShare>> getUserMedKitById(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        getMedKitDto dto = new getMedKitDto();

        return new ResponseEntity<>(dto.transferDrugs(user.getMedKit()), HttpStatus.OK);
    }

    /**
     * @param username user username
     * @return List of Diagnosis
     */
    @GetMapping(value = "get/diagnosis/{username}")
    public ResponseEntity<List<Diagnosis>> getUserDiagnosisById(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user.getDiagnosisList(), HttpStatus.OK);
    }

    /**
     * Method delete drug from user's med kit
     *
     * @param dto barcode, username
     * @return Status
     */
    @PostMapping("deleteDrugFromMedKit")
    public ResponseEntity<BooleanDto> deleteFromMedKit(@RequestBody BarcodeDto dto) {
        String username = dto.getUsername();
        String barcode = dto.getBarcode();
        Boolean result = userService.deleteFromMedKit(barcode, username);
        BooleanDto reqDto = new BooleanDto();
        reqDto.setFlag(result);
        if (result) {
            return ResponseEntity.ok(reqDto);
        } else {
            return ResponseEntity.ok(reqDto);
        }
    }

    /**
     * Change user's password
     *
     * @param dto decoded password and username
     * @return boolean value true, if successful, false if not
     */
    @PostMapping("changePassword")
    public ResponseEntity<BooleanDto> deleteFromMedKit(@RequestBody ChangePasswordDto dto) {
        Boolean result = userService.changePassword(dto.getUsername(), dto.getPassword());
        BooleanDto resultDto = new BooleanDto();
        resultDto.setFlag(result);
        return ResponseEntity.ok(resultDto);
    }

    /**
     * Checks if passwords are equal
     *
     * @param passwordDto password
     * @return boolean value true, if successful, false if not
     */
    @PostMapping("checkPasswordEquals")
    public ResponseEntity<BooleanDto> checkPasswordEquals(@RequestBody ChangePasswordDto passwordDto) {
        BooleanDto dto = new BooleanDto();
        Boolean result = userService.assertPasswords(passwordDto.getPassword(), passwordDto.getUsername());
        dto.setFlag(result);
        return ResponseEntity.ok(dto);
    }

    /**
     * Get user by username
     *
     * @param username username
     * @return HTTP response: body UserDto
     */
    @GetMapping(value = "getUser/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

