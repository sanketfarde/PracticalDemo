package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Dto.DistributionRequest;
import com.example.WeaponArmaryManagementSystem.Dto.ReturnRequest;
import com.example.WeaponArmaryManagementSystem.Dto.DistributionRequestWrapper;
import com.example.WeaponArmaryManagementSystem.Service.WeaponDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController
@RequestMapping("/distributionBellOfArms")
public class BellOfArmWeaponDistributionController {
    @Autowired
    private WeaponDistributionService weaponDistributionService;

    @PostMapping("/distribute")
    public ResponseEntity<String> distributeWeapons(@RequestBody DistributionRequestWrapper requestWrapper) {
        weaponDistributionService.distributeWeapons(requestWrapper.getRequests(), requestWrapper.getGodownName());
        return ResponseEntity.ok("Weapons distributed successfully.");
    }
}

 */

@RestController
@RequestMapping("/api/distribution")
public class BellOfArmWeaponDistributionController {

    @Autowired
    private WeaponDistributionService weaponDistributionService;

    @PostMapping("/distribute")
    public ResponseEntity<String> distributeWeapons(@RequestBody DistributionRequest distributionRequest) {
        List<String> failedDistributions = weaponDistributionService.distributeWeapons(distributionRequest);
        if (failedDistributions.isEmpty()) {
            return ResponseEntity.ok("All weapons distributed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .body("Some weapons could not be distributed: " + String.join(", ", failedDistributions));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnWeapons(@RequestBody ReturnRequest returnRequest) {
        List<String> failedReturns = weaponDistributionService.returnWeapons(returnRequest);
        if (failedReturns.isEmpty()) {
            return ResponseEntity.ok("All weapons returned successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .body("Some weapons could not be returned: " + String.join(", ", failedReturns));
        }
    }
}
