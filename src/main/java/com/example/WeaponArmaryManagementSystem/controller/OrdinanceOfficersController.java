package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.OrdinanceOfficerService;
import com.example.WeaponArmaryManagementSystem.model.OrdinanceOfficer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordinanceOfficer")
public class OrdinanceOfficersController {

    @Autowired
    private OrdinanceOfficerService ordinanceOfficerService;

    @PostMapping("/distribute")
    public ResponseEntity<String> distributeWeapons(@RequestBody OrdinanceOfficer ordinanceOfficer) {
        try {
            ordinanceOfficerService.distributeWeaponsAndAccessories(ordinanceOfficer);
            return ResponseEntity.ok("{\"message\": \"Weapon Distributed Successfully\", \"Id\": 0}");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnWeapons(@RequestBody OrdinanceOfficer ordinanceOfficer) {
        try {
            ordinanceOfficerService.returnWeaponsAndAccessories(ordinanceOfficer);
            return ResponseEntity.ok("Weapons and accessories returned successfully and data saved.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}

