package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.Impl.TotalsDailyReportOfServiceServices;
import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import com.example.WeaponArmaryManagementSystem.repository.TotalsDailyReportOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/TotalsDailyServiceReport")
@CrossOrigin(origins = "*")
public class TotalsDailyServiceReportController {

    @Autowired
    private TotalsDailyReportOfServiceServices totalsDailyReportOfServiceServices;
    
	@Autowired
    private TotalsDailyReportOfServiceRepository totalsDailyReportOfServiceRepository;
	
	
	
	@PostMapping("/getTotalsDailyServiceReportByDate/{date}")
    public ResponseEntity<?> getTotalsDailyReportOfServiceByDate(@PathVariable String date) {
        try {
            List<TotalsDailyReportOfService> totalsDailyReportOfService = totalsDailyReportOfServiceRepository.findAllByDate(date);
            if (!totalsDailyReportOfService.isEmpty()) {
                return ResponseEntity.ok(totalsDailyReportOfService);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving weapon reports: " + e.getMessage());
        }
    }
	
	
	
    @PostMapping("/listTotalsDailyServiceReport")
    public ResponseEntity<List<TotalsDailyReportOfService>> listWeapon() {
        try {
            List<TotalsDailyReportOfService> totalsDailyReportOfService = totalsDailyReportOfServiceRepository.findAllOrderByDateDesc();
            return new ResponseEntity<>(totalsDailyReportOfService, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
    
    

    @PostMapping("/TotalsDailyServiceReportBetweenDates")
    public ResponseEntity<List<TotalsDailyReportOfService>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {


        List<TotalsDailyReportOfService> reports = totalsDailyReportOfServiceServices.getReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
	}
}
