package com.example.WeaponArmaryManagementSystem.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.WeaponArmaryManagementSystem.Service.Impl.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WeaponArmaryManagementSystem.model.Designation;
import com.example.WeaponArmaryManagementSystem.model.InoutwardRegistered;
import com.example.WeaponArmaryManagementSystem.model.Stock;
import com.example.WeaponArmaryManagementSystem.model.TotalStock;
import com.example.WeaponArmaryManagementSystem.repository.StockRepository;
import com.example.WeaponArmaryManagementSystem.repository.TotalStockRepository;


@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
    private StockRepository stockRepository;

	@Autowired
	private StockService stockService;

	@Autowired
	private TotalStockRepository totalStockRepository;


	@PostMapping("/list")
	public List<Stock> getAllStock() {
		return stockRepository.findAllByInDateDesc();
	}

	@PostMapping("/save")
    public ResponseEntity<String> createStock(@RequestBody Stock stock) {
        // Set createdAt and updatedAt fields with current date and time
        LocalDateTime now = LocalDateTime.now();
        stock.setCreatedAt(now);
        stock.setUpdatedAt(now);
        
     // Set default value for status column
     //   distributionMagzine.setStatus("0");

        Stock savedStock = stockRepository.save(stock);
        String responseMessage = "{\"message\": \"Stock saved  successful\",\"Id\": 0"+"}";
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
	
	
	
	@PostMapping("/getById/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
	
	
	
	@PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteDesignation(@PathVariable Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
        	stockRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Stock Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock Not Found");
        }
    }

	
	//changed by vikas as per faiz on 01/06/2024
	@PostMapping("/saveTotal")
	public ResponseEntity<String> createStockAndTotalStock(@RequestBody Stock stock) {
	    try {
	        // Save stock entry
	        Stock savedStock = stockRepository.save(stock);

	        // Update total stock
	        TotalStock totalStock = totalStockRepository.findByWeaponNameAndRoundName(stock.getWeaponName(), stock.getRoundName());
	        if (totalStock != null) {
	            // Update total stock quantity
	            int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(stock.getRecievedQuantity());
	            int oldTotalStock = totalStock.getTotalStock(); // Store the old total stock value
	            totalStock.setTotalStock(newTotalStock);
	            totalStock.setUpdatedAt(LocalDateTime.now());
	            totalStockRepository.save(totalStock);

	            // Update available stock by subtracting distribution stock
	            int distributionStock = totalStock.getDistributionStock();
	            int newAvailableStock = newTotalStock - distributionStock;
	            totalStock.setAvailableStock(newAvailableStock);
	            totalStockRepository.save(totalStock);
	        } else {
	            // Create new total stock entry
	            TotalStock newTotalStockEntry = new TotalStock();
	            newTotalStockEntry.setWeaponName(stock.getWeaponName());
	            newTotalStockEntry.setRoundName(stock.getRoundName());
	            newTotalStockEntry.setTotalStock(Integer.parseInt(stock.getRecievedQuantity()));
	            newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
	            newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
	            newTotalStockEntry.setCreatedAt(LocalDateTime.now());
	            totalStockRepository.save(newTotalStockEntry);
	        }

	        // Respond with success message
	        String responseMessage = "{\"message\": \"Stock saved successfully\", \"Id\": 0}";
	        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
	    } catch (Exception e) {
	        // Handle any exceptions and respond with a failure message
	        String responseMessage = "{\"message\": \"Failed to save stock: " + e.getMessage() + "\", \"Id\": 1}";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	    }
	}

	
	
	@PostMapping("/listByWeaponOrRoundName")
	public List<Stock> getAllStockByWeaponOrRoundName(@RequestParam(required = false) String weaponName,
													  @RequestParam(required = false) String roundName) {
		if (weaponName != null && !weaponName.isEmpty()) {
			return stockRepository.findAllByWeaponName(weaponName);
		} else if (roundName != null && !roundName.isEmpty()) {
			return stockRepository.findAllByRoundName(roundName);
		} else {
			return new ArrayList<>(); // Or handle the case where neither weaponName nor roundName is provided
	 }
	}

	@PostMapping("/GetStockBetweenDates")
	public ResponseEntity<List<Stock>> getReportsByDateRange(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
		List<Stock> reports = stockService.getReportsByDateRange(startDate, endDate);
		return ResponseEntity.ok(reports);
	}

	@PostMapping("/expiredWeaponAlerts")
	//  public ResponseEntity<List<DistributionMagzineDto>> getAlerts() {
	public ResponseEntity<List<Stock>> getExpiredWeaponAlerts() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Calculate the date 10 days from now
		LocalDate tenDaysLater = currentDate.plusDays(10);

		// Format the dates to match the format in the database
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String currentDateStr = currentDate.format(formatter);
		String tenDaysLaterStr = tenDaysLater.format(formatter);

		// Get distribution magazines with dateOfRetirement within the next 10 days or already passed
		List<Stock> alerts = stockRepository.findAlert(currentDateStr, tenDaysLaterStr);

		// Convert to DTO and return
		//  List<DistributionMagzineDto> dtos = alerts.stream()
		//  .map(DistributionMagzineDto::new)
		// .collect(Collectors.toList());
		return ResponseEntity.ok(alerts);
		// return ResponseEntity.ok(dtos);
	}
}




/*
@PostMapping("/saveTotal")
public ResponseEntity<String> createStockAndTotalStock(@RequestBody Stock stock) {
    // Save stock entry
    Stock savedStock = stockRepository.save(stock);

    // Update total stock
    TotalStock totalStock = totalStockRepository.findByWeaponNameAndRoundName(stock.getWeaponName(), stock.getRoundName());
    if (totalStock != null) {
        // Update total stock quantity
        int newTotalStock = totalStock.getTotalStock() + Integer.parseInt(stock.getRecievedQuantity());
        int oldTotalStock = totalStock.getTotalStock(); // Store the old total stock value
        totalStock.setTotalStock(newTotalStock);
		totalStock.setUpdatedAt(LocalDateTime.now());
        totalStockRepository.save(totalStock);

        // Update available stock by subtracting distribution stock
        int distributionStock = totalStock.getDistributionStock();
        int newAvailableStock = newTotalStock - distributionStock;
        totalStock.setAvailableStock(newAvailableStock);
        totalStockRepository.save(totalStock);
    } else {
        // Create new total stock entry
        TotalStock newTotalStockEntry = new TotalStock();
        newTotalStockEntry.setWeaponName(stock.getWeaponName());
        newTotalStockEntry.setRoundName(stock.getRoundName());
        newTotalStockEntry.setTotalStock(Integer.parseInt(stock.getRecievedQuantity()));
        newTotalStockEntry.setDistributionStock(0); // Initial distribution stock is 0
        newTotalStockEntry.setAvailableStock(newTotalStockEntry.getTotalStock()); // Available stock is initially the same as total stock
        newTotalStockEntry.setCreatedAt(LocalDateTime.now());
		totalStockRepository.save(newTotalStockEntry);
    }

    // Respond with success message
    String responseMessage = "{\"message\": \"Stock saved successfully\",\"Id\":0 " + "}";
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
}
*/

