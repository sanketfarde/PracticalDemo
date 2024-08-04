package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.model.MagzineAccessories;
import com.example.WeaponArmaryManagementSystem.model.Material;
import com.example.WeaponArmaryManagementSystem.repository.MagzineAccessoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/magzineAccessories")
@CrossOrigin(origins = "*")
public class MagzineAccessoriesController {
    @Autowired
    private MagzineAccessoriesRepository magzineAccessoriesRepository;

    @PostMapping("/saveMagzineAccessories")
    public ResponseEntity<String> createMagzineAccessories(@RequestBody MagzineAccessories magzineAccessories) {
        try{ // Set createdAt and updatedAt fields with current date and time
            LocalDateTime now = LocalDateTime.now();
            magzineAccessories.setCreatedAt(now);
            magzineAccessories.setUpdatedAt(now);

            MagzineAccessories savedMagzineAccessories = magzineAccessoriesRepository.save(magzineAccessories);
            return new ResponseEntity<>("{\"message\": \"MagzineAccessories added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"MagzineAccessories addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/indexMagzineAccessories")
    public List<MagzineAccessories> getAllMaterials() {
        return magzineAccessoriesRepository.findAllSortedByAccessoriesNames();
    }

    @PostMapping("/listMagzineAccessories")
    public ResponseEntity<List<String>> listActiveMagzineAccessories() {
        try {
            List<String> magzineAccessories = magzineAccessoriesRepository.findActiveAccessoriesNames();
            return new ResponseEntity<>(magzineAccessories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById/{id}")
    public ResponseEntity<MagzineAccessories> getMagzineAccessoriesById(@PathVariable Long id) {
        Optional<MagzineAccessories> magzineAccessories = magzineAccessoriesRepository.findById(id);
        return magzineAccessories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/updateById/{id}")
    public ResponseEntity<String> updateMagzineAccessories(@PathVariable Long id, @RequestBody MagzineAccessories updatedMagzineAccessories) {
        Optional<MagzineAccessories> existingMagzineAccessoriesOptional = magzineAccessoriesRepository.findById(id);
        if (existingMagzineAccessoriesOptional.isPresent()) {
            MagzineAccessories existingMagzineAccessories = existingMagzineAccessoriesOptional.get();

            // Retain the createdAt value from the existing entity
            updatedMagzineAccessories.setCreatedAt(existingMagzineAccessories.getCreatedAt());

            // Set updatedAt field with current date and time
            LocalDateTime now = LocalDateTime.now();
            updatedMagzineAccessories.setUpdatedAt(now);

            // Update the existing distribution magazine properties with the updated values

            existingMagzineAccessories.setAccessoriesName(updatedMagzineAccessories.getAccessoriesName());
       //     existingMagzineAccessories.setWeaponType(updatedMagzineAccessories.getWeaponType());
            existingMagzineAccessories.setStatus(updatedMagzineAccessories.getStatus());
            existingMagzineAccessories.setUpdatedAt(updatedMagzineAccessories.getUpdatedAt());

            // Update other properties similarly...

            MagzineAccessories savedMagzineAccessories = magzineAccessoriesRepository.save(existingMagzineAccessories);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"MagzineAccessories updated successful\",\"Id\": 0"+"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"MagzineAccessories not found\",\"Id\": 1"+"}");
        }
    }

}
