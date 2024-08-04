package com.example.WeaponArmaryManagementSystem.controller;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponReportDto;
import com.example.WeaponArmaryManagementSystem.Service.Impl.WeaponReportService;
import com.example.WeaponArmaryManagementSystem.model.WeaponReport;
import com.example.WeaponArmaryManagementSystem.repository.WeaponReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weaponReport")
@CrossOrigin(origins = "*")
public class WeaponReportController {
	
	@Autowired
    private WeaponReportRepo weaponReportRepo;
	@Autowired
	private WeaponReportService weaponReportService;


	@PostMapping("/saveWeaponReport")
	public ResponseEntity<String> saveWeaponReport(@RequestBody WeaponReportDto weaponReportDto) {
		try {
			LocalDateTime now = LocalDateTime.now();
			weaponReportDto.setCreatedAt(now);
			weaponReportDto.setUpdatedAt(now);
			//  WeaponReportDto savedWeaponReportDto = weaponReportRepo.save(weaponReportDto);
			weaponReportService.saveWeaponReport(weaponReportDto);
			return new ResponseEntity<>("{\"message\": \"WeaponReport added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"WeaponReport addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	
	
	 @PostMapping("/listWeaponReport")
	    public ResponseEntity<List<WeaponReport>> listWeaponReport() {
	        try {
	            List<WeaponReport> weaponReport = weaponReportRepo.findAllOrderByDateDesc();
	            return new ResponseEntity<>(weaponReport, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 
	 @PostMapping("/getWeaponReportById/{id}")
	    public ResponseEntity<WeaponReport> getWeaponReportById(@PathVariable Integer id) {
	        try {
	            Optional<WeaponReport> weaponReportOptional = weaponReportRepo.findById(id);
	            if (weaponReportOptional.isPresent()) {
	            	WeaponReport weaponReport = weaponReportOptional.get();
	                return new ResponseEntity<>(weaponReport, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } 


	 
	 @PostMapping("/getWeaponReportByDate/{date}")
	 public ResponseEntity<?> getWeaponReportByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
	     try {
	         List<WeaponReport> weaponReports = weaponReportRepo.findAllByDate(date);
	         if (!weaponReports.isEmpty()) {
	             return ResponseEntity.ok(weaponReports);
	         } else {
	             return ResponseEntity.notFound().build();
	         }
	     } catch (DataAccessException e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving weapon reports: " + e.getMessage());
	     }
	 }
	 
	 

	@PostMapping("/weapon-reports")
	public ResponseEntity<List<WeaponReport>> getReportsByDateRange(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		List<WeaponReport> reports = weaponReportService.getReportsByDateRange(startDate, endDate);
		return ResponseEntity.ok(reports);
	}

}

/*	@PostMapping("/saveWeaponReport")
public ResponseEntity<String> createWeaponReport(@RequestBody WeaponReport weaponReport) {
    try {
        LocalDateTime now = LocalDateTime.now();	    
        weaponReport.setCreatedAt(now);
        weaponReport.setUpdatedAt(now);	   
        WeaponReport savedWeaponReport = weaponReportRepo.save(weaponReport);
        return new ResponseEntity<>("{\"message\": \"WeaponReport added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("{\"message\": \"WeaponReport addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/
