package com.example.WeaponArmaryManagementSystem.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeaponArmaryManagementSystem.model.InwardItems;
import com.example.WeaponArmaryManagementSystem.model.InwardRegister;
import com.example.WeaponArmaryManagementSystem.model.PrefixConfig;
import com.example.WeaponArmaryManagementSystem.model.SaveDataRequest;
import com.example.WeaponArmaryManagementSystem.repository.InwardItemsRepository;
import com.example.WeaponArmaryManagementSystem.repository.InwardRegisterRepository;
import com.example.WeaponArmaryManagementSystem.repository.PrefixConfigRepository;


@RestController
@CrossOrigin(origins = "*")
public class InwardController {
	

    private InwardRegisterRepository inwardRegisterRepository;
    private InwardItemsRepository inwardItemsRepository;
    private PrefixConfigRepository prefixConfigRepository;
     

    @Autowired
    public InwardController(InwardRegisterRepository inwardRegisterRepository, 
                            InwardItemsRepository inwardItemsRepository,
                            PrefixConfigRepository prefixConfigRepository) {
        this.inwardRegisterRepository = inwardRegisterRepository;
        this.inwardItemsRepository = inwardItemsRepository;
        this.prefixConfigRepository = prefixConfigRepository;
    }

  
    private String generateInwardNumber(String departmentId) {
        PrefixConfig prefixConfig = prefixConfigRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "";

        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        InwardRegister lastEntry = inwardRegisterRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        int nextNumber = 1;

        if (lastEntry != null) {
            String lastInwardNumber = lastEntry.getInwardNumber();
            if (lastInwardNumber != null && lastInwardNumber.startsWith(prefix)) {
                String numberPart = lastInwardNumber.substring(prefix.length());
                if (!numberPart.isEmpty()) {
                    try {
                        nextNumber = Integer.parseInt(numberPart) + 1;
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing number part of inward number: " + e.getMessage());
                    }
                }
            }
        }

        String formattedNumber = String.format("%04d", nextNumber);
        return prefix + formattedNumber;
    }

    
    
    @PostMapping("/save_inward_register")
    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
        InwardRegister inwardRegister = requestData.getInwardRegister();
        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

        String departmentId = inwardRegister.getDepartmentId();
        String inwardNumber = generateInwardNumber(departmentId);
        inwardRegister.setInwardNumber(inwardNumber);

        inwardRegisterRepository.save(inwardRegister);

        for (String[] item : inwardItemsArray) {
            InwardItems inwardItems = new InwardItems();
            inwardItems.setInwardRegister(inwardRegister);
            inwardItems.setButtNo(item[0]);
            inwardItems.setManufacturingNo(item[1]);
            inwardItems.setCount(item[2]);
           // inwardItems.setDepartmentId(item[3]);
            //inwardItems.setInwardNumber(inwardNumber);
            inwardItemsRepository.save(inwardItems);
        }
        return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
    }

    
    @PostMapping("/getInwardRegi_bydept_id")
    public ResponseEntity<?> getAllInwardRegisters(@RequestParam String departmentId) {
        try {
            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
            return ResponseEntity.ok(inwardRegisters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
        }
    }
	 
	   @PostMapping("/getByBetweenDate_inward")
	    public ResponseEntity<?> getByDateRangeAndDepartment(
	            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
	            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate,
	            @RequestParam("departmentId") String departmentId) {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDateBetweenAndDepartmentId(startDate, endDate, departmentId);

	            if (inwardRegisters.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(Collections.singletonMap("message", "No records found for the given date range and department ID."));
	            } else {
	                return ResponseEntity.ok(inwardRegisters);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
	   
      
    
    @PostMapping("/savePrefixConfiginward")
    public ResponseEntity<String> savePrefixConfig(@RequestBody PrefixConfig prefixConfig) {
        try {
            prefixConfigRepository.save(prefixConfig);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully\",\"Id\": 0}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data not saved successfully\",\"Id\": 1}" + e.getMessage());
        }
    }
    
    
   
    @PostMapping("/getLatestPrefix/{departmentId}")
    public ResponseEntity<String> getLatestPrefix(@PathVariable String departmentId) {
        try {
            PrefixConfig latestPrefixConfig = prefixConfigRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
            if (latestPrefixConfig != null) {
                return ResponseEntity.ok("{\"prefix\": \"" + latestPrefixConfig.getPrefix() + "\"}");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No prefix found for the given departmentId\",\"Id\": 1}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error fetching prefix\",\"Id\": 1} " + e.getMessage());
        }
    }
    
    
	 
	   @PostMapping("/getByDate_inward")
	    public ResponseEntity<?> getByDate(
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDate(date.toString());

	            if (inwardRegisters.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(Collections.singletonMap("message", "No records found for the given date."));
	            } else {
	                return ResponseEntity.ok(inwardRegisters);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
	   
	   

	      //old
		   @PostMapping("/getAllInwardRegisters")
		    public ResponseEntity<?> getAllInwardRegisters() {
		        try {
		            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findAllOrderByIdDesc();
		            return ResponseEntity.ok(inwardRegisters);
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
		        }
		    }
		   
			 

		 @PostMapping("/getByInwardNumberfromregister1")
		 public ResponseEntity<?> getByInwardNumber1(@RequestParam String inwardNumber) {
		     try {
		         // Fetch records from outwardRegister by outwardNumber
		         List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByInwardNumber(inwardNumber);

		         if (inwardRegisters.isEmpty()) {
		             return ResponseEntity.notFound().build();
		         } else {
		             // Get the first InwardRegister entity (assuming inwardNumber is unique)
		        	 InwardRegister inwardRegister = inwardRegisters.get(0);

		             // Fetch related OutwardItems using outwardRegister's ID
		             List<InwardItems> inwardItems = inwardItemsRepository.findByInwardRegisterId(inwardRegister.getId());

		             return ResponseEntity.ok(inwardItems);
		         }
		     } catch (Exception e) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
		     }
		 }
		 
		  
		 
		 @PostMapping("/getByInwardNumberfromregister")
		 public ResponseEntity<?> getByInwardNumber(@RequestParam String inwardNumber) {
		     try {
		         // Fetch records from InwardRegister by inwardNumber
		         List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByInwardNumber(inwardNumber);

		         if (inwardRegisters.isEmpty()) {
		             return ResponseEntity.notFound().build();
		         } else {
		             // Get the first InwardRegister entity (assuming inwardNumber is unique)
		        	 InwardRegister inwardRegister = inwardRegisters.get(0);
		             return ResponseEntity.ok(inwardRegister);
		         }
		     } catch (Exception e) {
		         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
		     }
		   }
	} 

	   
	   /*   
	   @PostMapping("/getByBetweenDate_inward")
	    public ResponseEntity<?> getByDateRange(
	            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
	            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDateBetween(startDate.toString(), endDate.toString());

	            if (inwardRegisters.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(Collections.singletonMap("message", "No records found for the given date range."));
	            } else {
	                return ResponseEntity.ok(inwardRegisters);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }*/
	   
	   
	  
	   
	  /* 
	   //new
	   @PostMapping("/getAllInwardRegisters_bydept_id")
	    public ResponseEntity<?> getAllInwardRegisters(@RequestParam String departmentId) {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
	            return ResponseEntity.ok(inwardRegisters);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }}
*/
	   
	        
	  
    
    
   /*
    private String generateInwardNumber(String departmentId) {
        // Fetch the latest prefix for the given department ID from the database
        PrefixConfig prefixConfig = prefixConfigRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "";

        // Ensure the prefix ends with a separator if it is not empty
        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        // Find the last inward register entry for the same department to determine the next number
        InwardRegister lastEntry = inwardRegisterRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        int nextNumber = 1; // Default to 1 if no last entry

        if (lastEntry != null) {
            String lastInwardNumber = lastEntry.getInwardNumber();
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

    
    @PostMapping("/save_inward_register")
    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
        // Parse data from request
        InwardRegister inwardRegister = requestData.getInwardRegister();
        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

        // Generate Inward Number using departmentId
        String departmentId = inwardRegister.getDepartmentId();
        String inwardNumber = generateInwardNumber(departmentId);
       // inwardRegister.setInwardNumber(inwardNumber);

        // Save InwardRegister
        inwardRegisterRepository.save(inwardRegister);

        // Save InwardItems
        for (String[] item : inwardItemsArray) {
            InwardItems inwardItems = new InwardItems();
            inwardItems.setInwardRegister(inwardRegister);
            inwardItems.setButtNo(item[0]);
            inwardItems.setManufacturingNo(item[1]);
            inwardItems.setCount(item[2]);
            inwardItems.setDepartmentId(item[3]);
          //  inwardItems.setInwardNumber(inwardNumber); // Set the inward number for each item
            inwardItemsRepository.save(inwardItems);
        }
        return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
    }
    */
    
        
/*
    @PostMapping("/getPrefixInward")
    public ResponseEntity<String> getCurrentPrefix() {
        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
        if (prefixConfig != null) {
            return ResponseEntity.ok("{\"prefix\": \"" + prefixConfig.getPrefix() + "\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/


	 
	
/* 
private String generateInwardNumber() {
    // Fetch the latest prefix from the database
    PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
    String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "";

    // Ensure the prefix ends with a separator if it is not empty
    if (!prefix.isEmpty() && !prefix.endsWith("/")) {
        prefix += "/";
    }

    // Find the last inward register entry to determine the next number
    InwardRegister lastEntry = inwardRegisterRepository.findTopByOrderByIdDesc();
    int nextNumber = 1; // Default to 1 if no last entry

    if (lastEntry != null) {
        String lastInwardNumber = lastEntry.getInwardNumber();
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
*/





/* 
@PostMapping("/save_inward_register")
public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
    // Parse data from request
    InwardRegister inwardRegister = requestData.getInwardRegister();
    List<String[]> inwardItemsArray = requestData.getInwardItemsList();

    // Generate Inward Number
    inwardRegister.setInwardNumber(generateInwardNumber());

    // Save InwardRegister
    inwardRegisterRepository.save(inwardRegister);

    // Save InwardItems
    for (String[] item : inwardItemsArray) {
        InwardItems inwardItems = new InwardItems();
        inwardItems.setInwardRegister(inwardRegister);
        inwardItems.setButtNo(item[0]);
        inwardItems.setManufacturingNo(item[1]);
        inwardItems.setCount(item[2]);
        inwardItems.setDepartmentId(item[3]);
        inwardItemsRepository.save(inwardItems);
    }
    return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\":0}", HttpStatus.OK);
}
*/

/*
@PostMapping("/update_prefix_inward")
public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
    PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
    if (prefixConfig != null) {
        prefixConfig.setPrefix(newPrefix);
    } else {
        prefixConfig = new PrefixConfig();
        prefixConfig.setPrefix(newPrefix);
    }
    prefixConfigRepository.save(prefixConfig);
    return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\":0}", HttpStatus.OK);
}
*/


/*
   //working descending by date
	   @PostMapping("/getAllInwardRegisters")
	    public ResponseEntity<?> getAllInwardRegisters() {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findAllOrderByDateDesc();
	            return ResponseEntity.ok(inwardRegisters);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }*/	 
	 
	   /*
	//working 
	 @PostMapping("/getAllInwardRegisters")
	 public ResponseEntity<?> getAllInwardRegisters() {
	     try {
	         // Fetch all records from InwardRegister
	         List<InwardRegister> inwardRegisters = inwardRegisterRepository.findAll();
	         
	         return ResponseEntity.ok(inwardRegisters);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }*/

	 
	 /*//working
	  @PostMapping("/getAllInwardItems")
	    public ResponseEntity<List<InwardItems>> getAllInwardItems() {
	        try {
	            List<InwardItems> inwardItemsList = inwardItemsRepository.findAll();
	            return ResponseEntity.ok().body(inwardItemsList);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }*/



	 
	 /*
	//run ok 
	  @PostMapping("/getByDate_inward/{date}")
	  public ResponseEntity<?> getByDate(@RequestParam String date) {
	        try {
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDate(date);

	            if (inwardRegisters.isEmpty()) {
	                return ResponseEntity.notFound().build();
	            } else {
	                return ResponseEntity.ok(inwardRegisters);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
	 */
	 
	    
	    /*
	    private String generateInwardNumber() {
	        // Fetch the latest prefix from the database
	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        String prefix = prefixConfig != null ? prefixConfig.getPrefix() : "DEFAULT";

	        // Find the last inward register entry to determine the next number
	        InwardRegister lastEntry = inwardRegisterRepository.findTopByOrderByIdDesc();
	        int nextNumber = lastEntry == null ? 1 : Integer.parseInt(lastEntry.getInwardNumber().substring(prefix.length())) + 1;

	        return prefix + nextNumber;
	    }

	    
	      
	    @PostMapping("/save_inward_register")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
	        // Parse data from request
	        InwardRegister inwardRegister = requestData.getInwardRegister();
	        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

	        // Generate Inward Number
	        inwardRegister.setInwardNumber(generateInwardNumber());

	        // Save InwardRegister
	        inwardRegisterRepository.save(inwardRegister);

	        // Save InwardItems
	        for (String[] item : inwardItemsArray) {
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setInwardRegister(inwardRegister);
	            inwardItems.setButtNo(item[0]);
	            inwardItems.setManufacturingNo(item[1]);
	            inwardItems.setCount(item[2]);
	            inwardItemsRepository.save(inwardItems);
	        }
	        return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": 0}", HttpStatus.OK);
	    }

	    
	    
	    @PostMapping("/update_prefix")
	    public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        if (prefixConfig != null) {
	            prefixConfig.setPrefix(newPrefix);
	        } else {
	            prefixConfig = new PrefixConfig();
	            prefixConfig.setPrefix(newPrefix);
	        }
	        prefixConfigRepository.save(prefixConfig);
	        return new ResponseEntity<>("{\"message\": \"Prefix updated successfully\" id:0\"}", HttpStatus.OK);
	    }
	    
	    
	    @PostMapping("/getPrefix")
	    public ResponseEntity<String> getCurrentPrefix() {
	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        if (prefixConfig != null) {
	            return ResponseEntity.ok("{\"prefix\": \"" + prefixConfig.getPrefix() + "\"}");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
		  */
	    
	    
	    
		 
		/* @PostMapping("/save_inward_register")
		    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
		        // Parse data from request
		        InwardRegister inwardRegister = requestData.getInwardRegister();
		        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

		        // Save InwardRegister
		        inwardRegisterRepository.save(inwardRegister);

		        // Save InwardItems
		        for (String[] item : inwardItemsArray) {
		            InwardItems inwardItems = new InwardItems();
		            inwardItems.setInwardRegister(inwardRegister);
		            inwardItems.setButtNo(item[0]);
		            inwardItems.setManufacturingNo(item[1]);
		            inwardItems.setCount(item[2]);
		            //inwardItems.setLocation(item[3]);
		           // inwardItems.setDate(LocalDateTime.now());
		            inwardItemsRepository.save(inwardItems);
		        }
		        //("{\"message\": \"Failed to save weapon data\",\"Id\": 1}");
		        return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": 0}", HttpStatus.OK);
		    }
		 */
		 
		 
	 
	 /*
	  @PostMapping("/getByInwardNumberfromregister1/{inwardNumber}")
	    public ResponseEntity<?> getByInwardNumber1(@PathVariable String inwardNumber) {
	        try {
	            // Fetch records from InwardRegister by inwardNumber
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByInwardNumber(inwardNumber);

	            if (inwardRegisters.isEmpty()) {
	                return ResponseEntity.notFound().build();
	            } else {
	                // Get the first InwardRegister entity (assuming inwardNumber is unique)
	                InwardRegister inwardRegister = inwardRegisters.get(0);
	                
	                // Fetch related InwardItems using inwardRegister's ID
	                List<InwardItems> inwardItems = inwardItemsRepository.findByInwardRegisterId(inwardRegister.getId());
	                
	                return ResponseEntity.ok(inwardItems);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
	*/ 
	 
	 
	 /*   
	//prefectly working
	    private String generateInwardNumber() {
	        // Fetch the latest prefix from the database
	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "DEFAULT/";

	        // Ensure the prefix ends with a separator
	        if (!prefix.endsWith("/")) {
	            prefix += "/";
	        }

	        // Find the last inward register entry to determine the next number
	        InwardRegister lastEntry = inwardRegisterRepository.findTopByOrderByIdDesc();
	        int nextNumber = 1; // Default to 1 if no last entry

	        if (lastEntry != null) {
	            String lastInwardNumber = lastEntry.getInwardNumber();
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

	        return prefix + nextNumber;
	    }
	    

	    @PostMapping("/save_inward_register")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
	        // Parse data from request
	        InwardRegister inwardRegister = requestData.getInwardRegister();
	        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

	        // Generate Inward Number
	        inwardRegister.setInwardNumber(generateInwardNumber());

	        // Save InwardRegister
	        inwardRegisterRepository.save(inwardRegister);

	        // Save InwardItems
	        for (String[] item : inwardItemsArray) {
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setInwardRegister(inwardRegister);
	            inwardItems.setButtNo(item[0]);
	            inwardItems.setManufacturingNo(item[1]);
	            inwardItems.setCount(item[2]);
	            inwardItemsRepository.save(inwardItems);
	        }
	        return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": 0}", HttpStatus.OK);
	    }
	    
	    
	    
	    @PostMapping("/update_prefix_inward")
	    public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        if (prefixConfig != null) {
	            prefixConfig.setPrefix(newPrefix);
	        } else {
	            prefixConfig = new PrefixConfig();
	            prefixConfig.setPrefix(newPrefix);
	        }
	        prefixConfigRepository.save(prefixConfig);
	        return new ResponseEntity<>("{\"message\": \"Prefix updated successfully\" id:0\"}", HttpStatus.OK);
	    }*/
	    
	    /*
	    
	    @PostMapping("/update_prefix_inward")
	    public ResponseEntity<String> updatePrefix(@RequestBody Map<String, String> request) {
	        String newPrefix = request.get("updated_prefix");

	        if (newPrefix == null || newPrefix.isEmpty()) {
	            return new ResponseEntity<>("{\"error\": \"Invalid prefix provided\"}", HttpStatus.BAD_REQUEST);
	        }

	        PrefixConfig prefixConfig = prefixConfigRepository.findTopByOrderByIdDesc();
	        if (prefixConfig != null) {
	            prefixConfig.setPrefix(newPrefix);
	        } else {
	            prefixConfig = new PrefixConfig();
	            prefixConfig.setPrefix(newPrefix);
	        }
	        prefixConfigRepository.save(prefixConfig);

	        return new ResponseEntity<>("{\"updated_prefix\": \"" + newPrefix + "\"}", HttpStatus.OK);
	    }*/

	    
		 
	 /*
	   //working
	 @PostMapping("/getByInwardNumberfromregister/{inwardNumber}")
	 public ResponseEntity<?> getByInwardNumber(@PathVariable String inwardNumber) {
	     try {
	         // Fetch records from InwardRegister by inwardNumber
	         List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByInwardNumber(inwardNumber);
	         
	         if (inwardRegisters.isEmpty()) {
	             return ResponseEntity.notFound().build();
	         } else {
	             return ResponseEntity.ok(inwardRegisters);
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }*/
	 
	 /* 
	  //working
	  @PostMapping("/getAllInwardItems")
	  public ResponseEntity<?> getAllInwardItems() {
	      try {
	          // Fetch all records from InwardItems
	          List<InwardItems> inwardItems = inwardItemsRepository.findAll();
	          
	          return ResponseEntity.ok(inwardItems);
	      } catch (Exception e) {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                               .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	      }
	  }
	  */
	 	 


	 /*
	 
	  @PostMapping("/get_data_by_date")
	    public ResponseEntity<List<InwardItems>> getDataByDate(
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

	        // Convert LocalDate to String
	        String dateString = date.toString();

	        // Fetch inward registers by date
	        List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDate(dateString);
	        if (inwardRegisters.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        // Initialize a list to hold the combined inward items
	        List<InwardItems> combinedInwardItems = new ArrayList<>();

	        // Iterate through each inward register to fetch corresponding inward items
	        for (InwardRegister inwardRegister : inwardRegisters) {
	            String inwardNumber = inwardRegister.getInwardNumber();
	            List<InwardItems> inwardItems = inwardItemsRepository.findByInwardRegister_InwardNumber(inwardNumber);
	            combinedInwardItems.addAll(inwardItems);
	        }

	        if (combinedInwardItems.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(combinedInwardItems, HttpStatus.OK);
	    }
	 */
	   
	  
	
	  /*
	  @PostMapping("/get_all_inward_items")
	  public ResponseEntity<?> getAllInwardItemsWithoutDate() {
	      try {
	          List<InwardItems> inwardItems = inwardItemsRepository.findAllWithoutDate();
	          return ResponseEntity.ok(inwardItems);
	      } catch (Exception e) {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                               .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	      }
	  }

	  */

	  
	
/*
	  @PostMapping("/getAllInwardItems")
	  public ResponseEntity<?> getAllInwardItemsWithoutDate() {
	      try {
	          // Fetch all records from InwardItems
	          List<InwardItems> inwardItems = inwardItemsRepository.findAll();
	          
	          // Remove the date field from each record
	          inwardItems.forEach(item -> item.setDate(null));
	          
	          return ResponseEntity.ok(inwardItems);
	      } catch (Exception e) {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                               .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	      }
	  }
	  */
	  	  
	  
	 
	/*
	  @PostMapping("/get_data_by_date")
	    public ResponseEntity<List<InwardItems>> getDataByDate(
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {

	        // Fetch inward items by date
	        List<InwardItems> inwardItems = inwardItemsRepository.findByDate(date);
	        if (inwardItems.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(inwardItems, HttpStatus.OK);
	    }*/
	  
	  
	
	 /*
	 //working
	 @PostMapping("/getAllData")
	 public ResponseEntity<Map<String, Object>> getAllData() {
	     try {
	         // Fetch all records from InwardRegister
	         List<InwardRegister> inwardRegisters = inwardRegisterRepository.findAll();

	         // Fetch all records from InwardItems
	         List<InwardItems> inwardItems = inwardItemsRepository.findAll();

	         // Create a map to hold the response data
	         Map<String, Object> responseData = new HashMap<>();
	         responseData.put("inwardRegisters", inwardRegisters);
	         responseData.put("inwardItems", inwardItems);

	         return ResponseEntity.ok(responseData);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }
*/
	  
	 
	  
	  /*
	   @PostMapping("/saveData")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest request) {
	        try {
	            // Save data to inward register
	            InwardRegister inwardRegister = new InwardRegister();
	            inwardRegister.setInwardNumber(request.getInwardNumber());
	            inwardRegister.setWeaponName(request.getWeaponName());
	            inwardRegister.setDate(request.getDate());
	            inwardRegister.setDescription(request.getDescription());
	            inwardRegister.setQuantity(request.getQuantity());
	            inwardRegisterRepository.save(inwardRegister);
	            
	            // Save data to inward items
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setDate(request.getDate());
	            inwardItems.setButtNo(request.getButtNo());
	            inwardItems.setManufacturingNo(request.getManufacturingNo());
	            inwardItems.setCount(request.getCount());
	            inwardItems.setCreatedAt(request.getCreatedAt());
	            inwardItems.setInwardRegister(inwardRegister); // Link inward items to inward register
	            inwardItemsRepository.save(inwardItems);
	            
	            return ResponseEntity.ok("Data saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save data: " + e.getMessage());
	        }
	    }
	  */
	 
	/* 
	  @PostMapping("/save_inward_register")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
	        // Parse data from request
	        InwardRegister inwardRegister = requestData.getInwardRegister();
	        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

	        // Save InwardRegister
	        inwardRegisterRepository.save(inwardRegister);

	        // Save InwardItems
	        for (String[] item : inwardItemsArray) {
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setInwardRegister(inwardRegister);
	            
	            // Check if inward number is provided
	            if (item.length > 0 && item[0] != null && !item[0].isEmpty()) {
	                inwardItems.setInwardNumber(item[0]);
	            } else {
	                inwardItems.setInwardNumber(null); // or any default value you choose
	            }
	            
	            inwardItems.setButtNo(item[1]);
	            inwardItems.setManufacturingNo(item[2]);
	            inwardItems.setCount(item[3]);
	            inwardItems.setDate(item[4]);
	            inwardItemsRepository.save(inwardItems);
	        }

	        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	    }*/
	  
	    
	  
	/*  
	//working	 
	  @PostMapping("/save_inward_register")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
	        // Parse data from request
	        InwardRegister inwardRegister = requestData.getInwardRegister();
	        List<String[]> inwardItemsArray = requestData.getInwardItemsList();

	        // Save InwardRegister
	        inwardRegisterRepository.save(inwardRegister);

	        // Save InwardItems
	        for (String[] item : inwardItemsArray) {
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setInwardRegister(inwardRegister);
	           // inwardItems.setInwardNumber(item[0]);
	            inwardItems.setButtNo(item[1]);
	            inwardItems.setManufacturingNo(item[2]);
	            inwardItems.setCount(item[3]);
	            inwardItems.setDate(item[4]);
	            inwardItemsRepository.save(inwardItems);
	        }

	        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	    }
	 */
	  
	  
	 
	/*  
	 @PostMapping("/getDataByDate_inward")
	    public ResponseEntity<List<InwardRegister>> getDataByDate(@RequestParam("date") String date) {
	        // Assuming date is in format "yyyy-MM-dd"
	        List<InwardRegister> data = inwardRegisterRepository.findByDate(date);
	        return new ResponseEntity<>(data, HttpStatus.OK);
	    }	 
	 */
	
	/*
	  @PostMapping("/get_data_by_date")
	    public ResponseEntity<List<InwardItems>> getDataByDate(
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

	        // Fetch inward items by date
	        List<InwardItems> inwardItems = inwardItemsRepository.findByDate(date);
	        if (inwardItems.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(inwardItems, HttpStatus.OK);
	    }
	*/
	 
	 
	  
	 /*
	  @PostMapping("/get_data_by_date")
	    public ResponseEntity<List<InwardItems>> getDataByDate(
	            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

	        // Fetch inward register by date
	        List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDate(date);
	        if (inwardRegisters.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        // Initialize a list to hold the combined inward items
	        List<InwardItems> combinedInwardItems = new ArrayList<>();

	        // Iterate through each inward register to fetch corresponding inward items
	        for (InwardRegister inwardRegister : inwardRegisters) {
	            String inwardNumber = inwardRegister.getInwardNumber();
	            List<InwardItems> inwardItems = inwardItemsRepository.findByInwardRegister_InwardNumber(inwardNumber);
	            combinedInwardItems.addAll(inwardItems);
	        }

	        if (combinedInwardItems.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(combinedInwardItems, HttpStatus.OK);
	    }
	 */
	 
	

/*
	 //working
	 @PostMapping("/get_inward_data_by_date")
	    public ResponseEntity<?> getAllByDate(@RequestParam String date) {
	        try {
	            // Retrieve inward register entries by date
	            List<InwardRegister> inwardRegisters = inwardRegisterRepository.findByDate(date);

	            // Retrieve inward items entries by date
	            List<InwardItems> inwardItems = inwardItemsRepository.findByDate(date);

	            // Construct response object
	            GetAllByDateResponse response = new GetAllByDateResponse();
	            response.setInwardRegisters(inwardRegisters);
	            response.setInwardItems(inwardItems);

	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        
	    }
	 
	 public class GetAllByDateResponse {
	     private List<InwardRegister> inwardRegisters;
	     private List<InwardItems> inwardItems;

	     public List<InwardRegister> getInwardRegisters() {
	         return inwardRegisters;
	     }

	     public void setInwardRegisters(List<InwardRegister> inwardRegisters) {
	         this.inwardRegisters = inwardRegisters;
	     }

	     public List<InwardItems> getInwardItems() {
	         return inwardItems;
	     }

	     public void setInwardItems(List<InwardItems> inwardItems) {
	         this.inwardItems = inwardItems;
	     }
	 }

	 */
	 
	
	 
	/* 
	 //working
	 @PostMapping("/get_inward_data_by_date")
	    public ResponseEntity<List<Map<String, Object>>> getDataByDate(@RequestParam String date) {
	        // Assuming date is in format yyyy-MM-dd

	        // Retrieve InwardRegister data by date
	        List<InwardRegister> inwardRegisterList = inwardRegisterRepository.findByDate(date);

	        // Prepare the response list
	        List<Map<String, Object>> response = new ArrayList<>();

	        // Loop through each InwardRegister
	        for (InwardRegister inwardRegister : inwardRegisterList) {
	            // Retrieve InwardItems data by InwardRegister id
	            List<InwardItems> inwardItemsList = inwardItemsRepository.findByInwardRegisterId(inwardRegister.getId());

	            // Prepare a map to hold InwardRegister and its associated InwardItems
	            Map<String, Object> inwardRegisterMap = new HashMap<>();
	            inwardRegisterMap.put("id", inwardRegister.getId());
	            inwardRegisterMap.put("inwardNumber", inwardRegister.getInwardNumber());
	            inwardRegisterMap.put("weaponName", inwardRegister.getWeaponName());
	            inwardRegisterMap.put("date", inwardRegister.getDate());
	            inwardRegisterMap.put("description", inwardRegister.getDescription());
	            inwardRegisterMap.put("quantity", inwardRegister.getQuantity());
	            
	            // Prepare a list to hold inward items details
	            List<Map<String, Object>> inwardItemsMapList = new ArrayList<>();
	            for (InwardItems inwardItems : inwardItemsList) {
	                Map<String, Object> inwardItemsMap = new HashMap<>();
	                inwardItemsMap.put("id", inwardItems.getId());
	                inwardItemsMap.put("date", inwardItems.getDate());
	                inwardItemsMap.put("buttNo", inwardItems.getButtNo());
	                inwardItemsMap.put("manufacturingNo", inwardItems.getManufacturingNo());
	                inwardItemsMap.put("count", inwardItems.getCount());
	                inwardItemsMap.put("createdAt", inwardItems.getCreatedAt());
	                inwardItemsMapList.add(inwardItemsMap);
	            }
	            inwardRegisterMap.put("inwardItemsList", inwardItemsMapList);

	            // Add the InwardRegister map to the response list
	            response.add(inwardRegisterMap);
	        }

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 */
	 
	 
	 
	 
	/* @PostMapping("/get_inward_data_by_date")
	    public ResponseEntity<Map<String, Object>> getDataByDate(@RequestParam String date) {
	        // Assuming date is in format yyyy-MM-dd

	        // Retrieve InwardRegister data by date
	        List<InwardRegister> inwardRegisterList = inwardRegisterRepository.findByDate(date);

	        // Prepare the response map
	        Map<String, Object> response = new HashMap<>();

	        // Loop through each InwardRegister and fetch associated InwardItems
	        for (InwardRegister inwardRegister : inwardRegisterList) {
	            // Retrieve InwardItems data by InwardRegister id
	            List<InwardItems> inwardItemsList = inwardItemsRepository.findByInwardRegisterId(inwardRegister.getId());

	            // Prepare a map to hold InwardRegister and its associated InwardItems
	            Map<String, Object> inwardRegisterMap = new HashMap<>();
	            inwardRegisterMap.put("id", inwardRegister.getId());
	            inwardRegisterMap.put("inwardNumber", inwardRegister.getInwardNumber());
	            inwardRegisterMap.put("weaponName", inwardRegister.getWeaponName());
	            inwardRegisterMap.put("date", inwardRegister.getDate());
	            inwardRegisterMap.put("description", inwardRegister.getDescription());
	            inwardRegisterMap.put("quantity", inwardRegister.getQuantity());
	            inwardRegisterMap.put("inwardItemsList", inwardItemsList);

	            // Add the InwardRegister map to the response
	            response.put("inwardRegister", inwardRegisterMap);
	        }

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }*/
	 
	 
	 
	/* @PostMapping("/get_inward_data_by_date")
	    public ResponseEntity<Map<String, Object>> getDataByDate(@RequestParam String date) {
	        // Assuming date is in format yyyy-MM-dd

	        // Retrieve InwardRegister data by date
	        List<InwardRegister> inwardRegisterList = inwardRegisterRepository.findByDate(date);

	        // Prepare the response map
	        Map<String, Object> response = new HashMap<>();

	        // Loop through each InwardRegister and fetch associated InwardItems
	        for (InwardRegister inwardRegister : inwardRegisterList) {
	            // Retrieve InwardItems data by InwardRegister id
	            List<InwardItems> inwardItemsList = inwardItemsRepository.findByInwardRegisterId(inwardRegister.getId());

	            // Prepare a map to hold InwardRegister and its associated InwardItems
	            Map<String, Object> inwardRegisterMap = new HashMap<>();
	            inwardRegisterMap.put("id", inwardRegister.getId());
	            inwardRegisterMap.put("inwardNumber", inwardRegister.getInwardNumber());
	            inwardRegisterMap.put("weaponName", inwardRegister.getWeaponName());
	            inwardRegisterMap.put("date", inwardRegister.getDate());
	            inwardRegisterMap.put("description", inwardRegister.getDescription());
	            inwardRegisterMap.put("quantity", inwardRegister.getQuantity());
	            inwardRegisterMap.put("inwardItemsList", inwardItemsList);

	            // Add the InwardRegister map to the response
	            response.put("inwardRegister" + inwardRegister.getId(), inwardRegisterMap);
	        }

	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 */
	 
	 
	 /* @PostMapping("/get_inward_data_by_date")
	    public ResponseEntity<List<InwardRegister>> getDataByDate(@RequestParam String date) {
	        // Assuming date is in format yyyy-MM-dd

	        // Retrieve InwardRegister data by date
	        List<InwardRegister> inwardRegisterList = inwardRegisterRepository.findByDate(date);

	        // Retrieve InwardItems data by date
	        List<InwardItems> inwardItemsList = inwardItemsRepository.findByDate(date);

	        // Associate InwardItems with their respective InwardRegister
	        for (InwardRegister inwardRegister : inwardRegisterList) {
	            List<InwardItems> associatedItems = inwardItemsRepository.findByInwardRegister(inwardRegister);
	            inwardRegister.setInwardItemsList(associatedItems);
	        }

	        return new ResponseEntity<>(inwardRegisterList, HttpStatus.OK);
	    }
	 */
	
	 
	 
	/* 
//working
	  @PostMapping("/save_inward_register")
	    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest requestData) {
	        // Parse data from request
	        InwardRegister inwardRegister = requestData.getInwardRegister();
	        List<InwardItems> inwardItemsList = requestData.getInwardItemsList();

	        // Set relationship between InwardRegister and InwardItems
	        for (InwardItems inwardItems : inwardItemsList) {
	            inwardItems.setInwardRegister(inwardRegister);
	        }
	        inwardRegister.setInwardItemsList(inwardItemsList);

	        // Save InwardRegister and InwardItems
	        inwardRegisterRepository.save(inwardRegister);

	        // InwardItems will be saved automatically due to CascadeType.ALL

	        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
	    }

	 */
	 
	/* 
	  @PostMapping("/save_inward_register")
	    public ResponseEntity<?> saveInwardData(@RequestBody InwardRegister inwardDataDTO) {
	        try {
	            // Create InwardRegister entity
	            InwardRegister inwardRegister = new InwardRegister();
	            inwardRegister.setWeaponName(inwardDataDTO.getWeaponName());
	            inwardRegister.setDate(inwardDataDTO.getDate());
	            inwardRegister.setDescription(inwardDataDTO.getDescription());
	            inwardRegister.setRound(inwardDataDTO.getRound());
	            inwardRegister.setQuantity(inwardDataDTO.getQuantity());

	            // Save InwardRegister entity
	            inwardRegister = inwardRegisterRepository.save(inwardRegister);

	            // Create InwardItems entity
	            InwardItems inwardItems = new InwardItems();
	            inwardItems.setInwardNumber(String.valueOf(inwardRegister.getId()));
	            inwardItems.setButtNo(inwardDataDTO.getButtNo());
	            inwardItems.setManufacturingNo(inwardDataDTO.getManufacturingNo());
	            inwardItems.setCount(inwardDataDTO.getCount());
	            inwardItems.setCreatedAt(inwardDataDTO.getCreatedAt());

	            // Save InwardItems entity
	            inwardItemsRepository.save(inwardItems);

	            return ResponseEntity.status(HttpStatus.CREATED).body("Inward data saved successfully");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving inward data: " + e.getMessage());
	        }
	    }
	 */
	 
	 
	 
	/*
	 @PostMapping("/save_inward_register1")
	    public ResponseEntity<?> saveInwardRegister(@RequestBody InwardRegister inwardRegister) {
	        try {
	            // Save the InwardRegister entity
	            inwardRegisterRepository.save(inwardRegister);
	            
	            // Return success response
	            return ResponseEntity.status(HttpStatus.CREATED).body("Inward Register saved successfully");
	        } catch (Exception e) {
	            // Return error response if there's an exception
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Inward Register: " + e.getMessage());
	        }
	    }
*/

