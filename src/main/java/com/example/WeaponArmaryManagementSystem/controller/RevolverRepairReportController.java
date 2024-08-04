package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.Impl.RevolverRepairReportService;
import com.example.WeaponArmaryManagementSystem.model.RevolverRepairReport;
import com.example.WeaponArmaryManagementSystem.repository.RevolverRepairReportRepository;
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
@RequestMapping("/revolverRepairReport")
@CrossOrigin(origins = "*")
public class RevolverRepairReportController {

		@Autowired
		private RevolverRepairReportService revolverRepairReportService;
		
		@Autowired
	    private RevolverRepairReportRepository revolverRepairReportRepository;
	
		
		
		@PostMapping("/saveRevolverRepairReport")
		public ResponseEntity<String> createRevolverRepairReport(@RequestBody RevolverRepairReport revolverRepairReport) {
		    try {
		        LocalDateTime now = LocalDateTime.now();	    
		        revolverRepairReport.setCreatedAt(now);
		        revolverRepairReport.setUpdatedAt(now);	   
		        RevolverRepairReport saveRevolverRepairReport = revolverRepairReportRepository.save(revolverRepairReport);
		        return new ResponseEntity<>("{\"message\": \"Weapon added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
		    } catch (Exception e) {
		        return new ResponseEntity<>("{\"message\": \"Weapon addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}


	    
	    @PostMapping("/listRevolverRepairReport")
	    public ResponseEntity<List<RevolverRepairReport>> listRevolverRepairReport() {
	        try {
	            List<RevolverRepairReport> revolverRepairReport = revolverRepairReportRepository.findAllOrderByDateDesc();
	            return new ResponseEntity<>(revolverRepairReport, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	 

	    
	    @PostMapping("/getRevolverRepairReportByDate/{date}")
		 public ResponseEntity<?> getRevolverRepairReportByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		     try {
		         List<RevolverRepairReport> revolverRepairReport = revolverRepairReportRepository.findAllByDate(date);
		         if (!revolverRepairReport.isEmpty()) {
		             return ResponseEntity.ok(revolverRepairReport);
		         } else {
		             return ResponseEntity.notFound().build();
		         }
		     } catch (DataAccessException e) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving weapon reports: " + e.getMessage());
		     }
		 }
	    
	    
	    

	@PostMapping("/revolver-repair-reports")
	public ResponseEntity<List<RevolverRepairReport>> getReportsByDateRange(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		List<RevolverRepairReport> reports = revolverRepairReportService.getReportsByDateRange(startDate, endDate);
		return ResponseEntity.ok(reports);
	}

	
	
	@PostMapping("/getRevolverRepairReportById/{id}")
	public ResponseEntity<RevolverRepairReport> getRevolverRepairReportById(@PathVariable Long id) {
		try {
			Optional<RevolverRepairReport> revolverRepairReportOptional = revolverRepairReportRepository.findById(id);
			if (revolverRepairReportOptional.isPresent()) {
				RevolverRepairReport revolverRepairReport = revolverRepairReportOptional.get();
				return new ResponseEntity<>(revolverRepairReport, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	@PostMapping("/getByButtNo/{buttNo}")
	public ResponseEntity<?> getRevolverRepairReportByButtNo(@PathVariable String buttNo) {
		try {
			List<RevolverRepairReport> revolverRepairReports = revolverRepairReportRepository.findAllByButtNo(buttNo);
			if (!revolverRepairReports.isEmpty()) {
				return ResponseEntity.ok(revolverRepairReports);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving revolver repair reports: " + e.getMessage());
		}
	}

}


/* @PostMapping("/getWeaponById/{id}")
public ResponseEntity<RevolverRepairReport> getRevolverRepairReportById(@PathVariable Long id) {
    try {
        Optional<RevolverRepairReport> revolverRepairReportOptional = revolverRepairReportRepository.findById(id);
        if (revolverRepairReportOptional.isPresent()) {
        	RevolverRepairReport revolverRepairReport = revolverRepairReportOptional.get();
            return new ResponseEntity<>(revolverRepairReport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/