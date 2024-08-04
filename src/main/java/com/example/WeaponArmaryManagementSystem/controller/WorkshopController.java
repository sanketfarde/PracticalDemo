package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Service.Impl.WorkshopService;
import com.example.WeaponArmaryManagementSystem.model.InwardWorkshopPrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardWorkshopPrefix;
import com.example.WeaponArmaryManagementSystem.model.WorkshopInOutward;
import com.example.WeaponArmaryManagementSystem.model.WorkshopTotalInOutward;
import com.example.WeaponArmaryManagementSystem.repository.InwardWorkshopPrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardWorkshopPrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.WorkshopInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.WorkshopTotalInOutwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/workshop")
@CrossOrigin(origins = "*")
public class WorkshopController {


    @Autowired
    private WorkshopInOutwardRepository workshopInOutwardRepository;

    @Autowired
    private WorkshopTotalInOutwardRepository workshopTotalInOutwardRepository;

    @Autowired
    private InwardWorkshopPrefixRepository inwardWorkshopPrefixRepository;

    @Autowired
    private OutwardWorkshopPrefixRepository outwardWorkshopPrefixRepository;

    @Autowired
    private WorkshopService workshopService;



    @Autowired
    public WorkshopController(WorkshopInOutwardRepository workshopInOutwardRepository,
                              WorkshopTotalInOutwardRepository workshopTotalInOutwardRepository,
                              InwardWorkshopPrefixRepository inwardWorkshopPrefixRepository,
                              OutwardWorkshopPrefixRepository outwardWorkshopPrefixRepository) {
        this.workshopInOutwardRepository = workshopInOutwardRepository;
        this.workshopTotalInOutwardRepository = workshopTotalInOutwardRepository;
        this.inwardWorkshopPrefixRepository = inwardWorkshopPrefixRepository;
        this.outwardWorkshopPrefixRepository = outwardWorkshopPrefixRepository;
    }

    private String generateInwardNumber() {
        // Fetch the latest prefix from the database
        InwardWorkshopPrefix inwardWorkshopPrefix = inwardWorkshopPrefixRepository.findTopByOrderByIdDesc();
        String prefix = (inwardWorkshopPrefix != null && inwardWorkshopPrefix.getPrefixName() != null) ? inwardWorkshopPrefix.getPrefixName() : "";

        // Ensure the prefix ends with a separator if it is not empty
        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        // Find the last warehouse inward entry to determine the next number
        WorkshopInOutward lastEntry = workshopInOutwardRepository.findTopByOrderByIdDesc();
        int nextNumber = 1; // Default to 1 if no last entry

        if (lastEntry != null) {
            String lastInwardNumber = lastEntry.getInwardNo();
            if (lastInwardNumber != null && lastInwardNumber.startsWith(prefix)) {
                String numberPart = lastInwardNumber.substring(prefix.length());
                if (!numberPart.isEmpty()) {
                    try {
                        nextNumber = Integer.parseInt(numberPart) + 1;
                    } catch (NumberFormatException e) {
                        // Handle potential parsing issues
                        System.err.println("Error parsing number part of inward number: " + e.getMessage());
                    }
                }
            }
        }

        // Format the number part to be 4 digits with leading zeros
        String formattedNumber = String.format("%04d", nextNumber);

        return prefix + formattedNumber;
    }


    @PostMapping("/saveTotal")
    public ResponseEntity<String> createStockAndTotalStock(@RequestBody WorkshopInOutward workshopInOutward) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        workshopInOutward.setCreatedAt(now);
        workshopInOutward.setUpdatedAt(now);

        // Generate Inward Number
        workshopInOutward.setInwardNo(generateInwardNumber());

        // Set default value for status column
        workshopInOutward.setStatus("0");

        // Save warehouse in/outward entry
        WorkshopInOutward savedWorkshopInOutward = workshopInOutwardRepository.save(workshopInOutward);

        // Update total stock
        WorkshopTotalInOutward totalStock = workshopTotalInOutwardRepository.findByWeaponNameAndRoundName(
                workshopInOutward.getWeaponName(), workshopInOutward.getRoundName());

        if (totalStock != null) {
            // Update total stock quantity
            int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(workshopInOutward.getRecievedQuantity());
            totalStock.setTotalStock(newTotalStock);
            // Update available stock by subtracting distribution stock
            int distributionStock = totalStock.getDistributionStock();
            int newAvailableStock = newTotalStock - distributionStock;
            totalStock.setAvailableStock(newAvailableStock);
            workshopTotalInOutwardRepository.save(totalStock);
        } else {
            // Create new total stock entry
            WorkshopTotalInOutward newTotalStockEntry = new WorkshopTotalInOutward();
            newTotalStockEntry.setWeaponName(workshopInOutward.getWeaponName());
            newTotalStockEntry.setRoundName(workshopInOutward.getRoundName());
            newTotalStockEntry.setTotalStock(Integer.parseInt(workshopInOutward.getRecievedQuantity()));
            newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
            newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
            workshopTotalInOutwardRepository.save(newTotalStockEntry);
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Workshop In/Outward entry Saved Successfully...\",\"Id\":0}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


    @PostMapping("/update_prefix_inward")
    public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
        InwardWorkshopPrefix inwardWorkshopPrefix = inwardWorkshopPrefixRepository.findTopByOrderByIdDesc();
        if (inwardWorkshopPrefix != null) {
            inwardWorkshopPrefix.setPrefixName(newPrefix);
        } else {
            inwardWorkshopPrefix = new InwardWorkshopPrefix();
            inwardWorkshopPrefix.setPrefixName(newPrefix);
        }
        inwardWorkshopPrefixRepository.save(inwardWorkshopPrefix);
        return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\":0}", HttpStatus.OK);
    }


    private String generateOutwardNumber() {
        OutwardWorkshopPrefix outwardWorkshopPrefix = outwardWorkshopPrefixRepository.findTopByOrderByIdDesc();
        String prefix = (outwardWorkshopPrefix != null && outwardWorkshopPrefix.getPrefixName() != null) ? outwardWorkshopPrefix.getPrefixName() : "";

        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        WorkshopInOutward lastEntry = workshopInOutwardRepository.findTopByOrderByIdDesc();
        int nextNumber = 1;

        if (lastEntry != null) {
            String lastOutwardNumber = lastEntry.getOutwardNo();
            if (lastOutwardNumber != null && lastOutwardNumber.startsWith(prefix)) {
                String numberPart = lastOutwardNumber.substring(prefix.length());
                if (!numberPart.isEmpty()) {
                    try {
                        nextNumber = Integer.parseInt(numberPart) + 1;
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number part of outward number: " + e.getMessage());
                    }
                }
            }
        }

        String formattedNumber = String.format("%04d", nextNumber);
        return prefix + formattedNumber;
    }


    @PostMapping("/spreadOutward")
    @Transactional
    public ResponseEntity<String> spreadOutward(@RequestBody WorkshopInOutward workshopInOutward) {
        int receivedQuantity = Integer.parseInt(workshopInOutward.getRecievedQuantity());

        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        WorkshopTotalInOutward totalStockByWeapon = null;
        WorkshopTotalInOutward totalStockByRound = null;

        List<WorkshopTotalInOutward> totalStockByWeaponList = workshopTotalInOutwardRepository.findByWeaponName(workshopInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock();

            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                workshopTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        } else {
            sufficientStockWeapon = false;
        }

        List<WorkshopTotalInOutward> totalStockByRoundList = workshopTotalInOutwardRepository.findByRoundName(workshopInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = totalStockByRound.getAvailableStock();

            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
                workshopTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        } else {
            sufficientStockRound = false;
        }

        if (!sufficientStockWeapon && !sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + workshopInOutward.getWeaponName() + " or " + workshopInOutward.getRoundName());
        }

        LocalDateTime now = LocalDateTime.now();
        workshopInOutward.setCreatedAt(now);
        workshopInOutward.setUpdatedAt(now);

        workshopInOutward.setOutwardNo(generateOutwardNumber());
        workshopInOutward.setStatus("1");

        WorkshopInOutward savedWorkshopInOutward = workshopInOutwardRepository.save(workshopInOutward);

        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": 0 }";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }



    @PostMapping("/list")
    public List<WorkshopInOutward> getAllStock() {
        return workshopInOutwardRepository.findAll();
    }


    @PostMapping("/listTotal")
    public List<WorkshopTotalInOutward> getAllTotalStock() {
        return workshopTotalInOutwardRepository.findAll();
    }


    @PostMapping("/status/0")
    public ResponseEntity<List<WorkshopInOutward>> getEntriesWithStatusZero() {
        List<WorkshopInOutward> entries = workshopInOutwardRepository.findByStatus("0");
        return ResponseEntity.ok(entries);
    }


    @PostMapping("/status/1")
    public ResponseEntity<List<WorkshopInOutward>> getEntriesWithStatusOne() {
        List<WorkshopInOutward> entries = workshopInOutwardRepository.findByStatus("1");
        return ResponseEntity.ok(entries);
    }


    @PostMapping("/getById/{id}")
    public ResponseEntity<WorkshopInOutward> getWorkshopInOutwardById(@PathVariable Long id) {
        Optional<WorkshopInOutward> entries = workshopInOutwardRepository.findById(id);
        return entries.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/getWarehouseInOutwardByDate/{date}")
    public ResponseEntity<?> getWorkshopInOutwardByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        try {
            List<WorkshopInOutward> workshopInOutward = workshopInOutwardRepository.findAllByDate(date);
            if (!workshopInOutward.isEmpty()) {
                return ResponseEntity.ok(workshopInOutward);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving InOutward by date: " + e.getMessage());
        }
    }


  /*  @PostMapping("/GetWarehouseInOutwardBetweenDates")
    public ResponseEntity<List<WorkshopInOutward>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<WorkshopInOutward> reports = workshopService.getReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }
*/

    @PostMapping("/GetInwardBetweenDates")
    public ResponseEntity<List<WorkshopInOutward>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<WorkshopInOutward> reports = workshopInOutwardRepository.findByStatusAndDateBetween("0", startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    // GetByDate InventoryOutwardDate  10-06-2024  By Sanket
    @PostMapping("/GetOutwardBetweenDates")
    public ResponseEntity<List<WorkshopInOutward>> getReportsByDateRange1(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<WorkshopInOutward> reports = workshopInOutwardRepository.findByStatusAndDateBetween("1", startDate, endDate);
        return ResponseEntity.ok(reports);
        }

    @PostMapping("/totalStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> getTotalStockWhereWeaponNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/totalStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> getTotalStockWhereRoundNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/availableStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> getAvailableStockWhereWeaponNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/availableStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> findTotalAvailableStockWhereRoundNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/distributionStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> findTotalDistributionStockWhereWeaponNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/distributionStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> findTotalDistributionStockWhereRoundNameIsPresent() {
        Integer totalStock = workshopTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/stockSummary")
    public ResponseEntity<Map<String, Integer>> getStockSummary() {
        Map<String, Integer> summary = new HashMap<>();
        summary.put("TotalStockWhereWeaponNameIsPresent", workshopTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent());
        summary.put("TotalStockWhereRoundNameIsPresent", workshopTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent());
        summary.put("AvailableStockWhereWeaponNameIsPresent", workshopTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent());
        summary.put("AvailableStockWhereRoundNameIsPresent", workshopTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent());
        summary.put("DistributionStockWhereWeaponNameIsPresent", workshopTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent());
        summary.put("DistributionStockWhereRoundNameIsPresent", workshopTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent());
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}


