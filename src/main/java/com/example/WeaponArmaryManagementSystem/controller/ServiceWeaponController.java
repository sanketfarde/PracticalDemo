package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.model.ServiceWeapon;
import com.example.WeaponArmaryManagementSystem.repository.ServiceWeaponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weapons")
@CrossOrigin(origins = "*")
public class ServiceWeaponController {
	
	@Autowired
    private ServiceWeaponRepo serviceWeaponRepo;

	
	
	@PostMapping("/saveWeapon")
	public ResponseEntity<String> createWeapon(@RequestBody ServiceWeapon serviceWeapon) {
	    try {
	        LocalDateTime now = LocalDateTime.now();	    
	        serviceWeapon.setCreatedAt(now);
	        serviceWeapon.setUpdatedAt(now);
	        ServiceWeapon savedServiceWeapon = serviceWeaponRepo.save(serviceWeapon);
	        return new ResponseEntity<>("{\"message\": \"Weapon added successfully\",\"Id\": 0"+"}", HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("{\"message\": \"Weapon addition failed\",\"Id\": 1"+"}", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	    
	    @PostMapping("/listWeapon")
	    public ResponseEntity<List<ServiceWeapon>> listWeapon() {
	        try {
	            List<ServiceWeapon> serviceWeapon = serviceWeaponRepo.findAll();
	            return new ResponseEntity<>(serviceWeapon, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	@PostMapping("/listWeaponNames")
	public ResponseEntity<List<String>> listActiveWeaponNames() {
		try {
			List<String> weaponNames = serviceWeaponRepo.findActiveWeaponNames();
			return new ResponseEntity<>(weaponNames, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/listRoundNames")
	public ResponseEntity<List<String>> listActiveRoundNames() {
		try {
			List<String> roundNames = serviceWeaponRepo.findActiveRoundNames();
			return new ResponseEntity<>(roundNames, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	    
	    @PostMapping("/getWeaponById/{id}")
	    public ResponseEntity<ServiceWeapon> getWeaponById(@PathVariable Integer id) {
	        try {
	            Optional<ServiceWeapon> weaponOptional = serviceWeaponRepo.findById(id);
	            if (weaponOptional.isPresent()) {
	                ServiceWeapon serviceWeapon = weaponOptional.get();
	                return new ResponseEntity<>(serviceWeapon, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    
	    @PostMapping("/updateById/{id}")
	    public ResponseEntity<String> updateWeapon(@PathVariable Integer id, @RequestBody ServiceWeapon updatedServiceWeapon) {
	        Optional<ServiceWeapon> optionalWeapon = serviceWeaponRepo.findById(id);
	        if (optionalWeapon.isPresent()) {
	            ServiceWeapon serviceWeapon = optionalWeapon.get();
	         // Retain the createdAt value from the existing entity
		        updatedServiceWeapon.setCreatedAt(updatedServiceWeapon.getCreatedAt());

		        // Set updatedAt field with current date and time
		        LocalDateTime now = LocalDateTime.now();
		        updatedServiceWeapon.setUpdatedAt(now);
	            serviceWeapon.setWeaponName(updatedServiceWeapon.getWeaponName());
	            serviceWeapon.setStatus(updatedServiceWeapon.getStatus());
	            serviceWeapon.setUpdatedAt(updatedServiceWeapon.getUpdatedAt());
	            serviceWeapon.setWeaponType(updatedServiceWeapon.getWeaponType());
	            
	            serviceWeaponRepo.save(serviceWeapon);
	            
	            return new ResponseEntity<>("{\"message\": \"Weapon updated successfully\",\"Id\": 0"+"}", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("{\"message\": \"Weapon does not updated\",\"Id\": 1"+"}", HttpStatus.NOT_FOUND);
	        }
	    }
	
}
