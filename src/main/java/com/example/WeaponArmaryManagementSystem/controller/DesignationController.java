package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.model.Designation;
import com.example.WeaponArmaryManagementSystem.repository.DesignationRepository;

//import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/designations")
@CrossOrigin(origins = "*")
//@Api(value = "Designation Management System", tags = {"Designation Management"})
//@Api(value = "Designation", tags = {"Designation"})
public class DesignationController {

    @Autowired
    private DesignationRepository designationRepository;
 /*
    //29/05/2024
    //changes as per avinash for validations by vikas
    private static final Pattern VALID_INPUT_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s]+$");

    @PostMapping("/saveDesignation")
    public ResponseEntity<String> createDesignation(@RequestBody Designation designation) {
        // Validate input fields
        if (!isValid(designation.getEmployeeId()) || !isValid(designation.getEmployeeName()) ||
            !isValid(designation.getEmployeeDesignation()) || !isValid(designation.getDesignation()) ||
            !isValid(designation.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
               //  .body("{\"message\": \"Invalid input: Special characters are not allowed.\",\Id\":1"}");"
        .body("{\"message\": \"Invalid input: Special characters are not allowed\",\"Id\": 1}");
        }

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        designation.setCreatedAt(now);
        designation.setUpdatedAt(now);

        Designation savedDesignation = designationRepository.save(designation);
        String responseMessage = "{\"message\": \"Data Saved Successfully..\",\"Id\":0 " + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    private boolean isValid(String input) {
        return input != null && VALID_INPUT_PATTERN.matcher(input).matches();
    }

  */


    private static final Pattern VALID_INPUT_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s@\\-\\\"]+$");

    @PostMapping("/saveDesignation")
    public ResponseEntity<String> createDesignation(@RequestBody Designation designation) {
        // Check if the designation already exists
        if (designationRepository.existsByDesignation(designation.getDesignation())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Designation already exists.\",\"Id\": 2}");
        }

        // Validate designation format
        if (!isValidDesignation(designation.getDesignation())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Invalid input: Designation must contain only '@' or '-' symbols and alphabetical characters.\",\"Id\": 3}");
        }

        if (!isValid(designation.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"Invalid input: Special characters are not allowed or invalid designation.\",\"Id\": 1}");
        }



        /*  // Validate input fields
          if (!isValid(designation.getEmployeeId()) || !isValid(designation.getEmployeeName()) ||
              !isValid(designation.getEmployeeDesignation()) || !isValidDesignation(designation.getDesignation()) ||
              !isValid(designation.getStatus())) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("{\"message\": \"Invalid input: Special characters are not allowed or invalid designation.\",\"Id\": 1}");
          }*/


        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        designation.setCreatedAt(now);
        designation.setUpdatedAt(now);

        Designation savedDesignation = designationRepository.save(designation);
        String responseMessage = "{\"message\": \"Data Saved Successfully..\",\"Id\": 0}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    private boolean isValid(String input) {
        // Define the pattern to allow only alphanumeric characters and spaces
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        return input != null && pattern.matcher(input).matches();
    }

    private boolean isValidDesignation(String input) {
        // Define the pattern to allow only '@' and '-' symbols
        Pattern pattern = Pattern.compile("^[a-zA-Z@-]+$");
        return input != null && pattern.matcher(input).matches();
    }

    @PostMapping("/list")
    public List<Designation> getAllDesignations() {
        return designationRepository.findAllSortedByDesignation();
    }

    
    
    @PostMapping("/getById/{id}")
    public ResponseEntity<Designation> getDesignationById(@PathVariable Long id) {
        Optional<Designation> designation = designationRepository.findById(id);
        return designation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/listDesignations")
    public ResponseEntity<List<String>> listActiveDesignations() {
        try {
            List<String> designations = designationRepository.findActiveDesignations();
            return new ResponseEntity<>(designations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   

    @PostMapping("/updateById/{id}")
    public ResponseEntity<String> updateDesignation(@PathVariable Long id, @RequestBody Designation updatedDesignation) {
        Optional<Designation> existingDesignationOptional = designationRepository.findById(id);
        if (existingDesignationOptional.isPresent()) {
            Designation existingDesignation = existingDesignationOptional.get();

            // Retain the createdAt value from the existing entity
            updatedDesignation.setCreatedAt(existingDesignation.getCreatedAt());

            // Set updatedAt field with current date and time
            LocalDateTime now = LocalDateTime.now();
            updatedDesignation.setUpdatedAt(now);

            existingDesignation.setDesignation(updatedDesignation.getDesignation());
            existingDesignation.setStatus(updatedDesignation.getStatus());
            existingDesignation.setUpdatedAt(updatedDesignation.getUpdatedAt());

            Designation savedDesignation = designationRepository.save(existingDesignation);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Data updated Successfully..\",\"Id\": 0}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Data not updated..\",\"Id\": 0}");
        }
    }
    

//delete method
    @PostMapping("/{id}")
    public ResponseEntity<?> deleteDesignation(@PathVariable Long id) {
        Optional<Designation> designation = designationRepository.findById(id);
        if (designation.isPresent()) {
            designationRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Designation Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Designation Not Found");
        }
    }
}


/* 
//29/05/2024
@PostMapping("/saveDesignation")
public ResponseEntity<String> createDesignation(@RequestBody Designation designation) {
    // Set createdAt and updatedAt fields with current date and time
    LocalDateTime now = LocalDateTime.now();
    designation.setCreatedAt(now);
    designation.setUpdatedAt(now);

    Designation savedDesignation = designationRepository.save(designation);
    String responseMessage = "{\"message\": \"Data Saved Successfully..\",\"Id\": 0}";
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
}*/
