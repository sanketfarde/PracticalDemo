package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.Dto.DistributionRequest;
import com.example.WeaponArmaryManagementSystem.Dto.ReturnRequest;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponDistribution;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponReturn;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmTotalInOutward;
import com.example.WeaponArmaryManagementSystem.model.DistributionGodown;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmTotalInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.DistributionGodownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
@Service
public class WeaponDistributionService {

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Autowired
    private DistributionGodownRepository distributionGodownRepository;

    @Transactional
    public void distributeWeapons(List<DistributionRequest> requests, String godownName) {
        StringBuilder weaponNames = new StringBuilder();
        StringBuilder weaponQuantities = new StringBuilder();

        for (DistributionRequest request : requests) {
            String weaponName = request.getWeaponName();
            int quantityToDistribute = request.getQuantity();

            // Fetch the record from BellOfArmTotalInOutward
            Optional<BellOfArmTotalInOutward> bellOfArmRecordOpt = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);

            if (bellOfArmRecordOpt.isPresent()) {
                BellOfArmTotalInOutward bellOfArmRecord = bellOfArmRecordOpt.get();

                if (bellOfArmRecord.getAvailableStock() >= quantityToDistribute) {
                    // Update availableStock and distributionStock
                    bellOfArmRecord.setAvailableStock(bellOfArmRecord.getAvailableStock() - quantityToDistribute);
                    bellOfArmRecord.setDistributionStock(bellOfArmRecord.getDistributionStock() + quantityToDistribute);
                    bellOfArmTotalInOutwardRepository.save(bellOfArmRecord);

                    // Add to weapon names and quantities
                    weaponNames.append(weaponName).append(",");
                    weaponQuantities.append(quantityToDistribute).append(",");

                    // Check if the record already exists in DistributionGodown
                    Optional<DistributionGodown> distributionGodownOpt = distributionGodownRepository.findByWeaponNameAndGodownName(weaponName, godownName);
                    DistributionGodown distributionGodown;
                    if (distributionGodownOpt.isPresent()) {
                        distributionGodown = distributionGodownOpt.get();
                        distributionGodown.setQuantity(distributionGodown.getQuantity() + quantityToDistribute);
                    } else {
                        distributionGodown = new DistributionGodown();
                        distributionGodown.setWeaponName(weaponName);
                        distributionGodown.setGodownName(godownName);
                        distributionGodown.setQuantity(quantityToDistribute);
                    }
                    distributionGodownRepository.save(distributionGodown);
                } else {
                    throw new IllegalArgumentException("Insufficient stock for weapon: " + weaponName);
                }
            } else {
                throw new IllegalArgumentException("Weapon not found: " + weaponName);
            }
        }

        // Remove the last comma from weaponNames and weaponQuantities
        if (weaponNames.length() > 0) {
            weaponNames.setLength(weaponNames.length() - 1);
        }
        if (weaponQuantities.length() > 0) {
            weaponQuantities.setLength(weaponQuantities.length() - 1);
        }

        // Store the combined weapon names and quantities in the godown
        DistributionGodown combinedRecord = new DistributionGodown();
        combinedRecord.setWeaponName(weaponNames.toString());
        combinedRecord.setGodownName(godownName);
        combinedRecord.setQuantity(Integer.parseInt(weaponQuantities.toString()));
        distributionGodownRepository.save(combinedRecord);
    }
}

 */

/*
@Service
public class WeaponDistributionService {

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Autowired
    private DistributionGodownRepository distributionGodownRepository;

    @Transactional
    public void distributeWeapons(List<DistributionRequest> requests, String godownName) {
        for (DistributionRequest request : requests) {
            String weaponName = request.getWeaponName();
            int quantityToDistribute = request.getQuantity();

            // Fetch the record from BellOfArmTotalInOutward
            List<BellOfArmTotalInOutward> bellOfArmRecords = bellOfArmTotalInOutwardRepository.findByWeaponName(weaponName);

            if (bellOfArmRecords.isEmpty()) {
                throw new IllegalArgumentException("Weapon not found: " + weaponName);
            }

            BellOfArmTotalInOutward bellOfArmRecord = bellOfArmRecords.get(0); // Assuming there's only one record per weapon name

            if (bellOfArmRecord.getAvailableStock() >= quantityToDistribute) {
                // Update availableStock and distributionStock
                bellOfArmRecord.setAvailableStock(bellOfArmRecord.getAvailableStock() - quantityToDistribute);
                bellOfArmRecord.setDistributionStock(bellOfArmRecord.getDistributionStock() + quantityToDistribute);
                bellOfArmTotalInOutwardRepository.save(bellOfArmRecord);

                // Check if the record already exists in DistributionGodown
                Optional<DistributionGodown> distributionGodownOpt = distributionGodownRepository.findByWeaponNameAndGodownName(weaponName, godownName);
                DistributionGodown distributionGodown;
                if (distributionGodownOpt.isPresent()) {
                    distributionGodown = distributionGodownOpt.get();
                    distributionGodown.setQuantity(distributionGodown.getQuantity() + quantityToDistribute);
                } else {
                    distributionGodown = new DistributionGodown();
                    distributionGodown.setWeaponName(weaponName);
                    distributionGodown.setGodownName(godownName);
                    distributionGodown.setQuantity(quantityToDistribute);
                }
                distributionGodownRepository.save(distributionGodown);
            } else {
                throw new IllegalArgumentException("Insufficient stock for weapon: " + weaponName);
            }
        }
    }
}

 */

@Service
public class WeaponDistributionService {

    @Autowired
    private DistributionGodownRepository distributionGodownRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Transactional
    public List<String> distributeWeapons(DistributionRequest distributionRequest) {
        List<String> failedDistributions = new ArrayList<>();

        for (WeaponDistribution weapon : distributionRequest.getWeapons()) {
            Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOpt = bellOfArmTotalInOutwardRepository
                    .findByWeaponName(weapon.getWeaponName())
                    .stream().findFirst();

            if (bellOfArmTotalInOutwardOpt.isPresent()) {
                BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOpt.get();
                if (bellOfArmTotalInOutward.getAvailableStock() >= weapon.getQuantity()) {
                    bellOfArmTotalInOutward.setAvailableStock(
                            bellOfArmTotalInOutward.getAvailableStock() - weapon.getQuantity());
                    bellOfArmTotalInOutward.setDistributionStock(
                            bellOfArmTotalInOutward.getDistributionStock() + weapon.getQuantity());
                    bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);

                    DistributionGodown distributionGodown = new DistributionGodown();
                    distributionGodown.setWeaponName(weapon.getWeaponName());
                    distributionGodown.setDate(distributionRequest.getCommonInfo().getDate());
                    distributionGodown.setQuantity(weapon.getQuantity());
                    distributionGodown.setRemark(distributionRequest.getCommonInfo().getRemark());
                    distributionGodown.setGodownName(distributionRequest.getCommonInfo().getGodownName());
                    distributionGodown.setEmployeeId(distributionRequest.getCommonInfo().getEmployeeId());
                    distributionGodown.setEmployeeName(distributionRequest.getCommonInfo().getEmployeeName());
                    distributionGodown.setEmployeeDesignation(distributionRequest.getCommonInfo().getEmployeeDesignation());
                    distributionGodown.setEmployeePostingDate(distributionRequest.getCommonInfo().getEmployeePostingDate());

                    distributionGodownRepository.save(distributionGodown);
                } else {
                    failedDistributions.add(weapon.getWeaponName() + " (insufficient stock)");
                }
            } else {
                failedDistributions.add(weapon.getWeaponName() + " (not found)");
            }
        }

        return failedDistributions;
    }


    @Transactional
    public List<String> returnWeapons(ReturnRequest returnRequest) {
        List<String> failedReturns = new ArrayList<>();

        for (WeaponReturn weapon : returnRequest.getWeapons()) {
            Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOpt = bellOfArmTotalInOutwardRepository
                    .findByWeaponName(weapon.getWeaponName())
                    .stream().findFirst();

            if (bellOfArmTotalInOutwardOpt.isPresent()) {
                BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOpt.get();
                if (bellOfArmTotalInOutward.getDistributionStock() >= weapon.getQuantity()) {
                    bellOfArmTotalInOutward.setAvailableStock(
                            bellOfArmTotalInOutward.getAvailableStock() + weapon.getQuantity());
                    bellOfArmTotalInOutward.setDistributionStock(
                            bellOfArmTotalInOutward.getDistributionStock() - weapon.getQuantity());
                    bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);

                    DistributionGodown distributionGodown = new DistributionGodown();
                    distributionGodown.setWeaponName(weapon.getWeaponName());
                    distributionGodown.setDate(returnRequest.getCommonInfo().getDate());
                    distributionGodown.setQuantity(weapon.getQuantity());
                    distributionGodown.setRemark(returnRequest.getCommonInfo().getRemark());
                    distributionGodown.setGodownName(returnRequest.getCommonInfo().getGodownName());
                    distributionGodown.setEmployeeId(returnRequest.getCommonInfo().getEmployeeId());
                    distributionGodown.setEmployeeName(returnRequest.getCommonInfo().getEmployeeName());
                    distributionGodown.setEmployeeDesignation(returnRequest.getCommonInfo().getEmployeeDesignation());
                    distributionGodown.setEmployeePostingDate(returnRequest.getCommonInfo().getEmployeePostingDate());

                    distributionGodownRepository.save(distributionGodown);
                } else {
                    failedReturns.add(weapon.getWeaponName() + " (insufficient distributed stock)");
                }
            } else {
                failedReturns.add(weapon.getWeaponName() + " (not found)");
            }
        }

        return failedReturns;
    }
}






