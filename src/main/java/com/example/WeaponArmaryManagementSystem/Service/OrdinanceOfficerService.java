package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.BellOfArmTotalInOutward;
import com.example.WeaponArmaryManagementSystem.model.DistributionGodown;
import com.example.WeaponArmaryManagementSystem.model.OrdinanceOfficer;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmTotalInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.DistributionGodownRepository;
import com.example.WeaponArmaryManagementSystem.repository.OrdinanceOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
@Service
public class OrdinanceOfficerService {

    @Autowired
    private OrdinanceOfficerRepository ordinanceOfficerRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Autowired
    private DistributionGodownRepository distributionGodownRepository;

    @Transactional
    public void distributeWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Check and update stock for the primary weapon
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());

        // Check and update stock for bionet, magazine, filler, and launcher
        if (ordinanceOfficer.getBionet() != null) {
            updateStock(ordinanceOfficer.getBionet(), ordinanceOfficer.getWeaponIssue());
        }
        if (ordinanceOfficer.getMagazine() != null) {
            updateStock(ordinanceOfficer.getMagazine(), ordinanceOfficer.getWeaponIssue());
        }
        if (ordinanceOfficer.getFiller() != null) {
            updateStock(ordinanceOfficer.getFiller(), ordinanceOfficer.getWeaponIssue());
        }
        if (ordinanceOfficer.getLauncher() != null) {
            updateStock(ordinanceOfficer.getLauncher(), ordinanceOfficer.getWeaponIssue());
        }

        // Save entry in DistributionGodown
        DistributionGodown distributionGodown = new DistributionGodown();
        distributionGodown.setWeaponName(ordinanceOfficer.getWeaponName());
        distributionGodown.setDate(ordinanceOfficer.getWeaponDepositeDateTime().toString());
        distributionGodown.setQuantity(Integer.parseInt(ordinanceOfficer.getWeaponIssue()));
        distributionGodown.setRemark(ordinanceOfficer.getRemark());
        distributionGodown.setGodownName(ordinanceOfficer.getPlaceOfDuty());
        distributionGodown.setEmployeeId(ordinanceOfficer.getEmployeeId());
        distributionGodown.setEmployeeName(ordinanceOfficer.getEmployeeName());
        distributionGodown.setEmployeeDesignation(ordinanceOfficer.getEmployeeDesignation());
        distributionGodown.setEmployeePostingDate(ordinanceOfficer.getEmployeePostingDate());

        distributionGodownRepository.save(distributionGodown);
    }

    private void updateStock(String weaponName, String issueQuantity) throws Exception {
        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);
        if (bellOfArmTotalInOutward.getAvailableStock() < issueQty) {
            throw new Exception("Insufficient stock for weapon: " + weaponName);
        }

        bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() - issueQty);
        bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() + issueQty);

        bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);
    }
}

 */

/*
@Service
public class OrdinanceOfficerService {

    @Autowired
    private OrdinanceOfficerRepository ordinanceOfficerRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Transactional
    public void distributeWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Check and update stock for the primary weapon
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());

        // Check and update stock for bionet, magazine, filler, and launcher
        updateStock("bionet", ordinanceOfficer.getBionet());
        updateStock("magazine", ordinanceOfficer.getMagazine());
        updateStock("filler", ordinanceOfficer.getFiller());
        updateStock("launcher", ordinanceOfficer.getLauncher());

        // Save entry in OrdinanceOfficer
        ordinanceOfficerRepository.save(ordinanceOfficer);
    }

    private void updateStock(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);
        if (bellOfArmTotalInOutward.getAvailableStock() < issueQty) {
            throw new Exception("Insufficient stock for weapon: " + weaponName);
        }

        bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() - issueQty);
        bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() + issueQty);

        bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);
    }
}


 */

/*
@Service
public class OrdinanceOfficerService {

    @Autowired
    private OrdinanceOfficerRepository ordinanceOfficerRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Transactional
    public void distributeWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Check stock availability for all items first
        checkStockAvailability(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        checkStockAvailability("bionet", ordinanceOfficer.getBionet());
        checkStockAvailability("magazine", ordinanceOfficer.getMagazine());
        checkStockAvailability("filler", ordinanceOfficer.getFiller());
        checkStockAvailability("launcher", ordinanceOfficer.getLauncher());

        // If all items have sufficient stock, proceed to update the stock
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        updateStock("bionet", ordinanceOfficer.getBionet());
        updateStock("magazine", ordinanceOfficer.getMagazine());
        updateStock("filler", ordinanceOfficer.getFiller());
        updateStock("launcher", ordinanceOfficer.getLauncher());

        // Save entry in OrdinanceOfficer
        ordinanceOfficerRepository.save(ordinanceOfficer);
    }

    private void checkStockAvailability(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);
        if (bellOfArmTotalInOutward.getAvailableStock() < issueQty) {
            throw new Exception("Insufficient stock for weapon: " + weaponName);
        }
    }

    private void updateStock(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);

        bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() - issueQty);
        bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() + issueQty);

        bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);
    }
}

 */

/*
    // This is proper method of of Distribution properly working 100%

@Service
public class OrdinanceOfficerService {

    @Autowired
    private OrdinanceOfficerRepository ordinanceOfficerRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Transactional
    public void distributeWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Check stock availability for all items first
        checkAllStockAvailability(ordinanceOfficer);

        // If all items have sufficient stock, proceed to update the stock
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        updateStock("bionet", ordinanceOfficer.getBionet());
        updateStock("magazine", ordinanceOfficer.getMagazine());
        updateStock("filler", ordinanceOfficer.getFiller());
        updateStock("launcher", ordinanceOfficer.getLauncher());

        // Save entry in OrdinanceOfficer
        ordinanceOfficerRepository.save(ordinanceOfficer);
    }

    private void checkAllStockAvailability(OrdinanceOfficer ordinanceOfficer) throws Exception {
        checkStockAvailability(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        checkStockAvailability("bionet", ordinanceOfficer.getBionet());
        checkStockAvailability("magazine", ordinanceOfficer.getMagazine());
        checkStockAvailability("filler", ordinanceOfficer.getFiller());
        checkStockAvailability("launcher", ordinanceOfficer.getLauncher());
    }

    private void checkStockAvailability(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);
        if (bellOfArmTotalInOutward.getAvailableStock() < issueQty) {
            throw new Exception("Insufficient stock for weapon: " + weaponName);
        }
    }

    private void updateStock(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);

        bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() - issueQty);
        bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() + issueQty);

        bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);
    }
}

 */

@Service
public class OrdinanceOfficerService {

    @Autowired
    private OrdinanceOfficerRepository ordinanceOfficerRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Transactional
    public void distributeWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Check stock availability for all items first
        checkAllStockAvailability(ordinanceOfficer);

        // If all items have sufficient stock, proceed to update the stock
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue(), false);
        updateStock("bionet", ordinanceOfficer.getBionet(), false);
        updateStock("magazine", ordinanceOfficer.getMagazine(), false);
        updateStock("filler", ordinanceOfficer.getFiller(), false);
        updateStock("launcher", ordinanceOfficer.getLauncher(), false);

        // Save entry in OrdinanceOfficer
        ordinanceOfficerRepository.save(ordinanceOfficer);
    }

    @Transactional
    public void returnWeaponsAndAccessories(OrdinanceOfficer ordinanceOfficer) throws Exception {
        // Ensure that all returned items can be processed (i.e., they were previously distributed)
        checkAllReturnStock(ordinanceOfficer);

        // If all items can be returned, proceed to update the stock
        updateStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue(), true);
        updateStock("bionet", ordinanceOfficer.getBionet(), true);
        updateStock("magazine", ordinanceOfficer.getMagazine(), true);
        updateStock("filler", ordinanceOfficer.getFiller(), true);
        updateStock("launcher", ordinanceOfficer.getLauncher(), true);

        // Save entry in OrdinanceOfficer
        ordinanceOfficerRepository.save(ordinanceOfficer);
    }

    private void checkAllStockAvailability(OrdinanceOfficer ordinanceOfficer) throws Exception {
        checkStockAvailability(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        checkStockAvailability("bionet", ordinanceOfficer.getBionet());
        checkStockAvailability("magazine", ordinanceOfficer.getMagazine());
        checkStockAvailability("filler", ordinanceOfficer.getFiller());
        checkStockAvailability("launcher", ordinanceOfficer.getLauncher());
    }

    private void checkStockAvailability(String weaponName, String issueQuantity) throws Exception {
        if (issueQuantity == null || issueQuantity.isEmpty()) {
            return; // Skip if no issue quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int issueQty = Integer.parseInt(issueQuantity);
        if (bellOfArmTotalInOutward.getAvailableStock() < issueQty) {
            throw new Exception("Insufficient stock for weapon: " + weaponName);
        }
    }

    private void checkAllReturnStock(OrdinanceOfficer ordinanceOfficer) throws Exception {
        checkReturnStock(ordinanceOfficer.getWeaponName(), ordinanceOfficer.getWeaponIssue());
        checkReturnStock("bionet", ordinanceOfficer.getBionet());
        checkReturnStock("magazine", ordinanceOfficer.getMagazine());
        checkReturnStock("filler", ordinanceOfficer.getFiller());
        checkReturnStock("launcher", ordinanceOfficer.getLauncher());
    }

    private void checkReturnStock(String weaponName, String returnQuantity) throws Exception {
        if (returnQuantity == null || returnQuantity.isEmpty()) {
            return; // Skip if no return quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int returnQty = Integer.parseInt(returnQuantity);
        if (bellOfArmTotalInOutward.getDistributionStock() < returnQty) {
            throw new Exception("Insufficient distributed stock for weapon: " + weaponName);
        }
    }

    private void updateStock(String weaponName, String quantity, boolean isReturning) throws Exception {
        if (quantity == null || quantity.isEmpty()) {
            return; // Skip if no quantity provided
        }

        Optional<BellOfArmTotalInOutward> bellOfArmTotalInOutwardOptional = bellOfArmTotalInOutwardRepository.findOneByWeaponName(weaponName);
        if (!bellOfArmTotalInOutwardOptional.isPresent()) {
            throw new Exception("Weapon not found: " + weaponName);
        }

        BellOfArmTotalInOutward bellOfArmTotalInOutward = bellOfArmTotalInOutwardOptional.get();
        int qty = Integer.parseInt(quantity);

        if (isReturning) {
            bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() + qty);
            bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() - qty);
        } else {
            bellOfArmTotalInOutward.setAvailableStock(bellOfArmTotalInOutward.getAvailableStock() - qty);
            bellOfArmTotalInOutward.setDistributionStock(bellOfArmTotalInOutward.getDistributionStock() + qty);
        }

        bellOfArmTotalInOutwardRepository.save(bellOfArmTotalInOutward);
    }
}