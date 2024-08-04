package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.Impl.BellOfArmService;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmInOutward;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmTotalInOutward;
import com.example.WeaponArmaryManagementSystem.model.InwardBellOfArmPrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardBellOfArmPrefix;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmTotalInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.InwardBellOfArmPrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardBellOfArmPrefixRepository;
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
@RequestMapping("/api/bellofarms")
@CrossOrigin(origins = "*")
public class BellOfArmsController {

    @Autowired
    private BellOfArmInOutwardRepository bellOfArmInOutwardRepository;

    @Autowired
    private BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository;

    @Autowired
    private InwardBellOfArmPrefixRepository inwardBellOfArmPrefixRepository;

    @Autowired
    private OutwardBellOfArmPrefixRepository outwardBellOfArmPrefixRepository;

    @Autowired
    private BellOfArmService bellOfArmService;



    @Autowired
    public BellOfArmsController(BellOfArmInOutwardRepository bellOfArmInOutwardRepository,
                                BellOfArmTotalInOutwardRepository bellOfArmTotalInOutwardRepository,
                                InwardBellOfArmPrefixRepository inwardBellOfArmPrefixRepository,
                                OutwardBellOfArmPrefixRepository outwardBellOfArmPrefixRepository) {
        this.bellOfArmInOutwardRepository = bellOfArmInOutwardRepository;
        this.bellOfArmTotalInOutwardRepository = bellOfArmTotalInOutwardRepository;
        this.inwardBellOfArmPrefixRepository = inwardBellOfArmPrefixRepository;
        this.outwardBellOfArmPrefixRepository = outwardBellOfArmPrefixRepository;
    }

    private String generateInwardNumber() {
        // Fetch the latest prefix from the database
        InwardBellOfArmPrefix inwardBellOfArmPrefix = inwardBellOfArmPrefixRepository.findTopByOrderByIdDesc();
        String prefix = (inwardBellOfArmPrefix != null && inwardBellOfArmPrefix.getPrefixName() != null) ? inwardBellOfArmPrefix.getPrefixName() : "";

        // Ensure the prefix ends with a separator if it is not empty
        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        // Find the last warehouse inward entry to determine the next number
        BellOfArmInOutward lastEntry = bellOfArmInOutwardRepository.findTopByOrderByIdDesc();
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
    public ResponseEntity<String> createStockAndTotalStock(@RequestBody BellOfArmInOutward bellOfArmInOutward) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        bellOfArmInOutward.setCreatedAt(now);
        bellOfArmInOutward.setUpdatedAt(now);

        // Generate Inward Number
        bellOfArmInOutward.setInwardNo(generateInwardNumber());

        // Set default value for status column
        bellOfArmInOutward.setStatus("0");

        // Save warehouse in/outward entry
        BellOfArmInOutward savedBellOfArmInOutward = bellOfArmInOutwardRepository.save(bellOfArmInOutward);

        // Update total stock
        BellOfArmTotalInOutward totalStock = bellOfArmTotalInOutwardRepository.findByWeaponNameAndRoundName(
                bellOfArmInOutward.getWeaponName(), bellOfArmInOutward.getRoundName());

        if (totalStock != null) {
            // Update total stock quantity
            int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(bellOfArmInOutward.getRecievedQuantity());
            totalStock.setTotalStock(newTotalStock);
            // Update available stock by subtracting distribution stock
            int distributionStock = totalStock.getDistributionStock();
            int newAvailableStock = newTotalStock - distributionStock;
            totalStock.setAvailableStock(newAvailableStock);
            bellOfArmTotalInOutwardRepository.save(totalStock);
        } else {
            // Create new total stock entry
            BellOfArmTotalInOutward newTotalStockEntry = new BellOfArmTotalInOutward();
            newTotalStockEntry.setWeaponName(bellOfArmInOutward.getWeaponName());
            newTotalStockEntry.setRoundName(bellOfArmInOutward.getRoundName());
            newTotalStockEntry.setTotalStock(Integer.parseInt(bellOfArmInOutward.getRecievedQuantity()));
            newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
            newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
            bellOfArmTotalInOutwardRepository.save(newTotalStockEntry);
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Workshop In/Outward entry Saved Successfully...\",\"Id\":0}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }


    @PostMapping("/update_prefix_inward")
    public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
        InwardBellOfArmPrefix inwardBellOfArmPrefix = inwardBellOfArmPrefixRepository.findTopByOrderByIdDesc();
        if (inwardBellOfArmPrefix != null) {
            inwardBellOfArmPrefix.setPrefixName(newPrefix);
        } else {
            inwardBellOfArmPrefix = new InwardBellOfArmPrefix();
            inwardBellOfArmPrefix.setPrefixName(newPrefix);
        }
        inwardBellOfArmPrefixRepository.save(inwardBellOfArmPrefix);
        return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\":0}", HttpStatus.OK);
    }


    private String generateOutwardNumber() {
        OutwardBellOfArmPrefix outwardBellOfArmPrefix = outwardBellOfArmPrefixRepository.findTopByOrderByIdDesc();
        String prefix = (outwardBellOfArmPrefix != null && outwardBellOfArmPrefix.getPrefixName() != null) ? outwardBellOfArmPrefix.getPrefixName() : "";

        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        BellOfArmInOutward lastEntry = bellOfArmInOutwardRepository.findTopByOrderByIdDesc();
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
    public ResponseEntity<String> spreadOutward(@RequestBody BellOfArmInOutward bellOfArmInOutward) {
        int receivedQuantity = Integer.parseInt(bellOfArmInOutward.getRecievedQuantity());

        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        BellOfArmTotalInOutward totalStockByWeapon = null;
        BellOfArmTotalInOutward totalStockByRound = null;

        List<BellOfArmTotalInOutward> totalStockByWeaponList = bellOfArmTotalInOutwardRepository.findByWeaponName(bellOfArmInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock();

            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                bellOfArmTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        } else {
            sufficientStockWeapon = false;
        }

        List<BellOfArmTotalInOutward> totalStockByRoundList = bellOfArmTotalInOutwardRepository.findByRoundName(bellOfArmInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = totalStockByRound.getAvailableStock();

            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
                bellOfArmTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        } else {
            sufficientStockRound = false;
        }

        if (!sufficientStockWeapon && !sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + bellOfArmInOutward.getWeaponName() + " or " + bellOfArmInOutward.getRoundName());
        }

        LocalDateTime now = LocalDateTime.now();
        bellOfArmInOutward.setCreatedAt(now);
        bellOfArmInOutward.setUpdatedAt(now);

        bellOfArmInOutward.setOutwardNo(generateOutwardNumber());
        bellOfArmInOutward.setStatus("1");

        BellOfArmInOutward savedBellOfArmInOutward = bellOfArmInOutwardRepository.save(bellOfArmInOutward);

        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": 0 }";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }



    @PostMapping("/list")
    public List<BellOfArmInOutward> getAllStock() {
        return bellOfArmInOutwardRepository.findAll();
    }


    @PostMapping("/listTotal")
    public List<BellOfArmTotalInOutward> getAllTotalStock() {
        return bellOfArmTotalInOutwardRepository.findAll();
    }


    @PostMapping("/status/0")
    public ResponseEntity<List<BellOfArmInOutward>> getEntriesWithStatusZero() {
        List<BellOfArmInOutward> entries = bellOfArmInOutwardRepository.findByStatus("0");
        return ResponseEntity.ok(entries);
    }


    @PostMapping("/status/1")
    public ResponseEntity<List<BellOfArmInOutward>> getEntriesWithStatusOne() {
        List<BellOfArmInOutward> entries = bellOfArmInOutwardRepository.findByStatus("1");
        return ResponseEntity.ok(entries);
    }


    @PostMapping("/getById/{id}")
    public ResponseEntity<BellOfArmInOutward> getBellOfArmInOutwardById(@PathVariable Long id) {
        Optional<BellOfArmInOutward> entries = bellOfArmInOutwardRepository.findById(id);
        return entries.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/getBellOfArmInOutwardByDate/{date}")
    public ResponseEntity<?> getBellOfArmInOutwardByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        try {
            List<BellOfArmInOutward> bellOfArmInOutward = bellOfArmInOutwardRepository.findAllByDate(date);
            if (!bellOfArmInOutward.isEmpty()) {
                return ResponseEntity.ok(bellOfArmInOutward);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving InOutward by date: " + e.getMessage());
        }
    }


  /*  @PostMapping("/GetBellOfArmInOutwardBetweenDates")
    public ResponseEntity<List<BellOfArmInOutward>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<BellOfArmInOutward> reports = bellOfArmService.getReportsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reports);
    }*/


    @PostMapping("/GetInwardBetweenDates")
    public ResponseEntity<List<BellOfArmInOutward>> getReportsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<BellOfArmInOutward> reports = bellOfArmInOutwardRepository.findByStatusAndDateBetween("0", startDate, endDate);
        return ResponseEntity.ok(reports);
    }

    // GetByDate InventoryOutwardDate  10-06-2024  By Sanket
    @PostMapping("/GetOutwardBetweenDates")
    public ResponseEntity<List<BellOfArmInOutward>> getReportsByDateRange1(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
        List<BellOfArmInOutward> reports = bellOfArmInOutwardRepository.findByStatusAndDateBetween("1", startDate, endDate);
        return ResponseEntity.ok(reports);
        }

    @PostMapping("/totalStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> getTotalStockWhereWeaponNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/totalStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> getTotalStockWhereRoundNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/availableStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> getAvailableStockWhereWeaponNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/availableStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> findTotalAvailableStockWhereRoundNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/distributionStockWhereWeaponNameIsPresent")
    public ResponseEntity<Integer> findTotalDistributionStockWhereWeaponNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/distributionStockWhereRoundNameIsPresent")
    public ResponseEntity<Integer> findTotalDistributionStockWhereRoundNameIsPresent() {
        Integer totalStock = bellOfArmTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent();
        return ResponseEntity.ok(totalStock);
    }


    @PostMapping("/stockSummary")
    public ResponseEntity<Map<String, Integer>> getStockSummary() {
        Map<String, Integer> summary = new HashMap<>();
        summary.put("TotalStockWhereWeaponNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent());
        summary.put("TotalStockWhereRoundNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent());
        summary.put("AvailableStockWhereWeaponNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent());
        summary.put("AvailableStockWhereRoundNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent());
        summary.put("DistributionStockWhereWeaponNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent());
        summary.put("DistributionStockWhereRoundNameIsPresent", bellOfArmTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent());
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}

