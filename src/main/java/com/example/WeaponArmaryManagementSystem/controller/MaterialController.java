package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.Dto.MaterialDto;
import com.example.WeaponArmaryManagementSystem.model.Material;
import com.example.WeaponArmaryManagementSystem.repository.MaterialRepository;


@RestController
@RequestMapping("/materials")
@CrossOrigin(origins = "*")
public class MaterialController {

	@Autowired
	private MaterialRepository materialRepository;


    @PostMapping("/list")
    public List<Material> getAllMaterials() {
        return materialRepository.findAllSortedByMaterial();
    }


    @PostMapping("/listWeaponNames")
    public ResponseEntity<List<String>> listActiveWeaponNames() {
        try {
            List<String> weaponNames = materialRepository.findActiveWeaponNames();
            return new ResponseEntity<>(weaponNames, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/listRoundNames")
    public ResponseEntity<List<String>> listActiveRoundNames() {
        try {
            List<String> roundNames = materialRepository.findActiveRoundNames();
            return new ResponseEntity<>(roundNames, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
	@PostMapping("/getById/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // New method to get accessories by material name
    @PostMapping("/getAccessoriesByMaterialName/{materialName}")
    public ResponseEntity<List<String>> getAccessoriesByMaterialName(@PathVariable String materialName) {
        Optional<Material> material = materialRepository.findByAccessoriesMaterialName(materialName);
        if (material.isPresent()) {
            List<String> accessories = material.get().getAccessoriesList();
            return ResponseEntity.ok(accessories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




/*
    @PostMapping("/saveMaterial")
    public ResponseEntity<String> createMaterial(@RequestBody Material material) {
        try{ // Set createdAt and updatedAt fields with current date and time
            LocalDateTime now = LocalDateTime.now();
            material.setCreatedAt(now);
            material.setUpdatedAt(now);

            Material savedMaterial = materialRepository.save(material);
            return new ResponseEntity<>("{\"message\": \"Weapon/Round added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Weapon/Round addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 */

    /*

    @PostMapping("/saveMaterial")
    public ResponseEntity<String> createMaterial(@RequestBody MaterialDto materialDto) {
        try {
            LocalDateTime now = LocalDateTime.now();

            Material material = new Material();
            material.setMaterialName(materialDto.getMaterialName());
            material.setWeaponType(materialDto.getWeaponType());
            material.setStatus(materialDto.getStatus());
            material.setEmployeeId(materialDto.getEmployeeId());
            material.setEmployeeName(materialDto.getEmployeeName());
            material.setEmployeeDesignation(materialDto.getEmployeeDesignation());
            material.setEmployeePostingDate(materialDto.getEmployeePostingDate());
            material.setCreatedAt(now);
            material.setUpdatedAt(now);

            // Set accessories as comma-separated string
            material.setAccessoriesList(materialDto.getAccessories());





            Material savedMaterial = materialRepository.save(material);
            return new ResponseEntity<>("{\"message\": \"Weapon/Round added successfully\",\"Id\": " + savedMaterial.getId() + "}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Weapon/Round addition failed\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

   /*
   // Working method before that Round requirement
    @PostMapping("/saveMaterial")
    public ResponseEntity<String> createMaterial(@RequestBody MaterialDto materialDto) {
        try {
            LocalDateTime now = LocalDateTime.now();

            Material material = new Material();
            material.setMaterialName(materialDto.getMaterialName());
            material.setWeaponDepartmentType(materialDto.getWeaponDepartmentType());
            material.setDepartmentId(materialDto.getDepartmentId());
            material.setWeaponType(materialDto.getWeaponType());
            material.setStatus(materialDto.getStatus());
            material.setEmployeeId(materialDto.getEmployeeId());
            material.setEmployeeName(materialDto.getEmployeeName());
            material.setEmployeeDesignation(materialDto.getEmployeeDesignation());
            material.setEmployeePostingDate(materialDto.getEmployeePostingDate());
            material.setCreatedAt(now);
            material.setUpdatedAt(now);

            // Set accessories as JSON string
            material.setAccessoriesList(materialDto.getAccessories());

            Material savedMaterial = materialRepository.save(material);
            return new ResponseEntity<>("{\"message\": \"Weapon/Round added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Weapon/Round addition failed\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    */

    @PostMapping("/saveMaterial")
    public ResponseEntity<String> createMaterial(@RequestBody MaterialDto materialDto) {
        try {
            LocalDateTime now = LocalDateTime.now();

            Material material = new Material();
            material.setMaterialName(materialDto.getMaterialName());
            material.setWeaponDepartmentType(materialDto.getWeaponDepartmentType());
            material.setDepartmentId(materialDto.getDepartmentId());
            material.setWeaponType(materialDto.getWeaponType());
            material.setStatus(materialDto.getStatus());
            material.setEmployeeId(materialDto.getEmployeeId());
            material.setEmployeeName(materialDto.getEmployeeName());
            material.setEmployeeDesignation(materialDto.getEmployeeDesignation());
            material.setEmployeePostingDate(materialDto.getEmployeePostingDate());
            material.setCreatedAt(now);
            material.setUpdatedAt(now);

            // Set accessories as JSON string if weaponType is not "Round"
            if (!"Round".equalsIgnoreCase(materialDto.getWeaponType())) {
                material.setAccessoriesList(materialDto.getAccessories());
            } else {
                material.setAccessoriesList(Collections.emptyList()); // or set to null
            }

            Material savedMaterial = materialRepository.save(material);
            return new ResponseEntity<>("{\"message\": \"Weapon/Round added successfully\",\"Id\": 0}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Weapon/Round addition failed\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/updateById/{id}")
    public ResponseEntity<String> updateMaterial(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        Optional<Material> existingMaterialOptional = materialRepository.findById(id);
        if (existingMaterialOptional.isPresent()) {
        	Material existingMaterial = existingMaterialOptional.get();

            // Retain the createdAt value from the existing entity
        	updatedMaterial.setCreatedAt(existingMaterial.getCreatedAt());

            // Set updatedAt field with current date and time
            LocalDateTime now = LocalDateTime.now();
            updatedMaterial.setUpdatedAt(now);

            // Update the existing distribution magazine properties with the updated values
          
            existingMaterial.setMaterialName(updatedMaterial.getMaterialName());
            existingMaterial.setWeaponType(updatedMaterial.getWeaponType());
            existingMaterial.setStatus(updatedMaterial.getStatus());
            existingMaterial.setUpdatedAt(updatedMaterial.getUpdatedAt());
           
            // Update other properties similarly...

            Material savedMaterial = materialRepository.save(existingMaterial);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Material updated successful\",\"Id\": 0"+"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material Not Found");
        }
    }
	
	
	
	@PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable Long id) {
        Optional<Material> material = materialRepository.findById(id);
        if (material.isPresent()) {
        	materialRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Material Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material Not Found");
        }
    } 
}
