package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.Service.Impl.WarehouseService;
import com.example.WeaponArmaryManagementSystem.model.InwardServicePrefix;
import com.example.WeaponArmaryManagementSystem.model.OutwardServicePrefix;
//import com.example.WeaponArmaryManagementSystem.Service.Impl.WarehouseService;
import com.example.WeaponArmaryManagementSystem.model.WarehouseInOutward;
import com.example.WeaponArmaryManagementSystem.model.WarehouseTotalInOutward;
import com.example.WeaponArmaryManagementSystem.repository.InwardServicePrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardServicePrefixRepository;
import com.example.WeaponArmaryManagementSystem.repository.WarehouseInOutwardRepository;
import com.example.WeaponArmaryManagementSystem.repository.WarehouseTotalInOutwardRepository;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController {


	
	    @Autowired
	    private WarehouseInOutwardRepository warehouseInOutwardRepository;

	    @Autowired
	    private WarehouseTotalInOutwardRepository warehouseTotalInOutwardRepository;
	    
	    @Autowired
	    private InwardServicePrefixRepository inwardServicePrefixRepository;
	    
	    @Autowired
	    private OutwardServicePrefixRepository outwardServicePrefixRepository;
	    
	    @Autowired
	    private WarehouseService warehouseService;

	
	    
	    @Autowired
	    public WarehouseController(WarehouseInOutwardRepository warehouseInOutwardRepository,
	                               WarehouseTotalInOutwardRepository warehouseTotalInOutwardRepository,
	                               InwardServicePrefixRepository inwardServicePrefixRepository,
	                               OutwardServicePrefixRepository outwardServicePrefixRepository) {
	        this.warehouseInOutwardRepository = warehouseInOutwardRepository;
	        this.warehouseTotalInOutwardRepository = warehouseTotalInOutwardRepository;
	        this.inwardServicePrefixRepository = inwardServicePrefixRepository;
	        this.outwardServicePrefixRepository = outwardServicePrefixRepository;
	    }

	    private String generateInwardNumber() {
	        // Fetch the latest prefix from the database
	        InwardServicePrefix inwardServicePrefix = inwardServicePrefixRepository.findTopByOrderByIdDesc();
	        String prefix = (inwardServicePrefix != null && inwardServicePrefix.getPrefixName() != null) ? inwardServicePrefix.getPrefixName() : "";

	        // Ensure the prefix ends with a separator if it is not empty
	        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
	            prefix += "/";
	        }

	        // Find the last warehouse inward entry to determine the next number
	        WarehouseInOutward lastEntry = warehouseInOutwardRepository.findTopByOrderByIdDesc();
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
	    public ResponseEntity<String> createStockAndTotalStock(@RequestBody WarehouseInOutward warehouseInOutward) {
	        // Set createdAt and updatedAt fields with current date and time
	        LocalDateTime now = LocalDateTime.now();
	        warehouseInOutward.setCreatedAt(now);
	        warehouseInOutward.setUpdatedAt(now);

	        // Generate Inward Number
	        warehouseInOutward.setInwardNo(generateInwardNumber());

	        // Set default value for status column
	        warehouseInOutward.setStatus("0");

	        // Save warehouse in/outward entry
	        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

	        // Update total stock
	        WarehouseTotalInOutward totalStock = warehouseTotalInOutwardRepository.findByWeaponNameAndRoundName(
	                warehouseInOutward.getWeaponName(), warehouseInOutward.getRoundName());

	        if (totalStock != null) {
	            // Update total stock quantity
	            int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(warehouseInOutward.getRecievedQuantity());
	            totalStock.setTotalStock(newTotalStock);
	            // Update available stock by subtracting distribution stock
	            int distributionStock = totalStock.getDistributionStock();
	            int newAvailableStock = newTotalStock - distributionStock;
	            totalStock.setAvailableStock(newAvailableStock);
	            warehouseTotalInOutwardRepository.save(totalStock);
	        } else {
	            // Create new total stock entry
	            WarehouseTotalInOutward newTotalStockEntry = new WarehouseTotalInOutward();
	            newTotalStockEntry.setWeaponName(warehouseInOutward.getWeaponName());
	            newTotalStockEntry.setRoundName(warehouseInOutward.getRoundName());
	            newTotalStockEntry.setTotalStock(Integer.parseInt(warehouseInOutward.getRecievedQuantity()));
	            newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
	            newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
	            warehouseTotalInOutwardRepository.save(newTotalStockEntry);
	        }

	        // Respond with success message
	        String responseMessage = "{\"message\": \"Warehouse in/outward entry saved successfully\",\"Id:0\": "
	                + "}";
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
	    }

	    
	    @PostMapping("/update_prefix_inward")
	    public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
	        InwardServicePrefix inwardServicePrefix = inwardServicePrefixRepository.findTopByOrderByIdDesc();
	        if (inwardServicePrefix != null) {
	            inwardServicePrefix.setPrefixName(newPrefix);
	        } else {
	            inwardServicePrefix = new InwardServicePrefix();
	            inwardServicePrefix.setPrefixName(newPrefix);
	        }
	        inwardServicePrefixRepository.save(inwardServicePrefix);
	        return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\":0}", HttpStatus.OK);
	    }
	    
	    
	    private String generateOutwardNumber() {
	        OutwardServicePrefix outwardServicePrefix = outwardServicePrefixRepository.findTopByOrderByIdDesc();
	        String prefix = (outwardServicePrefix != null && outwardServicePrefix.getPrefixName() != null) ? outwardServicePrefix.getPrefixName() : "";

	        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
	            prefix += "/";
	        }

	        WarehouseInOutward lastEntry = warehouseInOutwardRepository.findTopByOrderByIdDesc();
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
	    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
	        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

	        boolean sufficientStockWeapon = true;
	        boolean sufficientStockRound = true;

	        WarehouseTotalInOutward totalStockByWeapon = null;
	        WarehouseTotalInOutward totalStockByRound = null;

	        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
	        if (!totalStockByWeaponList.isEmpty()) {
	            totalStockByWeapon = totalStockByWeaponList.get(0);
	            int availableStockWeapon = totalStockByWeapon.getAvailableStock();

	            if (availableStockWeapon >= receivedQuantity) {
	                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
	                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
	                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
	            } else {
	                sufficientStockWeapon = false;
	            }
	        } else {
	            sufficientStockWeapon = false;
	        }

	        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
	        if (!totalStockByRoundList.isEmpty()) {
	            totalStockByRound = totalStockByRoundList.get(0);
	            int availableStockRound = totalStockByRound.getAvailableStock();

	            if (availableStockRound >= receivedQuantity) {
	                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
	                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
	                warehouseTotalInOutwardRepository.save(totalStockByRound);
	            } else {
	                sufficientStockRound = false;
	            }
	        } else {
	            sufficientStockRound = false;
	        }

	        if (!sufficientStockWeapon && !sufficientStockRound) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName() + " or " + warehouseInOutward.getRoundName());
	        }

	        LocalDateTime now = LocalDateTime.now();
	        warehouseInOutward.setCreatedAt(now);
	        warehouseInOutward.setUpdatedAt(now);

	        warehouseInOutward.setOutwardNo(generateOutwardNumber());
	        warehouseInOutward.setStatus("1");

	        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

	        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
	    }
	    
	    
	    
	    @PostMapping("/list")
	    public List<WarehouseInOutward> getAllStock() {
		   return warehouseInOutwardRepository.findAll();
		  }
	    
	    
	    @PostMapping("/listTotal")
	    public List<WarehouseTotalInOutward> getAllTotalStock() {
		   return warehouseTotalInOutwardRepository.findAll();
		  }
	    
	    
	    @PostMapping("/status/0")
	    public ResponseEntity<List<WarehouseInOutward>> getEntriesWithStatusZero() {
	        List<WarehouseInOutward> entries = warehouseInOutwardRepository.findByStatus("0");
	        return ResponseEntity.ok(entries);
	    }
	    
	    
	    @PostMapping("/status/1")
	    public ResponseEntity<List<WarehouseInOutward>> getEntriesWithStatusOne() {
	        List<WarehouseInOutward> entries = warehouseInOutwardRepository.findByStatus("1");
	        return ResponseEntity.ok(entries);
	    }
	    
	    
	    @PostMapping("/getById/{id}")
	    public ResponseEntity<WarehouseInOutward> getWarehouseInOutwardById(@PathVariable Long id) {
	        Optional<WarehouseInOutward> entries = warehouseInOutwardRepository.findById(id);
	        return entries.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    
	    @PostMapping("/getWarehouseInOutwardByDate/{date}")
		 public ResponseEntity<?> getWarehouseInOutwardByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
		     try {
		         List<WarehouseInOutward> warehouseInOutward = warehouseInOutwardRepository.findAllByDate(date);
		         if (!warehouseInOutward.isEmpty()) {
		             return ResponseEntity.ok(warehouseInOutward);
		         } else {
		             return ResponseEntity.notFound().build();
		         }
		     } catch (DataAccessException e) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving InOutward by date: " + e.getMessage());
		     }
		 }
	    
	    
	    /*@PostMapping("/GetWarehouseInOutwardBetweenDates")
		public ResponseEntity<List<WarehouseInOutward>> getReportsByDateRange(
				@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
				@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
			List<WarehouseInOutward> reports = warehouseService.getReportsByDateRange(startDate, endDate);
			return ResponseEntity.ok(reports);
		}
	    */

	@PostMapping("/GetInwardBetweenDates")
	public ResponseEntity<List<WarehouseInOutward>> getReportsByDateRange(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
		List<WarehouseInOutward> reports = warehouseInOutwardRepository.findByStatusAndDateBetween("0", startDate, endDate);
		return ResponseEntity.ok(reports);
	}

	// GetByDate InventoryOutwardDate  10-06-2024  By Sanket
	@PostMapping("/GetOutwardBetweenDates")
	public ResponseEntity<List<WarehouseInOutward>> getReportsByDateRange1(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
		List<WarehouseInOutward> reports = warehouseInOutwardRepository.findByStatusAndDateBetween("1", startDate, endDate);
		return ResponseEntity.ok(reports);
	}
	    
	    @PostMapping("/totalStockWhereWeaponNameIsPresent")
	    public ResponseEntity<Integer> getTotalStockWhereWeaponNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/totalStockWhereRoundNameIsPresent")
	    public ResponseEntity<Integer> getTotalStockWhereRoundNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/availableStockWhereWeaponNameIsPresent")
	    public ResponseEntity<Integer> getAvailableStockWhereWeaponNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/availableStockWhereRoundNameIsPresent")
	    public ResponseEntity<Integer> findTotalAvailableStockWhereRoundNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/distributionStockWhereWeaponNameIsPresent")
	    public ResponseEntity<Integer> findTotalDistributionStockWhereWeaponNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/distributionStockWhereRoundNameIsPresent")
	    public ResponseEntity<Integer> findTotalDistributionStockWhereRoundNameIsPresent() {
	        Integer totalStock = warehouseTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent();
	        return ResponseEntity.ok(totalStock);
	    }
	    
	    
	    @PostMapping("/stockSummary")
	    public ResponseEntity<Map<String, Integer>> getStockSummary() {
	        Map<String, Integer> summary = new HashMap<>();
	        summary.put("TotalStockWhereWeaponNameIsPresent", warehouseTotalInOutwardRepository.findTotalStockWhereWeaponNameIsPresent());
	        summary.put("TotalStockWhereRoundNameIsPresent", warehouseTotalInOutwardRepository.findTotalStockWhereRoundNameIsPresent());
	        summary.put("AvailableStockWhereWeaponNameIsPresent", warehouseTotalInOutwardRepository.findTotalAvailableStockWhereWeaponNameIsPresent());
	        summary.put("AvailableStockWhereRoundNameIsPresent", warehouseTotalInOutwardRepository.findTotalAvailableStockWhereRoundNameIsPresent());
	        summary.put("DistributionStockWhereWeaponNameIsPresent", warehouseTotalInOutwardRepository.findTotalDistributionStockWhereWeaponNameIsPresent());
	        summary.put("DistributionStockWhereRoundNameIsPresent", warehouseTotalInOutwardRepository.findTotalDistributionStockWhereRoundNameIsPresent());
	        return new ResponseEntity<>(summary, HttpStatus.OK);
	    }    
}



//@Autowired
//private WarehouseService warehouseService;
//
//@PostMapping("/save")
//public ResponseEntity<String> saveWarehouseInOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
//  warehouseService.saveWarehouseInOutward(warehouseInOutward);
//  return ResponseEntity.ok("Warehouse InOutward saved successfully.");
//}
//
//@PostMapping("/create")
//public ResponseEntity<String> createWarehouseInOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
//  warehouseService.createWarehouseInOutward(warehouseInOutward);
//  return ResponseEntity.ok("Warehouse InOutward saved successfully.");
//}



/* Main method	    @PostMapping("/saveTotal")
	    public ResponseEntity<String> createStockAndTotalStock(@RequestBody WarehouseInOutward warehouseInOutward) {
	    	// Set createdAt and updatedAt fields with current date and time
	        LocalDateTime now = LocalDateTime.now();
	        warehouseInOutward.setCreatedAt(now);
	        warehouseInOutward.setUpdatedAt(now);

	        // Set default value for status column
	        warehouseInOutward.setStatus("0");
	        // Save warehouse in/outward entry
	        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

	        // Update total stock
	        WarehouseTotalInOutward totalStock = warehouseTotalInOutwardRepository.findByWeaponNameAndRoundName(
	                warehouseInOutward.getWeaponName(), warehouseInOutward.getRoundName());

	        if (totalStock != null) {
	            // Update total stock quantity
	            int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(warehouseInOutward.getRecievedQuantity());
	            totalStock.setTotalStock(newTotalStock);
	         //   totalStock.setUpdatedAt(LocalDateTime.now());
	            warehouseTotalInOutwardRepository.save(totalStock);

	            // Update available stock by subtracting distribution stock
	            int distributionStock = totalStock.getDistributionStock();
	            int newAvailableStock = newTotalStock - distributionStock;
	            totalStock.setAvailableStock(newAvailableStock);
	            warehouseTotalInOutwardRepository.save(totalStock);
	        } else {
	            // Create new total stock entry
	            WarehouseTotalInOutward newTotalStockEntry = new WarehouseTotalInOutward();
	            newTotalStockEntry.setWeaponName(warehouseInOutward.getWeaponName());
	            newTotalStockEntry.setRoundName(warehouseInOutward.getRoundName());
	            newTotalStockEntry.setTotalStock(Integer.parseInt(warehouseInOutward.getRecievedQuantity()));
	            newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
	            newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
	         //   newTotalStockEntry.setCreatedAt(LocalDateTime.now());
	            warehouseTotalInOutwardRepository.save(newTotalStockEntry);
	        }

	        // Respond with success message
	        String responseMessage = "{\"message\": \"Warehouse in/outward entry saved successfully\",\"Id\": "
	                + savedWarehouseInOutward.getId() + "}";
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
	    } */



/*	    @PostMapping("/saveDistributionMagzine")
    public ResponseEntity<String> createDistributionMagzine(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        warehouseInOutward.setCreatedAt(now);
        warehouseInOutward.setUpdatedAt(now);

        // Set default value for status column
        warehouseInOutward.setStatus("0");

        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Check if totalWeapon or depositeRound is not null or empty
        if ((warehouseInOutward.getTotalWeapon() != null && !warehouseInOutward.getTotalWeapon().isEmpty()) ||
                (warehouseInOutward.getDepositeRound() != null && !warehouseInOutward.getDepositeRound().isEmpty())) {

            String weaponName = warehouseInOutward.getWeaponName();
            String roundName = warehouseInOutward.getRoundName();

            // Fetch TotalStock by WeaponName and/or RoundName
            WarehouseTotalInOutward totalStockByWeapon = null;
            WarehouseTotalInOutward totalStockByRound = null;

            if (weaponName != null && !weaponName.isEmpty()) {
                totalStockByWeapon = warehouseTotalInOutwardRepository.findByWeaponName(weaponName);
            }

            if (roundName != null && !roundName.isEmpty()) {
                totalStockByRound = warehouseTotalInOutwardRepository.findByRoundName(roundName);
            }

            // Check if TotalStock for Weapon and/or Round is found
            if ((totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null) ||
                    (totalStockByRound != null && totalStockByRound.getTotalStock() != null)) {

                // Assuming totalWeapon and depositeRound are Integer fields
                int totalWeapon = 0;
                int depositeRound = 0;

                if (warehouseInOutward.getTotalWeapon() != null && !warehouseInOutward.getTotalWeapon().isEmpty()) {
                    totalWeapon = Integer.parseInt(warehouseInOutward.getTotalWeapon());
                }

                if (warehouseInOutward.getDepositeRound() != null && !warehouseInOutward.getDepositeRound().isEmpty()) {
                    depositeRound = Integer.parseInt(warehouseInOutward.getDepositeRound());
                }

                if ("0".equals(warehouseInOutward.getStatus())) {
                    if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && totalWeapon > 0) {
                        int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                        int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                        if (availableStockWeapon >= totalWeapon) {
                            totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + totalWeapon); // Update distribution_stock
                            totalStockByWeapon.setAvailableStock(availableStockWeapon - totalWeapon); // Update available_stock
                            warehouseTotalInOutwardRepository.save(totalStockByWeapon);
                        } else {
                            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + weaponName);
                        }
                    }

                    if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && depositeRound > 0) {
                        int currentTotalStockRound = totalStockByRound.getTotalStock();
                        int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                        if (availableStockRound >= depositeRound) {
                            totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + depositeRound); // Update distribution_stock
                            totalStockByRound.setAvailableStock(availableStockRound - depositeRound); // Update available_stock
                            warehouseTotalInOutwardRepository.save(totalStockByRound);
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

        String responseMessage = "{\"message\": \"Distribution magzine info saved successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */
    
/*    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Update total stock for outward movement
        WarehouseTotalInOutward totalStockByWeapon = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        WarehouseTotalInOutward totalStockByRound = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());

        if (totalStockByWeapon != null && totalStockByWeapon.getTotalStock() != null && receivedQuantity > 0) {
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
            }
        }

        if (totalStockByRound != null && totalStockByRound.getTotalStock() != null && receivedQuantity > 0) {
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
            }
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */
    
/*	    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Update total stock for outward movement
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());

        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        for (WarehouseTotalInOutward totalStockByWeapon : totalStockByWeaponList) {
            if (totalStockByWeapon.getTotalStock() != null && receivedQuantity > 0) {
                int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
                int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
                if (availableStockWeapon >= receivedQuantity) {
                    totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
                    totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
                    warehouseTotalInOutwardRepository.save(totalStockByWeapon);
                } else {
                    sufficientStockWeapon = false;
                }
            }
        }

        for (WarehouseTotalInOutward totalStockByRound : totalStockByRoundList) {
            if (totalStockByRound.getTotalStock() != null && receivedQuantity > 0) {
                int currentTotalStockRound = totalStockByRound.getTotalStock();
                int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
                if (availableStockRound >= receivedQuantity) {
                    totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
                    totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
                    warehouseTotalInOutwardRepository.save(totalStockByRound);
                } else {
                    sufficientStockRound = false;
                }
            }
        }

        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }  */
    
    
/*	    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }  */
    
    
/*	 1st proper   @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and check stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            if (availableStockWeapon < receivedQuantity) {
                sufficientStockWeapon = false;
            }
        }

        // Find and check stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            if (availableStockRound < receivedQuantity) {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Update total stock for outward movement by weapon name
        for (WarehouseTotalInOutward totalStockByWeapon : totalStockByWeaponList) {
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
            totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
            warehouseTotalInOutwardRepository.save(totalStockByWeapon);
        }

        // Update total stock for outward movement by round name
        for (WarehouseTotalInOutward totalStockByRound : totalStockByRoundList) {
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
            totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
            warehouseTotalInOutwardRepository.save(totalStockByRound);
        }

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }	    */
    
/*	2nd proper    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */


/*	    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Log the received warehouseInOutward object
        System.out.println("Received warehouseInOutward: " + warehouseInOutward.toString());

        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = currentTotalStockWeapon - totalStockByWeapon.getDistributionStock(); // Calculate available stock
            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int currentTotalStockRound = totalStockByRound.getTotalStock();
            int availableStockRound = currentTotalStockRound - totalStockByRound.getDistributionStock(); // Calculate available stock
            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity); // Update distribution_stock
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity); // Update available_stock
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */

/*    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int currentTotalStockWeapon = totalStockByWeapon.getTotalStock();
            int availableStockWeapon = totalStockByWeapon.getAvailableStock(); // Use balancedQuantity for available stock
            if (availableStockWeapon >= receivedQuantity) {
                totalStockByWeapon.setAvailableStock(String.valueOf(availableStockWeapon - receivedQuantity)); // Update balanced_quantity
                totalStockByWeapon.setDistributionStock(String.valueOf(receivedQuantity)); // Update received_quantity
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int currentTotalStockRound = Integer.parseInt(totalStockByRound.getTotalQuantity());
            int availableStockRound = Integer.parseInt(totalStockByRound.getBalancedQuantity()); // Use balancedQuantity for available stock
            if (availableStockRound >= receivedQuantity) {
                totalStockByRound.setBalancedQuantity(String.valueOf(availableStockRound - receivedQuantity)); // Update balanced_quantity
                totalStockByRound.setRecievedQuantity(String.valueOf(receivedQuantity)); // Update received_quantity
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */
    
/*    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = Integer.parseInt(totalStockByWeapon.getBalancedQuantity()); // Get available stock

            if (availableStockWeapon >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByWeapon.setRecievedQuantity(String.valueOf(Integer.parseInt(totalStockByWeapon.getRecievedQuantity()) + receivedQuantity)); 
                totalStockByWeapon.setBalancedQuantity(String.valueOf(availableStockWeapon - receivedQuantity));
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = Integer.parseInt(totalStockByRound.getBalancedQuantity()); // Get available stock

            if (availableStockRound >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByRound.setRecievedQuantity(String.valueOf(Integer.parseInt(totalStockByRound.getRecievedQuantity()) + receivedQuantity)); 
                totalStockByRound.setBalancedQuantity(String.valueOf(availableStockRound - receivedQuantity));
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */

/* working    @PostMapping("/spreadOutward")
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock(); // Get available stock

            if (availableStockWeapon >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = totalStockByRound.getAvailableStock(); // Get available stock

            if (availableStockRound >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }  */
    
    
/* working	    @PostMapping("/spreadOutward")
    @Transactional
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            WarehouseTotalInOutward totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock(); // Get available stock

            if (availableStockWeapon >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            WarehouseTotalInOutward totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = totalStockByRound.getAvailableStock(); // Get available stock

            if (availableStockRound >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        if (!sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getRoundName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }  */

/* Perfect	    @PostMapping("/spreadOutward")
    @Transactional
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStock = true;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        WarehouseTotalInOutward totalStockByWeapon = null;

        if (!totalStockByWeaponList.isEmpty()) {
            totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock(); // Get available stock

            if (availableStockWeapon >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStock = false;
            }
        } else {
            sufficientStock = false;
        }

        // Check for sufficient stock in WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStock) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName());
        }

        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */
    
/* main method 	    @PostMapping("/spreadOutward")
    @Transactional
    public ResponseEntity<String> spreadOutward(@RequestBody WarehouseInOutward warehouseInOutward) {
        // Parse received quantity
        int receivedQuantity = Integer.parseInt(warehouseInOutward.getRecievedQuantity());

        // Initialize flags to track stock sufficiency
        boolean sufficientStockWeapon = true;
        boolean sufficientStockRound = true;

        // Initialize variables for stock updates
        WarehouseTotalInOutward totalStockByWeapon = null;
        WarehouseTotalInOutward totalStockByRound = null;

        // Find and update stock by weapon name if present
        List<WarehouseTotalInOutward> totalStockByWeaponList = warehouseTotalInOutwardRepository.findByWeaponName(warehouseInOutward.getWeaponName());
        if (!totalStockByWeaponList.isEmpty()) {
            totalStockByWeapon = totalStockByWeaponList.get(0);
            int availableStockWeapon = totalStockByWeapon.getAvailableStock(); // Get available stock

            if (availableStockWeapon >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByWeapon.setDistributionStock(totalStockByWeapon.getDistributionStock() + receivedQuantity);
                totalStockByWeapon.setAvailableStock(availableStockWeapon - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByWeapon);
            } else {
                sufficientStockWeapon = false;
            }
        } else {
            sufficientStockWeapon = false;
        }

        // Find and update stock by round name if present
        List<WarehouseTotalInOutward> totalStockByRoundList = warehouseTotalInOutwardRepository.findByRoundName(warehouseInOutward.getRoundName());
        if (!totalStockByRoundList.isEmpty()) {
            totalStockByRound = totalStockByRoundList.get(0);
            int availableStockRound = totalStockByRound.getAvailableStock(); // Get available stock

            if (availableStockRound >= receivedQuantity) {
                // Update distribution and available stock
                totalStockByRound.setDistributionStock(totalStockByRound.getDistributionStock() + receivedQuantity);
                totalStockByRound.setAvailableStock(availableStockRound - receivedQuantity);
                warehouseTotalInOutwardRepository.save(totalStockByRound);
            } else {
                sufficientStockRound = false;
            }
        } else {
            sufficientStockRound = false;
        }

        // Check for sufficient stock in either WarehouseTotalInOutward and respond with appropriate message
        if (!sufficientStockWeapon && !sufficientStockRound) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient available stock for " + warehouseInOutward.getWeaponName() + " or " + warehouseInOutward.getRoundName());
        }

        LocalDateTime now = LocalDateTime.now();
        warehouseInOutward.setCreatedAt(now);
        warehouseInOutward.setUpdatedAt(now);

        // Set default value for status column
        warehouseInOutward.setStatus("1");
        
        // Save warehouse in/outward entry
        WarehouseInOutward savedWarehouseInOutward = warehouseInOutwardRepository.save(warehouseInOutward);

        // Respond with success message
        String responseMessage = "{\"message\": \"Outward data spread successfully\",\"Id\": " + savedWarehouseInOutward.getId() + "}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } */
	    
	    