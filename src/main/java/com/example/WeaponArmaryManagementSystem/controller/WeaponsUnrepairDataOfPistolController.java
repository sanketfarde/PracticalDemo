package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Dto.WeaponsUnrepairDataOfPistolDto;
import com.example.WeaponArmaryManagementSystem.Exception.DuplicateEntryException;
import com.example.WeaponArmaryManagementSystem.Service.WeaponsUnrepairDataOfPistolService;
import com.example.WeaponArmaryManagementSystem.model.WeaponsUnrepairDataOfPistol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/weapons-unrepair")
@CrossOrigin(origins = "*")
public class WeaponsUnrepairDataOfPistolController {

    @Autowired
    private WeaponsUnrepairDataOfPistolService weaponsUnrepairDataOfPistolService;


    @PostMapping("/save")
    public ResponseEntity<?> saveUnrepairData(@RequestBody WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        try {
            WeaponsUnrepairDataOfPistol savedRecord = weaponsUnrepairDataOfPistolService.saveUnrepairData(weaponsUnrepairDataOfPistolDto);
            return ResponseEntity.ok("{\"message\": \"Data Save Successfull...\",\"Id\": 0, \"Weapon Type\": \"" + savedRecord.getWeaponType() + "\"}");

        } catch (DuplicateEntryException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data Saved Successfully...\",\"Id\": 1}");
        }
    }



    // Get unrepair data by date
    @PostMapping("/byDate")
    public ResponseEntity<?> getUnrepairDataByDate(@RequestParam("date") String date) {
        List<WeaponsUnrepairDataOfPistol> unrepairData = weaponsUnrepairDataOfPistolService.getUnrepairDataByDate(date);
        if (!((List<?>) unrepairData).isEmpty()) {
            return ResponseEntity.ok(unrepairData);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"message\": \"No data found for the specified date\"}");
        }
    }

    
    // Get all unrepair data
    @PostMapping("/all")
    public ResponseEntity<?> getAllUnrepairData() {
        List<WeaponsUnrepairDataOfPistol> allUnrepairData = weaponsUnrepairDataOfPistolService.getAllUnrepairData();
        if (!allUnrepairData.isEmpty()) {
            return ResponseEntity.ok(allUnrepairData);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"message\": \"No data found\"}");
        }
    }
    
    
    
    @PostMapping("/byWeaponName")
    public ResponseEntity<?> getUnrepairDataByWeaponName(@RequestParam("weaponName") String weaponName) {
        List<WeaponsUnrepairDataOfPistol> unrepairData = weaponsUnrepairDataOfPistolService.getUnrepairDataByWeaponName(weaponName);
        if (!unrepairData.isEmpty()) {
            return ResponseEntity.ok(unrepairData);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"message\": \"No data found for the specified weapon name\"}");
        }
    }


    
    @PostMapping("/betweenDates")
    public ResponseEntity<?> getUnrepairDataBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {

        List<WeaponsUnrepairDataOfPistol> unrepairData =
                weaponsUnrepairDataOfPistolService.getUnrepairDataBetweenDates(startDate, endDate);

        if (!unrepairData.isEmpty()) {
            return ResponseEntity.ok(unrepairData);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("{\"message\": \"No data found between the specified dates\"}");
        }
    }

    
    // Get unrepair data by weapon name and date
    @PostMapping("/byWeaponNameAndDate")
    public ResponseEntity<?> getUnrepairDataByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {

        List<WeaponsUnrepairDataOfPistol> unrepairData = weaponsUnrepairDataOfPistolService.getUnrepairDataByWeaponNameAndDate(weaponName, date);
        if (!unrepairData.isEmpty()) {
            return ResponseEntity.ok(unrepairData);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"message\": \"No data found for the specified weapon name and date\"}");
        }
    }

    //08/06/2024 by manish
    //this is updated method for the makrand as per requirement.........
    @PostMapping("/updateByWeaponNameAndDate")
    public ResponseEntity<?> updateUnrepairDataByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date,
            @RequestBody WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        try {
            if (weaponsUnrepairDataOfPistolDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Request Body is missing...Id:6"));
            }

            // Fetch the existing records to retain the weapon type and append the updated remark
            List<WeaponsUnrepairDataOfPistol> existingRecords = weaponsUnrepairDataOfPistolService.findByWeaponNameAndDate(weaponName, date);
            if (existingRecords.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No data found for the specific date and weapon name...Id:5"));
            }

            // For simplicity, use the first record (adjust logic if multiple records need to be updated)
            WeaponsUnrepairDataOfPistol existingRecord = existingRecords.get(0);

            // Retrieve the existing updated remarks and append the new updated remarks
            String existingUpdatedRemarks = existingRecord.getUpdatedRemark();
            String newUpdatedRemarks = weaponsUnrepairDataOfPistolDto.getUpdatedRemark();

            // Handle the case where existing updated remarks might be null
            if (existingUpdatedRemarks == null) {
                existingUpdatedRemarks = "";
            }

            // Append new updated remarks to the existing ones, separated by a newline or any delimiter of your choice
            String combinedUpdatedRemarks = existingUpdatedRemarks + (existingUpdatedRemarks.isEmpty() ? "" : "\n") + newUpdatedRemarks;

            // Update the DTO with the combined updated remarks
            weaponsUnrepairDataOfPistolDto.setUpdatedRemark(combinedUpdatedRemarks);

            // Retain the original weapon type
            weaponsUnrepairDataOfPistolDto.setWeaponType(existingRecord.getWeaponType());

            // Update the record with the new data
            WeaponsUnrepairDataOfPistol updatedRecord = weaponsUnrepairDataOfPistolService.updateUnrepairDataByWeaponNameAndDate(weaponName, date, weaponsUnrepairDataOfPistolDto);

            // Create a response map to return a proper JSON response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Data Update Successful...");
            response.put("Id", 0);
            response.put("Weapon Type", updatedRecord.getWeaponType());
            response.put("Updated Remarks", combinedUpdatedRemarks);

            // Return the response as a JSON object
            return ResponseEntity.ok(response);

        } catch (NoSuchElementException e) {
            // Print specific exception message to the console
            System.err.println("Error: No data found for the specified date and weapon name. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No data found for the specific date and weapon name...Id=1"));
        } catch (Exception e) {
            // Print full stack trace to the console
            e.printStackTrace();  // This will print the error details to the console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "An error occurred while updating the data...Id=2", "error", e.getMessage()));
}
}


   /*
    //as per manish on 06/06/2024
    @PostMapping("/updateByWeaponNameAndDate")
    public ResponseEntity<?> updateUnrepairDataByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date,


            @RequestBody WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        try {
            if (weaponsUnrepairDataOfPistolDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Request body is missing\"}");
            }
            WeaponsUnrepairDataOfPistol updatedRecord = weaponsUnrepairDataOfPistolService.updateUnrepairDataByWeaponNameAndDate(weaponName, date, weaponsUnrepairDataOfPistolDto);
            return ResponseEntity.ok("{\"message\": \"Data Update Successful...\",\"Id\": 0"  + ", \"Weapon Type\": \"" + updatedRecord.getWeaponType() + "\"}");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No data for the Specific date and weapon Name Id :1\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data Update Failed.. Id :1\"}");
        }
    }

    */

/*    @PostMapping("/updateByWeaponNameAndDate")
    public ResponseEntity<?> updateUnrepairDataByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date,
            @RequestBody WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        try {
            if (weaponsUnrepairDataOfPistolDto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Request body is missing\"}");
            }

            // Fetch the existing record
            WeaponsUnrepairDataOfPistol existingRecord = weaponsUnrepairDataOfPistolService.findByWeaponNameAndDate(weaponName, date);

            if (existingRecord == null) {
                throw new NoSuchElementException("No data found for the specified date and weapon name.");
            }

            // Update only the fields that are allowed to be updated
            existingRecord.setField1(weaponsUnrepairDataOfPistolDto.getField1());
            existingRecord.setField2(weaponsUnrepairDataOfPistolDto.getField2());
            // Add other fields as necessary, except weaponType

            // Save the updated record
            WeaponsUnrepairDataOfPistol updatedRecord = weaponsUnrepairDataOfPistolService.save(existingRecord);

            return ResponseEntity.ok("{\"message\": \"Data Update Successful...\",\"Id\": 0, \"Weapon Type\": \"" + updatedRecord.getWeaponType() + "\"}");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No data for the specific date and weapon name. Id: 1\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data update failed. Id: 1\"}");
        }
    }*/
    
    
    

    /*
    //as per manish on 06/06/2024
    @PostMapping("/updateByWeaponNameAndDate")
    public ResponseEntity<?> updateUnrepairDataByWeaponNameAndDate(
            @RequestParam("weaponName") String weaponName,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date,
            @RequestBody WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        try {
            WeaponsUnrepairDataOfPistol updatedRecord = weaponsUnrepairDataOfPistolService.updateUnrepairDataByWeaponNameAndDate(weaponName, date, weaponsUnrepairDataOfPistolDto);
            return ResponseEntity.ok("{\"message\": \"Data Update Successfull...\",\"Id\": " + updatedRecord.getId() + ", \"Weapon Type\": \"" + updatedRecord.getWeaponType() + "\"}");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No data found for the specified weapon name and date\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data Update Failed\"}");
        }
    }*/
}
