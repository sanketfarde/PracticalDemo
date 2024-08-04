package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeaponArmaryManagementSystem.model.InwardServicePrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardServicePrefix;
import com.example.WeaponArmaryManagementSystem.repository.InwardAddServicePrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardAddServicePrefixRepository;

@RestController
@RequestMapping("/addmaster")
@CrossOrigin(origins = "*")
public class InOutwardWearhouseController {
   
   @Autowired
   private InwardAddServicePrefixRepository inwardAddServicePrefixRepository;
   
   @Autowired
   private OutwardAddServicePrefixRepository outwardAddServicePrefixRepository;
	
    @PostMapping("/saveInward")
    public ResponseEntity<String> createInwardPrefix(@RequestBody InwardServicePrefix inwardServicePrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        inwardServicePrefix.setCreatedAt(now);
        inwardServicePrefix.setUpdatedAt(now);
        
     // Set default value for status column
     //   distributionMagzine.setStatus("0");

        InwardServicePrefix savedInwardServicePrefix = inwardAddServicePrefixRepository.save(inwardServicePrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
	
    
    @PostMapping("/saveOutward")
    public ResponseEntity<String> createOutwardPrefix(@RequestBody OutwardServicePrefix outwardServicePrefix) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        outwardServicePrefix.setCreatedAt(now);
        outwardServicePrefix.setUpdatedAt(now);
        
     // Set default value for status column
     //   distributionMagzine.setStatus("0");

        OutwardServicePrefix savedOutwardServicePrefix = outwardAddServicePrefixRepository.save(outwardServicePrefix);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

}
