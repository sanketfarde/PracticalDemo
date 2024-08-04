package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeaponArmaryManagementSystem.Dto.InoutwardRegisteredDto;
import com.example.WeaponArmaryManagementSystem.model.InoutwardRegistered;
import com.example.WeaponArmaryManagementSystem.repository.InOutWardRegisteredRepository;

@RestController
@RequestMapping("/inOutWardRegistered")
@CrossOrigin(origins = "*")
public class InOutWardRegisteredController {

	@Autowired
	private InOutWardRegisteredRepository inOutWardRegisteredRepository;
	
	public InOutWardRegisteredController(InOutWardRegisteredRepository inOutWardRegisteredRepository) {
        this.inOutWardRegisteredRepository = inOutWardRegisteredRepository;
    }
	
	
	@PostMapping("/saveInwardRegistered")
    public ResponseEntity<String> createInwardRegistered(@RequestBody InoutwardRegistered inoutwardRegistered) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        inoutwardRegistered.setCreatedAt(now);
        inoutwardRegistered.setUpdatedAt(now);
        inoutwardRegistered.setButtNo(inoutwardRegistered.getButtNo());
        
     // Set default value for status column
        inoutwardRegistered.setStatus("0");

        InoutwardRegistered savedInoutwardRegistered = inOutWardRegisteredRepository.save(inoutwardRegistered);
        String responseMessage = "{\"message\": \"In ward registered  successfully\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } 
	
	
	@PostMapping("/saveOutwardRegistered")
    public ResponseEntity<String> createOutwardRegistered(@RequestBody InoutwardRegistered inoutwardRegistered) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        inoutwardRegistered.setCreatedAt(now);
        inoutwardRegistered.setUpdatedAt(now);
        
     // Set default value for status column
        inoutwardRegistered.setStatus("1");

        InoutwardRegistered savedInoutwardRegistered = inOutWardRegisteredRepository.save(inoutwardRegistered);
        String responseMessage = "{\"message\": \"Out ward registered successfully\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
	
	
	@PostMapping("/inwardRegisteredList")
    public List<InoutwardRegisteredDto> getAllInwardRegisteredWithStatusZero() {
        List<InoutwardRegistered> inoutwardRegistered = inOutWardRegisteredRepository.findByStatus("0");
        return inoutwardRegistered.stream()
                .map(InoutwardRegisteredDto::new)
                .toList(); // Requires Java 16 or higher, or use Collectors.toList() for older versions
    }
	
	
	@PostMapping("/outwardRegisteredList")
    public List<InoutwardRegisteredDto> getAllOutwardRegisteredWithStatusOne() {
        List<InoutwardRegistered> inoutwardRegistered = inOutWardRegisteredRepository.findByStatus("1");
        return inoutwardRegistered.stream()
                .map(InoutwardRegisteredDto::new)
                .toList(); // Requires Java 16 or higher, or use Collectors.toList() for older versions
    } 
	
	
	@PostMapping("/getById/{id}")
    public ResponseEntity<InoutwardRegistered> getInOutwardRegisteredById(@PathVariable Long id) {
        Optional<InoutwardRegistered> inoutwardRegistered = inOutWardRegisteredRepository.findById(id);
        return inoutwardRegistered.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
