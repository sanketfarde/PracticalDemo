package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Dto.AddTeargasWeaponsDto;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponsUnrepairDataOfPistolDto;
import com.example.WeaponArmaryManagementSystem.Exception.DuplicateEntryException;
import com.example.WeaponArmaryManagementSystem.Service.TearGasSummaryService;
import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummaryTotal;

import com.example.WeaponArmaryManagementSystem.model.WeaponsUnrepairDataOfPistol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teargas")
@CrossOrigin(origins = "*")
public class TearGasController {

    private final TearGasSummaryService tearGasSummaryService;

    @Autowired
    public TearGasController(TearGasSummaryService tearGasSummaryService) {
        this.tearGasSummaryService = tearGasSummaryService;

    }


    
    @PostMapping("/total/all")
    public ResponseEntity<List<TearGasSummaryTotal>> getAllTearGasSummariesTotal() {
        try {
            List<TearGasSummaryTotal> allSummaries = tearGasSummaryService.getAllTearGasSummariesTotal();
            if (allSummaries.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allSummaries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }
    
    
    
    @PostMapping("/summary")
    public ResponseEntity<?> saveOrUpdateTearGasSummary(@RequestBody TearGasSummary tearGasSummary) {
        try {
            TearGasSummary savedSummary = tearGasSummaryService.saveOrUpdateTearGasSummary(tearGasSummary);
            String successMessage = "{\"message\": \"Data Saved Successfully...\",\"Id\": 0}";
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "{\"message\": \"Tear gas summary for the given date and teargas amunities already exists\",\"Id\": 1}";
            return ResponseEntity.badRequest().body(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to save summary data\",\"Id\": 2}");
        }
    }



    @PostMapping("/summary/{date}")
    public ResponseEntity<Map<String, Object>> getSummaryAndTotalsByDate(@PathVariable String date) {
        try {
            Map<String, Object> summaryAndTotals = tearGasSummaryService.getSummaryAndTotalsByDate(date);
            if (summaryAndTotals.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(summaryAndTotals);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to retrieve summary data and totals for the date: " + date));
        }
    }



    @PostMapping("/summary/between-dates")
    public ResponseEntity<?> getSummaryAndTotalsBetweenDates(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        try {
            // Parse the startDate and endDate strings into LocalDate objects
            String parsedStartDate = String.valueOf(LocalDate.parse(startDate));
            LocalDate parsedEndDate = LocalDate.parse(endDate);

            List<Map<String, Object>> data = tearGasSummaryService.getSummaryAndTotalsBetweenDates(parsedStartDate, String.valueOf(parsedEndDate));
            if (data.isEmpty()) {
                return new ResponseEntity<>(Map.of("message", "No data available for the specified date range."), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Invalid date format. Please use ISO date format (yyyy-MM-dd)."), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping("/summary/all")
    public ResponseEntity<List<TearGasSummary>> getAllTearGasSummaries() {
        try {
            List<TearGasSummary> allSummaries = tearGasSummaryService.getAllTearGasSummaries();
            if (allSummaries.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(allSummaries);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.emptyList());
        }
    }


}


/*  @PostMapping("/summary/all")
public ResponseEntity<List<TearGasSummary>> getAllTearGasSummaries() {
    try {
        List<TearGasSummary> allSummaries = tearGasSummaryService.getAllTearGasSummaries();
        if (allSummaries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allSummaries);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.emptyList());
    }
}*/
