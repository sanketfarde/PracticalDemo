package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.model.InwardWorkshopPrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardWorkshopPrefix;
import com.example.WeaponArmaryManagementSystem.repository.InwardAddWorkshopPrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardAddWorkshopPrefixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/masterInWorkshop")
@CrossOrigin(origins = "*")
public class InOutwardWorkshopController {


    @Autowired
    private InwardAddWorkshopPrefixRepository inwardAddWorkshopPrefixRepository;

    @Autowired
    private OutwardAddWorkshopPrefixRepository outwardAddWorkshopPrefixRepository;

    @PostMapping("/saveInward")
    public ResponseEntity<String> createInwardPrefix(@RequestBody InwardWorkshopPrefix inwardWorkshopPrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        inwardWorkshopPrefix.setCreatedAt(now);
        inwardWorkshopPrefix.setUpdatedAt(now);

        // Set default value for status column
        //   distributionMagzine.setStatus("0");

        InwardWorkshopPrefix savedInwardWorkshopPrefix = inwardAddWorkshopPrefixRepository.save(inwardWorkshopPrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


    @PostMapping("/saveOutward")
    public ResponseEntity<String> createOutwardPrefix(@RequestBody OutwardWorkshopPrefix outwardWorkshopPrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        outwardWorkshopPrefix.setCreatedAt(now);
        outwardWorkshopPrefix.setUpdatedAt(now);

        // Set default value for status column
        //   distributionMagzine.setStatus("0");

        OutwardWorkshopPrefix savedOutwardWorkshopPrefix = outwardAddWorkshopPrefixRepository.save(outwardWorkshopPrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


}
