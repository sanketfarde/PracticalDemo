package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Service.DailyReportOfServiceService;
import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
// sanket code for the daily report
@RestController
@RequestMapping("/dailyReport")
@CrossOrigin(origins = "*")
public class DailyReportController {

	
	private final DailyReportOfServiceService dailyReportOfServiceService;

   @Autowired
    public DailyReportController(DailyReportOfServiceService dailyReportOfServiceService) {
        this.dailyReportOfServiceService = dailyReportOfServiceService;
   }

   
    @PostMapping("/saveDaily")
    public ResponseEntity<?> saveOrUpdateDailyReportOfService(@RequestBody DailyReportOfService dailyReportOfService) {
        try {
            DailyReportOfService savedReport = dailyReportOfServiceService.saveOrUpdateDailyReportOfService(dailyReportOfService);
            String successMessage = "{\"message\": \"Data saved successfully...Id=0\"}";
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "{\"message\": \"Daily report for the given date already exists...Id=1\"}";
            return ResponseEntity.badRequest().body(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to save data....Id=2\"}");
        }
    } 

    

    @PostMapping("/summary/{date}")
    public ResponseEntity<Map<String, Object>> getSummaryAndTotalsByDate(@PathVariable String date) {
        Map<String, Object> summaryAndTotals = dailyReportOfServiceService.getSummaryAndTotalsByDate(date);
        if (summaryAndTotals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summaryAndTotals);
    } 

    
    
    @PostMapping("/summary/between")
    public ResponseEntity<List<Map<String, Object>>> getSummaryAndTotalsBetweenDates(
            @RequestParam String startDate, @RequestParam String endDate) {
        List<Map<String, Object>> summaryAndTotals = dailyReportOfServiceService.getSummaryAndTotalsBetweenDates(startDate, endDate);
        if (summaryAndTotals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summaryAndTotals);
    }
} 


