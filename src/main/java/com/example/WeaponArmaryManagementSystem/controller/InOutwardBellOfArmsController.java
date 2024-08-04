package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.model.InwardBellOfArmPrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardBellOfArmPrefix;
import com.example.WeaponArmaryManagementSystem.repository.InwardAddBellOfArmPrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardAddBellOfArmPrefixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/masterInBellOfArms")
@CrossOrigin(origins = "*")
public class InOutwardBellOfArmsController {


    @Autowired
    private InwardAddBellOfArmPrefixRepository inwardAddBellOfArmPrefixRepository;

    @Autowired
    private OutwardAddBellOfArmPrefixRepository outwardAddBellOfArmPrefixRepository;

    @PostMapping("/saveInward")
    public ResponseEntity<String> createInwardPrefix(@RequestBody InwardBellOfArmPrefix inwardBellOfArmPrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        inwardBellOfArmPrefix.setCreatedAt(now);
        inwardBellOfArmPrefix.setUpdatedAt(now);

        // Set default value for status column
        //   distributionMagzine.setStatus("0");

        InwardBellOfArmPrefix savedInwardBellOfArmPrefix = inwardAddBellOfArmPrefixRepository.save(inwardBellOfArmPrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


    @PostMapping("/saveOutward")
    public ResponseEntity<String> createOutwardPrefix(@RequestBody OutwardBellOfArmPrefix outwardBellOfArmPrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        outwardBellOfArmPrefix.setCreatedAt(now);
        outwardBellOfArmPrefix.setUpdatedAt(now);

        // Set default value for status column
        //   distributionMagzine.setStatus("0");

        OutwardBellOfArmPrefix savedOutwardBellOfArmPrefix = outwardAddBellOfArmPrefixRepository.save(outwardBellOfArmPrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}
