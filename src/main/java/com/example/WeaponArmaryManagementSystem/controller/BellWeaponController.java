package com.example.WeaponArmaryManagementSystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import com.example.WeaponArmaryManagementSystem.Dto.BellweaponDto;
import com.example.WeaponArmaryManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.Dto.WeaponSummaryDto;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponSummaryandTotalDto;
import com.example.WeaponArmaryManagementSystem.model.BellWeapon;
import com.example.WeaponArmaryManagementSystem.model.DistributionGodowReturnData;
import com.example.WeaponArmaryManagementSystem.model.DistributionGodown;
import com.example.WeaponArmaryManagementSystem.model.TotalWeaponSummary;
import com.example.WeaponArmaryManagementSystem.model.WeaponSummary;

@RestController
@CrossOrigin(origins = "*")
public class BellWeaponController {

    private final WeaponSummaryRepo weaponSummaryRepo;
    private final BellWeaponSummaryRepository bellWeaponSummaryRepository;
    private final DistributionGodownRepository distributionGodownRepository;
    private final BellWeaponRepository bellWeaponRepository;
    private final TotalWeaponSummaryRepo totalWeaponSummaryRepo;
    private final DistributionGodowReturnDataRepository distributionGodowReturnDataRepository;
    // private final DistributionGodownTotalRepository totalRepository;

    @Autowired
    public BellWeaponController(TotalWeaponSummaryRepo totalWeaponSummaryRepo, WeaponSummaryRepo weaponSummaryRepo, BellWeaponSummaryRepository bellWeaponSummaryRepository, BellWeaponRepository bellWeaponRepository,
                                DistributionGodowReturnDataRepository distributionGodowReturnDataRepository, DistributionGodownRepository distributionGodownRepository) {

        this.totalWeaponSummaryRepo = totalWeaponSummaryRepo;
        this.weaponSummaryRepo = weaponSummaryRepo;
        this.bellWeaponSummaryRepository = bellWeaponSummaryRepository;
        this.distributionGodownRepository = distributionGodownRepository;
        this.bellWeaponRepository = bellWeaponRepository;
        // this.totalRepository = totalRepository;
        this.distributionGodowReturnDataRepository = distributionGodowReturnDataRepository;
    }


    @PostMapping("/save_distribution_return_data")
    public ResponseEntity<?> saveDistributionReturnData(@RequestBody Map<String, Object> distributionReturnDataMap) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = new ArrayList<>();

            String godownName = (String) distributionReturnDataMap.get("godownName");
            String returnDate = (String) distributionReturnDataMap.get("returnDate");
            String distributeDate = (String) distributionReturnDataMap.get("distributeDate");
            List<String> weaponNames = (List<String>) distributionReturnDataMap.get("weaponNames");
            List<String> quantitiesAsString = (List<String>) distributionReturnDataMap.get("quantities");
            String remark = (String) distributionReturnDataMap.get("remark");
            String employeeId = (String) distributionReturnDataMap.get("employeeId");
            String employeeName = (String) distributionReturnDataMap.get("employeeName");
            String employeeDesignation = (String) distributionReturnDataMap.get("employeeDesignation");
            String employeePostingDate = (String) distributionReturnDataMap.get("employeePostingDate");
            String weaponCondition = (String) distributionReturnDataMap.get("weaponCondition");
            String submittedBy = (String) distributionReturnDataMap.get("submittedBy");
            String weaponDamage = (String) distributionReturnDataMap.get("weaponDamage");

            // Null checks
            if (godownName == null || returnDate == null || distributeDate == null || weaponNames == null ||
                    quantitiesAsString == null || remark == null || employeeId == null || employeeName == null ||
                    employeeDesignation == null || employeePostingDate == null || weaponCondition == null ||
                    submittedBy == null || weaponDamage == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
            }

            if (weaponNames.size() != quantitiesAsString.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mismatch between weapon names and quantities");
            }

            for (int i = 0; i < weaponNames.size(); i++) {
                DistributionGodowReturnData distributionReturnData = new DistributionGodowReturnData();
                distributionReturnData.setWeaponName(weaponNames.get(i).trim());
                distributionReturnData.setReturnDate(returnDate);
                distributionReturnData.setDistributeDate(distributeDate);
                distributionReturnData.setGodownName(godownName);
                distributionReturnData.setRemark(remark);
                distributionReturnData.setEmployeeId(employeeId);
                distributionReturnData.setEmployeeName(employeeName);
                distributionReturnData.setEmployeeDesignation(employeeDesignation);
                distributionReturnData.setEmployeePostingDate(employeePostingDate);
                distributionReturnData.setWeaponCondition(weaponCondition);
                distributionReturnData.setSubmittedBy(submittedBy);
                distributionReturnData.setWeaponDamage(weaponDamage);

                try {
                    // Parse quantities from String to Integer
                    Integer quantity = Integer.parseInt(quantitiesAsString.get(i));
                    distributionReturnData.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity format at index " + i);
                }

                distributionReturnDataList.add(distributionReturnData);
            }

            List<DistributionGodowReturnData> savedDistributionReturnData = distributionGodowReturnDataRepository.saveAll(distributionReturnDataList);

            if (savedDistributionReturnData.size() == distributionReturnDataList.size()) {
                return ResponseEntity.status(HttpStatus.CREATED).body("\"{\\\"message\\\": \\\"All distribution return data saved successfully\\\",\\\"Id\\\":0}\"");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Some distribution return data failed to save\",\"Id\": 1}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\\\"message\\\": \\\"Some distribution return data failed to save\\\",\\\"Id\\\": 1} " + e.getMessage());
        }
    }


    @PostMapping("/get_all_distribution_return_data")
    public ResponseEntity<?> getAllDistributionReturnData() {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findAll();
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data: " + e.getMessage());
        }
    }


    @PostMapping("/get_distribution_return_data_between_dates")
    public ResponseEntity<?> getDistributionReturnDataBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findByReturnDateBetween(startDate, endDate);
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data: " + e.getMessage());
        }
    }
    
 
    /*
    //07/06/2024
    @PostMapping("/getDistributionDataByDateAndGodown")
    public ResponseEntity<?> getDistributionDataByDateAndGodown(@RequestParam String date, @RequestParam String godownName) {
        if (date == null || date.isEmpty()) {
            return ResponseEntity.badRequest().body("Date parameter is required");
        }
        
        if (godownName == null || godownName.isEmpty()) {
            return ResponseEntity.badRequest().body("GodownName parameter is required");
        }

        List<DistributionGodown> entries = distributionGodownRepository.findByDateAndGodownName(date, godownName);

        if (entries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given date and godownName");
        }

        
        //this is way to customize your output json
        Map<String, Object> response = new HashMap<>();
       // response.put("date", date);
       // response.put("godownName", godownName);
        response.put("entries", entries);

        return ResponseEntity.ok(response);
    }*/


    //run ok 07/06/2024
    @PostMapping("/getDistributionDataByDate")
    public ResponseEntity<?> getDistributionDataByDate(@RequestParam String date) {
        if (date == null || date.isEmpty()) {
            return ResponseEntity.badRequest().body("Date parameter is required");
        }

        List<DistributionGodownSummary> summaries = distributionGodownRepository.findTotalQuantityByDate(date);
        List<DistributionGodown> entries = distributionGodownRepository.findByDate(date);

        Map<String, Object> response = new HashMap<>();
        response.put("date", date);
        response.put("summaries", summaries);
        response.put("entries", entries);

        return ResponseEntity.ok(response);
    }



    
    /*
    @PostMapping("/get_distribution_return_data_by_date_and_godown")
    public ResponseEntity<?> getDistributionReturnDataByDateAndGodown(@RequestParam String distributeDate, @RequestParam String godownName) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findByReturnDateAndGodownName(distributeDate, godownName);
            if (distributionReturnDataList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given returnDate and godownName");
            }
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data: " + e.getMessage());
        }
    }


     */

    
    
 

    /*
    //run ok
    @PostMapping("/get_distribution_return_data_by_date_and_godown")
    public ResponseEntity<?> getDistributionReturnDataByDateAndGodown(@RequestParam String returnDate, @RequestParam String godownName) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findByReturnDateAndGodownName(returnDate, godownName);
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data by date and godown: " + e.getMessage());
        }
    }
    */


    @PostMapping("/get_distribution_godown_by_date_and_godown")
    public ResponseEntity<?> getDistributionGodownsByDateAndGodownName(
            @RequestParam("date") String date,
            @RequestParam("godownName") String godownName) {
        try {
            List<DistributionGodown> distributionGodowns = distributionGodownRepository.findByDateAndGodownName(date, godownName);
            if (distributionGodowns.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found for the provided date and godown name");
            }
            return ResponseEntity.ok(distributionGodowns);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution godown details: " + e.getMessage());
        }
    }


    @PostMapping("/get_weapon_names_by_date_and_godown")
    public ResponseEntity<?> getWeaponNamesByDateAndGodownName(
            @RequestParam("date") String date,
            @RequestParam("godownName") String godownName) {
        try {
            List<String> weaponNames = distributionGodownRepository.findWeaponNamesByDateAndGodownName(date, godownName);
            if (weaponNames.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No weapon names found for the provided date and godown name");
            }
            return ResponseEntity.ok(weaponNames);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving weapon names: " + e.getMessage());
        }
    }


    //08/06/2024
    @PostMapping("/getDistributionDataByDateAndGodown")
    public ResponseEntity<?> getDistributionDataByDateAndGodown(@RequestParam String date, @RequestParam String godownName) {
        if (date == null || date.isEmpty()) {
            return ResponseEntity.badRequest().body("Date parameter is required");
        }

        if (godownName == null || godownName.isEmpty()) {
            return ResponseEntity.badRequest().body("GodownName parameter is required");
        }

        List<DistributionGodown> entries = distributionGodownRepository.findByDateAndGodownName(date, godownName);
        List<DistributionGodownSummary> summaries = distributionGodownRepository.findTotalQuantityByDateAndGodownName(date, godownName);

        if (entries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given date and godownName");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("entries", entries);
        response.put("summaries", summaries);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/save_distribution_godown_details1")
    public ResponseEntity<?> saveDistributionGodowns1(@RequestBody Map<String, Object> distributionGodownMap) {
        try {
            List<DistributionGodown> distributionGodowns = new ArrayList<>();

            String godownName = (String) distributionGodownMap.get("godownName");
            String date = (String) distributionGodownMap.get("date");
            List<String> weaponNames = (List<String>) distributionGodownMap.get("weaponNames");
            List<String> quantitiesAsString = (List<String>) distributionGodownMap.get("quantities"); // Change to String
            String remark = (String) distributionGodownMap.get("remark");

            // Null checks
            if (godownName == null || date == null || weaponNames == null || quantitiesAsString == null || remark == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
            }

            if (weaponNames.size() != quantitiesAsString.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mismatch between weapon names and quantities");
            }

            for (int i = 0; i < weaponNames.size(); i++) {
                DistributionGodown distributionGodown = new DistributionGodown();
                distributionGodown.setWeaponName(weaponNames.get(i).trim()); // Trim any leading/trailing spaces
                distributionGodown.setDate(date);
                distributionGodown.setGodownName(godownName);
                distributionGodown.setRemark(remark);

                try {
                    // Parse quantities from String to Integer
                    Integer quantity = Integer.parseInt(quantitiesAsString.get(i));
                    distributionGodown.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity format at index " + i);
                }

                // distributionGodown.setTotal(String.valueOf(calculateTotalWeaponCount1(distributionGodown.getQuantity())));
                distributionGodowns.add(distributionGodown);
            }

            List<DistributionGodown> savedDistributionGodowns = distributionGodownRepository.saveAll(distributionGodowns);

            if (savedDistributionGodowns.size() == distributionGodowns.size()) {
                return ResponseEntity.status(HttpStatus.CREATED).body( "{\"message\": \"Data Saved Successfully...\",\"Id\":0}");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( "{\"message\": \"Data Not Saved...\",\"Id\":1}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data Not Saved...\",\"Id\":2}" + e.getMessage());
        }
    }

    private int calculateTotalWeaponCount1(int quantity) {
        return quantity;
    }


    @PostMapping("/save_bell_weapon")
    public ResponseEntity<String> saveBellWeapon(@RequestBody BellWeapon bellWeapon) {
        try {
            // Save the BellWeapon entity with all fields
            bellWeaponRepository.save(bellWeapon);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id:0\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data Not Saved successfully...Id:1\"}");
        }
    }


    @PostMapping("/listBellweapon")
    public ResponseEntity<List<BellWeapon>> listBellweapon() {
        try {
            List<BellWeapon> bellweapon = bellWeaponRepository.findAll();
            return new ResponseEntity<>(bellweapon, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/all_bell_weapons")
    public List<BellWeapon> getAllBellWeapons() {
        return bellWeaponRepository.findAll();
    }


    @PostMapping("/all_distribution_godowns_details")
    public List<DistributionGodown> getAllDistributionGodownsDetails() {
        return distributionGodownRepository.findAll();
    }


    @PostMapping("/all_distribution_bell_weapons")
    public ResponseEntity<List<BellWeapon>> getAllDistributionWeapons() {
        try {
            List<BellWeapon> distributionWeapons = bellWeaponRepository.findByWeaponType("distributionWeapon");
            return ResponseEntity.ok(distributionWeapons);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //08/06/2024
    @PostMapping("/all_dept_distribution_bell_weapons")
    public ResponseEntity<List<BellWeapon>> getAllDeptDistributionWeapons() {
        try {
            List<BellWeapon> deptDistributionWeapons = bellWeaponRepository.findByWeaponTypeAndStatus("department_distribution", "true");
            return ResponseEntity.ok(deptDistributionWeapons);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*

    @PostMapping("/all_dept_distribution_bell_weapons")
    public ResponseEntity<List<BellWeapon>> getAllDeptDistributionWeapons() {
        try {
            List<BellWeapon> dept_distributionWeapons = bellWeaponRepository.findByWeaponType("department_distribution");
            return ResponseEntity.ok(dept_distributionWeapons);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

     */


    @PostMapping("/bell_weapon/{id}")
    public ResponseEntity<BellWeapon> getBellWeaponById(@PathVariable Long id) {
        try {
            Optional<BellWeapon> bellWeapon = bellWeaponRepository.findById(id);
            if (bellWeapon.isPresent()) {
                return ResponseEntity.ok(bellWeapon.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/all_bell_weapon_names")
    public List<String> getAllWeaponNames() {
        return bellWeaponRepository.findAllWeaponNames();
    }


    //04/06/2024 by manish as per viplav
    @PostMapping("/update_bell_weapon")
    public ResponseEntity<String> updateBellWeapon(@RequestBody BellWeapon updatedBellWeapon) {
        try {
            BellWeapon existingBellWeapon = bellWeaponRepository.findById((updatedBellWeapon.getId()))
                    .orElseThrow(() -> new RuntimeException("Bell Weapon not found with id: " + updatedBellWeapon.getId()));

            existingBellWeapon.setWeaponName(updatedBellWeapon.getWeaponName());
            existingBellWeapon.setWeapon1(updatedBellWeapon.getWeapon1());
            existingBellWeapon.setWeapon2(updatedBellWeapon.getWeapon2());
            existingBellWeapon.setWeapon3(updatedBellWeapon.getWeapon3());
            existingBellWeapon.setWeapon4(updatedBellWeapon.getWeapon4());
            existingBellWeapon.setStatus(updatedBellWeapon.getStatus());
            existingBellWeapon.setWeaponType(updatedBellWeapon.getWeaponType());

            bellWeaponRepository.save(existingBellWeapon);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Data updated successfully...Id:0\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data not updated successfully...Id:1\"}");
        }
    }
//"Bell Weapon updated successfully.""Failed to update Bell Weapon."


    //all 6 vitaran offices data
    @PostMapping("/getAllDistributionData")
    public ResponseEntity<?> getAllDistributionGodowns2() {
        List<DistributionGodown> distributionGodowns = distributionGodownRepository.findAll();
        List<DistributionGodownSummary> summaries = distributionGodownRepository.findTotalQuantityPerDateAndWeaponName();

        Map<String, Object> response = new HashMap<>();
        response.put("entries", distributionGodowns);
        response.put("summaries", summaries);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/getDistributionDataBetweenDates")
    public ResponseEntity<?> getDistributionDataBetweenDates(@RequestParam String startDate, @RequestParam String endDate) {
        if (startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty()) {
            return ResponseEntity.badRequest().body("Both startDate and endDate parameters are required");
        }

        // Fetch entries between dates
        List<DistributionGodown> entries = distributionGodownRepository.findByDateBetween(startDate, endDate);

        // Fetch total quantity by weapon name between dates
        List<Object[]> weaponQuantities = distributionGodownRepository.findTotalQuantityByWeaponNameBetweenDates(startDate, endDate);
        Map<String, Long> totalQuantityByWeaponName = new HashMap<>();
        for (Object[] result : weaponQuantities) {
            String weaponName = (String) result[0];
            Long quantity = (Long) result[1];
            totalQuantityByWeaponName.put(weaponName, quantity);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("entries", entries);
        response.put("totalQuantityByWeaponName", totalQuantityByWeaponName);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/distribution_godowns_bydate")
    public ResponseEntity<?> getDistributionGodownsByDate1(@RequestParam("date") String date) {
        try {
            List<DistributionGodown> distributionGodowns = distributionGodownRepository.findByDate(date);
            if (!distributionGodowns.isEmpty()) {
                return ResponseEntity.ok().body(distributionGodowns);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution godown details by date: " + e.getMessage());
        }

    }


    @PostMapping("/save_distribution_office_details")
    public ResponseEntity<String> saveDistributionGodown(@RequestBody DistributionGodown distributionGodown) {
        try {
            DistributionGodown savedGodown = distributionGodownRepository.save(distributionGodown);
            return ResponseEntity.status(HttpStatus.CREATED).body("Distribution Godown saved successfully with ID: " + savedGodown.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save Distribution Godown");
        }
    }


    @PostMapping("/add_distribution_godown")
    public ResponseEntity<String> saveDistributionsGodown(@RequestBody DistributionGodown distributionGodown) {
        try {
            distributionGodownRepository.save(distributionGodown);
            return ResponseEntity.status(HttpStatus.CREATED).body("Distribution Godown saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save BellWeaponSummary");
        }
    }


    @PostMapping("/all_godowns")
    public List<DistributionGodown> getAllDistributionGodowns() {
        return distributionGodownRepository.findAll();
    }


    //chandrikas project added by vikas on 01/06/2024
    @PostMapping("/addWeaponSummary")
    public ResponseEntity<String> addWeaponSummary(@RequestBody WeaponSummaryDto weaponSummaryDto) {
        try {
            Optional<WeaponSummary> existingEntry = weaponSummaryRepo.findByWeaponNameAndDate(
                    weaponSummaryDto.getWeaponName(), weaponSummaryDto.getDate());

            if (existingEntry.isPresent()) {
                return new ResponseEntity<>("Weapon with the same name and date already exists!", HttpStatus.CONFLICT);
            }
            int mumbaiPoliceAdhibha = weaponSummaryDto.getMumbaiPoliceAdhibha();
            int policeStationDistribution = weaponSummaryDto.getPoliceStationDistribution();
            int ptsDistribution = weaponSummaryDto.getPtsDistribution();
            int calina = weaponSummaryDto.getCalina();
            int weaponStorageOffice = weaponSummaryDto.getWeaponStorageOffice();
            int workshop = weaponSummaryDto.getWorkshop();
            int punerr = weaponSummaryDto.getPunerr();
            int policeStationcr = weaponSummaryDto.getPoliceStationcr();
            int onduty = weaponSummaryDto.getOnduty();
            int godownRepair = weaponSummaryDto.getGodownRepair();
            int godownNonRepair = weaponSummaryDto.getGodownNonRepair();
            int coachNonRepair = weaponSummaryDto.getCoachNonRepair();

            int coachRepair = mumbaiPoliceAdhibha - policeStationDistribution - ptsDistribution - calina
                    - weaponStorageOffice - workshop - punerr - policeStationcr - onduty - godownRepair - godownNonRepair - coachNonRepair;
            weaponSummaryDto.setCoachRepair(coachRepair);

            WeaponSummary weaponSummary = new WeaponSummary();
            weaponSummary.setWeaponName(weaponSummaryDto.getWeaponName());
            weaponSummary.setMumbaiPoliceAdhibha(weaponSummaryDto.getMumbaiPoliceAdhibha());
            weaponSummary.setPoliceStationDistribution(weaponSummaryDto.getPoliceStationDistribution());
            weaponSummary.setPtsDistribution(weaponSummaryDto.getPtsDistribution());
            weaponSummary.setCalina(weaponSummaryDto.getCalina());
            weaponSummary.setWeaponStorageOffice(weaponSummaryDto.getWeaponStorageOffice());
            weaponSummary.setWorkshop(weaponSummaryDto.getWorkshop());
            weaponSummary.setPunerr(weaponSummaryDto.getPunerr());
            weaponSummary.setPoliceStationcr(weaponSummaryDto.getPoliceStationcr());
            weaponSummary.setOnduty(weaponSummaryDto.getOnduty());
            weaponSummary.setGodownRepair(weaponSummaryDto.getGodownRepair());
            weaponSummary.setGodownNonRepair(weaponSummaryDto.getGodownNonRepair());
            weaponSummary.setCoachRepair(weaponSummaryDto.getCoachRepair());
            weaponSummary.setCoachNonRepair(weaponSummaryDto.getCoachNonRepair());
            weaponSummary.setDate(weaponSummaryDto.getDate());
            weaponSummaryRepo.save(weaponSummary);

            updateTotalWeaponSummary(weaponSummaryDto.getDate(), weaponSummaryDto);

            return new ResponseEntity<>("Weapon added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Weapon addition failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private void updateTotalWeaponSummary(Date date, WeaponSummaryDto weaponSummaryDto) {

        Optional<TotalWeaponSummary> totalEntry = totalWeaponSummaryRepo.findByDate(date);
        TotalWeaponSummary totalWeaponSummary;
        if (totalEntry.isPresent()) {
            totalWeaponSummary = totalEntry.get();
            totalWeaponSummary.setMumbaiPoliceAdhibha(totalWeaponSummary.getMumbaiPoliceAdhibha() + weaponSummaryDto.getMumbaiPoliceAdhibha());
            totalWeaponSummary.setPoliceStationDistribution(totalWeaponSummary.getPoliceStationDistribution() + weaponSummaryDto.getPoliceStationDistribution());
            totalWeaponSummary.setPtsDistribution(totalWeaponSummary.getPtsDistribution() + weaponSummaryDto.getPtsDistribution());
            totalWeaponSummary.setCalina(totalWeaponSummary.getCalina() + weaponSummaryDto.getCalina());
            totalWeaponSummary.setWeaponStorageOffice(totalWeaponSummary.getWeaponStorageOffice() + weaponSummaryDto.getWeaponStorageOffice());
            totalWeaponSummary.setWorkshop(totalWeaponSummary.getWorkshop() + weaponSummaryDto.getWorkshop());
            totalWeaponSummary.setPunerr(totalWeaponSummary.getPunerr() + weaponSummaryDto.getPunerr());
            totalWeaponSummary.setPoliceStationcr(totalWeaponSummary.getPoliceStationcr() + weaponSummaryDto.getPoliceStationcr());
            totalWeaponSummary.setOnduty(totalWeaponSummary.getOnduty() + weaponSummaryDto.getOnduty());
            totalWeaponSummary.setGodownRepair(totalWeaponSummary.getGodownRepair() + weaponSummaryDto.getGodownRepair());
            totalWeaponSummary.setGodownNonRepair(totalWeaponSummary.getGodownNonRepair() + weaponSummaryDto.getGodownNonRepair());
            totalWeaponSummary.setCoachRepair(totalWeaponSummary.getCoachRepair() + weaponSummaryDto.getCoachRepair());
            totalWeaponSummary.setCoachNonRepair(totalWeaponSummary.getCoachNonRepair() + weaponSummaryDto.getCoachNonRepair());
        } else {
            totalWeaponSummary = new TotalWeaponSummary();
            // totalWeaponSummary.setDate(date);
            totalWeaponSummary.setMumbaiPoliceAdhibha(weaponSummaryDto.getMumbaiPoliceAdhibha());
            totalWeaponSummary.setPtsDistribution(weaponSummaryDto.getPtsDistribution());
            totalWeaponSummary.setCalina(weaponSummaryDto.getCalina());
            totalWeaponSummary.setWeaponStorageOffice(weaponSummaryDto.getWeaponStorageOffice());
            totalWeaponSummary.setWorkshop(weaponSummaryDto.getWorkshop());
            totalWeaponSummary.setPunerr(weaponSummaryDto.getPunerr());
            totalWeaponSummary.setPoliceStationcr(weaponSummaryDto.getPoliceStationcr());
            totalWeaponSummary.setOnduty(weaponSummaryDto.getOnduty());
            totalWeaponSummary.setGodownRepair(weaponSummaryDto.getGodownRepair());
            totalWeaponSummary.setGodownNonRepair(weaponSummaryDto.getGodownNonRepair());
            totalWeaponSummary.setCoachRepair(weaponSummaryDto.getCoachRepair());
            totalWeaponSummary.setCoachNonRepair(weaponSummaryDto.getCoachNonRepair());
        }

        totalWeaponSummaryRepo.save(totalWeaponSummary);
    }


    @PostMapping("/weaponSummaryAndTotalByDate")
    public ResponseEntity<?> getWeaponSummaryAndTotalByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<WeaponSummary> weaponSummaries = weaponSummaryRepo.findAllByDate(date);
            List<TotalWeaponSummary> totalWeaponSummaries = totalWeaponSummaryRepo.findAllByDate(date);

            if (!weaponSummaries.isEmpty() && !totalWeaponSummaries.isEmpty()) {
                WeaponSummary weaponSummary = weaponSummaries.get(0);
                TotalWeaponSummary totalWeaponSummary = totalWeaponSummaries.get(0);
                WeaponSummaryandTotalDto responseDto = new WeaponSummaryandTotalDto(weaponSummary, totalWeaponSummary);
                return ResponseEntity.ok(responseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to fetch weapon summaries and total summary: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @PostMapping("/weaponSummaryByDate")
    public ResponseEntity<List<WeaponSummary>> getWeaponSummaryByDate(@RequestParam String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            List<WeaponSummary> weaponSummaries = weaponSummaryRepo.findByDate(sqlDate);
            if (weaponSummaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(weaponSummaries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/weaponSummaryBetweenDates")
    public ResponseEntity<List<WeaponSummary>> getWeaponSummaryBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String startDateString,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") String endDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDate = dateFormat.parse(startDateString);
            java.util.Date endDate = dateFormat.parse(endDateString);

            List<WeaponSummary> weaponSummaries = weaponSummaryRepo.findByDateBetween(startDate, endDate);
            if (weaponSummaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(weaponSummaries, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/distribution_bell_weapon/{id}")
    public ResponseEntity<BellWeapon> getDistributionWeaponById(@PathVariable Long id) {
        try {
            Optional<BellWeapon> bellWeaponOptional = bellWeaponRepository.findById(id);
            if (bellWeaponOptional.isPresent()) {
                BellWeapon distributionWeapon = bellWeaponOptional.get();
                return ResponseEntity.ok(distributionWeapon);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @PostMapping("/update_distribution_bell_weapon/{id}")
    public ResponseEntity<String> updateSparePart(@PathVariable Long id, @RequestBody BellweaponDto bellweaponDto) {
        try {
            Optional<BellWeapon> bellWeaponOptional = bellWeaponRepository.findById(id);
            if (bellWeaponOptional.isPresent()) {
                BellWeapon bellWeapon = bellWeaponOptional.get();
                bellWeapon.setWeaponName(bellweaponDto.getWeaponName());
                bellWeapon.setStatus(bellweaponDto.getStatus());
                bellWeaponRepository.save(bellWeapon);

                return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\": 0}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\": \"Data not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Data Failed To Update...\",\"Id\": 2, \"error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //10/06/2024

    @PostMapping("/get_distribution_return_data_by_date_and_godown")
    public ResponseEntity<?> getDistributionReturnDataByDateAndGodown(@RequestParam String distributeDate, @RequestParam String godownName) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findBydistributeDateAndGodownName(distributeDate, godownName);
            List<DistributionGodownSummary> summaries = distributionGodownRepository.findTotalQuantityPerDateAndWeaponName();


            if (distributionReturnDataList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given returnDate and godownName");
            }
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data: " + e.getMessage());
        }

    }
}
           



    
    
    
/*
    
    @PostMapping("/save_distribution_return_data")
    public ResponseEntity<?> saveDistributionReturnData(@RequestBody Map<String, Object> distributionReturnDataMap) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = new ArrayList<>();

            String godownName = (String) distributionReturnDataMap.get("godownName");
            String returnDate = (String) distributionReturnDataMap.get("returnDate");
            String distributeDate = (String) distributionReturnDataMap.get("distributeDate");
            List<String> weaponNames = (List<String>) distributionReturnDataMap.get("weaponNames");
            List<String> quantitiesAsString = (List<String>) distributionReturnDataMap.get("quantities");
            String remark = (String) distributionReturnDataMap.get("remark");
            String employeeId = (String) distributionReturnDataMap.get("employeeId");
            String employeeName = (String) distributionReturnDataMap.get("employeeName");
            String employeeDesignation = (String) distributionReturnDataMap.get("employeeDesignation");
            String employeePostingDate = (String) distributionReturnDataMap.get("employeePostingDate");
            String weaponCondition = (String) distributionReturnDataMap.get("weaponCondition");
            String submittedBy = (String) distributionReturnDataMap.get("submittedBy");
            String weaponDamage = (String) distributionReturnDataMap.get("weaponDamage");

            // Null checks
            if (godownName == null || returnDate == null || distributeDate == null || weaponNames == null ||
                    quantitiesAsString == null || remark == null || employeeId == null || employeeName == null ||
                    employeeDesignation == null || employeePostingDate == null || weaponCondition == null ||
                    submittedBy == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
            }

            if (weaponNames.size() != quantitiesAsString.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mismatch between weapon names and quantities");
            }

            for (int i = 0; i < weaponNames.size(); i++) {
                DistributionGodowReturnData distributionReturnData = new DistributionGodowReturnData();
                distributionReturnData.setWeaponName(weaponNames.get(i).trim());
                distributionReturnData.setReturnDate(returnDate);
                distributionReturnData.setDistributeDate(distributeDate);
                distributionReturnData.setGodownName(godownName);
                distributionReturnData.setRemark(remark);
                distributionReturnData.setEmployeeId(employeeId);
                distributionReturnData.setEmployeeName(employeeName);
                distributionReturnData.setEmployeeDesignation(employeeDesignation);
                distributionReturnData.setEmployeePostingDate(employeePostingDate);
                distributionReturnData.setWeaponCondition(weaponCondition);
                distributionReturnData.setSubmittedBy(submittedBy);
                distributionReturnData.setWeaponDamage(weaponDamage);

                try {
                    // Parse quantities from String to Integer
                    Integer quantity = Integer.parseInt(quantitiesAsString.get(i));
                    distributionReturnData.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity format at index " + i);
                }

                distributionReturnDataList.add(distributionReturnData);
            }

            List<DistributionGodowReturnData> savedDistributionReturnData = distributionGodowReturnDataRepository.saveAll(distributionReturnDataList);

            if (savedDistributionReturnData.size() == distributionReturnDataList.size()) {
                return ResponseEntity.status(HttpStatus.CREATED).body("All distribution return data saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some distribution return data failed to save");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving distribution return data: " + e.getMessage());
        }
    }

    */
   /* 
    @PostMapping("/save_distribution_return_data")
    public ResponseEntity<?> saveDistributionReturnData(@RequestBody Map<String, Object> distributionReturnDataMap) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = new ArrayList<>();

            String godownName = (String) distributionReturnDataMap.get("godownName");
            String returnDate = (String) distributionReturnDataMap.get("returnDate");
            String distributeDate = (String) distributionReturnDataMap.get("distributeDate");
            List<String> weaponNames = (List<String>) distributionReturnDataMap.get("weaponNames");
            List<String> quantitiesAsString = (List<String>) distributionReturnDataMap.get("quantities");
            String remark = (String) distributionReturnDataMap.get("remark");
            String employeeId = (String) distributionReturnDataMap.get("employeeId");
            String employeeName = (String) distributionReturnDataMap.get("employeeName");
            String employeeDesignation = (String) distributionReturnDataMap.get("employeeDesignation");
            String employeePostingDate = (String) distributionReturnDataMap.get("employeePostingDate");
            String weaponCondition = (String) distributionReturnDataMap.get("weaponCondition");
            String submittedBy = (String) distributionReturnDataMap.get("submittedBy");
            String weaponDamage = (String) distributionReturnDataMap.get("weaponDamage");

            // Null checks
            if (godownName == null || returnDate == null || distributeDate == null || weaponNames == null || 
                quantitiesAsString == null || remark == null || employeeId == null || employeeName == null || 
                employeeDesignation == null || employeePostingDate == null || weaponCondition == null || 
                submittedBy == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
            }

            if (weaponNames.size() != quantitiesAsString.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mismatch between weapon names and quantities");
            }

            for (int i = 0; i < weaponNames.size(); i++) {
                DistributionGodowReturnData distributionReturnData = new DistributionGodowReturnData();
                distributionReturnData.setWeaponName(weaponNames.get(i).trim());
                distributionReturnData.setReturnDate(returnDate);
                distributionReturnData.setDistributeDate(distributeDate);
                distributionReturnData.setGodownName(godownName);
                distributionReturnData.setRemark(remark);
                distributionReturnData.setEmployeeId(employeeId);
                distributionReturnData.setEmployeeName(employeeName);
                distributionReturnData.setEmployeeDesignation(employeeDesignation);
                distributionReturnData.setEmployeePostingDate(employeePostingDate);
                distributionReturnData.setWeaponCondition(weaponCondition);
                distributionReturnData.setSubmittedBy(submittedBy);
                distributionReturnData.setSubmittedBy(weaponDamage);

                try {
                    // Parse quantities from String to Integer
                    Integer quantity = Integer.parseInt(quantitiesAsString.get(i));
                    distributionReturnData.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity format at index " + i);
                }

                distributionReturnDataList.add(distributionReturnData);
            }

            List<DistributionGodowReturnData> savedDistributionReturnData = distributionGodowReturnDataRepository.saveAll(distributionReturnDataList);

            if (savedDistributionReturnData.size() == distributionReturnDataList.size()) {
                return ResponseEntity.status(HttpStatus.CREATED).body("All distribution return data saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some distribution return data failed to save");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving distribution return data: " + e.getMessage());
        }
    }
*/
    
  
 
    
   /* 
    @PostMapping("/save_distribution_return_data")
    public ResponseEntity<?> saveDistributionReturnData(@RequestBody Map<String, Object> distributionReturnDataMap) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = new ArrayList<>();

            String godownName = (String) distributionReturnDataMap.get("godownName");
            String returnDate = (String) distributionReturnDataMap.get("returnDate");
            List<String> weaponNames = (List<String>) distributionReturnDataMap.get("weaponNames");
            List<String> quantitiesAsString = (List<String>) distributionReturnDataMap.get("quantities");
            String remark = (String) distributionReturnDataMap.get("remark");
            String employeeId = (String) distributionReturnDataMap.get("employeeId");
            String employeeName = (String) distributionReturnDataMap.get("employeeName");
            String employeeDesignation = (String) distributionReturnDataMap.get("employeeDesignation");
            String employeePostingDate = (String) distributionReturnDataMap.get("employeePostingDate");

            // Null checks
            if (godownName == null || returnDate == null || weaponNames == null || quantitiesAsString == null || remark == null || employeeId == null || employeeName == null || employeeDesignation == null || employeePostingDate == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
            }

            if (weaponNames.size() != quantitiesAsString.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mismatch between weapon names and quantities");
            }

            for (int i = 0; i < weaponNames.size(); i++) {
                DistributionGodowReturnData distributionReturnData = new DistributionGodowReturnData();
                distributionReturnData.setWeaponName(weaponNames.get(i).trim());
                distributionReturnData.setReturnDate(returnDate);
                distributionReturnData.setGodownName(godownName);
                distributionReturnData.setRemark(remark);
                distributionReturnData.setEmployeeId(employeeId);
                distributionReturnData.setEmployeeName(employeeName);
                distributionReturnData.setEmployeeDesignation(employeeDesignation);
                distributionReturnData.setEmployeePostingDate(employeePostingDate);

                try {
                    // Parse quantities from String to Integer
                    Integer quantity = Integer.parseInt(quantitiesAsString.get(i));
                    distributionReturnData.setQuantity(quantity);
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid quantity format at index " + i);
                }

                distributionReturnDataList.add(distributionReturnData);
            }

            List<DistributionGodowReturnData> savedDistributionReturnData = distributionGodowReturnDataRepository.saveAll(distributionReturnDataList);

            if (savedDistributionReturnData.size() == distributionReturnDataList.size()) {
                return ResponseEntity.status(HttpStatus.CREATED).body("All distribution return data saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some distribution return data failed to save");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving distribution return data: " + e.getMessage());
        }
    }
*/
/*
    //8/6/2024
    @PostMapping("/get_distribution_return_data_by_date_and_godown")
    public ResponseEntity<?> getDistributionReturnDataByDateAndGodown(@RequestParam String distributeDate, @RequestParam String godownName) {
        try {
            List<DistributionGodowReturnData> distributionReturnDataList = distributionGodowReturnDataRepository.findBydistributeDateAndGodownName(distributeDate, godownName);
            if (distributionReturnDataList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found for the given returnDate and godownName");
            }
            return ResponseEntity.ok(distributionReturnDataList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution return data: " + e.getMessage());
        }
    }*/

    
    /*
    @PostMapping("/get_distribution_godown_by_date_and_godown")
    public ResponseEntity<?> getDistributionGodownsByDateAndGodownName(
            @RequestParam("date") String date,
            @RequestParam("godownName") String godownName) {
        try {
            // Fetch distribution godowns by date and godown name
            List<DistributionGodown> distributionGodowns = distributionGodownRepository.findByDateAndGodownName(date, godownName);
            if (distributionGodowns.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found for the provided date and godown name");
            }

            // Fetch weapon-wise total counts
            List<Object[]> weaponWiseTotals = distributionGodownRepository.findWeaponWiseTotalByDateAndGodownName(date, godownName);

            // Prepare the response
            Map<String, Object> response = new HashMap<>();
            response.put("distributionGodowns", distributionGodowns);
            response.put("weaponWiseTotals", weaponWiseTotals);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving distribution godown details: " + e.getMessage());
        }
    }*/
    
    
    
  
//Bell Weapon saved successfully
    
/*
  //01/06/2024
    @PostMapping("/addBellweapon")
    public ResponseEntity<String> addBellweapon(@RequestBody BellweaponDto bellweaponDto) {
        try {
            Bellweapon newBellweapon = new Bellweapon();
            bellweaponRepo.save(newBellweapon);
            return new ResponseEntity<>("BellWeapon added successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Bellweapon addition failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }*/



/*
@PostMapping("/save_bell_weapon")
public ResponseEntity<String> saveBellWeapon(@RequestBody BellWeapon bellWeapon) {
    try {
        // Save the BellWeapon entity with all fields
        bellWeaponRepository.save(bellWeapon);
        //  return ResponseEntity.status(HttpStatus.CREATED).body(" ,\"{\\\"message\\\": \\\"successfully Bell Weapon saved\\\"id:0}");
        //  return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"weapon saved successfully\" id:0\"}");
        return ResponseEntity.status(HttpStatus.CREATED).body("\"message\": \"weapon saved successfully\" id:0\"");

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("\"message\": \"weapon not saved successfully\" id:0\"");

    }
}
*/


/*  @PostMapping("/getAllDistributionData")
public ResponseEntity<?> getAllDistributionGodowns2() {
    List<DistributionGodown> distributionGodowns = distributionGodownRepository.findAll();
    List<DistributionGodownSummary> summaries = distributionGodownRepository.findTotalQuantityPerDateAndWeaponName();

    Map<String, Object> response = new HashMap<>();
    response.put("distributionGodowns", distributionGodowns);
    response.put("summaries", summaries);

    return ResponseEntity.ok(response);
}
*/

/*
import com.example.WeaponArmaryManagementSystem.model.BellWeapon;
import com.example.WeaponArmaryManagementSystem.model.DistributionGodown;
import com.example.WeaponArmaryManagementSystem.repository.BellWeaponRepository;
import com.example.WeaponArmaryManagementSystem.repository.DistributionGodownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BellWeaponController {


    private final DistributionGodownRepository distributionGodownRepository;
    private final BellWeaponRepository bellWeaponRepository;

    @Autowired
    public BellWeaponController(BellWeaponRepository bellWeaponRepository,
                                DistributionGodownRepository distributionGodownRepository) {

        this.distributionGodownRepository = distributionGodownRepository;
        this.bellWeaponRepository = bellWeaponRepository;
    }


    @PostMapping("/add_distribution_godown")
    public ResponseEntity<String> saveDistributionGodown(@RequestBody DistributionGodown distributionGodown) {
        try {
            distributionGodownRepository.save(distributionGodown);
            return ResponseEntity.status(HttpStatus.CREATED).body("Distribution Godown saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save BellWeaponSummary");
        }
    }


    @PostMapping("/all_godowns")
    public List<DistributionGodown> getAllDistributionGodowns() {
        return distributionGodownRepository.findAll();
    }


    @PostMapping("/save_bell_weapon")
    public ResponseEntity<String> saveBellWeapon(@RequestBody BellWeapon bellWeapon) {
        try {
            // Save the BellWeapon entity with all fields
            bellWeaponRepository.save(bellWeapon);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bell Weapon saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save Bell Weapon.");
        }
    }

    @PostMapping("/all_bell_weapon_names")
    public List<String> getAllWeaponNames() {
        return bellWeaponRepository.findAllWeaponNames();
    }



    @PostMapping("/all_bell_weapons")
    public List<BellWeapon> getAllBellWeapons() {
        return bellWeaponRepository.findAll();
    }

     //this apis working good not so good     
    
 *//*   @PostMapping("/all_bell_weapons")
    public Map<String, Object> getAllBellWeaponsWithWrapper() {
        List<BellWeapon> allWeapons = bellWeaponRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("allBellWeapons", allWeapons);
        return response;
    }*//*

    @PostMapping("/all_distribution_bell_weapons")
    public ResponseEntity<List<BellWeapon>> getAllDistributionWeapons() {
        try {
            List<BellWeapon> distributionWeapons = bellWeaponRepository.findByWeaponType("distributionWeapon");
            return ResponseEntity.ok(distributionWeapons);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }

    }
}
    */



