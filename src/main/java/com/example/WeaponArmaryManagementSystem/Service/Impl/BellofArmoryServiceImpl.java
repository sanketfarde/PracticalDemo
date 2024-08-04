
package com.example.WeaponArmaryManagementSystem.Service.Impl;
import com.example.WeaponArmaryManagementSystem.Dto.BellArmoryDTO;
import com.example.WeaponArmaryManagementSystem.Service.BellofArmoryService;
import com.example.WeaponArmaryManagementSystem.model.BellArmory;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmsReport;
import com.example.WeaponArmaryManagementSystem.model.BellofArmsTotalcount;
import com.example.WeaponArmaryManagementSystem.repository.BellArmoryRepository;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmsReportRepository;
import com.example.WeaponArmaryManagementSystem.repository.BellofArmsTotalcountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BellofArmoryServiceImpl implements BellofArmoryService {

    @Autowired
    private BellArmoryRepository bellArmoryRepository;

    @Autowired
    private BellofArmsTotalcountRepository bellofArmsTotalcountRepository;

    @Override
    public Map<String, Object> getBellArmoriesAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        // Fetch BellArmory reports for the given date
        List<BellArmory> bellArmories = bellArmoryRepository.findByDate(date);

        // Fetch BellofArmsTotalcount totals for the given date
        BellofArmsTotalcount bellofArmsTotalcount = bellofArmsTotalcountRepository.findByDate(date);

        // Add both to the result map
        result.put("bellArmories", bellArmories);
        result.put("bellofArmsTotalcount", bellofArmsTotalcount);

        return result;
    }

    @Override
    public List<Map<String, Object>> getBellArmoriesAndTotalsBetweenDates(String startDate, String endDate) {
        return null;
    }

    public List<BellArmory> getBellArmoriesByWeaponNameAndDate(String weaponName, String date) {
        // Assuming your repository has a method to find by weapon name and date
        return bellArmoryRepository.findByWeaponNameAndDate(weaponName, date);
    }

    @Override
    public List<BellArmory> getBellArmoriesByWeaponNamesAndDate(List<String> weaponNames, String date) {
        return bellArmoryRepository.findByWeaponNameAndDate(String.valueOf(weaponNames), date);
    }

    public BellArmory getBellArmoryById(Long id) {
        return bellArmoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<BellArmory> getAllBellArmories() {
        List<BellArmory> allSummaries = bellArmoryRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(BellArmory::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<BellArmory> getBellArmoriesBetweenDates(String startDate, String endDate) {
        return bellArmoryRepository.findAllByDateBetween(startDate, endDate);
    }

    @Override
    public BellArmory saveBellArmory(BellArmoryDTO bellArmoryDTO) {
        String date = bellArmoryDTO.getDate();
        String[] weaponNames = bellArmoryDTO.getWeaponName().toArray(new String[0]);

        // Retrieve existing BellArmory entry for the given date and weapon names
        BellArmory existingBellArmory = bellArmoryRepository.findByDateAndWeaponName(date, weaponNames[0]);

        // Check if there is an existing entry
        if (existingBellArmory != null) {
            // If an existing entry is found, update it with new data
            updateExistingBellArmory(existingBellArmory, weaponNames, bellArmoryDTO);
            bellArmoryRepository.save(existingBellArmory);
        } else {
            // If no existing entry, create and save a new one
            BellArmory newBellArmory = createNewBellArmory(date, weaponNames, bellArmoryDTO);
            bellArmoryRepository.save(newBellArmory);
        }

        // Update totals after saving BellArmory
        updateTotals(date);

        return existingBellArmory != null ? existingBellArmory : bellArmoryRepository.findByDateAndWeaponName(date, weaponNames[0]);
    }

    // Method to update an existing BellArmory entity with new data
    private void updateExistingBellArmory(BellArmory existingBellArmory, String[] weaponNames, BellArmoryDTO bellArmoryDTO) {
        existingBellArmory.setWeaponName(weaponNames[0]);
        if (weaponNames.length > 1) existingBellArmory.setWeapon1(weaponNames[1]);
        if (weaponNames.length > 2) existingBellArmory.setWeapon2(weaponNames[2]);
        if (weaponNames.length > 3) existingBellArmory.setWeapon3(weaponNames[3]);
        if (weaponNames.length > 4) existingBellArmory.setWeapon4(weaponNames[4]);

        existingBellArmory.setTaddevGodownDistribution(bellArmoryDTO.getTaddevGodownDistribution().toString());
        existingBellArmory.setMumbaiPoliceAdhibhar(String.valueOf(bellArmoryDTO.getMumbaiPoliceAdhibhar()));
        existingBellArmory.setCaLiNa(bellArmoryDTO.getCaLiNa().toString());
        existingBellArmory.setPoliceThaneCR(bellArmoryDTO.getPoliceThaneCR().toString());
        existingBellArmory.setPuneR(bellArmoryDTO.getPuneR().toString());
        // existingBellArmory.setCoachTotal(bellArmoryDTO.getCoachTotal().toString());
        // existingBellArmory.setGodownTotal(bellArmoryDTO.getGodownTotal().toString());
        existingBellArmory.setPuneDeposited(bellArmoryDTO.getPuneDeposited().toString());
        existingBellArmory.setPoliceThaneVitaran(bellArmoryDTO.getPoliceThaneVitaran().toString());
        existingBellArmory.setNaygaonWeaponStock(bellArmoryDTO.getNaygaonWeaponStock().toString());
        existingBellArmory.setGodownGoodstockAvailable(bellArmoryDTO.getGodownGoodstockAvailable().toString());
        existingBellArmory.setGodownDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        existingBellArmory.setWorkshop(bellArmoryDTO.getWorkshop().toString());
        existingBellArmory.setOnDuty(bellArmoryDTO.getOnDuty().toString());
        existingBellArmory.setCoachGoodstockAvailable(bellArmoryDTO.getCoachGoodstockAvailable().toString());
        existingBellArmory.setCoachDamageStockAvailable(bellArmoryDTO.getCoachDamageStockAvailable().toString());
        existingBellArmory.setUpdatedAt(LocalDateTime.now());
        // Update other fields from DTO as needed
    }

    // Method to create a new BellArmory entity with data
    private BellArmory createNewBellArmory(String date, String[] weaponNames, BellArmoryDTO bellArmoryDTO) {
        BellArmory bellArmory = new BellArmory();
        bellArmory.setDate(date);
        bellArmory.setWeaponName(weaponNames[0]);
        if (weaponNames.length > 1) bellArmory.setWeapon1(weaponNames[1]);
        if (weaponNames.length > 2) bellArmory.setWeapon2(weaponNames[2]);
        if (weaponNames.length > 3) bellArmory.setWeapon3(weaponNames[3]);
        if (weaponNames.length > 4) bellArmory.setWeapon4(weaponNames[4]);

        bellArmory.setTaddevGodownDistribution(bellArmoryDTO.getTaddevGodownDistribution().toString());
        bellArmory.setMumbaiPoliceAdhibhar(String.valueOf(bellArmoryDTO.getMumbaiPoliceAdhibhar()));
        bellArmory.setCaLiNa(bellArmoryDTO.getCaLiNa().toString());
        bellArmory.setPoliceThaneCR(bellArmoryDTO.getPoliceThaneCR().toString());
        bellArmory.setPuneR(bellArmoryDTO.getPuneR().toString());
        //  bellArmory.setCoachTotal(bellArmoryDTO.getCoachTotal().toString());
        // bellArmory.setGodownTotal(bellArmoryDTO.getGodownTotal().toString());
        bellArmory.setPuneDeposited(bellArmoryDTO.getPuneDeposited().toString());
        bellArmory.setPoliceThaneVitaran(bellArmoryDTO.getPoliceThaneVitaran().toString());
        bellArmory.setNaygaonWeaponStock(bellArmoryDTO.getNaygaonWeaponStock().toString());
        bellArmory.setGodownGoodstockAvailable(bellArmoryDTO.getGodownGoodstockAvailable().toString());
        bellArmory.setGodownDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        bellArmory.setWorkshop(bellArmoryDTO.getWorkshop().toString());
        bellArmory.setOnDuty(bellArmoryDTO.getOnDuty().toString());
        bellArmory.setCoachGoodstockAvailable(bellArmoryDTO.getCoachGoodstockAvailable().toString());
        bellArmory.setCoachDamageStockAvailable(bellArmoryDTO.getCoachDamageStockAvailable().toString());
        bellArmory.setCreatedAt(LocalDateTime.now());
        // Set other fields from DTO as needed
        return bellArmory;
    }

    private void updateTotals(String date) {
        try {
            // Fetch all BellArmory entries for the given date
            List<BellArmory> bellArmories = bellArmoryRepository.findByDate(date);

            // Initialize total object
            BellofArmsTotalcount total = bellofArmsTotalcountRepository.findByDate(date);
            if (total == null) {
                total = new BellofArmsTotalcount();
                total.setDate(date);
            }

            // Initialize total counters
            int totalTaddevGodownDistribution = 0;
            int totalMumbaiPoliceAdhibhar = 0;
            int totalCaLiNa = 0;
            int totalPoliceThaneCR = 0;
            int totalPuneR = 0;
            int totalCoachTotal = 0;
            int totalGodownTotal = 0;
            int totalPuneDeposited = 0;
            int totalPoliceThaneVitaran = 0;
            int totalNaygaonWeaponStock = 0;
            int totalGodownGoodstockAvailable = 0;
            int totalGodownDamageStockAvailable = 0;
            int totalWorkshop = 0;
            int totalOnDuty = 0;
            int totalCoachGoodstockAvailable = 0;
            int totalCoachDamageStockAvailable = 0;

            // Calculate totals
            for (BellArmory armory : bellArmories) {
                totalTaddevGodownDistribution += sumArrayElements(armory.getTaddevGodownDistribution());
                totalMumbaiPoliceAdhibhar += sumArrayElements(armory.getMumbaiPoliceAdhibhar());
                totalCaLiNa += sumArrayElements(armory.getCaLiNa());
                totalPoliceThaneCR += sumArrayElements(armory.getPoliceThaneCR());
                totalPuneR += sumArrayElements(armory.getPuneR());
                // totalCoachTotal += sumArrayElements(armory.getCoachTotal());
                // totalGodownTotal += sumArrayElements(armory.getGodownTotal());
                totalPuneDeposited += sumArrayElements(armory.getPuneDeposited());
                totalPoliceThaneVitaran += sumArrayElements(armory.getPoliceThaneVitaran());
                totalNaygaonWeaponStock += sumArrayElements(armory.getNaygaonWeaponStock());
                totalGodownGoodstockAvailable += sumArrayElements(armory.getGodownGoodstockAvailable());
                totalGodownDamageStockAvailable += sumArrayElements(armory.getGodownDamageStockAvailable());
                totalWorkshop += sumArrayElements(armory.getWorkshop());
                totalOnDuty += sumArrayElements(armory.getOnDuty());
                totalCoachGoodstockAvailable += sumArrayElements(armory.getCoachGoodstockAvailable());
                totalCoachDamageStockAvailable += sumArrayElements(armory.getCoachDamageStockAvailable());
            }

            // Set the calculated totals to the total object
            total.setTotalTaddevGodownDistribution(String.valueOf(totalTaddevGodownDistribution));
            total.setTotalMumbaiPoliceAdhibhar(String.valueOf(totalMumbaiPoliceAdhibhar));
            total.setTotalCaLiNa(String.valueOf(totalCaLiNa));
            total.setTotalPoliceThaneCR(String.valueOf(totalPoliceThaneCR));
            total.setTotalPuneR(String.valueOf(totalPuneR));
            total.setTotalCoachTotal(String.valueOf(totalCoachTotal));
            // total.setTotalGodownTotal(totalGodownTotal);
            total.setTotalPuneDeposited(String.valueOf(totalPuneDeposited));
            total.setTotal_policeThaneVitaran(String.valueOf(totalPoliceThaneVitaran));
            total.setTotalNaygaonWeaponStock(String.valueOf(totalNaygaonWeaponStock));
            total.setTotalGodownGoodstockAvailable(String.valueOf(totalGodownGoodstockAvailable));
            total.setTotalGodownDamageStockAvailable(String.valueOf(totalGodownDamageStockAvailable));
            total.setTotalWorkshop(String.valueOf(totalWorkshop));
            total.setTotalOnDuty(String.valueOf(totalOnDuty));
            total.setTotalCoachGoodstockAvailable(String.valueOf(totalCoachGoodstockAvailable));
            total.setTotalCoachDamageStockAvailable(String.valueOf(totalCoachDamageStockAvailable));

            // Save the updated total
            bellofArmsTotalcountRepository.save(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to parse integer from string with possible spaces and commas
    private int parseIntFromStr(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        // Remove spaces, commas, and brackets and then parse integer
        String cleanedStr = str.replaceAll("\\s+", "").replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", "");
        return Integer.parseInt(cleanedStr);
    }

    // Helper method to sum elements of a string array
    private int sumArrayElements(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        // Split the string into elements, remove non-digit characters, and sum them
        String[] elements = str.split(",");
        int sum = 0;
        for (String element : elements) {
            element = element.replaceAll("[^\\d]", "");  // Remove any non-digit characters
            if (!element.isEmpty()) {
                sum += Integer.parseInt(element);
            }
        }
        return sum;
    }




    @Autowired
    private BellOfArmsReportRepository bellOfArmsReportRepository;
    @Override
    public BellOfArmsReport saveBellOfArmsReport(BellOfArmsReport bellOfArmsReport) {
        bellOfArmsReport.setCreatedAt(LocalDateTime.now());
        bellOfArmsReport.setUpdatedAt(LocalDateTime.now());
        return bellOfArmsReportRepository.save(bellOfArmsReport);
    }

    @Override
    public List <BellOfArmsReport> getByButtNo(String buttNo) {
        return (List<BellOfArmsReport>) bellOfArmsReportRepository.findByButtNo(buttNo);
    }

    @Override
    public List<BellOfArmsReport> getAllBellOfArmsReports() {
        return bellOfArmsReportRepository.findAll();
    }

    @Override
    public List<BellOfArmsReport> getByDepositedDate(LocalDate depositedDate) {
        return bellOfArmsReportRepository.findByDepositedDate(String.valueOf(depositedDate));
    }

    @Override
    public List<BellOfArmsReport> getBetweenDates(String startDate, String endDate) {
        return bellOfArmsReportRepository.findByDepositedDateBetween(startDate, endDate);
    }



    @Override
    public List<BellArmory> getBellArmoriesByDate(String date) {
        return bellArmoryRepository.findByDate(date);
    }
}

    // existing old code which is working fine
/*
    @Override
    public BellArmory saveBellArmory(BellArmoryDTO bellArmoryDTO) {
        String date = bellArmoryDTO.getDate();
        String weaponName = bellArmoryDTO.getWeaponName().toString();

        // Retrieve existing BellArmory entry for the given date and weapon name
        BellArmory existingBellArmory = bellArmoryRepository.findByDateAndWeaponName(date, weaponName);

        // Check if there is an existing entry
        if (existingBellArmory != null) {
            // If an existing entry is found, update it with new data
            updateExistingBellArmory(existingBellArmory, bellArmoryDTO);
            return existingBellArmory;
        } else {
            // If no existing entry, save a new one
            BellArmory newBellArmory = createNewBellArmory(date, weaponName, bellArmoryDTO);
            return newBellArmory;
        }
    }

    private void updateExistingBellArmory(BellArmory existingBellArmory, BellArmoryDTO bellArmoryDTO) {
        existingBellArmory.setTaddevGodownDistribution(bellArmoryDTO.getTaddevGodownDistribution().toString());
        existingBellArmory.setMumbaiPoliceAdhibhar(bellArmoryDTO.getMumbaiPoliceAdhibhar().toString());
        existingBellArmory.setCaLiNa(bellArmoryDTO.getCaLiNa().toString());
        existingBellArmory.setPoliceThaneCR(bellArmoryDTO.getPoliceThaneCR().toString());
        existingBellArmory.setPuneR(bellArmoryDTO.getPuneR().toString());
        existingBellArmory.setCoachTotal(bellArmoryDTO.getCoachTotal().toString());
        existingBellArmory.setGodownTotal(bellArmoryDTO.getGodownTotal().toString());
        existingBellArmory.setPuneDeposited(bellArmoryDTO.getPuneDeposited().toString());
        existingBellArmory.setPoliceThaneVitaran(bellArmoryDTO.getPoliceThaneVitaran().toString());
        existingBellArmory.setNaygaonWeaponStock(bellArmoryDTO.getNaygaonWeaponStock().toString());
        existingBellArmory.setGodownGoodstockAvailable(bellArmoryDTO.getGodownGoodstockAvailable().toString());
        existingBellArmory.setGodownDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        existingBellArmory.setWorkshop(bellArmoryDTO.getWorkshop().toString());
        existingBellArmory.setOnDuty(bellArmoryDTO.getOnDuty().toString());
        existingBellArmory.setCoachGoodstockAvailable(bellArmoryDTO.getCoachGoodstockAvailable().toString());
        existingBellArmory.setCoachDamageStockAvailable(bellArmoryDTO.getCoachDamageStockAvailable().toString());

        bellArmoryRepository.save(existingBellArmory); // Save the updated entry
        updateTotals(existingBellArmory.getDate());
    }

    private BellArmory createNewBellArmory(String date, String weaponName, BellArmoryDTO bellArmoryDTO) {
        BellArmory newBellArmory = new BellArmory();
        newBellArmory.setDate(date);
        newBellArmory.setWeaponName(weaponName);
        // Set other fields from the DTO
        newBellArmory.setTaddevGodownDistribution(bellArmoryDTO.getTaddevGodownDistribution().toString());
        newBellArmory.setMumbaiPoliceAdhibhar(bellArmoryDTO.getMumbaiPoliceAdhibhar().toString());
        newBellArmory.setCaLiNa(bellArmoryDTO.getCaLiNa().toString());
        newBellArmory.setPoliceThaneCR(bellArmoryDTO.getPoliceThaneCR().toString());
        newBellArmory.setPuneR(bellArmoryDTO.getPuneR().toString());
        newBellArmory.setCoachTotal(bellArmoryDTO.getCoachTotal().toString());
        newBellArmory.setGodownTotal(bellArmoryDTO.getGodownTotal().toString());
        newBellArmory.setPuneDeposited(bellArmoryDTO.getPuneDeposited().toString());
        newBellArmory.setPoliceThaneVitaran(bellArmoryDTO.getPoliceThaneVitaran().toString());
        newBellArmory.setNaygaonWeaponStock(bellArmoryDTO.getNaygaonWeaponStock().toString());
        newBellArmory.setGodownGoodstockAvailable(bellArmoryDTO.getGodownGoodstockAvailable().toString());
        newBellArmory.setGodownDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        newBellArmory.setWorkshop(bellArmoryDTO.getWorkshop().toString());
        newBellArmory.setOnDuty(bellArmoryDTO.getOnDuty().toString());
        newBellArmory.setCoachGoodstockAvailable(bellArmoryDTO.getCoachGoodstockAvailable().toString());
        newBellArmory.setCoachDamageStockAvailable(bellArmoryDTO.getCoachDamageStockAvailable().toString());

        bellArmoryRepository.save(newBellArmory); // Save the new entry

        // Update the totals
        updateTotals(date);
        return newBellArmory;
    }*/


// this methods for the bell of arms report



/*

import com.example.Emanyata.Dto.BellArmoryDTO;
import com.example.Emanyata.Entity.BellArmory;
import com.example.Emanyata.Entity.BellOfArmsReport;
import com.example.Emanyata.Repo.BellArmoryRepository;
import com.example.Emanyata.Repo.BellOfArmsReportRepository;
import com.example.Emanyata.Service.BellofArmoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BellofArmoryServiceImpl implements BellofArmoryService {

    @Autowired
    private BellArmoryRepository bellArmoryRepository;

    @Autowired
    private BellOfArmsReportRepository bellOfArmsReportRepository;
    @Override
    public void saveBellArmory(BellArmoryDTO bellArmoryDTO) {
        // Check if an entry with the same weaponName and date already exists
        if (bellArmoryRepository.existsByWeaponNameAndDate(
                bellArmoryDTO.getWeaponName().toString(), bellArmoryDTO.getDate())) {
            throw new IllegalStateException("Weapon name for this date is already registered.");
        }

        // If not exists, proceed with saving
        BellArmory bellArmory = new BellArmory();
        // Set data from DTO to entity
        bellArmory.setDate(bellArmoryDTO.getDate());
        bellArmory.setWeaponName(bellArmoryDTO.getWeaponName().toString());
        bellArmory.setMumbaiPoliceAdhibhar(bellArmoryDTO.getMumbaiPoliceAdhibhar().toString());
        bellArmory.setPoliceThaneVitaran(bellArmoryDTO.getPoliceThaneVitaran().toString());
        bellArmory.setTaddevGodownDistribution(bellArmoryDTO.getTaddevGodownDistribution().toString());
        bellArmory.setCaLiNa(bellArmoryDTO.getCaLiNa().toString());
        bellArmory.setPuneR(bellArmoryDTO.getPuneR().toString());
        bellArmory.setPuneDeposited(bellArmoryDTO.getPuneDeposited().toString());
        bellArmory.setNaygaonWeaponStock(bellArmoryDTO.getNaygaonWeaponStock().toString());
        bellArmory.setPoliceThaneCR(bellArmoryDTO.getPoliceThaneCR().toString());
        bellArmory.setGodownGoodstockAvailable(bellArmoryDTO.getGodownGoodstockAvailable().toString());
        bellArmory.setGodownDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        bellArmory.setGodownTotal(bellArmoryDTO.getGodownTotal().toString());
        bellArmory.setWorkshop(bellArmoryDTO.getWorkshop().toString());
        bellArmory.setOnDuty(bellArmoryDTO.getOnDuty().toString());
        bellArmory.setCoachDamageStockAvailable(bellArmoryDTO.getGodownDamageStockAvailable().toString());
        bellArmory.setCoachGoodstockAvailable(bellArmoryDTO.getCoachGoodstockAvailable().toString());
        bellArmory.setCoachTotal(bellArmoryDTO.getCoachTotal().toString());
        // Set other fields similarly

        // Save entity to repository
        bellArmoryRepository.save(bellArmory);
    }
    @Override
    public List<BellArmory> getBellArmoriesByDate(String date) {
        return bellArmoryRepository.findByDate(date);
    }

    @Override
    public List<BellArmory> getAllBellArmories() {
        return bellArmoryRepository.findAll();
    }
     @Override
    public BellOfArmsReport saveBellOfArmsReport(BellOfArmsReport bellOfArmsReport) {
        bellOfArmsReport.setCreatedAt(LocalDateTime.now());
        bellOfArmsReport.setUpdatedAt(LocalDateTime.now());
        return bellOfArmsReportRepository.save(bellOfArmsReport);
    }

    @Override
    public List <BellOfArmsReport> getByButtNo(String buttNo) {
        return (List<BellOfArmsReport>) bellOfArmsReportRepository.findByButtNo(buttNo);
    }

    @Override
    public List<BellOfArmsReport> getAllBellOfArmsReports() {
        return bellOfArmsReportRepository.findAll();
    }

    @Override
    public List<BellArmory> getBellArmoriesByDate(String date) {
        return bellArmoryRepository.findByDate(date);
    }

    }
*/

// this methods for the bell of arms Reports


