package com.example.WeaponArmaryManagementSystem.controller;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.example.WeaponArmaryManagementSystem.Service.Impl.DistributionMagzineService;
import com.example.WeaponArmaryManagementSystem.Service.PoliceStationRegistrationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.model.PoliceStationRegistration;
import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import com.example.WeaponArmaryManagementSystem.repository.PoliceStationRegistrationRepository;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseUserRepository;

@RestController
@RequestMapping("policeRegister")
public class PoliceStationRegistrationController {

	
	    @Autowired
	    private PoliceStationRegistrationRepository policeStationRegistrationRepository;

	    @Autowired
	    private WareHouseUserRepository wareHouseUserRepository;

	    @Autowired
	    private PoliceStationRegistrationService policeStationRegistrationService;

	    @Autowired
	    DistributionMagzineService distributionMagzineService;

	    @PostMapping("/savePoliceStation")
	    public ResponseEntity<String> savePoliceStationRegistration(@RequestBody PoliceStationRegistration policeStationRegistration) {
	        PoliceStationRegistration savedPoliceStation = policeStationRegistrationRepository.save(policeStationRegistration);

	        WareHouseUser wareHouseUser = new WareHouseUser();
	        wareHouseUser.setName(savedPoliceStation.getName());
	        wareHouseUser.setAddress(savedPoliceStation.getAddress());
	        wareHouseUser.setEmail(savedPoliceStation.getEmail());
	        wareHouseUser.setPhone(savedPoliceStation.getPhone());
	        wareHouseUser.setDepartmentId(savedPoliceStation.getDepartmentId());
	        wareHouseUser.setStatus(savedPoliceStation.getStatus().equalsIgnoreCase("active"));
	        // Set other attributes as needed

	        WareHouseUser savedWareHouseUser = wareHouseUserRepository.save(wareHouseUser);

	        if (savedPoliceStation != null && savedWareHouseUser != null) {
	            return new ResponseEntity<>("{\"message\": \"Data saved successfully...\",\"Id\": 0}", HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>("{\"message\": \"Data not saved successfully...\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    

	    @PostMapping("/getAllPoliceStations")
	    public ResponseEntity<List<PoliceStationRegistration>> getAllPoliceStations() {
	        List<PoliceStationRegistration> policeStations = policeStationRegistrationRepository.findAll();
	        return new ResponseEntity<>(policeStations, HttpStatus.OK);
	    }

	/*@PostMapping("/from-distribution-magzine")
	public ResponseEntity<List<PoliceStationRegistration>> getPoliceStationsFromDistributionMagzine() {
		List<PoliceStationRegistration> policeStations = distributionMagzineService.getPoliceStationsFromDistributionMagzine();
		return ResponseEntity.ok(policeStations);
	}*/

	/*@PostMapping("/from-distribution-magazine")
	public ResponseEntity<List<PoliceStationRegistration>> getPoliceStationsFromDistributionMagzine() {
		List<PoliceStationRegistration> policeStations = distributionMagzineService.getPoliceStationsFromDistributionMagzine();
		if (policeStations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(policeStations, HttpStatus.OK);
	}*/

	/*@PostMapping("/from-distribution-magazine")
	public ResponseEntity<List<PoliceStationRegistration>> getPoliceStationsFromDistributionMagzine() {
		List<PoliceStationRegistration> policeStations = distributionMagzineService.getPoliceStationsFromDistributionMagzine();

		if (policeStations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(policeStations, HttpStatus.OK);
	}*/

	private static final Logger logger = LoggerFactory.getLogger(PoliceStationRegistrationController.class);

	@PostMapping("/from-distribution-magazine")
	public ResponseEntity<List<PoliceStationRegistration>> getPoliceStationsFromDistributionMagzine() {
		List<PoliceStationRegistration> policeStations = distributionMagzineService.getPoliceStationsFromDistributionMagzine();

		if (policeStations.isEmpty()) {
			logger.info("No police stations found.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		logger.info("Returning police stations: {}", policeStations);
		return new ResponseEntity<>(policeStations, HttpStatus.OK);
	}


	/*@PostMapping("/getNamesWithNonZeroBalanceWeapon")
	public ResponseEntity<List<String>> getPoliceStationNamesWithNonZeroBalanceWeapon() {
		List<String> names = policeStationRegistrationService.getPoliceStationNamesWithNonZeroBalanceWeapon();
		if (names == null || names.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(names);
	}*/
	
}
