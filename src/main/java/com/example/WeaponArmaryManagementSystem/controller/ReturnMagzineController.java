package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Dto.ReturnMagzineDto;
import com.example.WeaponArmaryManagementSystem.Service.PoliceStationRegistrationService;
import com.example.WeaponArmaryManagementSystem.Service.ReturnMagzineService;
import com.example.WeaponArmaryManagementSystem.model.*;
import com.example.WeaponArmaryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/returnMagzines")
@CrossOrigin(origins = "*")
public class ReturnMagzineController {

    @Autowired
    private TotalStockRepository totalStockRepository;

    @Autowired
    private RmButtNoAndManufacturingNoRepository rmButtNoAndManufacturingNoRepository;

    @Autowired
    private ReturnMagzineRepository returnMagzineRepository;

    @Autowired
    private PoliceStationTotalRepository policeStationTotalRepository;

    @Autowired
    private DistributionMagzineRepository distributionMagzineRepository;

    @Autowired
    private ReturnMagzineService returnMagzineService;

    @Autowired
    private PoliceStationRegistrationService policeStationRegistrationService;

/*    @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        returnMagzine.setCreatedAt(now);
        returnMagzine.setUpdatedAt(now);

        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) ||
                (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(returnMagzineDto.getTotalWeapon());
                }

                if (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(returnMagzineDto.getDepositeRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < totalWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < totalRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Save ReturnMagzine after stock validation
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo table if weapon details are provided
                if (returnMagzineDto.getButtNo() != null && !returnMagzineDto.getButtNo().isEmpty() && returnMagzineDto.getManufacturingNo() != null && !returnMagzineDto.getManufacturingNo().isEmpty()) {
                    for (int i = 0; i < returnMagzineDto.getButtNo().size(); i++) {
                        RmButtNoAndManufacturingNo rmButtNoAndManufacturingNo = new RmButtNoAndManufacturingNo();
                        rmButtNoAndManufacturingNo.setButtNo(returnMagzineDto.getButtNo().get(i));
                        rmButtNoAndManufacturingNo.setManufacturingNo(returnMagzineDto.getManufacturingNo().get(i));
                        rmButtNoAndManufacturingNo.setReturnId(savedReturnMagzine.getId());
                        rmButtNoAndManufacturingNo.setRoundName(returnMagzineDto.getRoundName());
                        rmButtNoAndManufacturingNo.setWeaponName(returnMagzineDto.getWeaponName());
                        rmButtNoAndManufacturingNo.setWeaponType(returnMagzineDto.getWeaponType());
                        rmButtNoAndManufacturingNo.setReturnStatus("1");
                        rmButtNoAndManufacturingNoRepository.save(rmButtNoAndManufacturingNo);
                    }
                } else {
                    returnMagzine.setButtNoAsList(Collections.singletonList("null"));
                    returnMagzine.setManufacturingNoAsList(Collections.singletonList("null"));
                }

                // Update TotalStock after saving ReturnMagzine
                if ("1".equals(savedReturnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                            int newDistributionStockWeapon = currentDistributionStockWeapon + totalWeapon;
                            totalStockByWeapon.setDistributionStock(newDistributionStockWeapon);
                            totalStockRepository.save(totalStockByWeapon);
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                            int newDistributionStockRound = currentDistributionStockRound + totalRound;
                            totalStockByRound.setDistributionStock(newDistributionStockRound);
                            totalStockRepository.save(totalStockByRound);
                        }
                    }
                }

                return ResponseEntity.ok("ReturnMagzine saved successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TotalStock not found for weapon: " + weaponName + " or round: " + roundName);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound cannot be null or empty.");
        }
    }

 */

/*

    //  Working method Upto dont save entry in retrunMagzine table
    @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        returnMagzine.setCreatedAt(now);
        returnMagzine.setUpdatedAt(now);

        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) ||
                (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(returnMagzineDto.getTotalWeapon());
                }

                if (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(returnMagzineDto.getDepositeRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < totalWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < totalRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Save ReturnMagzine after stock validation
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo if weapon is submitted
                if (returnMagzine.getSubmittedWeapon() != null && !returnMagzine.getSubmittedWeapon().isEmpty()) {
                    List<String> buttNoList = returnMagzine.getButtNoAsList();
                    List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                    for (int i = 0; i < buttNoList.size(); i++) {
                        RmButtNoAndManufacturingNo rmEntry = new RmButtNoAndManufacturingNo();
                        rmEntry.setButtNo(buttNoList.get(i));
                        rmEntry.setManufacturingNo(manufacturingNoList.get(i));
                        rmEntry.setReturnId(savedReturnMagzine.getId());
                        rmEntry.setRoundName(returnMagzineDto.getRoundName());
                        rmEntry.setWeaponName(returnMagzineDto.getWeaponName());
                        rmEntry.setWeaponType(returnMagzineDto.getWeaponType());
                        rmEntry.setReturnStatus("1");
                      //  rmEntry.setReturnMagzine(savedReturnMagzine);
                        rmButtNoAndManufacturingNoRepository.save(rmEntry);
                    }
                }

                // Update distribution stock and available stock in TotalStock
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null) {
                        int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                        if (totalWeapon <= currentDistributionStockWeapon) {
                            int distributionStockWeapon = currentDistributionStockWeapon - totalWeapon; // Calculate new distribution_stock
                            totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                            totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + totalWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for weapon exceeds distributed quantity for weapon: " + returnMagzine.getWeaponName());
                        }
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null) {
                        int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                        if (totalRound <= currentDistributionStockRound) {
                            int distributionStockRound = currentDistributionStockRound - totalRound; // Calculate new distribution_stock
                            totalStockByRound.setDistributionStock(distributionStockRound);
                            totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + totalRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for round exceeds distributed quantity for round: " + returnMagzine.getRoundName());
                        }
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(returnMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null && totalWeapon > 0) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (returnMagzine.getTotalWeapon() != null && !returnMagzine.getTotalWeapon().isEmpty()) {
                                int currentWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity());
                                if (currentWeaponQuantity >= totalWeapon) {
                                    int updatedWeaponQuantity = currentWeaponQuantity - totalWeapon;
                                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for weapon: " + returnMagzine.getWeaponName());
                                }
                            }
                        }
                    } else if (returnMagzine.getWeaponName() != null && !returnMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(returnMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short"); // Set weaponStaticType to "Short" for new entries
                        newPoliceStationTotalWeapon.setWeaponQuantity(returnMagzine.getTotalWeapon() != null ? returnMagzine.getTotalWeapon() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null && totalRound > 0) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (returnMagzine.getDepositeRound() != null && !returnMagzine.getDepositeRound().isEmpty()) {
                                int currentRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity());
                                if (currentRoundQuantity >= totalRound) {
                                    int updatedRoundQuantity = currentRoundQuantity - totalRound;
                                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for round: " + returnMagzine.getRoundName());
                                }
                            }
                        }
                    } else if (returnMagzine.getRoundName() != null && !returnMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(returnMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundStaticType("Short"); // Set roundStaticType to "Short" for new entries
                        newPoliceStationTotalRound.setRoundQuantity(returnMagzine.getDepositeRound() != null ? returnMagzine.getDepositeRound() : "0");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                return ResponseEntity.ok("ReturnMagzine saved successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Total stock not found for " + weaponName + " or " + roundName);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon and DepositeRound are null or empty for " + returnMagzineDto.getWeaponName() + " or " + returnMagzineDto.getRoundName());
        }
    }
*/

    /*

     // Upto this Functionality our entry was not save save if no changes was made in totalStock table
    @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        // [All the set fields code remains unchanged]
        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        returnMagzine.setCreatedAt(now);
        returnMagzine.setUpdatedAt(now);

        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) ||
                (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int totalWeapon = 0;
                int totalRound = 0;

                if (returnMagzineDto.getTotalWeapon() != null && !returnMagzineDto.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(returnMagzineDto.getTotalWeapon());
                }

                if (returnMagzineDto.getDepositeRound() != null && !returnMagzineDto.getDepositeRound().isEmpty()) {
                    totalRound = Integer.parseInt(returnMagzineDto.getDepositeRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < totalWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && totalRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < totalRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Track if changes were made to the TotalStock
                boolean changesMade = false;

                // Update distribution stock and available stock in TotalStock
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null) {
                        int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                        if (totalWeapon <= currentDistributionStockWeapon) {
                            int distributionStockWeapon = currentDistributionStockWeapon - totalWeapon; // Calculate new distribution_stock
                            totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                            totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + totalWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for weapon exceeds distributed quantity for weapon: " + returnMagzine.getWeaponName());
                        }
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null) {
                        int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                        if (totalRound <= currentDistributionStockRound) {
                            int distributionStockRound = currentDistributionStockRound - totalRound; // Calculate new distribution_stock
                            totalStockByRound.setDistributionStock(distributionStockRound);
                            totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + totalRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for round exceeds distributed quantity for round: " + returnMagzine.getRoundName());
                        }
                    }
                }

                // If no changes were made to TotalStock, return an error response
                if (!changesMade) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No changes made to TotalStock, nothing to save.");
                }

                // Save ReturnMagzine after stock validation and changes
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo if weapon is submitted
                if (returnMagzine.getSubmittedWeapon() != null && !returnMagzine.getSubmittedWeapon().isEmpty()) {
                    List<String> buttNoList = returnMagzine.getButtNoAsList();
                    List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                    for (int i = 0; i < buttNoList.size(); i++) {
                        RmButtNoAndManufacturingNo rmEntry = new RmButtNoAndManufacturingNo();
                        rmEntry.setButtNo(buttNoList.get(i));
                        rmEntry.setManufacturingNo(manufacturingNoList.get(i));
                        rmEntry.setReturnId(savedReturnMagzine.getId());
                        rmEntry.setRoundName(returnMagzineDto.getRoundName());
                        rmEntry.setWeaponName(returnMagzineDto.getWeaponName());
                        rmEntry.setWeaponType(returnMagzineDto.getWeaponType());
                        rmEntry.setReturnStatus("1");
                        //  rmEntry.setReturnMagzine(savedReturnMagzine);
                        rmButtNoAndManufacturingNoRepository.save(rmEntry);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(returnMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null && totalWeapon > 0) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (returnMagzine.getTotalWeapon() != null && !returnMagzine.getTotalWeapon().isEmpty()) {
                                int currentWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity());
                                if (currentWeaponQuantity >= totalWeapon) {
                                    int updatedWeaponQuantity = currentWeaponQuantity - totalWeapon;
                                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for weapon: " + returnMagzine.getWeaponName());
                                }
                            }
                        }
                    } else if (returnMagzine.getWeaponName() != null && !returnMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(returnMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponQuantity(returnMagzine.getTotalWeapon());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null && totalRound > 0) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (returnMagzine.getDepositeRound() != null && !returnMagzine.getDepositeRound().isEmpty()) {
                                int currentRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity());
                                if (currentRoundQuantity >= totalRound) {
                                    int updatedRoundQuantity = currentRoundQuantity - totalRound;
                                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for round: " + returnMagzine.getRoundName());
                                }
                            }
                        }
                    } else if (returnMagzine.getRoundName() != null && !returnMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(returnMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundQuantity(returnMagzine.getDepositeRound());
                        newPoliceStationTotalRound.setRoundStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalStock not found for Weapon or Round");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound cannot be null or empty.");
        }
    }

     */

/*
       // main method upto thursday 08.00 pm
    // Upto this Functionality our entry was not save save if no changes was made in totalStock table (Final method upto distribution involve and buttno deletion)
    @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        // [All the set fields code remains unchanged]
        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        returnMagzine.setCreatedAt(now);
        returnMagzine.setUpdatedAt(now);

        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) ||
                (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int submittedWeapon = 0;
                int submittedRound = 0;

                if (returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) {
                    submittedWeapon = Integer.parseInt(returnMagzineDto.getSubmittedWeapon());
                }

                if (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty()) {
                    submittedRound = Integer.parseInt(returnMagzineDto.getSubmittedRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && submittedWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < submittedWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && submittedRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < submittedRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Track if changes were made to the TotalStock
                boolean changesMade = false;

                // Update distribution stock and available stock in TotalStock
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null) {
                        int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                        if (submittedWeapon <= currentDistributionStockWeapon) {
                            int distributionStockWeapon = currentDistributionStockWeapon - submittedWeapon; // Calculate new distribution_stock
                            totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                            totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + submittedWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for weapon exceeds distributed quantity for weapon: " + returnMagzine.getWeaponName());
                        }
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null) {
                        int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                        if (submittedRound <= currentDistributionStockRound) {
                            int distributionStockRound = currentDistributionStockRound - submittedRound; // Calculate new distribution_stock
                            totalStockByRound.setDistributionStock(distributionStockRound);
                            totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + submittedRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for round exceeds distributed quantity for round: " + returnMagzine.getRoundName());
                        }
                    }
                }


                // If no changes were made to TotalStock, return an error response
                if (!changesMade) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No changes made to TotalStock, nothing to save.");
                }

                // Save ReturnMagzine after stock validation and changes
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo if weapon is submitted
                if (returnMagzine.getSubmittedWeapon() != null && !returnMagzine.getSubmittedWeapon().isEmpty()) {
                    List<String> buttNoList = returnMagzine.getButtNoAsList();
                    List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                    for (int i = 0; i < buttNoList.size(); i++) {
                        RmButtNoAndManufacturingNo rmEntry = new RmButtNoAndManufacturingNo();
                        rmEntry.setButtNo(buttNoList.get(i));
                        rmEntry.setManufacturingNo(manufacturingNoList.get(i));
                        rmEntry.setReturnId(savedReturnMagzine.getId());
                        rmEntry.setRoundName(returnMagzineDto.getRoundName());
                        rmEntry.setWeaponName(returnMagzineDto.getWeaponName());
                        rmEntry.setWeaponType(returnMagzineDto.getWeaponType());
                        rmEntry.setReturnStatus("1");
                        //  rmEntry.setReturnMagzine(savedReturnMagzine);
                        rmButtNoAndManufacturingNoRepository.save(rmEntry);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(returnMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null && submittedWeapon > 0) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (returnMagzine.getTotalWeapon() != null && !returnMagzine.getTotalWeapon().isEmpty()) {
                                int currentWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity());
                                if (currentWeaponQuantity >= submittedWeapon) {
                                    int updatedWeaponQuantity = currentWeaponQuantity - submittedWeapon;
                                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for weapon: " + returnMagzine.getWeaponName());
                                }
                            }
                        }
                    } else if (returnMagzine.getWeaponName() != null && !returnMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(returnMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponQuantity(returnMagzine.getTotalWeapon());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null && submittedRound > 0) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (returnMagzine.getDepositeRound() != null && !returnMagzine.getDepositeRound().isEmpty()) {
                                int currentRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity());
                                if (currentRoundQuantity >= submittedRound) {
                                    int updatedRoundQuantity = currentRoundQuantity - submittedRound;
                                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for round: " + returnMagzine.getRoundName());
                                }
                            }
                        }
                    } else if (returnMagzine.getRoundName() != null && !returnMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(returnMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundQuantity(returnMagzine.getDepositeRound());
                        newPoliceStationTotalRound.setRoundStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                */
/*//*
/ Additional logic for updating DistributionMagzine
                List<String> buttNoList = returnMagzine.getButtNoAsList();
                List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                for (int i = 0; i < buttNoList.size(); i++) {
                    String buttNo = buttNoList.get(i);
                    String manufacturingNo = manufacturingNoList.get(i);

                    List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoAndManufacturingNo(buttNo, manufacturingNo);

                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        int returnWeaponCount = Integer.parseInt(distributionMagzine.getReturnWeapon() + 1);
                        int balanceWeaponCount = distributionMagzine.getTotalWeapon() - returnWeaponCount;

                        distributionMagzine.setReturnWeapon(String.valueOf(returnWeaponCount));
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeaponCount));

                        if (balanceWeaponCount <= 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                }*//*


                // Additional logic for updating DistributionMagzine
                List<String> buttNoList = returnMagzine.getButtNoAsList();
                List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                for (int i = 0; i < buttNoList.size(); i++) {
                    String buttNo = buttNoList.get(i);
                    String manufacturingNo = manufacturingNoList.get(i);

                    List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoAndManufacturingNo(buttNo, manufacturingNo);

                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        // Convert the String values to int for calculation
                        int returnWeaponCount = Integer.parseInt(distributionMagzine.getReturnWeapon()) + 1;
                        int totalWeaponCount = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        int balanceWeaponCount = totalWeaponCount - returnWeaponCount;

                        // Set the updated values back as String
                        distributionMagzine.setReturnWeapon(String.valueOf(returnWeaponCount));
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeaponCount));

                        // Delete or save the entry based on balanceWeaponCount
                        if (balanceWeaponCount <= 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                }

                return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalStock not found for Weapon or Round");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound cannot be null or empty.");
        }
    }
*/

    @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        // [All the set fields code remains unchanged]
        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());
        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) ||
                (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int submittedWeapon = 0;
                int submittedRound = 0;

                if (returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) {
                    submittedWeapon = Integer.parseInt(returnMagzineDto.getSubmittedWeapon());
                }

                if (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty()) {
                    submittedRound = Integer.parseInt(returnMagzineDto.getSubmittedRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && submittedWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < submittedWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && submittedRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < submittedRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Track if changes were made to the TotalStock
                boolean changesMade = false;

                // Update distribution stock and available stock in TotalStock
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null) {
                        int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                        if (submittedWeapon <= currentDistributionStockWeapon) {
                            int distributionStockWeapon = currentDistributionStockWeapon - submittedWeapon; // Calculate new distribution_stock
                            totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                            totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + submittedWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for weapon exceeds distributed quantity for weapon: " + returnMagzine.getWeaponName());
                        }
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null) {
                        int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                        if (submittedRound <= currentDistributionStockRound) {
                            int distributionStockRound = currentDistributionStockRound - submittedRound; // Calculate new distribution_stock
                            totalStockByRound.setDistributionStock(distributionStockRound);
                            totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + submittedRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for round exceeds distributed quantity for round: " + returnMagzine.getRoundName());
                        }
                    }
                }

                // If no changes were made to TotalStock, return an error response
                if (!changesMade) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No changes made to TotalStock, nothing to save.");
                }

                // Save ReturnMagzine after stock validation and changes
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo if weapon is submitted
                if (returnMagzine.getSubmittedWeapon() != null && !returnMagzine.getSubmittedWeapon().isEmpty()) {
                    List<String> buttNoList = returnMagzine.getButtNoAsList();
                    List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                    for (int i = 0; i < buttNoList.size(); i++) {
                        RmButtNoAndManufacturingNo rmEntry = new RmButtNoAndManufacturingNo();
                        rmEntry.setButtNo(buttNoList.get(i));
                        rmEntry.setManufacturingNo(manufacturingNoList.get(i));
                        rmEntry.setReturnId(savedReturnMagzine.getId());
                        rmEntry.setRoundName(returnMagzineDto.getRoundName());
                        rmEntry.setWeaponName(returnMagzineDto.getWeaponName());
                        rmEntry.setWeaponType(returnMagzineDto.getWeaponType());
                        rmEntry.setReturnStatus("1");
                        //  rmEntry.setReturnMagzine(savedReturnMagzine);
                        rmButtNoAndManufacturingNoRepository.save(rmEntry);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(returnMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null && submittedWeapon > 0) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (returnMagzine.getTotalWeapon() != null && !returnMagzine.getTotalWeapon().isEmpty()) {
                                int currentWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity());
                                if (currentWeaponQuantity >= submittedWeapon) {
                                    int updatedWeaponQuantity = currentWeaponQuantity - submittedWeapon;
                                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for weapon: " + returnMagzine.getWeaponName());
                                }
                            }
                        }
                    } else if (returnMagzine.getWeaponName() != null && !returnMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(returnMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponQuantity(returnMagzine.getTotalWeapon());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null && submittedRound > 0) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (returnMagzine.getDepositeRound() != null && !returnMagzine.getDepositeRound().isEmpty()) {
                                int currentRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity());
                                if (currentRoundQuantity >= submittedRound) {
                                    int updatedRoundQuantity = currentRoundQuantity - submittedRound;
                                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for round: " + returnMagzine.getRoundName());
                                }
                            }
                        }
                    } else if (returnMagzine.getRoundName() != null && !returnMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(returnMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundQuantity(returnMagzine.getDepositeRound());
                        newPoliceStationTotalRound.setRoundStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }

                /*
                // Fetch all DistributionMagzine entries by buttNo and manufacturingNo
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        int countButtNo = returnMagzineDto.getButtNo().size();

                        // Increment returnWeapon field
                        int currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Update balanceWeapon and totalWeapon
                        int totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove DistributionMagzine entry if balanceWeapon is zero
                        if (balanceWeapon == 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }

                 */


                // it is working for 1 ButtNo and 1 ManufacturingNO accurately but ( requires only 1 buttNo & manuNo in distribution)
                // Fetch all DistributionMagzine entries by buttNo and manufacturingNo
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        int countButtNo = returnMagzineDto.getButtNo().size();

                        // Parse currentReturnWeapon safely
                        int currentReturnWeapon = 0;
                        if (distributionMagzine.getReturnWeapon() != null && !distributionMagzine.getReturnWeapon().isEmpty()) {
                            currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        }
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Parse totalWeapon safely
                        int totalWeapon = 0;
                        if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                            totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        }

                        // Update balanceWeapon and handle possible errors
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove DistributionMagzine entry if balanceWeapon is zero
                        if (balanceWeapon == 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }



              /*  // Fetch all DistributionMagzine entries by buttNo and manufacturingNo
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        int countButtNo = returnMagzineDto.getButtNo().size();

                        // Parse currentReturnWeapon safely
                        int currentReturnWeapon = 0;
                        if (distributionMagzine.getReturnWeapon() != null && !distributionMagzine.getReturnWeapon().isEmpty()) {
                            currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        }
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Parse totalWeapon safely
                        int totalWeapon = 0;
                        if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                            totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        }

                        // Update balanceWeapon and handle possible errors
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove the specified buttNo and manufacturingNo from the distributionMagzine entry
                        List<String> buttNos = new ArrayList<>(Arrays.asList(distributionMagzine.getButtNo().split(",")));
                        List<String> manufacturingNos = new ArrayList<>(Arrays.asList(distributionMagzine.getManufacturingNo().split(",")));

                        boolean updated = false;

                        for (int i = 0; i < buttNos.size(); i++) {
                            if (returnMagzineDto.getButtNo().contains(buttNos.get(i)) && returnMagzineDto.getManufacturingNo().contains(manufacturingNos.get(i))) {
                                buttNos.remove(i);
                                manufacturingNos.remove(i);
                                updated = true;
                                break;
                            }
                        }

                        if (updated) {
                            distributionMagzine.setButtNo(String.join(",", buttNos));
                            distributionMagzine.setManufacturingNo(String.join(",", manufacturingNos));

                            // Remove DistributionMagzine entry if no buttNos or manufacturingNos are left
                            if (buttNos.isEmpty() && manufacturingNos.isEmpty()) {
                                distributionMagzineRepository.delete(distributionMagzine);
                            } else {
                                distributionMagzineRepository.save(distributionMagzine);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }*/

              /*
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        // Parse currentReturnWeapon safely
                        int currentReturnWeapon = 0;
                        if (distributionMagzine.getReturnWeapon() != null && !distributionMagzine.getReturnWeapon().isEmpty()) {
                            currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        }
                        int countButtNo = returnMagzineDto.getButtNo().size();
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Parse totalWeapon safely
                        int totalWeapon = 0;
                        if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                            totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        }

                        // Update balanceWeapon
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove the specified buttNo and manufacturingNo from the distributionMagzine entry
                        List<String> buttNos = new ArrayList<>(Arrays.asList(distributionMagzine.getButtNo().split(",")));
                        List<String> manufacturingNos = new ArrayList<>(Arrays.asList(distributionMagzine.getManufacturingNo().split(",")));

                        boolean updated = false;

                        for (int i = 0; i < buttNos.size(); i++) {
                            if (returnMagzineDto.getButtNo().contains(buttNos.get(i)) && returnMagzineDto.getManufacturingNo().contains(manufacturingNos.get(i))) {
                                buttNos.remove(i);
                                manufacturingNos.remove(i);
                                updated = true;
                                break;
                            }
                        }

                        if (updated) {
                            distributionMagzine.setButtNo(String.join(",", buttNos));
                            distributionMagzine.setManufacturingNo(String.join(",", manufacturingNos));

                            // Remove DistributionMagzine entry if no buttNos or manufacturingNos are left
                            if (buttNos.isEmpty() && manufacturingNos.isEmpty()) {
                                distributionMagzineRepository.delete(distributionMagzine);
                            } else {
                                distributionMagzineRepository.save(distributionMagzine);
                            }
                        }
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
                } *//*else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }
            */
               // It is working after return more buttNo and manufacturingNo but it was not maintain
              /*  List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        // Parse currentReturnWeapon safely
                        int currentReturnWeapon = 0;
                        if (distributionMagzine.getReturnWeapon() != null && !distributionMagzine.getReturnWeapon().isEmpty()) {
                            currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        }
                        int countButtNo = returnMagzineDto.getButtNo().size();
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Parse totalWeapon safely
                        int totalWeapon = 0;
                        if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                            totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        }

                        // Update balanceWeapon
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Logging for debugging
                        System.out.println("currentReturnWeapon: " + currentReturnWeapon);
                        System.out.println("countButtNo: " + countButtNo);
                        System.out.println("totalWeapon: " + totalWeapon);
                        System.out.println("balanceWeapon: " + balanceWeapon);

                        // Remove the specified buttNo and manufacturingNo from the distributionMagzine entry
                        List<String> buttNos = new ArrayList<>(Arrays.asList(distributionMagzine.getButtNo().split(",")));
                        List<String> manufacturingNos = new ArrayList<>(Arrays.asList(distributionMagzine.getManufacturingNo().split(",")));

                        boolean updated = false;

                        for (int i = 0; i < buttNos.size(); i++) {
                            if (returnMagzineDto.getButtNo().contains(buttNos.get(i)) && returnMagzineDto.getManufacturingNo().contains(manufacturingNos.get(i))) {
                                buttNos.remove(i);
                                manufacturingNos.remove(i);
                                updated = true;
                                break;
                            }
                        }

                        if (updated) {
                            distributionMagzine.setButtNo(String.join(",", buttNos));
                            distributionMagzine.setManufacturingNo(String.join(",", manufacturingNos));

                            // Remove DistributionMagzine entry if no buttNos or manufacturingNos are left
                            if (buttNos.isEmpty() && manufacturingNos.isEmpty()) {
                                distributionMagzineRepository.delete(distributionMagzine);
                            } else {
                                distributionMagzineRepository.save(distributionMagzine);
                            }
                        }
                    }
                    return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
                } *//*else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }*/

           /*     // Last Method
                // Fetch all DistributionMagzine entries by buttNo, manufacturingNo, policeStationName or individualName, and weaponName
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoInAndWeaponNameAndPoliceStationNameOrIndividualName(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo(), returnMagzineDto.getWeaponName(),
                        returnMagzineDto.getPoliceStationName(), returnMagzineDto.getIndividualName());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        // Convert buttNo and manufacturingNo to Lists for easy manipulation
                        List<String> buttNos = new ArrayList<>(Arrays.asList(distributionMagzine.getButtNo().split(",")));
                        List<String> manufacturingNos = new ArrayList<>(Arrays.asList(distributionMagzine.getManufacturingNo().split(",")));

                        boolean updated = false;

                        for (int i = 0; i < buttNos.size(); i++) {
                            if (returnMagzineDto.getButtNo().contains(buttNos.get(i)) && returnMagzineDto.getManufacturingNo().contains(manufacturingNos.get(i))) {
                                buttNos.remove(i);
                                manufacturingNos.remove(i);
                                updated = true;
                                break;
                            }
                        }

                        if (updated) {
                            int countButtNo = returnMagzineDto.getButtNo().size();

                            // Parse currentReturnWeapon safely
                            int currentReturnWeapon = 0;
                            if (distributionMagzine.getReturnWeapon() != null && !distributionMagzine.getReturnWeapon().isEmpty()) {
                                currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                            }
                            distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                            // Parse totalWeapon safely
                            int totalWeapon = 0;
                            if (distributionMagzine.getTotalWeapon() != null && !distributionMagzine.getTotalWeapon().isEmpty()) {
                                totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                            }

                            // Update balanceWeapon
                            int balanceWeapon = totalWeapon - countButtNo;
                            distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                            // Update buttNo and manufacturingNo in the entry
                            distributionMagzine.setButtNo(String.join(",", buttNos));
                            distributionMagzine.setManufacturingNo(String.join(",", manufacturingNos));

                            // Remove DistributionMagzine entry if no buttNos or manufacturingNos are left
                            if (buttNos.isEmpty() && manufacturingNos.isEmpty()) {
                                distributionMagzineRepository.delete(distributionMagzine);
                            } else {
                                distributionMagzineRepository.save(distributionMagzine);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo, manufacturingNo, policeStationName or individualName, and weaponName.");
                }*/



                return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalStock not found for Weapon or Round");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound cannot be null or empty.");
        }
    }




    // Upto this Functionality our entry was not save save if no changes was made in totalStock table
 /*   @PostMapping("/saveReturnMagzine")
    public ResponseEntity<String> createReturnMagzine(@RequestBody ReturnMagzineDto returnMagzineDto) {
        ReturnMagzine returnMagzine = new ReturnMagzine();

        // Set fields from DTO to entity
        // [All the set fields code remains unchanged]
        // Set fields from DTO to entity
        returnMagzine.setWeaponType(returnMagzineDto.getWeaponType());
        returnMagzine.setWeaponName(returnMagzineDto.getWeaponName());
        returnMagzine.setRoundName(returnMagzineDto.getRoundName());
        returnMagzine.setWeaponLocation(returnMagzineDto.getWeaponLocation());
        returnMagzine.setMobileNumber(returnMagzineDto.getMobileNumber());
        returnMagzine.setButtNoAsList(returnMagzineDto.getButtNo());
        returnMagzine.setManufacturingNoAsList(returnMagzineDto.getManufacturingNo());
        returnMagzine.setSevarthId(returnMagzineDto.getSevarthId());
        returnMagzine.setDistributedBy(returnMagzineDto.getDistributedBy());
        returnMagzine.setAccessoriesAsList(returnMagzineDto.getAccessories());
        returnMagzine.setReturnAccessoriesAsList(returnMagzineDto.getReturnAccessories());
        returnMagzine.setDistributionType(returnMagzineDto.getDistributionType());
        returnMagzine.setPoliceStationName(returnMagzineDto.getPoliceStationName());
        returnMagzine.setIndividualName(returnMagzineDto.getIndividualName());
        returnMagzine.setWeaponCondition(returnMagzineDto.getWeaponCondition());
        returnMagzine.setIdentityType(returnMagzineDto.getIdentityType());
        returnMagzine.setIdentityNo(returnMagzineDto.getIdentityNo());
        returnMagzine.setPermanentAddress(returnMagzineDto.getPermanentAddress());
        returnMagzine.setTemporaryAddress(returnMagzineDto.getTemporaryAddress());
        returnMagzine.setDesignation(returnMagzineDto.getDesignation());
        returnMagzine.setBucckleNo(returnMagzineDto.getBucckleNo());
        returnMagzine.setBirthDate(returnMagzineDto.getBirthDate());
        returnMagzine.setDateOfRetirement(returnMagzineDto.getDateOfRetirement());
        returnMagzine.setPosting(returnMagzineDto.getPosting());
        returnMagzine.setDepositeRound(returnMagzineDto.getDepositeRound());
        returnMagzine.setWeaponCheckedBy(returnMagzineDto.getWeaponCheckedBy());
        returnMagzine.setServicingDate(returnMagzineDto.getServicingDate());
        returnMagzine.setDistributionDate(returnMagzineDto.getDistributionDate());
        returnMagzine.setDistributionTime(returnMagzineDto.getDistributionTime());
        returnMagzine.setSubmittedWeapon(returnMagzineDto.getSubmittedWeapon());
        returnMagzine.setRecievedWeaponCondition(returnMagzineDto.getRecievedWeaponCondition());
        returnMagzine.setRecievedWeaponCheckedBy(returnMagzineDto.getRecievedWeaponCheckedBy());
        returnMagzine.setIsWeaponDamage(returnMagzineDto.getIsWeaponDamage());
        returnMagzine.setRecievedWeaponDate(returnMagzineDto.getRecievedWeaponDate());
        returnMagzine.setRecievedWeaponTime(returnMagzineDto.getRecievedWeaponTime());
        returnMagzine.setUsedRound(returnMagzineDto.getUsedRound());
        returnMagzine.setSubmittedRound(returnMagzineDto.getSubmittedRound());
        returnMagzine.setDefectedRound(returnMagzineDto.getDefectedRound());
        returnMagzine.setRecievedRoundCondition(returnMagzineDto.getRecievedRoundCondition());
        returnMagzine.setRecievedroundCheckedBy(returnMagzineDto.getRecievedroundCheckedBy());
        returnMagzine.setRecievedRoundDate(returnMagzineDto.getRecievedRoundDate());
        returnMagzine.setRecievedRoundTime(returnMagzineDto.getRecievedRoundTime());
        returnMagzine.setIssueRemark(returnMagzineDto.getIssueRemark());
        returnMagzine.setSubmittedRemark(returnMagzineDto.getSubmittedRemark());
        returnMagzine.setTotalWeapon(returnMagzineDto.getTotalWeapon());
        returnMagzine.setDistributeEmployeeId(returnMagzineDto.getDistributeEmployeeId());
        returnMagzine.setDistributeEmployeeName(returnMagzineDto.getDistributeEmployeeName());
        returnMagzine.setDistributeEmployeeDesignation(returnMagzineDto.getDistributeEmployeeDesignation());
        returnMagzine.setDistributeEmployeePostingDate(returnMagzineDto.getDistributeEmployeePostingDate());
        returnMagzine.setRecievedEmployeeId(returnMagzineDto.getRecievedEmployeeId());
        returnMagzine.setRecievedEmployeeName(returnMagzineDto.getRecievedEmployeeName());
        returnMagzine.setRecievedEmployeeDesignation(returnMagzineDto.getRecievedEmployeeDesignation());
        returnMagzine.setRecievedEmployeePostingDate(returnMagzineDto.getRecievedEmployeePostingDate());
        returnMagzine.setStatus(returnMagzineDto.getStatus());
        returnMagzine.setCreatedAt(returnMagzineDto.getCreatedAt());
        returnMagzine.setUpdatedAt(returnMagzineDto.getUpdatedAt());

        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        returnMagzine.setCreatedAt(now);
        returnMagzine.setUpdatedAt(now);

        // Set default value for status column
        returnMagzine.setStatus("1");

        // Check if TotalWeapon or DepositeRound is not null or empty
        if ((returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) ||
                (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty())) {

            String weaponName = returnMagzineDto.getWeaponName();
            String roundName = returnMagzineDto.getRoundName();
            String weaponType = returnMagzineDto.getWeaponType();

            // Fetch TotalStock by WeaponName and/or RoundName
            List<TotalStock> totalStockByWeaponList = new ArrayList<>();
            List<TotalStock> totalStockByRoundList = new ArrayList<>();

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeaponList = totalStockRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRoundList = totalStockRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            boolean totalStockByWeaponExists = totalStockByWeaponList != null && !totalStockByWeaponList.isEmpty();
            boolean totalStockByRoundExists = totalStockByRoundList != null && !totalStockByRoundList.isEmpty();

            if (totalStockByWeaponExists || totalStockByRoundExists) {

                // Assuming TotalWeapon and DepositeRound are Integer fields
                int submittedWeapon = 0;
                int submittedRound = 0;

                if (returnMagzineDto.getSubmittedWeapon() != null && !returnMagzineDto.getSubmittedWeapon().isEmpty()) {
                    submittedWeapon = Integer.parseInt(returnMagzineDto.getSubmittedWeapon());
                }

                if (returnMagzineDto.getSubmittedRound() != null && !returnMagzineDto.getSubmittedRound().isEmpty()) {
                    submittedRound = Integer.parseInt(returnMagzineDto.getSubmittedRound());
                }

                if ("1".equals(returnMagzine.getStatus())) {
                    for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && submittedWeapon > 0) {
                            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                            if (availableStockWeapon < submittedWeapon) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for weapon: " + weaponName);
                            }
                        }
                    }

                    for (TotalStock totalStockByRound : totalStockByRoundList) {
                        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && submittedRound > 0) {
                            int currentTotalStockRound = totalStockByRound.getTotalStock();
                            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                            if (availableStockRound < submittedRound) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for round: " + roundName);
                            }
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return status is not 1 for " + weaponName + " or " + roundName);
                }

                // Track if changes were made to the TotalStock
                boolean changesMade = false;

                // Update distribution stock and available stock in TotalStock
                for (TotalStock totalStockByWeapon : totalStockByWeaponList) {
                    if (totalStockByWeapon != null) {
                        int currentDistributionStockWeapon = totalStockByWeapon.getDistributionStock();
                        if (submittedWeapon <= currentDistributionStockWeapon) {
                            int distributionStockWeapon = currentDistributionStockWeapon - submittedWeapon; // Calculate new distribution_stock
                            totalStockByWeapon.setDistributionStock(distributionStockWeapon);
                            totalStockByWeapon.setAvailableStock(totalStockByWeapon.getAvailableStock() + submittedWeapon); // Update available_stock
                            totalStockRepository.save(totalStockByWeapon);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for weapon exceeds distributed quantity for weapon: " + returnMagzine.getWeaponName());
                        }
                    }
                }

                for (TotalStock totalStockByRound : totalStockByRoundList) {
                    if (totalStockByRound != null) {
                        int currentDistributionStockRound = totalStockByRound.getDistributionStock();
                        if (submittedRound <= currentDistributionStockRound) {
                            int distributionStockRound = currentDistributionStockRound - submittedRound; // Calculate new distribution_stock
                            totalStockByRound.setDistributionStock(distributionStockRound);
                            totalStockByRound.setAvailableStock(totalStockByRound.getAvailableStock() + submittedRound); // Update available_stock
                            totalStockRepository.save(totalStockByRound);
                            changesMade = true;
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Return quantity for round exceeds distributed quantity for round: " + returnMagzine.getRoundName());
                        }
                    }
                }

             *//*   // Fetch DistributionMagzine entries separately by buttNo and manufacturingNo
                List<DistributionMagzine> distributionMagzinesByButtNo = distributionMagzineRepository.findByButtNoIn(returnMagzineDto.getButtNo());
                List<DistributionMagzine> distributionMagzinesByManufacturingNo = distributionMagzineRepository.findByManufacturingNoIn(returnMagzineDto.getManufacturingNo());

// Combine the results to get a unique list of DistributionMagzine entries
                Set<DistributionMagzine> uniqueDistributionMagzines = new HashSet<>(distributionMagzinesByButtNo);
                uniqueDistributionMagzines.addAll(distributionMagzinesByManufacturingNo);

                if (!uniqueDistributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : uniqueDistributionMagzines) {
                        int countButtNo = returnMagzineDto.getButtNo().size();

                        // Increment returnWeapon field
                        int currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Update balanceWeapon and totalWeapon
                        int totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove DistributionMagzine entry if balanceWeapon is zero
                        if (balanceWeapon == 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }*//*



               *//* // Fetch all DistributionMagzine entries by buttNo and manufacturingNo
                List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByButtNoInAndManufacturingNoIn(
                        returnMagzineDto.getButtNo(), returnMagzineDto.getManufacturingNo());

                if (!distributionMagzines.isEmpty()) {
                    for (DistributionMagzine distributionMagzine : distributionMagzines) {
                        int countButtNo = returnMagzineDto.getButtNo().size();

                        // Increment returnWeapon field
                        int currentReturnWeapon = Integer.parseInt(distributionMagzine.getReturnWeapon());
                        distributionMagzine.setReturnWeapon(String.valueOf(currentReturnWeapon + countButtNo));

                        // Update balanceWeapon and totalWeapon
                        int totalWeapon = Integer.parseInt(distributionMagzine.getTotalWeapon());
                        int balanceWeapon = totalWeapon - countButtNo;
                        distributionMagzine.setBalanceWeapon(String.valueOf(balanceWeapon));

                        // Remove DistributionMagzine entry if balanceWeapon is zero
                        if (balanceWeapon == 0) {
                            distributionMagzineRepository.delete(distributionMagzine);
                        } else {
                            distributionMagzineRepository.save(distributionMagzine);
                        }
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No DistributionMagzine entries found for the specified buttNo and manufacturingNo.");
                }*//*



                // If no changes were made to TotalStock, return an error response
                if (!changesMade) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No changes made to TotalStock, nothing to save.");
                }

                // Save ReturnMagzine after stock validation and changes
                ReturnMagzine savedReturnMagzine = returnMagzineRepository.save(returnMagzine);

                // Only save entries in RmButtNoAndManufacturingNo if weapon is submitted
                if (returnMagzine.getSubmittedWeapon() != null && !returnMagzine.getSubmittedWeapon().isEmpty()) {
                    List<String> buttNoList = returnMagzine.getButtNoAsList();
                    List<String> manufacturingNoList = returnMagzine.getManufacturingNoAsList();

                    for (int i = 0; i < buttNoList.size(); i++) {
                        RmButtNoAndManufacturingNo rmEntry = new RmButtNoAndManufacturingNo();
                        rmEntry.setButtNo(buttNoList.get(i));
                        rmEntry.setManufacturingNo(manufacturingNoList.get(i));
                        rmEntry.setReturnId(savedReturnMagzine.getId());
                        rmEntry.setRoundName(returnMagzineDto.getRoundName());
                        rmEntry.setWeaponName(returnMagzineDto.getWeaponName());
                        rmEntry.setWeaponType(returnMagzineDto.getWeaponType());
                        rmEntry.setReturnStatus("1");
                        //  rmEntry.setReturnMagzine(savedReturnMagzine);
                        rmButtNoAndManufacturingNoRepository.save(rmEntry);
                    }
                }

                // Check if the distributionType is "Police Station" or "adhibhar"
                if ("Police Station/adhibhar".equals(returnMagzine.getDistributionType())) {
                    // Fetch the existing PoliceStationTotal entry for weapon
                    PoliceStationTotal existingPoliceStationTotalWeapon = policeStationTotalRepository.findByPoliceStationIdAndWeaponName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getWeaponName());

                    // Fetch the existing PoliceStationTotal entry for round
                    PoliceStationTotal existingPoliceStationTotalRound = policeStationTotalRepository.findByPoliceStationIdAndRoundName(
                            returnMagzine.getPoliceStationName(), returnMagzine.getRoundName());

                    // Update or create entry for weapon
                    if (existingPoliceStationTotalWeapon != null && submittedWeapon > 0) {
                        // Update existing entry for weapon only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalWeapon.getWeaponStaticType())) {
                            if (returnMagzine.getTotalWeapon() != null && !returnMagzine.getTotalWeapon().isEmpty()) {
                                int currentWeaponQuantity = Integer.parseInt(existingPoliceStationTotalWeapon.getWeaponQuantity());
                                if (currentWeaponQuantity >= submittedWeapon) {
                                    int updatedWeaponQuantity = currentWeaponQuantity - submittedWeapon;
                                    existingPoliceStationTotalWeapon.setWeaponQuantity(String.valueOf(updatedWeaponQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalWeapon);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for weapon: " + returnMagzine.getWeaponName());
                                }
                            }
                        }
                    } else if (returnMagzine.getWeaponName() != null && !returnMagzine.getWeaponName().isEmpty()) {
                        // Create new entry for weapon
                        PoliceStationTotal newPoliceStationTotalWeapon = new PoliceStationTotal();
                        newPoliceStationTotalWeapon.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalWeapon.setWeaponName(returnMagzine.getWeaponName());
                        newPoliceStationTotalWeapon.setWeaponQuantity(returnMagzine.getTotalWeapon());
                        newPoliceStationTotalWeapon.setWeaponStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalWeapon);
                    }

                    // Update or create entry for round
                    if (existingPoliceStationTotalRound != null && submittedRound > 0) {
                        // Update existing entry for round only if it's already marked as "Short"
                        if ("Short".equals(existingPoliceStationTotalRound.getRoundStaticType())) {
                            if (returnMagzine.getDepositeRound() != null && !returnMagzine.getDepositeRound().isEmpty()) {
                                int currentRoundQuantity = Integer.parseInt(existingPoliceStationTotalRound.getRoundQuantity());
                                if (currentRoundQuantity >= submittedRound) {
                                    int updatedRoundQuantity = currentRoundQuantity - submittedRound;
                                    existingPoliceStationTotalRound.setRoundQuantity(String.valueOf(updatedRoundQuantity));
                                    policeStationTotalRepository.save(existingPoliceStationTotalRound);
                                } else {
                                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient stock to return for round: " + returnMagzine.getRoundName());
                                }
                            }
                        }
                    } else if (returnMagzine.getRoundName() != null && !returnMagzine.getRoundName().isEmpty()) {
                        // Create new entry for round
                        PoliceStationTotal newPoliceStationTotalRound = new PoliceStationTotal();
                        newPoliceStationTotalRound.setPoliceStationId(returnMagzine.getPoliceStationName());
                        newPoliceStationTotalRound.setRoundName(returnMagzine.getRoundName());
                        newPoliceStationTotalRound.setRoundQuantity(returnMagzine.getDepositeRound());
                        newPoliceStationTotalRound.setRoundStaticType("Short");
                        policeStationTotalRepository.save(newPoliceStationTotalRound);
                    }
                }


                return ResponseEntity.status(HttpStatus.CREATED).body("ReturnMagzine created successfully and total stock updated.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalStock not found for Weapon or Round");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TotalWeapon or DepositeRound cannot be null or empty.");
        }
    }*/

  /*  @PostMapping("/getReturnType")
    public ResponseEntity<List<Map<String, Object>>> getReturnMagzines() {
        List<ReturnMagzine> returnMagzines = returnMagzineService.getAllReturnMagzines();
        if (returnMagzines == null || returnMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (ReturnMagzine returnMagzine : returnMagzines) {
            Map<String, Object> returnMagzineMap = new HashMap<>();
            returnMagzineMap.put("returnMagzine", returnMagzine);

            // Get count of buttNo and manufacturingNo
            int buttNoCount = returnMagzineService.getButtNoCount(returnMagzine.getId());
            int manufacturingNoCount = returnMagzineService.getManufacturingNoCount(returnMagzine.getId());

            returnMagzineMap.put("buttNoCount", buttNoCount);
            returnMagzineMap.put("manufacturingNoCount", manufacturingNoCount);

            result.add(returnMagzineMap);
        }

        return ResponseEntity.ok(result);
    }*/

  /*  @PostMapping("/getById/{id}")
    public ResponseEntity<ReturnMagzine> getReturnMagzineById(@PathVariable Long id) {
        Optional<ReturnMagzine> returnMagzine = returnMagzineRepository.findById(id);
        return returnMagzine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @PostMapping("/getById/{id}")
    public ResponseEntity<Map<String, Object>> getReturnMagzineById(@PathVariable Long id) {
        Optional<ReturnMagzine> returnMagzineOpt = returnMagzineRepository.findById(id);
        if (returnMagzineOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ReturnMagzine returnMagzine = returnMagzineOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("returnMagzine", returnMagzine);

        // Fetch and include the police station name
        String policeStationName = returnMagzine.getPoliceStationName();
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

  /*  @PostMapping("/getReturnType")
    public ResponseEntity<List<Map<String, Object>>> getReturnMagzines() {
        List<ReturnMagzine> returnMagzines = returnMagzineService.getAllReturnMagzines();
        if (returnMagzines == null || returnMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (ReturnMagzine returnMagzine : returnMagzines) {
            Map<String, Object> returnMagzineMap = new HashMap<>();
            returnMagzineMap.put("returnMagzine", returnMagzine);

            // Get count of buttNo and manufacturingNo
            int buttNoCount = returnMagzineService.getButtNoCount(returnMagzine.getId());
            int manufacturingNoCount = returnMagzineService.getManufacturingNoCount(returnMagzine.getId());

            returnMagzineMap.put("buttNoCount", buttNoCount);
            returnMagzineMap.put("manufacturingNoCount", manufacturingNoCount);

            // Fetch and include the police station name
            Long policeStationId = Long.parseLong(returnMagzine.getPoliceStationName());
            String policeStationName = policeStationRegistrationService.getPoliceStationNameById(policeStationId);
            returnMagzineMap.put("policeStationName", policeStationName);

            result.add(returnMagzineMap);
        }

        return ResponseEntity.ok(result);
    }*/

    @PostMapping("/getReturnType")
    public ResponseEntity<List<Map<String, Object>>> getReturnMagzines() {
        List<ReturnMagzine> returnMagzines = returnMagzineService.getAllReturnMagzines();
        if (returnMagzines == null || returnMagzines.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (ReturnMagzine returnMagzine : returnMagzines) {
            Map<String, Object> returnMagzineMap = new HashMap<>();
            returnMagzineMap.put("returnMagzine", returnMagzine);

            // Get count of buttNo and manufacturingNo
            int buttNoCount = returnMagzineService.getButtNoCount(returnMagzine.getId());
            int manufacturingNoCount = returnMagzineService.getManufacturingNoCount(returnMagzine.getId());

            returnMagzineMap.put("buttNoCount", buttNoCount);
            returnMagzineMap.put("manufacturingNoCount", manufacturingNoCount);

            // Fetch and include the police station name
            String policeStationName = returnMagzine.getPoliceStationName();
            if (policeStationName != null && !policeStationName.isEmpty()) {
                try {
                    Long policeStationId = Long.parseLong(policeStationName);
                    String actualPoliceStationName = policeStationRegistrationService.getPoliceStationNameById(policeStationId);
                    returnMagzineMap.put("policeStationName", actualPoliceStationName);
                } catch (NumberFormatException e) {
                    // Handle the case where policeStationName is not a valid number
                    returnMagzineMap.put("policeStationName", "Invalid police station ID");
                }
            } else {
                returnMagzineMap.put("policeStationName", "-");
            }

            result.add(returnMagzineMap);
        }

        return ResponseEntity.ok(result);
    }

}
