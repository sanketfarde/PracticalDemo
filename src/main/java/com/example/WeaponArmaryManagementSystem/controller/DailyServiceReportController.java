package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.Impl.DailyReportOfServiceServices;

import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import com.example.WeaponArmaryManagementSystem.repository.DailyReportOfServiceRepository;
import com.example.WeaponArmaryManagementSystem.repository.TotalsDailyReportOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*")
public class DailyServiceReportController {

	@Autowired
    private DailyReportOfServiceRepository dailyReportOfServiceRepository;

    @Autowired
    private TotalsDailyReportOfServiceRepository totalsDailyReportOfServiceRepository;

    @Autowired
    private DailyReportOfServiceServices dailyReportOfServiceServices;

    

    @PostMapping("/saveDailyReport")
    public ResponseEntity<String> saveDailyReport(@RequestBody DailyReportOfService dailyReportOfService) {
        try {
            LocalDateTime now = LocalDateTime.now();
            dailyReportOfService.setCreatedAt(now);
            dailyReportOfService.setUpdatedAt(now);

            // Check if the corresponding entry exists in DailyReportOfService
            DailyReportOfService existingDailyReport = dailyReportOfServiceRepository.findByWeaponNameAndDate(dailyReportOfService.getWeaponName(), dailyReportOfService.getDate());
            if (existingDailyReport != null) {
                return new ResponseEntity<>("{\"message\": \"A report with the same weaponName and date already exists in DailyReportOfService table.\",\"Id\": 1}", HttpStatus.BAD_REQUEST);
            }

            // Check if the corresponding entry exists in TotalsDailyReportOfService
            TotalsDailyReportOfService totalsReport = totalsDailyReportOfServiceRepository.findByWeaponNameAndDate(dailyReportOfService.getWeaponName(), dailyReportOfService.getDate());
            if (totalsReport != null) {
                // Add counts to existing data in TotalsDailyReportOfService table
                int newTotalPreviousCount = Integer.parseInt(totalsReport.getTotalPreviousCount()) + Integer.parseInt(dailyReportOfService.getPreviousCount());
                int newTotalReceivedCount = Integer.parseInt(totalsReport.getTotalReceivedCount()) + Integer.parseInt(dailyReportOfService.getReceivedCount());
                int newTotalIssuedCount = Integer.parseInt(totalsReport.getTotalIssuedCount()) + Integer.parseInt(dailyReportOfService.getIssuedCount());
                int newTotalBalancedCount = Integer.parseInt(totalsReport.getTotalBalancedCount()) + Integer.parseInt(dailyReportOfService.getBalancedCount());

                totalsReport.setTotalPreviousCount(String.valueOf(newTotalPreviousCount));
                totalsReport.setTotalReceivedCount(String.valueOf(newTotalReceivedCount));
                totalsReport.setTotalIssuedCount(String.valueOf(newTotalIssuedCount));
                totalsReport.setTotalBalancedCount(String.valueOf(newTotalBalancedCount));

                totalsReport.setUpdatedAt(now);

                totalsDailyReportOfServiceRepository.save(totalsReport);

                return new ResponseEntity<>("{\"message\": \"Counts added to existing data in TotalsDailyReportOfService table.\",\"Id\": 0}", HttpStatus.CREATED);
            }

            // Save entry in DailyReportOfService
            dailyReportOfServiceRepository.save(dailyReportOfService);

            // Check if the date is the same in TotalsDailyReportOfService table
            TotalsDailyReportOfService totalsReportByDate = totalsDailyReportOfServiceRepository.findFirstByDateOrderByCreatedAtDesc(dailyReportOfService.getDate());
            if (totalsReportByDate != null) {
                // Add counts to existing data in TotalsDailyReportOfService table
                int newTotalPreviousCount = Integer.parseInt(totalsReportByDate.getTotalPreviousCount()) + Integer.parseInt(dailyReportOfService.getPreviousCount());
                int newTotalReceivedCount = Integer.parseInt(totalsReportByDate.getTotalReceivedCount()) + Integer.parseInt(dailyReportOfService.getReceivedCount());
                int newTotalIssuedCount = Integer.parseInt(totalsReportByDate.getTotalIssuedCount()) + Integer.parseInt(dailyReportOfService.getIssuedCount());
                int newTotalBalancedCount = Integer.parseInt(totalsReportByDate.getTotalBalancedCount()) + Integer.parseInt(dailyReportOfService.getBalancedCount());

                totalsReportByDate.setTotalPreviousCount(String.valueOf(newTotalPreviousCount));
                totalsReportByDate.setTotalReceivedCount(String.valueOf(newTotalReceivedCount));
                totalsReportByDate.setTotalIssuedCount(String.valueOf(newTotalIssuedCount));
                totalsReportByDate.setTotalBalancedCount(String.valueOf(newTotalBalancedCount));

                totalsReportByDate.setUpdatedAt(now);

                totalsDailyReportOfServiceRepository.save(totalsReportByDate);

                return new ResponseEntity<>("{\"message\": \"Counts added to existing data in TotalsDailyReportOfService table.\",\"Id\": 0}", HttpStatus.CREATED);
            }

            // Create new entry in TotalsDailyReportOfService
            TotalsDailyReportOfService newTotalsDailyReport = new TotalsDailyReportOfService();
            newTotalsDailyReport.setDate(dailyReportOfService.getDate());
            newTotalsDailyReport.setWeaponName(dailyReportOfService.getWeaponName());
            newTotalsDailyReport.setTotalPreviousCount(dailyReportOfService.getPreviousCount());
            newTotalsDailyReport.setTotalReceivedCount(dailyReportOfService.getReceivedCount());
            newTotalsDailyReport.setTotalIssuedCount(dailyReportOfService.getIssuedCount());
            newTotalsDailyReport.setTotalBalancedCount(dailyReportOfService.getBalancedCount());
            newTotalsDailyReport.setStatus(dailyReportOfService.getStatus());
            newTotalsDailyReport.setCreatedAt(now);
            newTotalsDailyReport.setUpdatedAt(now);

            totalsDailyReportOfServiceRepository.save(newTotalsDailyReport);

            return new ResponseEntity<>("{\"message\": \"Daily Report added successfully\",\"Id\": 0}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Daily Report addition failed\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    @PostMapping("/listDailyReport")
    public ResponseEntity<List<DailyReportOfService>> listDailyReportOfService() {
        try {
            List<DailyReportOfService> dailyReportOfService = dailyReportOfServiceRepository.findAllOrderByDateDesc();
            return new ResponseEntity<>(dailyReportOfService, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @PostMapping("/getRevolverRepairReportByDate/{date}")
    public ResponseEntity<?> getDailyReportOfServiceByDate(@PathVariable String date) {
        try {
            List<DailyReportOfService> dailyReportOfService = dailyReportOfServiceRepository.findAllByDate(date);
            if (!dailyReportOfService.isEmpty()) {
                return ResponseEntity.ok(dailyReportOfService);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving weapon reports: " + e.getMessage());
        }
    }
    
    
    
    @PostMapping("/reports")
    public ResponseEntity<List<DailyReportOfService>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String  startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {

        List<DailyReportOfService> reports = dailyReportOfServiceServices.getReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

} 

