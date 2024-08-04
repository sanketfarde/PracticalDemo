package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.model.RepaireRevolver;
import com.example.WeaponArmaryManagementSystem.repository.RepaireRevolverRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class RepairRevolverController {

    private final RepaireRevolverRepository repaireRevolverRepository;

    public RepairRevolverController(RepaireRevolverRepository repaireRevolverRepository) {
        this.repaireRevolverRepository = repaireRevolverRepository;
    }

    


    @PostMapping("/save_repair_revolver")
    public ResponseEntity<?> saveRepairRevolver(@RequestBody RepaireRevolver repairRevolver) {
        try {
            // Check if a revolver with the same name already exists
            Optional<RepaireRevolver> existingRevolver = repaireRevolverRepository.findByRevolverName(repairRevolver.getRevolverName());
            if (existingRevolver.isPresent()) {
                // Revolver name already exists
                String errorMessage = "{\"message\": \"Duplicate Entry...\",\"Id\":2}";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
            }

            // Save the new repair revolver
            RepaireRevolver savedRepairRevolver = repaireRevolverRepository.save(repairRevolver);
            String successMessage = "{\"message\": \"Data Saved Successfully...\",\"Id\":0}";
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (DataIntegrityViolationException e) {
            // Catch exception for duplicate entry or other constraints violation
            String errorMessage = "{\"message\": \"Failed To Save...\",\"Id\":1}" + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
}
    }
    
    

    @PostMapping("/repair_revolver_by_id/{id}")
    public ResponseEntity<?> getRepairRevolverById(@PathVariable Long id) {
        try {
            Optional<RepaireRevolver> repairRevolverOptional = repaireRevolverRepository.findById(id);
            if (repairRevolverOptional.isPresent()) {
                RepaireRevolver repairRevolver = repairRevolverOptional.get();
                return ResponseEntity.ok(repairRevolver);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repair revolver not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving repair revolver");
        }
    }
    
    
    
      
    @PostMapping("/all_repair_revolver_details")
    public List<Map<String, Object>> getAllRepairRevolverDetails() {
        List<RepaireRevolver> repairRevolverList = repaireRevolverRepository.findAll();

        // Mapping ID, revolver name, and status into a Map for each entity
        return repairRevolverList.stream()
                .map(repairRevolver -> {
                    Map<String, Object> detailsMap = Map.of(
                            "id", repairRevolver.getId(),
                            "revolverName", repairRevolver.getRevolverName(),
                            "status", repairRevolver.getStatus()
                    );
                    return detailsMap;
                })
                .collect(Collectors.toList());
    }



    @PostMapping("/all_active_repair_revolvers")
    public ResponseEntity<List<RepaireRevolver>> getActiveRepairRevolvers() {
        List<RepaireRevolver> activeRevolvers = repaireRevolverRepository.findByStatus("active");
        if (activeRevolvers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(activeRevolvers);
        }
        return ResponseEntity.status(HttpStatus.OK).body(activeRevolvers);
    }
    
    
    @PostMapping("/update_repair_revolver/{id}")
    public ResponseEntity<?> partialUpdateRepairRevolver(@PathVariable Long id, @RequestBody RepaireRevolver request) {
        try {
            // Retrieve the repair revolver by ID
            RepaireRevolver repairRevolver = repaireRevolverRepository.findById(id)
                    .orElseThrow(() -> new AttributeNotFoundException(" \"id:1\"Repair revolver not found with id: " + id ));

            // Update the revolver name if present in the request
            if (request.getRevolverName() != null) {
                repairRevolver.setRevolverName(request.getRevolverName());
            }

            // Update the status if present in the request
            if (request.getStatus() != null) {
                repairRevolver.setStatus(request.getStatus());
            }

            // Save the updated repair revolver
            RepaireRevolver updatedRepairRevolver = repaireRevolverRepository.save(repairRevolver);

            // Return a custom response entity with success message and updated repair revolver
            return ResponseEntity.ok().body(" \"id:0\"Repair revolver with ID " + id + " updated successfully");

        } catch (AttributeNotFoundException e) {
            // Handle the case where repair revolver with given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating repair revolver");
        }
    }
}
 

/*
@PostMapping("/save_repair_revolver")
public ResponseEntity<?> saveRepairRevolver(@RequestBody RepaireRevolver repairRevolver) {
    try {
        RepaireRevolver savedRepairRevolver = repaireRevolverRepository.save(repairRevolver);
        String successMessage = "Repair revolver saved successfully id:0 ";
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    } catch (DataIntegrityViolationException e) {
        // Catch exception for duplicate entry or other constraints violation
        String errorMessage = "Error saving repair revolver: id:1 " + e.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}*/

