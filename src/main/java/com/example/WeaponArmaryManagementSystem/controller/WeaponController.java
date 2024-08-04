package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Service.WeaponService;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
//weapons controller for the magzine department made by manish
@RestController
@CrossOrigin(origins = "*")
public class WeaponController {

    private final WeaponService weaponService;

    @Autowired
    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }



    @PostMapping("/weapons")
    public ResponseEntity<?> saveOrUpdateWeapon(@RequestBody WeaponsDailyReport weaponsDailyReport) {
        try {
            WeaponsDailyReport savedWeapon = weaponService.saveOrUpdateWeapon(weaponsDailyReport);
            String successMessage = "{\"message\": \"Data Saved Successfully...\",\"Id\":0}";
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "{\"message\": \"Weapons daily report for the given date and weapon type already exists.\",\"Id\":1}";
            return ResponseEntity.badRequest().body(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to save Data...\",\"Id\":2}");
        }
    }


    @PostMapping ("/weapons/{date}")
    public ResponseEntity<Map<String, Object>> getWeaponsAndTotalsByDate(@PathVariable String date) {
        try {
            Map<String, Object> weaponsAndTotals = weaponService.getWeaponsAndTotalsByDate(date);
            if (weaponsAndTotals.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(weaponsAndTotals);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to retrieve weapon data and totals for the date: " + date));
        }
    }
    
    
    
    @PostMapping("/weapons/between-dates")
    public ResponseEntity<?> getWeaponsAndTotalsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Map<String, Object>> data = weaponService.getWeaponsAndTotalsBetweenDates(startDate.toString(), endDate.toString());
        if (data.isEmpty()) {
            return new ResponseEntity<>(Map.of("message", "No data available for the specified date range."), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(data);
    }


    @PostMapping("/weapons/all")
    public ResponseEntity<List<WeaponsDailyReport>> getAllWeaponsDailyReports() {
        try {
            List<WeaponsDailyReport> allReports = weaponService.getAllWeaponsDailyReports();
            if (allReports.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allReports);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


}