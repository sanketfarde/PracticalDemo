package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.Dto.BellArmoryDTO;
import com.example.WeaponArmaryManagementSystem.Service.BellofArmoryService;
import com.example.WeaponArmaryManagementSystem.model.BellArmory;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmsReport;

@RestController
@RequestMapping("/bellarmory")
@CrossOrigin(origins = "*")
public class BellArmoryController {

    @Autowired
    private BellofArmoryService bellofArmoryService;


    /*
    //time:436 ms
    @PostMapping("/save")
    public ResponseEntity<String> saveBellArmory(@RequestBody BellArmoryDTO bellArmoryDTO) {
        try {
            bellofArmoryService.saveBellArmory(bellArmoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id=0\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            // Handle the case where saving the data failed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data saved successfully...Id=0\"}"+ ex.getMessage());
        }
    }
*/
   
    //for optimization by vikas on 30/05/2024 time:420 ms
    @PostMapping("/save")
    public ResponseEntity<String> saveBellArmory(@RequestBody BellArmoryDTO bellArmoryDTO) {
        try {
            bellofArmoryService.saveBellArmory(bellArmoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id=0\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data  Not saved...Id=1\"}" + ex.getMessage());
        }
    }



//time:406 ms
    @PostMapping("/all")
    public ResponseEntity<List<BellArmory>> getAllBellArmories() {
        List<BellArmory> bellArmories = bellofArmoryService.getAllBellArmories();
        if (!bellArmories.isEmpty()) {
            return ResponseEntity.ok(bellArmories);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    
    
    /*
    //for optimization by vikas on 30/05/2024 time:
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllBellArmories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            PageRequest paging = PageRequest.of(page, size);
            Page<BellArmory> pageResult = bellofArmoryService.getAllBellArmories(paging);
            Map<String, Object> response = new HashMap<>();
            response.put("bellArmories", pageResult.getContent());
            response.put("currentPage", pageResult.getNumber());
            response.put("totalItems", pageResult.getTotalElements());
            response.put("totalPages", pageResult.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    

    // time:252 
    @PostMapping("/byDate")
    public ResponseEntity<?> getBellArmoriesByDate(@RequestParam("date") String date) {
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesByDate(date);
            if (!bellArmories.isEmpty()) {
                return ResponseEntity.ok(bellArmories);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to retrieve data\"}");
        }
    }
    
    
    /*
    //for optimization by vikas on 30/05/2024
    @PostMapping("/byDate")
    public ResponseEntity<?> getBellArmoriesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesByDate(date);
            if (bellArmories.isEmpty()) {
                return new ResponseEntity<>("{\"message\": \"No data found for the given date\"}", HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(bellArmories);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Invalid date format\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to retrieve data\"}");
        }
    }
*/
    

    
    //time:273 ms
    @PostMapping("/betweenDates")
    public ResponseEntity<Map<String, Object>> getBellArmoriesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {

        Map<String, Object> response = new HashMap<>();
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesBetweenDates(startDate, endDate);
            if (!bellArmories.isEmpty()) {
                response.put("BellArmories", bellArmories);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "Failed to retrieve data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


 /*   @PostMapping("/byWeaponNameAndDate")
    public ResponseEntity<?> getBellArmoriesByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") String date) {
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesByWeaponNameAndDate(weaponName, date);
            if (!bellArmories.isEmpty()) {
                return ResponseEntity.ok(bellArmories);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to retrieve data\"}");
        }
    }*/

    @PostMapping("/byWeaponNamesAndDate")
    public ResponseEntity<?> getBellArmoriesByWeaponNamesAndDate(
            @RequestParam("weapon1") List<String> weaponNames,
            @RequestParam("date") String date) {
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesByWeaponNamesAndDate(weaponNames, date);
            if (!bellArmories.isEmpty()) {
                return ResponseEntity.ok(bellArmories);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to retrieve data\"}");
        }
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> getBellArmoryById(@PathVariable Long id) {
        try {
            BellArmory bellArmory = bellofArmoryService.getBellArmoryById(id);
            if (bellArmory != null) {
                return ResponseEntity.ok(bellArmory);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"BellArmory not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to retrieve data\"}");
        }
    }
    /*
    //for optimization by vikas on 30/05/2024 time:
    @PostMapping("/betweenDates")
    public ResponseEntity<Map<String, Object>> getBellArmoriesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Map<String, Object> response = new HashMap<>();
        try {
            List<BellArmory> bellArmories = bellofArmoryService.getBellArmoriesBetweenDates(startDate, endDate);
            if (bellArmories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            response.put("bellArmories", bellArmories);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to retrieve data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    } */



    // Now this controller methods for the bell of arms Report

    @PostMapping("/report_save")
    public ResponseEntity<String> saveBellOfArmsReport(@RequestBody BellOfArmsReport bellOfArmsReport) {
        bellofArmoryService.saveBellOfArmsReport(bellOfArmsReport);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data Saved Successfully...Id:0\"}");
    }

    
    @PostMapping("/report/get_by_butt_no/{buttNo}")
    public List<BellOfArmsReport> getByButtNo(@PathVariable String buttNo) {
        return bellofArmoryService.getByButtNo(buttNo);
    }
    

    @PostMapping("/get_allreport")
    public ResponseEntity<List<BellOfArmsReport>> getAllBellOfArmsReports() {
        List<BellOfArmsReport> reports = bellofArmoryService.getAllBellOfArmsReports();
        return ResponseEntity.ok(reports);
    }
    
    
    @PostMapping("/report/get_by_date/{depositedDate}")
    public ResponseEntity<List<BellOfArmsReport>> getByDepositedDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate depositedDate) {
        List<BellOfArmsReport> reports = bellofArmoryService.getByDepositedDate(depositedDate);
        return ResponseEntity.ok(reports);
    }
    
    
    @PostMapping("/report/get_between_dates")
    public ResponseEntity<List<BellOfArmsReport>> getBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<BellOfArmsReport> reports = bellofArmoryService.getBetweenDates(startDate, endDate);
        return ResponseEntity.ok(reports);
    }
}


/*  @PostMapping("/all")
public ResponseEntity<Map<String, List<BellArmory>>> getAllBellArmories() {
    List<BellArmory> bellArmories = bellofArmoryService.getAllBellArmories();
    Map<String, List<BellArmory>> response = new HashMap<>();
    if (!bellArmories.isEmpty()) {
        response.put("BellArmory", bellArmories);
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
*/