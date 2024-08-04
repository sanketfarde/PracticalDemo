package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Dto.DistributionMagzineDto;
import com.example.WeaponArmaryManagementSystem.Service.Impl.DistributionMagzineService;
import com.example.WeaponArmaryManagementSystem.Service.Impl.StockService;
import com.example.WeaponArmaryManagementSystem.Service.PoliceStationRegistrationService;
import com.example.WeaponArmaryManagementSystem.model.*;
import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/distributionMagzines")
@CrossOrigin(origins = "*")
public class DistributionMagzineController {


    @Autowired
    private StockService stockService;

    @Autowired
    private MaterialRepository materialRepository;
    
    @Autowired
    private DistributionMagzineRepository distributionMagzineRepository;

    @Autowired
    private DmButtNoAndManufacturingNoRepository dmButtNoAndManufacturingNoRepository;

 //   @Autowired
 //   private DistributionMagzineDto distributionMagzineDto;

    @Autowired
    private DistributionMagzineService distributionMagzineService;

    @Autowired
    private TotalStockRepository totalStockRepository;

    @Autowired
    private PoliceStationTotalRepository policeStationTotalRepository;

    @Autowired
    private PoliceStationRegistrationService policeStationRegistrationService;

    // Constructor injection
    public DistributionMagzineController(DistributionMagzineRepository distributionMagzineRepository) {
        this.distributionMagzineRepository = distributionMagzineRepository;
    }

    @GetMapping("/distinct-police-stations")
    public List<String> getDistinctPoliceStations() {
        return distributionMagzineService.getDistinctPoliceStationsWithWeaponName();
    }

    @GetMapping("/matching-police-stations")
    public List<String> getMatchingPoliceStationNames() {
        return distributionMagzineService.getMatchingPoliceStationNames();
    }

    @GetMapping("/police-stations-with-matching-names")
    public List<String> getPoliceStationsWithMatchingNames() {
        return distributionMagzineService.getDistinctPoliceStationsWithMatchingNames();
    }

    /*@GetMapping("/weapon-names")
    public ResponseEntity<List<String>> getWeaponNamesByPoliceStationName(@RequestParam String policeStationName) {
        List<String> weaponNames = distributionMagzineService.getWeaponNamesByPoliceStationName(policeStationName);
        return ResponseEntity.ok(weaponNames);
    }*/

    /*@PostMapping("/weapon-names/{policeStationName}")
    public ResponseEntity<List<String>> getWeaponNamesByPoliceStationName(@PathVariable String policeStationName) {
        List<String> weaponNames = distributionMagzineService.getWeaponNamesByPoliceStationName(policeStationName);
        return ResponseEntity.ok(weaponNames);
    }*/

    @PostMapping("/weapon-names/{policeStationName}")
    public ResponseEntity<List<String>> getWeaponNamesByPoliceStationName(@PathVariable String policeStationName) {
        List<String> weaponNames = distributionMagzineService.getWeaponNamesByPoliceStationName(policeStationName);

        if (weaponNames.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(weaponNames);
    }

    @PostMapping("/total-weapon/{weaponName}")
    public ResponseEntity<List<Long>> getTotalWeaponByWeaponName(@PathVariable String weaponName) {
        List<Long> totalWeapons = distributionMagzineService.getTotalWeaponByWeaponName(weaponName);

        if (totalWeapons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(totalWeapons);
    }

    @PostMapping("/total-weapons/{policeStationName}/{weaponName}")
    public ResponseEntity<Long> getTotalWeaponsByPoliceStationAndWeaponName(
            @PathVariable String policeStationName,
            @PathVariable String weaponName) {

        Long totalWeapons = distributionMagzineService.getTotalWeaponsByPoliceStationAndWeaponName(policeStationName, weaponName);

        if (totalWeapons == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(totalWeapons);
    }

/*
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon >= totalWeapon) {
                            totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                            totalStockByWeapon.setAvailableStock(availableStockWeapon - totalWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound >= totalRound) {
                            totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                            totalStockByRound.setAvailableStock(availableStockRound - totalRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + roundName);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }
            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TotalStock not found for " + weaponName);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TotalStock not found for " + roundName);
                }
            }
        }

        String responseMessage = "{\"message\": \"Distribution magzine info saved successful\",\"Id\": 0" + savedDistributionMagzine.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */


    /*  // Main Method of our project before accessories

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 1 }" + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 2 }" + roundName);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Set accessories as JSON string
           //     distributionMagzine.setAccessoriesList(distributionMagzineDto.getAccessories());

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                String responseMessage = "{\"message\": \"Distribution magzine info saved successful\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for Id : 3}" + weaponName);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for ID : 4}" + roundName);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

     */

    /*
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponType != null && !weaponType.isEmpty()) {
                material = materialRepository.findByWeaponType(weaponType);
            } else if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            if (material != null) {
                // Set accessories from Material to DistributionMagzine
                distributionMagzine.setAccessories(material.getAccessories());
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 1 }" + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 2 }" + roundName);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for Id : 3}" + weaponName);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for ID : 4}" + roundName);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

     */

/*
   // 100% main working after Accessores Distribution

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
       //     if (weaponType != null && !weaponType.isEmpty()) {
       //         material = materialRepository.findByWeaponType(weaponType);
       //     } else
                if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, log and return error
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName + "}");
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 1 }" + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 2 }" + roundName);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for Id : 3}" + weaponName);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for ID : 4}" + roundName);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

 */

    /*

    // 17-06-2024  method perfectly work only for change after sir implementation at evening

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }



            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 1 }" + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for ID : 2 }" + roundName);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                // Fetch the existing PoliceStationTotal entry
                PoliceStationTotal existingPoliceStationTotal = policeStationTotalRepository.findByPoliceStationNameAndWeaponNameAndRoundName(
                        distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName(), distributionMagzine.getRoundName());

               // Check if material is found and set accessories from Material to DistributionMagzine
                if (material != null) {
                    distributionMagzine.setAccessories(material.getAccessories());
                } else {
                    // If no material found, set accessories to null
                    distributionMagzine.setAccessories(null);
                }

                if (existingPoliceStationTotal != null) {
                    // Update existing entry
                    if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                        int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotal.getWeaponQuantity()) + totalWeapon;
                        existingPoliceStationTotal.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                    }

                    if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                        int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotal.getRoundQuantity()) + totalRound;
                        existingPoliceStationTotal.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                    }

                    if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        existingPoliceStationTotal.setWeaponStaticType("Short");
                    }

                    if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        existingPoliceStationTotal.setRoundStaticType("Short");
                    }

                    policeStationTotalRepository.save(existingPoliceStationTotal);

                } else {
                    // Create new entry
                    PoliceStationTotal newPoliceStationTotal = new PoliceStationTotal();
                    newPoliceStationTotal.setPoliceStationName(distributionMagzine.getPoliceStationName());

                    if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        newPoliceStationTotal.setWeaponName(distributionMagzine.getWeaponName());
                        newPoliceStationTotal.setWeaponStaticType("Short");
                    }

                    if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        newPoliceStationTotal.setRoundName(distributionMagzine.getRoundName());
                        newPoliceStationTotal.setRoundStaticType("Short");
                    }

                    newPoliceStationTotal.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");
                    newPoliceStationTotal.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");

                    policeStationTotalRepository.save(newPoliceStationTotal);
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for Id : 3}" + weaponName);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for ID : 4}" + roundName);
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }
    */

/*
    // 100% working upto sir requirement change at evening on monday 17-06-2024 (Main Method)
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                // Fetch the existing PoliceStationTotal entry for weapon
                PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationNameAndWeaponName(
                        distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

                // Fetch the existing PoliceStationTotal entry for round
                PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationNameAndRoundName(
                        distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

                // Update or create entry for weapon
                if (existingPoliceStationTotalWeapon != null) {
                    // Update existing entry for weapon
                    if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                        int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
                        existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                    }

                    if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        existingPoliceStationTotalWeapon.setWeaponStaticType("Short");
                    }

                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);

                } else if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                    // Create new entry for weapon
                    PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                    newPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
                    newPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
                    newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                    newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");

                    policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                }

                // Update or create entry for round
                if (existingPoliceStationTotalRound != null) {
                    // Update existing entry for round
                    if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                        int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
                        existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                    }

                    if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        existingPoliceStationTotalRound.setRoundStaticType("Short");
                    }

                    policeStationTotalRepository.save(existingPoliceStationTotalRound);

                } else if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                    // Create new entry for round
                    PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                    newPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
                    newPoliceStationTotalRound.setRoundName(distributionMagzine.getRoundName());
                    newPoliceStationTotalRound.setRoundStaticType("Short");
                    newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");

                    policeStationTotalRepository.save(newPoliceStationTotalRound);
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);



            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for weapon: " + weaponName + "}");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for round: " + roundName + "}");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

 */



/*



    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

               // Fetch the existing PoliceStationTotal entry for weapon
PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
        distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

// Fetch the existing PoliceStationTotal entry for round
PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
        distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

// Fetch the Material entry for weapon
Material weaponMaterial = materialRepository.findByMaterialName(distributionMagzine.getWeaponName());

// Fetch the Material entry for round
Material roundMaterial = materialRepository.findByMaterialName(distributionMagzine.getRoundName());

// Update or create entry for weapon
if (weaponMaterial != null && "Short".equalsIgnoreCase(weaponMaterial.getWeaponType())) {
    if (existingPoliceStationTotalWeapon != null) {
        // Update existing entry for weapon
        if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
            int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
            existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
        }

        if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
            existingPoliceStationTotalWeapon.setWeaponStaticType("Short");
        }

        policeStationTotalRepository.save(existingPoliceStationTotalWeapon);

    } else if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
        // Create new entry for weapon
        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
        newPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
        newPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
        newPoliceStationTotalWeapon.setWeaponStaticType("Short");
        newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");

        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
    }
}

// Update or create entry for round
if (roundMaterial != null && "Short".equalsIgnoreCase(roundMaterial.getWeaponType())) {
    if (existingPoliceStationTotalRound != null) {
        // Update existing entry for round
        if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
            int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
            existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
        }

        if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
            existingPoliceStationTotalRound.setRoundStaticType("Short");
        }

        policeStationTotalRepository.save(existingPoliceStationTotalRound);

    } else if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
        // Create new entry for round
        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
        newPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
        newPoliceStationTotalRound.setRoundName(distributionMagzine.getRoundName());
        newPoliceStationTotalRound.setRoundStaticType("Short");
        newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");

        policeStationTotalRepository.save(newPoliceStationTotalRound);
    }
}

String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);




            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for weapon: " + weaponName + "}");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for round: " + roundName + "}");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }


 */


    /* // 100% Working Main Method 20-06-2024 (Main Accurate Method)

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");



        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                }

                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzine.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(distributionMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                                int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
                                existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                        }
                    } else if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short"); // Set weaponStaticType to "Short" for new entries
                        newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                                int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
                                existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalRound);
                        }
                    } else if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(distributionMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundStaticType("Short"); // Set roundStaticType to "Short" for new entries
                        newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);



            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for weapon: " + weaponName + "}");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for round: " + roundName + "}");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

     */

/*  // Working method after applying all things before only round distribution error
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzineDto distributionMagzineDto) {
        DistributionMagzine distributionMagzine = new DistributionMagzine();

        distributionMagzine.setWeaponType(distributionMagzineDto.getWeaponType());
        distributionMagzine.setWeaponName(distributionMagzineDto.getWeaponName());
        distributionMagzine.setRoundName(distributionMagzineDto.getRoundName());
        distributionMagzine.setButtNoAsList(distributionMagzineDto.getButtNo());
        distributionMagzine.setManufacturingNoAsList(distributionMagzineDto.getManufacturingNo());
        distributionMagzine.setWeaponLocation(distributionMagzineDto.getWeaponLocation());
        distributionMagzine.setMobileNumber(distributionMagzineDto.getMobileNumber());
        distributionMagzine.setSevarthId(distributionMagzineDto.getSevarthId());
        distributionMagzine.setDistributedBy(distributionMagzineDto.getDistributedBy());
    //    distributionMagzine.setAccessoriesList(distributionMagzineDto.getAccessories());
        distributionMagzine.setDistributionType(distributionMagzineDto.getDistributionType());
        distributionMagzine.setWeaponCondition(distributionMagzineDto.getWeaponCondition());
        distributionMagzine.setIdentityType(distributionMagzineDto.getIdentityType());
        distributionMagzine.setIdentityNo(distributionMagzineDto.getIdentityNo());
        distributionMagzine.setPermanentAddress(distributionMagzineDto.getPermanentAddress());
        distributionMagzine.setTemporaryAddress(distributionMagzineDto.getTemporaryAddress());
        distributionMagzine.setDesignation(distributionMagzineDto.getDesignation());
        distributionMagzine.setBucckleNo(distributionMagzineDto.getBucckleNo());
        distributionMagzine.setBirthDate(distributionMagzineDto.getBirthDate());
        distributionMagzine.setDateOfRetirement(distributionMagzineDto.getDateOfRetirement());
        distributionMagzine.setPosting(distributionMagzineDto.getPosting());
        distributionMagzine.setPoliceStationName(distributionMagzineDto.getPoliceStationName());
        distributionMagzine.setIndividualName(distributionMagzineDto.getIndividualName());
        distributionMagzine.setDepositeRound(distributionMagzineDto.getDepositeRound());
        distributionMagzine.setWeaponCheckedBy(distributionMagzineDto.getWeaponCheckedBy());
        distributionMagzine.setServicingDate(distributionMagzineDto.getServicingDate());
        distributionMagzine.setDistributionDate(distributionMagzineDto.getDistributionDate());
        distributionMagzine.setDistributionTime(distributionMagzineDto.getDistributionTime());
        distributionMagzine.setSubmittedWeapon(distributionMagzineDto.getSubmittedWeapon());
        distributionMagzine.setRecievedWeaponCondition(distributionMagzineDto.getRecievedWeaponCondition());
        distributionMagzine.setRecievedWeaponCheckedBy(distributionMagzineDto.getRecievedWeaponCheckedBy());
        distributionMagzine.setIsWeaponDamage(distributionMagzineDto.getIsWeaponDamage());
        distributionMagzine.setRecievedWeaponDate(distributionMagzineDto.getRecievedWeaponDate());
        distributionMagzine.setRecievedWeaponTime(distributionMagzineDto.getRecievedWeaponTime());
        distributionMagzine.setUsedRound(distributionMagzineDto.getUsedRound());
        distributionMagzine.setSubmittedRound(distributionMagzineDto.getSubmittedRound());
        distributionMagzine.setDefectedRound(distributionMagzineDto.getDefectedRound());
        distributionMagzine.setRecievedRoundCondition(distributionMagzineDto.getRecievedRoundCondition());
        distributionMagzine.setRecievedroundCheckedBy(distributionMagzineDto.getRecievedroundCheckedBy());
        distributionMagzine.setRecievedRoundDate(distributionMagzineDto.getRecievedRoundDate());
        distributionMagzine.setRecievedRoundTime(distributionMagzineDto.getRecievedRoundTime());
        distributionMagzine.setIssueRemark(distributionMagzineDto.getIssueRemark());
        distributionMagzine.setSubmittedRemark(distributionMagzineDto.getSubmittedRemark());
        distributionMagzine.setTotalWeapon(distributionMagzineDto.getTotalWeapon());
        distributionMagzine.setDistributeEmployeeId(distributionMagzineDto.getDistributeEmployeeId());
        distributionMagzine.setDistributeEmployeeName(distributionMagzineDto.getDistributeEmployeeName());
        distributionMagzine.setDistributeEmployeeDesignation(distributionMagzineDto.getDistributeEmployeeDesignation());
        distributionMagzine.setDistributeEmployeePostingDate(distributionMagzineDto.getDistributeEmployeePostingDate());
        distributionMagzine.setRecievedEmployeeId(distributionMagzineDto.getRecievedEmployeeId());
        distributionMagzine.setRecievedEmployeeName(distributionMagzineDto.getRecievedEmployeeName());
        distributionMagzine.setRecievedEmployeeDesignation(distributionMagzineDto.getRecievedEmployeeDesignation());
        distributionMagzine.setRecievedEmployeePostingDate(distributionMagzineDto.getRecievedEmployeePostingDate());
        distributionMagzine.setStatus(distributionMagzineDto.getStatus());
        distributionMagzine.setCreatedAt(distributionMagzineDto.getCreatedAt());
        distributionMagzine.setUpdatedAt(distributionMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) ||
                (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzineDto.getWeaponName();
            String roundName = distributionMagzineDto.getRoundName();
            String weaponType = distributionMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = null;
            List<TotalStock> totalStockByRoundList = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzineDto.getTotalWeapon());
                }

                if (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzineDto.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < totalWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < totalRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);


                // Save entries in DmButtNoAndManufacturingNo table
                for (int i = 0; i < distributionMagzineDto.getButtNo().size(); i++) {
                    DmButtNoAndManufacturingNo dmButtNoAndManufacturingNo = new DmButtNoAndManufacturingNo();
                    dmButtNoAndManufacturingNo.setButtNo(distributionMagzineDto.getButtNo().get(i));
                    dmButtNoAndManufacturingNo.setManufacturingNo(distributionMagzineDto.getManufacturingNo().get(i));
                    dmButtNoAndManufacturingNo.setDistributionId(distributionMagzine.getId());
                    dmButtNoAndManufacturingNo.setWeaponName(distributionMagzineDto.getWeaponName());
                    dmButtNoAndManufacturingNo.setRoundName(distributionMagzineDto.getRoundName());
                    dmButtNoAndManufacturingNo.setWeaponType(distributionMagzineDto.getWeaponType());
                    dmButtNoAndManufacturingNoRepository.save(dmButtNoAndManufacturingNo);
                }



                // Update stocks
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                        totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                        totalStockRepository.save(totalStockByWeapon);
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                        totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                        totalStockRepository.save(totalStockByRound);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(distributionMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                                int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
                                existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                        }
                    } else if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short"); // Set weaponStaticType to "Short" for new entries
                        newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                                int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
                                existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalRound);
                        }
                    } else if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(distributionMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundStaticType("Short"); // Set roundStaticType to "Short" for new entries
                        newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No TotalStock found for weapon: " + weaponName + " or round: " + roundName);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound must not be null or empty");
    }

 */

     // 100% working method upto 24-06-2024
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzineDto distributionMagzineDto) {
        DistributionMagzine distributionMagzine = new DistributionMagzine();

        // Set fields from DTO to entity
        distributionMagzine.setWeaponType(distributionMagzineDto.getWeaponType());
        distributionMagzine.setWeaponName(distributionMagzineDto.getWeaponName());
        distributionMagzine.setRoundName(distributionMagzineDto.getRoundName());
        distributionMagzine.setWeaponLocation(distributionMagzineDto.getWeaponLocation());
        distributionMagzine.setMobileNumber(distributionMagzineDto.getMobileNumber());
        distributionMagzine.setButtNoAsList(distributionMagzineDto.getButtNo());
        distributionMagzine.setManufacturingNoAsList(distributionMagzineDto.getManufacturingNo());
        distributionMagzine.setSevarthId(distributionMagzineDto.getSevarthId());
        distributionMagzine.setDistributedBy(distributionMagzineDto.getDistributedBy());
        distributionMagzine.setDistributionType(distributionMagzineDto.getDistributionType());
        distributionMagzine.setWeaponCondition(distributionMagzineDto.getWeaponCondition());
        distributionMagzine.setIdentityType(distributionMagzineDto.getIdentityType());
        distributionMagzine.setIdentityNo(distributionMagzineDto.getIdentityNo());
        distributionMagzine.setPermanentAddress(distributionMagzineDto.getPermanentAddress());
        distributionMagzine.setTemporaryAddress(distributionMagzineDto.getTemporaryAddress());
        distributionMagzine.setDesignation(distributionMagzineDto.getDesignation());
        distributionMagzine.setBucckleNo(distributionMagzineDto.getBucckleNo());
        distributionMagzine.setBirthDate(distributionMagzineDto.getBirthDate());
        distributionMagzine.setDateOfRetirement(distributionMagzineDto.getDateOfRetirement());
        distributionMagzine.setPosting(distributionMagzineDto.getPosting());
        distributionMagzine.setPoliceStationName(distributionMagzineDto.getPoliceStationName());
        distributionMagzine.setIndividualName(distributionMagzineDto.getIndividualName());
        distributionMagzine.setDepositeRound(distributionMagzineDto.getDepositeRound());
        distributionMagzine.setWeaponCheckedBy(distributionMagzineDto.getWeaponCheckedBy());
        distributionMagzine.setServicingDate(distributionMagzineDto.getServicingDate());
        distributionMagzine.setDistributionDate(distributionMagzineDto.getDistributionDate());
        distributionMagzine.setDistributionTime(distributionMagzineDto.getDistributionTime());
        distributionMagzine.setSubmittedWeapon(distributionMagzineDto.getSubmittedWeapon());
        distributionMagzine.setRecievedWeaponCondition(distributionMagzineDto.getRecievedWeaponCondition());
        distributionMagzine.setRecievedWeaponCheckedBy(distributionMagzineDto.getRecievedWeaponCheckedBy());
        distributionMagzine.setIsWeaponDamage(distributionMagzineDto.getIsWeaponDamage());
        distributionMagzine.setRecievedWeaponDate(distributionMagzineDto.getRecievedWeaponDate());
        distributionMagzine.setRecievedWeaponTime(distributionMagzineDto.getRecievedWeaponTime());
        distributionMagzine.setUsedRound(distributionMagzineDto.getUsedRound());
        distributionMagzine.setSubmittedRound(distributionMagzineDto.getSubmittedRound());
        distributionMagzine.setDefectedRound(distributionMagzineDto.getDefectedRound());
        distributionMagzine.setRecievedRoundCondition(distributionMagzineDto.getRecievedRoundCondition());
        distributionMagzine.setRecievedroundCheckedBy(distributionMagzineDto.getRecievedroundCheckedBy());
        distributionMagzine.setRecievedRoundDate(distributionMagzineDto.getRecievedRoundDate());
        distributionMagzine.setRecievedRoundTime(distributionMagzineDto.getRecievedRoundTime());
        distributionMagzine.setIssueRemark(distributionMagzineDto.getIssueRemark());
        distributionMagzine.setSubmittedRemark(distributionMagzineDto.getSubmittedRemark());
        distributionMagzine.setTotalWeapon(distributionMagzineDto.getTotalWeapon());
        distributionMagzine.setDistributeEmployeeId(distributionMagzineDto.getDistributeEmployeeId());
        distributionMagzine.setDistributeEmployeeName(distributionMagzineDto.getDistributeEmployeeName());
        distributionMagzine.setDistributeEmployeeDesignation(distributionMagzineDto.getDistributeEmployeeDesignation());
        distributionMagzine.setDistributeEmployeePostingDate(distributionMagzineDto.getDistributeEmployeePostingDate());
        distributionMagzine.setRecievedEmployeeId(distributionMagzineDto.getRecievedEmployeeId());
        distributionMagzine.setRecievedEmployeeName(distributionMagzineDto.getRecievedEmployeeName());
        distributionMagzine.setRecievedEmployeeDesignation(distributionMagzineDto.getRecievedEmployeeDesignation());
        distributionMagzine.setRecievedEmployeePostingDate(distributionMagzineDto.getRecievedEmployeePostingDate());
        distributionMagzine.setStatus(distributionMagzineDto.getStatus());
        distributionMagzine.setCreatedAt(distributionMagzineDto.getCreatedAt());
        distributionMagzine.setUpdatedAt(distributionMagzineDto.getUpdatedAt());
        distributionMagzine.setBalanceWeapon(distributionMagzineDto.getTotalWeapon());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) ||
                (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzineDto.getWeaponName();
            String roundName = distributionMagzineDto.getRoundName();
            String weaponType = distributionMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName

            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponType != null && weaponType.equalsIgnoreCase("Round") && roundName != null && !roundName.isEmpty()) {
                material = materialRepository.findByMaterialName(roundName);
            } else if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

          // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzineDto.getTotalWeapon());
                }

                if (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzineDto.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < totalWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < totalRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Only save entries in DmButtNoAndManufacturingNo table if weapon details are provided
                if (distributionMagzineDto.getButtNo() != null && !distributionMagzineDto.getButtNo().isEmpty() && distributionMagzineDto.getManufacturingNo() != null && !distributionMagzineDto.getManufacturingNo().isEmpty()) {
                    for (int i = 0; i < distributionMagzineDto.getButtNo().size(); i++) {
                        DmButtNoAndManufacturingNo dmButtNoAndManufacturingNo = new DmButtNoAndManufacturingNo();
                        dmButtNoAndManufacturingNo.setButtNo(distributionMagzineDto.getButtNo().get(i));
                        dmButtNoAndManufacturingNo.setManufacturingNo(distributionMagzineDto.getManufacturingNo().get(i));
                        dmButtNoAndManufacturingNo.setDistributionId(distributionMagzine.getId());
                        dmButtNoAndManufacturingNo.setWeaponName(distributionMagzineDto.getWeaponName());
                        dmButtNoAndManufacturingNo.setRoundName(distributionMagzineDto.getRoundName());
                        dmButtNoAndManufacturingNo.setWeaponType(distributionMagzineDto.getWeaponType());
                        dmButtNoAndManufacturingNo.setDistributionStatus("0");
                        dmButtNoAndManufacturingNoRepository.save(dmButtNoAndManufacturingNo);
                    }
                } else {
                    distributionMagzine.setButtNoAsList(Collections.singletonList("null"));
                    distributionMagzine.setManufacturingNoAsList(Collections.singletonList("null"));
                }

                // Update stocks
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                        totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                        totalStockRepository.save(totalStockByWeapon);
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                        totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                        totalStockRepository.save(totalStockByRound);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(distributionMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                                int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
                                existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                        }
                    } else if (distributionMagzine.getWeaponName() != null && !distributionMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short"); // Set weaponStaticType to "Short" for new entries
                        newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                                int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
                                existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                            }
                            policeStationTotalRepository.save(existingPoliceStationTotalRound);
                        }
                    } else if (distributionMagzine.getRoundName() != null && !distributionMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(distributionMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundStaticType("Short"); // Set roundStaticType to "Short" for new entries
                        newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": 0 }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
            } else {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("message", "No TotalStock found for weapon: " + weaponName + " or round: " + roundName);
                errorResponse.put("Id", 1);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.toString());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound must not be null or empty");
    }








    /*

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzineDto distributionMagzineDto) {
        DistributionMagzine distributionMagzine = new DistributionMagzine();

        // Set fields from DTO to entity
        distributionMagzine.setWeaponType(distributionMagzineDto.getWeaponType());
        distributionMagzine.setWeaponName(distributionMagzineDto.getWeaponName());
        distributionMagzine.setRoundName(distributionMagzineDto.getRoundName());
        distributionMagzine.setButtNoAsList(distributionMagzineDto.getButtNo());
        distributionMagzine.setManufacturingNoAsList(distributionMagzineDto.getManufacturingNo());
        // set other fields...

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) ||
                (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzineDto.getWeaponName();
            String roundName = distributionMagzineDto.getRoundName();
            String weaponType = distributionMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = null;
            TotalStock totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Fetch Material by weapon type or weapon name
            Material material = null;
            if (weaponName != null && !weaponName.isEmpty()) {
                material = materialRepository.findByMaterialName(weaponName);
            }

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                // If no material found, set accessories to null and log
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + weaponType + " or weapon name: " + weaponName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (distributionMagzineDto.getTotalWeapon() != null && !distributionMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(distributionMagzineDto.getTotalWeapon());
                }

                if (distributionMagzineDto.getDepositeRound() != null && !distributionMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(distributionMagzineDto.getDepositeRound());
                }

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(distributionMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getWeaponName());

                    // If the existing entry doesn't exist, create a new entry
                    if (existingPoliceStationTotalWeapon == null) {
                        existingPoliceStationTotalWeapon = new PoliceStationTotal();
                        existingPoliceStationTotalWeapon.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        existingPoliceStationTotalWeapon.setWeaponName(distributionMagzine.getWeaponName());
                    }

                    // Update totalStock based on the deposited weapon quantity
                    existingPoliceStationTotalWeapon.setTotalStock(existingPoliceStationTotalWeapon.getTotalStock() + totalWeapon);
                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            distributionMagzine.getPoliceStationName(), distributionMagzine.getRoundName());

                    // If the existing entry doesn't exist, create a new entry
                    if (existingPoliceStationTotalRound == null) {
                        existingPoliceStationTotalRound = new PoliceStationTotal();
                        existingPoliceStationTotalRound.setPoliceStationId(distributionMagzine.getPoliceStationName());
                        existingPoliceStationTotalRound.setWeaponName(distributionMagzine.getRoundName());
                    }

                    // Update totalStock based on the deposited round quantity
                    existingPoliceStationTotalRound.setTotalStock(existingPoliceStationTotalRound.getTotalStock() + totalRound);
                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                }

                // Response after successful save and stock update
                return ResponseEntity.status(HttpStatus.CREATED).body("DistributionMagzine and stock updates saved successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalStock not found for weapon: " + weaponName + " or round: " + roundName);
            }
        } else {
            // Save the DistributionMagzine without stock updates
            distributionMagzineRepository.save(distributionMagzine);
            return ResponseEntity.status(HttpStatus.CREATED).body("DistributionMagzine saved successfully.");
        }
    }

     */


    /*
 // last try
    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);
        distributionMagzine.setStatus("0");

        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();

            TotalStock totalStockByWeapon = weaponName != null && !weaponName.isEmpty() ?
                    totalStockRepository.findByWeaponName(weaponName) : null;
            TotalStock totalStockByRound = roundName != null && !roundName.isEmpty() ?
                    totalStockRepository.findByRoundName(roundName) : null;

            Material material = weaponName != null && !weaponName.isEmpty() ?
                    materialRepository.findByMaterialName(weaponName) : null;

            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                distributionMagzine.setAccessories(null);
                System.out.println("Material not found for weapon type: " + distributionMagzine.getWeaponType() + " or weapon name: " + weaponName);
            }

            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                int totalWeapon = distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty() ?
                        Integer.parseInt(distributionMagzine.getTotalWeapon()) : 0;
                int totalRound = distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty() ?
                        Integer.parseInt(distributionMagzine.getDepositeRound()) : 0;

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int availableStockWeapon = totalStockByWeapon.getTotalStock() - totalStockByWeapon.getDistributionStock();
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int availableStockRound = totalStockByRound.getTotalStock() - totalStockByRound.getDistributionStock();
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for " + weaponName + " or " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon);
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon);
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound);
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound);
                    totalStockRepository.save(totalStockByRound);
                }

                // Update or create entries in PoliceStationTotal
                updatePoliceStationTotal(distributionMagzine, totalWeapon, totalRound);

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": " + savedDistributionMagzine.getId() + " }";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for weapon: " + weaponName + " or round: " + roundName + "}");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

    private void updatePoliceStationTotal(DistributionMagzine distributionMagzine, int totalWeapon, int totalRound) {
        String policeStationId = distributionMagzine.getPoliceStationName();
        String weaponName = distributionMagzine.getWeaponName();
        String roundName = distributionMagzine.getRoundName();

        PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(policeStationId, weaponName);
        PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(policeStationId, roundName);

        Material weaponMaterial = materialRepository.findByMaterialName(weaponName);
        Material roundMaterial = materialRepository.findByMaterialName(roundName);

        if (weaponMaterial != null && "Short".equalsIgnoreCase(weaponMaterial.getWeaponType())) {
            if (existingPoliceStationTotalWeapon != null) {
                if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                    int updatedWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity()) + totalWeapon;
                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                }
                if (weaponName != null && !weaponName.isEmpty()) {
                    existingPoliceStationTotalWeapon.setWeaponStaticType("Short");
                }
                policeStationTotalRepository.save(existingPoliceStationTotalWeapon);

            } else if (weaponName != null && !weaponName.isEmpty()) {
                PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                newPoliceStationTotalWeapon.setPoliceStationId(policeStationId);
                newPoliceStationTotalWeapon.setWeaponName(weaponName);
                newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                newPoliceStationTotalWeapon.setWeaponQuantity(distributionMagzine.getTotalWeapon() != null ? distributionMagzine.getTotalWeapon() : "0");

                policeStationTotalRepository.save(newPoliceStationTotalWeapon);
            }
        }

        if (roundMaterial != null && "Short".equalsIgnoreCase(roundMaterial.getWeaponType())) {
            if (existingPoliceStationTotalRound != null) {
                if (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty()) {
                    int updatedRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity()) + totalRound;
                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                }
                if (roundName != null && !roundName.isEmpty()) {
                    existingPoliceStationTotalRound.setRoundStaticType("Short");
                }
                policeStationTotalRepository.save(existingPoliceStationTotalRound);

            } else if (roundName != null && !roundName.isEmpty()) {
                PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                newPoliceStationTotalRound.setPoliceStationId(policeStationId);
                newPoliceStationTotalRound.setRoundName(roundName);
                newPoliceStationTotalRound.setRoundStaticType("Short");
                newPoliceStationTotalRound.setRoundQuantity(distributionMagzine.getDepositeRound() != null ? distributionMagzine.getDepositeRound() : "0");

                policeStationTotalRepository.save(newPoliceStationTotalRound);
            }
        }
    }

*/



    /*

    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody DistributionMagzine distributionMagzine) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        distributionMagzine.setCreatedAt(now);
        distributionMagzine.setUpdatedAt(now);

        // Set default value for status column
        distributionMagzine.setStatus("0");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) ||
                (distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty())) {

            String weaponName = distributionMagzine.getWeaponName();
            String roundName = distributionMagzine.getRoundName();
            String weaponType = distributionMagzine.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            TotalStock totalStockByWeapon = weaponName != null && !weaponName.isEmpty() ? totalStockRepository.findByWeaponName(weaponName) : null;
            TotalStock totalStockByRound = roundName != null && !roundName.isEmpty() ? totalStockRepository.findByRoundName(roundName) : null;

            // Fetch Material by weapon name
            Material material = weaponName != null && !weaponName.isEmpty() ? materialRepository.findByMaterialName(weaponName) : null;

            // Check if material is found and set accessories from Material to DistributionMagzine
            if (material != null) {
                distributionMagzine.setAccessories(material.getAccessories());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Material not found for weapon name: " + weaponName + "}");
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty() ? Integer.parseInt(distributionMagzine.getTotalWeapon()) : 0;
                int totalRound = distributionMagzine.getDepositeRound() != null && !distributionMagzine.getDepositeRound().isEmpty() ? Integer.parseInt(distributionMagzine.getDepositeRound()) : 0;

                if ("0".equals(distributionMagzine.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int availableStockWeapon = totalStockByWeapon.getTotalStock() - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon < totalWeapon) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for weapon: " + weaponName + "}");
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                        int availableStockRound = totalStockByRound.getTotalStock() - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound < totalRound) {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{Insufficient available stock for round: " + roundName + "}");
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Distribution status is not 0 for weapon: " + weaponName + " or round: " + roundName);
                }

                // Save DistributionMagzine after stock validation
                DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(distributionMagzine);

                // Update stocks
                if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() - totalWeapon); // Update available_stock
                    totalStockRepository.save(totalStockByWeapon);
                }

                if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + totalRound); // Update distribution_stock
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() - totalRound); // Update available_stock
                    totalStockRepository.save(totalStockByRound);
                }

                String responseMessage = "{\"message\": \"Distribution magazine info saved successfully\",\"Id\": " + savedDistributionMagzine.getId() + "}";
                return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

            } else {
                if (totalStockByWeapon == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for weapon: " + weaponName + "}");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{TotalStock not found for round: " + roundName + "}");
                }
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound cannot be both null or empty");
    }

     */

/*

   // Working
    @PostMapping("/distributedList")
    public List<DistributionMagzineDto> getAllDistributionMagzinesWithStatusZero() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByStatus("0");
        return distributionMagzines.stream()
                .map(DistributionMagzineDto::new)
                .toList(); // Requires Java 16 or higher, or use Collectors.toList() for older versions
    }


    @PostMapping("/returnedList")
    public List<DistributionMagzineDto> getAllDistributionMagzinesWithStatusOne() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByStatus("1");
        return distributionMagzines.stream()
                .map(DistributionMagzineDto::new)
                .toList(); // Requires Java 16 or higher, or use Collectors.toList() for older versions
    }

*/

    // New method to get records by distribution_type Police Station/ Adhibhar

   /* @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        return ResponseEntity.ok(distributionMagzines);
    }*/

    /*  //This is running method already
    @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<DistributionMagzineDto>> getDistributionMagzinesByDistributionType() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByDistributionType();
        List<DistributionMagzineDto> distributionMagzineDtos = distributionMagzines.stream()
                .map(DistributionMagzineDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(distributionMagzineDtos);
    }

     */

   /* @PostMapping("/getByIndividualDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForIndividuals() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Individual");
        return ResponseEntity.ok(distributionMagzines);
    }*/

    // Latest Used

  /*  @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        return ResponseEntity.ok(distributionMagzines);
    }*/

    /* // this method is already running
    @PostMapping("/getByIndividualDistributionType")
    public ResponseEntity<List<DistributionMagzineDto>> getDistributionMagzinesByIndividualDistributionType() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByIndividualDistributionType();
        List<DistributionMagzineDto> distributionMagzineDtos = distributionMagzines.stream()
                .map(DistributionMagzineDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(distributionMagzineDtos);
    }

     */

   /* // Main method upto policeStation name by their id
    @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<Map<String, Object>>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        if (distributionMagzines == null || distributionMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (DistributionMagzine distributionMagzine : distributionMagzines) {
            Map<String, Object> distributionMagzineMap = new HashMap<>();
            distributionMagzineMap.put("distributionMagzine", distributionMagzine);

            // Get count of buttNo and manufacturingNo
            int buttNoCount = distributionMagzineService.getButtNoCount(distributionMagzine.getId());
            int manufacturingNoCount = distributionMagzineService.getManufacturingNoCount(distributionMagzine.getId());

            distributionMagzineMap.put("buttNoCount", buttNoCount);
            distributionMagzineMap.put("manufacturingNoCount", manufacturingNoCount);

            result.add(distributionMagzineMap);
        }

        return ResponseEntity.ok(result);
    }*/

    @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<Map<String, Object>>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        if (distributionMagzines == null || distributionMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (DistributionMagzine distributionMagzine : distributionMagzines) {
            Map<String, Object> distributionMagzineMap = new HashMap<>();
            distributionMagzineMap.put("distributionMagzine", distributionMagzine);

            // Get count of buttNo and manufacturingNo
            int buttNoCount = distributionMagzineService.getButtNoCount(distributionMagzine.getId());
            int manufacturingNoCount = distributionMagzineService.getManufacturingNoCount(distributionMagzine.getId());

            distributionMagzineMap.put("buttNoCount", buttNoCount);
            distributionMagzineMap.put("manufacturingNoCount", manufacturingNoCount);

            // Fetch and include the police station name
            Long policeStationId = Long.parseLong(distributionMagzine.getPoliceStationName());
            String policeStationName = policeStationRegistrationService.getPoliceStationNameById(policeStationId);
            distributionMagzineMap.put("policeStationName", policeStationName);

            result.add(distributionMagzineMap);
        }

        return ResponseEntity.ok(result);
    }


   /* @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        if (distributionMagzines == null || distributionMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(distributionMagzines);
    }*/

    @PostMapping("/getByIndividualDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForIndividual() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Individual");
        if (distributionMagzines == null || distributionMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(distributionMagzines);
    }



 /*   @PostMapping("/getByPoliceStationDistributionType")
    public ResponseEntity<List<DistributionMagzine>> getDistributionMagzinesForPoliceStation() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineService.getDistributionMagzinesByType("Police Station/adhibhar");
        if (distributionMagzines == null || distributionMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(distributionMagzines);
    }*/
    
   /* @PostMapping("/getById/{id}")
    public ResponseEntity<DistributionMagzine> getDistributionMagzineById(@PathVariable Long id) {
        Optional<DistributionMagzine> distributionMagzine = distributionMagzineRepository.findById(id);
        return distributionMagzine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @PostMapping("/getById/{id}")
    public ResponseEntity<Map<String, Object>> getDistributionMagzineById(@PathVariable Long id) {
        Optional<DistributionMagzine> distributionMagzineOpt = distributionMagzineRepository.findById(id);
        if (distributionMagzineOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DistributionMagzine distributionMagzine = distributionMagzineOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("distributionMagzine", distributionMagzine);

        // Fetch and include the police station name
        String policeStationName = distributionMagzine.getPoliceStationName();
        if (policeStationName != null && !policeStationName.isEmpty()) {
            try {
                Long policeStationId = Long.parseLong(policeStationName);
                String actualPoliceStationName = policeStationRegistrationService.getPoliceStationNameById(policeStationId);
                response.put("policeStationName", actualPoliceStationName);
            } catch (NumberFormatException e) {
                // Handle the case where policeStationName is not a valid number
                response.put("policeStationName", "Invalid police station ID");
            }
        } else {
            response.put("policeStationName", "Unknown police station");
        }

        return ResponseEntity.ok(response);
    }


   /* @GetMapping("/police-stations/non-zero-balance-weapon")
    public List<String> getPoliceStationsWithNonZeroBalanceWeapon() {
        return distributionMagzineService.getPoliceStationNamesByNonZeroBalanceWeapon();
    }*/

/*
   // main method before Accessories
    @PostMapping("/updateById/{id}")
    public ResponseEntity<String> updateDistributionMagzine(@PathVariable Long id, @RequestBody DistributionMagzine updatedDistributionMagzine) {
        Optional<DistributionMagzine> existingDistributionMagzineOptional = distributionMagzineRepository.findById(id);
        if (existingDistributionMagzineOptional.isPresent()) {
            DistributionMagzine existingDistributionMagzine = existingDistributionMagzineOptional.get();

            // Retain the createdAt value from the existing entity
            updatedDistributionMagzine.setCreatedAt(existingDistributionMagzine.getCreatedAt());

            // Set updatedAt field with current date and time
            LocalDateTime now = LocalDateTime.now();
            updatedDistributionMagzine.setUpdatedAt(now);

            // Update the existing distribution magazine properties with the updated values
            existingDistributionMagzine.setUpdatedAt(updatedDistributionMagzine.getUpdatedAt());
            existingDistributionMagzine.setSubmittedWeapon(updatedDistributionMagzine.getSubmittedWeapon());
            existingDistributionMagzine.setSubmittedRound(updatedDistributionMagzine.getSubmittedRound());
            existingDistributionMagzine.setRecievedWeaponCondition(updatedDistributionMagzine.getRecievedWeaponCondition());
            existingDistributionMagzine.setRecievedWeaponCheckedBy(updatedDistributionMagzine.getRecievedWeaponCheckedBy());
            existingDistributionMagzine.setIsWeaponDamage(updatedDistributionMagzine.getIsWeaponDamage());
            existingDistributionMagzine.setRecievedWeaponDate(updatedDistributionMagzine.getRecievedWeaponDate());
            existingDistributionMagzine.setRecievedWeaponTime(updatedDistributionMagzine.getRecievedWeaponTime());
            existingDistributionMagzine.setUsedRound(updatedDistributionMagzine.getUsedRound());
            existingDistributionMagzine.setDefectedRound(updatedDistributionMagzine.getDefectedRound());
            existingDistributionMagzine.setRecievedRoundCondition(updatedDistributionMagzine.getRecievedRoundCondition());
            existingDistributionMagzine.setRecievedroundCheckedBy(updatedDistributionMagzine.getRecievedroundCheckedBy());
            existingDistributionMagzine.setRecievedRoundDate(updatedDistributionMagzine.getRecievedRoundDate());
            existingDistributionMagzine.setRecievedRoundTime(updatedDistributionMagzine.getRecievedRoundTime());
            existingDistributionMagzine.setSubmittedRemark(updatedDistributionMagzine.getSubmittedRemark());
            existingDistributionMagzine.setAccessories(updatedDistributionMagzine.getAccessories());

            // Update status column to 1
            existingDistributionMagzine.setStatus("1");

            DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(existingDistributionMagzine);

            // Update TotalStock based on submitted counts
            int submittedWeaponCount = 0;
            int submittedRoundCount = 0;

            if (updatedDistributionMagzine.getSubmittedWeapon() != null && !updatedDistributionMagzine.getSubmittedWeapon().isEmpty()) {
                submittedWeaponCount = Integer.parseInt(updatedDistributionMagzine.getSubmittedWeapon());
            }

            if (updatedDistributionMagzine.getSubmittedRound() != null && !updatedDistributionMagzine.getSubmittedRound().isEmpty()) {
                submittedRoundCount = Integer.parseInt(updatedDistributionMagzine.getSubmittedRound());
            }

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeapon = null;
            List<TotalStock> totalStockByRound = null;

            String weaponName = existingDistributionMagzine.getWeaponName();
            String roundName = existingDistributionMagzine.getRoundName();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = totalStockRepository.findByRoundName(roundName);
            }

            // Update TotalStock with submitted counts
            if (totalStockByWeapon != null && submittedWeaponCount > 0) {
                int distributionStockWeapon = totalStockByWeapon.getDistributionStock() - submittedWeaponCount; // Calculate new distribution_stock
                totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + submittedWeaponCount); // Update available_stock
                totalStockRepository.save(totalStockByWeapon);
            }

            if (totalStockByRound != null && submittedRoundCount > 0) {
                int distributionStockRound = totalStockByRound.getDistributionStock() - submittedRoundCount; // Calculate new distribution_stock
                totalStockByRound.setDistributionStock(distributionStockRound);
                totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + submittedRoundCount); // Update available_stock
                totalStockRepository.save(totalStockByRound);
            }

            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Return Updated  successful\",\"Id\": 0" + "}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Return Updated  unsuccessful\",\"Id\": 1" + "}");
        }
    }

 */



    /*
    @PostMapping("/updateById/{id}")
    public ResponseEntity<String> updateDistributionMagzine(@PathVariable Long id, @RequestBody DistributionMagzine updatedDistributionMagzine) {
        Optional<DistributionMagzine> existingDistributionMagzineOptional = distributionMagzineRepository.findById(id);
        if (existingDistributionMagzineOptional.isPresent()) {
            DistributionMagzine existingDistributionMagzine = existingDistributionMagzineOptional.get();

            // Retain the createdAt value from the existing entity
            updatedDistributionMagzine.setCreatedAt(existingDistributionMagzine.getCreatedAt());

            // Set updatedAt field with current date and time
            LocalDateTime now = LocalDateTime.now();
            updatedDistributionMagzine.setUpdatedAt(now);

            // Update the existing distribution magazine properties with the updated values
            existingDistributionMagzine.setUpdatedAt(updatedDistributionMagzine.getUpdatedAt());
            existingDistributionMagzine.setSubmittedWeapon(updatedDistributionMagzine.getSubmittedWeapon());
            existingDistributionMagzine.setSubmittedRound(updatedDistributionMagzine.getSubmittedRound());
            existingDistributionMagzine.setRecievedWeaponCondition(updatedDistributionMagzine.getRecievedWeaponCondition());
            existingDistributionMagzine.setRecievedWeaponCheckedBy(updatedDistributionMagzine.getRecievedWeaponCheckedBy());
            existingDistributionMagzine.setIsWeaponDamage(updatedDistributionMagzine.getIsWeaponDamage());
            existingDistributionMagzine.setRecievedWeaponDate(updatedDistributionMagzine.getRecievedWeaponDate());
            existingDistributionMagzine.setRecievedWeaponTime(updatedDistributionMagzine.getRecievedWeaponTime());
            existingDistributionMagzine.setUsedRound(updatedDistributionMagzine.getUsedRound());
            existingDistributionMagzine.setDefectedRound(updatedDistributionMagzine.getDefectedRound());
            existingDistributionMagzine.setRecievedRoundCondition(updatedDistributionMagzine.getRecievedRoundCondition());
            existingDistributionMagzine.setRecievedroundCheckedBy(updatedDistributionMagzine.getRecievedroundCheckedBy());
            existingDistributionMagzine.setRecievedRoundDate(updatedDistributionMagzine.getRecievedRoundDate());
            existingDistributionMagzine.setRecievedRoundTime(updatedDistributionMagzine.getRecievedRoundTime());
            existingDistributionMagzine.setSubmittedRemark(updatedDistributionMagzine.getSubmittedRemark());

            // Convert accessories list to JSON string and update accessories
            try {
                existingDistributionMagzine.setAccessories(updatedDistributionMagzine.getAccessories());
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Invalid accessories format\"}");
            }

            // Update status column to 1
            existingDistributionMagzine.setStatus("1");

            DistributionMagzine savedDistributionMagzine = distributionMagzineRepository.save(existingDistributionMagzine);

            // Update TotalStock based on submitted counts
            int submittedWeaponCount = 0;
            int submittedRoundCount = 0;

            if (updatedDistributionMagzine.getSubmittedWeapon() != null && !updatedDistributionMagzine.getSubmittedWeapon().isEmpty()) {
                submittedWeaponCount = Integer.parseInt(updatedDistributionMagzine.getSubmittedWeapon());
            }

            if (updatedDistributionMagzine.getSubmittedRound() != null && !updatedDistributionMagzine.getSubmittedRound().isEmpty()) {
                submittedRoundCount = Integer.parseInt(updatedDistributionMagzine.getSubmittedRound());
            }

            // Update TotalStock for Weapon
            if (submittedWeaponCount > 0) {
                TotalStock totalStockByWeapon = totalStockRepository.findByWeaponName(existingDistributionMagzine.getWeaponName());
                if (totalStockByWeapon != null) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() - submittedWeaponCount);
                    totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + submittedWeaponCount);
                    totalStockRepository.save(totalStockByWeapon);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"TotalStock not found for Weapon: " + existingDistributionMagzine.getWeaponName() + "\"}");
                }
            }

            // Update TotalStock for Round
            if (submittedRoundCount > 0) {
                TotalStock totalStockByRound = totalStockRepository.findByRoundName(existingDistributionMagzine.getRoundName());
                if (totalStockByRound != null) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() - submittedRoundCount);
                    totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + submittedRoundCount);
                    totalStockRepository.save(totalStockByRound);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"TotalStock not found for Round: " + existingDistributionMagzine.getRoundName() + "\"}");
                }
            }

            String responseMessage = "{\"message\": \"Distribution magazine info updated successfully\",\"Id\": " + savedDistributionMagzine.getId() + "}";
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"DistributionMagzine not found with id: " + id + "\"}");
        }
    }

     */



    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteDistributionMagzine(@PathVariable Long id) {
        Optional<DistributionMagzine> distributionMagzine = distributionMagzineRepository.findById(id);
        if (distributionMagzine.isPresent()) {
            distributionMagzineRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("DistributionMagzine Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("DistributionMagzine Id Not Found");
        }
    }


    // Created by Vikas & integrated by faiz.............


    @PostMapping("/serviceAlerts")
    //  public ResponseEntity<List<DistributionMagzineDto>> getAlerts() {
    public ResponseEntity<List<DistributionMagzine>> getAlert() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the date 10 days from now
        LocalDate tenDaysLater = currentDate.plusDays(10);

        // Format the dates to match the format in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateStr = currentDate.format(formatter);
        String tenDaysLaterStr = tenDaysLater.format(formatter);

        // Get distribution magazines with dateOfRetirement within the next 10 days or already passed
        List<DistributionMagzine> alerts = distributionMagzineRepository.findAlert(currentDateStr, tenDaysLaterStr);

        // Convert to DTO and return
        //  List<DistributionMagzineDto> dtos = alerts.stream()
        //  .map(DistributionMagzineDto::new)
        // .collect(Collectors.toList());
        return ResponseEntity.ok(alerts);
        // return ResponseEntity.ok(dtos);
    }


    
    @PostMapping("/retireAlerts")
    //  public ResponseEntity<List<DistributionMagzineDto>> getAlerts() {
    public ResponseEntity<List<DistributionMagzine>> getAlerts() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the date 10 days from now
        LocalDate tenDaysLater = currentDate.plusDays(10);

        // Format the dates to match the format in the database
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDateStr = currentDate.format(formatter);
        String tenDaysLaterStr = tenDaysLater.format(formatter);

        // Get distribution magazines with dateOfRetirement within the next 10 days or already passed
        List<DistributionMagzine> alerts = distributionMagzineRepository.findAlerts(currentDateStr, tenDaysLaterStr);

        // Convert to DTO and return
        //  List<DistributionMagzineDto> dtos = alerts.stream()
        //  .map(DistributionMagzineDto::new)
        // .collect(Collectors.toList());
        return ResponseEntity.ok(alerts);
        // return ResponseEntity.ok(dtos);
    }

/*
    // Working
    @PostMapping("/search")
    public ResponseEntity<List<DistributionMagzineDto>> searchDistributionMagzines(
            @RequestParam(required = false) String buttNo,
            @RequestParam(required = false) String weaponName,
            //@RequestParam(required = false) String outwardNo,
            @RequestParam(required = false) String policeStationName,
            @RequestParam(required = false) String mobileNumber,             
            @RequestParam(required = false) String identityNo,
            @RequestParam(required = false) String dateOfRetirement,
            @RequestParam(required = false) String manufacturingNo,
            @RequestParam(required = false) String servicingDate,
            @RequestParam(required = false) String distributionStartDate,
            @RequestParam(required = false) String distributionEndDate,
            @RequestParam(required = false) String receivedStartDate,
            @RequestParam(required = false) String receivedEndDate) {

        List<DistributionMagzine> distributionMagzines;

        // Check if a date range is provided for distribution date
        if (!StringUtils.isEmpty(distributionStartDate) && !StringUtils.isEmpty(distributionEndDate)) {
            distributionMagzines = distributionMagzineRepository.findByDistributionDateBetween(distributionStartDate, distributionEndDate);
        }
        // Check if a date range is provided for received weapon date
        else if (!StringUtils.isEmpty(receivedStartDate) && !StringUtils.isEmpty(receivedEndDate)) {
            distributionMagzines = distributionMagzineRepository.findByRecievedWeaponDateBetween(receivedStartDate, receivedEndDate);
        }
        // Check other parameters
        else {
            if (!StringUtils.isEmpty(buttNo)) {
                distributionMagzines = distributionMagzineRepository.findByButtNo(buttNo);
            } else if (!StringUtils.isEmpty(weaponName)) {
                distributionMagzines = distributionMagzineRepository.findByWeaponName(weaponName);
            } else if (!StringUtils.isEmpty(policeStationName)) {
                distributionMagzines = distributionMagzineRepository.findByPoliceStationName(policeStationName);
            } else if (!StringUtils.isEmpty(mobileNumber)) {
                distributionMagzines = distributionMagzineRepository.findByMobileNumber(mobileNumber);
            } else if (!StringUtils.isEmpty(identityNo)) {
                distributionMagzines = distributionMagzineRepository.findByIdentityNo(identityNo);
            } else if (!StringUtils.isEmpty(dateOfRetirement)) {
                distributionMagzines = distributionMagzineRepository.findByDateOfRetirement(dateOfRetirement);
            } else if (!StringUtils.isEmpty(manufacturingNo)) {
                distributionMagzines = distributionMagzineRepository.findByManufacturingNo(manufacturingNo);
            } else if (!StringUtils.isEmpty(servicingDate)) {
                distributionMagzines = distributionMagzineRepository.findByServicingDate(servicingDate);
                // } else if (!StringUtils.isEmpty(outwardNo)) {
                //   distributionMagzines = outwardRepository.findByoutwardNo(outwardNo)  ;
            } else {
                // If none of the parameters are provided, return bad request
                return ResponseEntity.badRequest().build();
            }
        }

        // Convert to DTO and return
        List<DistributionMagzineDto> dtos = distributionMagzines.stream()
                .map(DistributionMagzineDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    */

    @PostMapping("/udr/{id}")
    public ResponseEntity<String> updateDistributionMagzine1(@PathVariable Long id, @RequestBody DistributionMagzine request) {
        try {
            Optional<DistributionMagzine> optionalDistributionMagzine = distributionMagzineRepository.findById(id);
            if (optionalDistributionMagzine.isPresent()) {
                DistributionMagzine distributionMagzine = optionalDistributionMagzine.get();

                // Update fields if they are provided in the request
                if (request.getServicingDate() != null) {
                    distributionMagzine.setServicingDate(request.getServicingDate());
                }
                if (request.getButtNo() != null) {
                    distributionMagzine.setButtNo(request.getButtNo());
                }
                if (request.getWeaponName() != null) {
                    distributionMagzine.setWeaponName(request.getWeaponName());
                }
                if (request.getIndividualName() != null) {
                    distributionMagzine.setIndividualName(request.getIndividualName());
                }
                if (request.getMobileNumber() != null) {
                    distributionMagzine.setMobileNumber(request.getMobileNumber());
                }

                // Update updatedAt field with current date and time
                distributionMagzine.setUpdatedAt(LocalDateTime.now());

                // Save the updated entity
                distributionMagzineRepository.save(distributionMagzine);

                return ResponseEntity.ok("{\"message\": \"DistributionMagzine updated successfully, id=0\"}");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to update DistributionMagzine, id=1\"}");
        }
    }

   /*
    // Working API off for probleam

    @PostMapping("/distributionListByWeaponOrRound")
    public List<DistributionMagzineDto> getAllDistributionMagzinesByWeaponOrRoundAndStatusZero(@RequestParam(required = false) String weaponName,
                                                                                               @RequestParam(required = false) String roundName) {
        if (weaponName != null && !weaponName.isEmpty()) {
            List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByWeaponNameAndStatus(weaponName, "0");
            return distributionMagzines.stream()
                    .map(DistributionMagzineDto::new)
                    .toList();
        } else if (roundName != null && !roundName.isEmpty()) {
            List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByRoundNameAndStatus(roundName, "0");
            return distributionMagzines.stream()
                    .map(DistributionMagzineDto::new)
                    .toList();
        } else {
            return new ArrayList<>(); // Or handle the case where neither weaponName nor roundName is provided
        }
    }
*/



  /*   // working
    @PostMapping("/GetDistributionMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<DistributionMagzine> reports = distributionMagzineService.getReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }*/
//10/06/2024
    @PostMapping("/GetDistributionMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<DistributionMagzine> reports = distributionMagzineRepository.findByStatusAndDistributionDateBetween("0", startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    // GetByDate DistributionDate  10-06-2024  By Chandirka
    @PostMapping("/GetDistributionMagzineReturnBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getReportsByDateRange1(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<DistributionMagzine> reports = distributionMagzineRepository.findByStatusAndDistributionDateBetween("1", startDate, endDate);
        return ResponseEntity.ok(reports);
    }



    /*
    @PostMapping("/GetDistributionMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getDistributionReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<DistributionMagzine> reports = distributionMagzineService.getDistributionReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/GetReturnMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getReturnReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<DistributionMagzine> reports = distributionMagzineService.getReturnReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

     */


    /*
    @PostMapping("/GetDistributionMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getDistributionReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DistributionMagzine> reports = distributionMagzineService.getDistributionReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    @PostMapping("/GetReturnMagzineBetweenDates")
    public ResponseEntity<List<DistributionMagzine>> getReturnReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DistributionMagzine> reports = distributionMagzineService.getReturnReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }

     */


    @PostMapping("/stockSummary")
    public ResponseEntity<Map<String, Object>> getStockSummary() {
        Map<String, Object> summary = new HashMap<>();

        // Summarizing stock where weapon name is present
        summary.put("TotalStockWhereWeaponNameIsPresent", distributionMagzineRepository.findTotalStockWhereWeaponNameIsPresent());
        summary.put("TotalStockWhereRoundNameIsPresent", distributionMagzineRepository.findTotalStockWhereRoundNameIsPresent());

        // Summarizing available stock where weapon name is present
        summary.put("AvailableStockWhereWeaponNameIsPresent", distributionMagzineRepository.findTotalAvailableStockWhereWeaponNameIsPresent());
        summary.put("AvailableStockWhereRoundNameIsPresent", distributionMagzineRepository.findTotalAvailableStockWhereRoundNameIsPresent());

        // Summarizing distribution stock where weapon name is present
        summary.put("DistributionStockWhereWeaponNameIsPresent", distributionMagzineRepository.findTotalDistributionStockWhereWeaponNameIsPresent());
        summary.put("DistributionStockWhereRoundNameIsPresent", distributionMagzineRepository.findTotalDistributionStockWhereRoundNameIsPresent());

        // Adding expired stock counts
        summary.put("ExpiredWeaponStock", stockService.getExpiredWeaponsCount());
        summary.put("ExpiredRoundStock", stockService.getExpiredRoundsCount());

        return new ResponseEntity<>(summary, HttpStatus.OK);
        }

}


