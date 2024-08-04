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
import com.example.WeaponArmaryManagementSystem.model.OutwardItems;
import com.example.WeaponArmaryManagementSystem.model.OutwardRegister;
import com.example.WeaponArmaryManagementSystem.model.PrefixConfig;
import com.example.WeaponArmaryManagementSystem.model.PrefixConfig1;
import com.example.WeaponArmaryManagementSystem.model.SaveDataRequest;
import com.example.WeaponArmaryManagementSystem.model.SaveDataRequest1;
import com.example.WeaponArmaryManagementSystem.repository.OutwardItemsRepository;
import com.example.WeaponArmaryManagementSystem.repository.OutwardRegisterRepository;
import com.example.WeaponArmaryManagementSystem.repository.PrefixConfig1Repository;

@RestController
@CrossOrigin(origins = "*")
public class OutwardController {

    private OutwardRegisterRepository outwardRegisterRepository;
    private OutwardItemsRepository outwardItemsRepository;
    private PrefixConfig1Repository prefixConfig1Repository;

    @Autowired
    public OutwardController(OutwardRegisterRepository outwardRegisterRepository,
                             OutwardItemsRepository outwardItemsRepository,
                             PrefixConfig1Repository prefixConfig1Repository) {
        this.outwardRegisterRepository = outwardRegisterRepository;
        this.outwardItemsRepository = outwardItemsRepository;
        this.prefixConfig1Repository = prefixConfig1Repository;
  
 }
   
    
    @PostMapping("/getOutwardRegi_bydept_id")
    public ResponseEntity<?> getAllOutwardRegisters(@RequestParam String departmentId) {
        try {
            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
            return ResponseEntity.ok(outwardRegisters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
        }
    }
    
    /*
    @PostMapping("/getOutwardRegi_bydept_id")
    public ResponseEntity<?> getAllOutwardRegisters(@RequestBody String departmentId) {
        try {
            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
            return ResponseEntity.ok(outwardRegisters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
        }
    }
    
  
    @PostMapping("/getOutwardRegi_bydept_id")
    public ResponseEntity<?> getAllOutwardRegisters(@RequestParam String departmentId) {
        try {
            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
            return ResponseEntity.ok(outwardRegisters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
        }
    }
*/
    
    
    private String generateOutwardNumber(String departmentId) {
        PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "";

        if (!prefix.isEmpty() && !prefix.endsWith("/")) {
            prefix += "/";
        }

        OutwardRegister lastEntry = outwardRegisterRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
        int nextNumber = 1;

        if (lastEntry != null) {
            String lastOutwardNumber = lastEntry.getOutwardNumber();
            if (lastOutwardNumber != null && lastOutwardNumber.startsWith(prefix)) {
                String numberPart = lastOutwardNumber.substring(prefix.length());
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

    
    @PostMapping("/save_outward_register")
    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData) {
    	OutwardRegister outwardRegister = requestData.getOutwardRegister();
        List<String[]> outwardItemsArray = requestData.getOutwardItemsList();

        String departmentId = outwardRegister.getDepartmentId();
        String outwardNumber = generateOutwardNumber(departmentId);
        outwardRegister.setOutwardNumber(outwardNumber);

        outwardRegisterRepository.save(outwardRegister);

        for (String[] item : outwardItemsArray) {
            OutwardItems outwardItems = new OutwardItems();
            outwardItems.setOutwardRegister(outwardRegister);
            outwardItems.setButtNo(item[0]);
            outwardItems.setManufacturingNo(item[1]);
            outwardItems.setCount(item[2]);
           // inwardItems.setDepartmentId(item[3]);
            //inwardItems.setInwardNumber(inwardNumber);
            outwardItemsRepository.save(outwardItems);
        }
        return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
    }


	 @PostMapping("/getByOutwardNumberfromregister1")
	 public ResponseEntity<?> getByOutwardNumber1(@RequestParam String outwardNumber) {
	     try {
	         // Fetch records from outwardRegister by outwardNumber
	         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

	         if (outwardRegisters.isEmpty()) {
	             return ResponseEntity.notFound().build();
	         } else {
	             // Get the first InwardRegister entity (assuming inwardNumber is unique)
	        	 OutwardRegister outwardRegister = outwardRegisters.get(0);

	             // Fetch related OutwardItems using outwardRegister's ID
	             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

	             return ResponseEntity.ok(outwardItems);
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }
	 
	 
	  
	  
	    
	    @PostMapping("/savePrefixConfigoutward")
	    public ResponseEntity<String> savePrefixConfig(@RequestBody PrefixConfig1 prefixConfig) {
	        try {
	            prefixConfig1Repository.save(prefixConfig);
	            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully\",\"Id\": 0}");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data not saved successfully\",\"Id\": 1}" + e.getMessage());
	        }
	    }
	    
   
	   
	    @PostMapping("/getLatestPrefix1bydeptid/{departmentId}")
	    public ResponseEntity<String> getLatestPrefix1(@PathVariable String departmentId) {
	        try {
	            PrefixConfig1 latestPrefixConfig = prefixConfig1Repository.findTopByDepartmentIdOrderByIdDesc(departmentId);
	            if (latestPrefixConfig != null) {
	                return ResponseEntity.ok("{\"prefix\": \"" + latestPrefixConfig.getPrefix() + "\"}");
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No prefix found for the given departmentId\",\"Id\": 1}");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error fetching prefix\",\"Id\": 1} " + e.getMessage());
	        }
	    }
	    
	    
	    
	    @PostMapping("/getByBetweenDate_outward")
	    public ResponseEntity<?> getByDateRangeAndDepartment(
	            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
	            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate,
	            @RequestParam("departmentId") String departmentId) {
	        try {
	            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDateBetweenAndDepartmentId(startDate, endDate, departmentId);

	            if (outwardRegisters.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body(Collections.singletonMap("message", "No records found for the given date range and department ID."));
	            } else {
	                return ResponseEntity.ok(outwardRegisters);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
	    
	   /*
	    @PostMapping("/getAllOutwardRegisters")
	    public ResponseEntity<?> getAllOutwardRegisters() {
	        try {
	            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findAllOrderByIdDesc();
	            return ResponseEntity.ok(outwardRegisters);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	        }
	    }
*/

	 
	 @PostMapping("/getByDate_outward")
	 public ResponseEntity<?> getByDate(
	         @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
	     try {
	         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDate(date.toString());

	         if (outwardRegisters.isEmpty()) {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                     .body(Collections.singletonMap("message", "No records found for the given date."));
	         } else {
	             return ResponseEntity.ok(outwardRegisters);
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }
	 
	 

	 @PostMapping("/getPrefixOutward")
	 public ResponseEntity<String> getCurrentPrefix() {
	     PrefixConfig1 prefixConfig1 = prefixConfig1Repository.findTopByOrderByIdDesc();
	     if (prefixConfig1 != null) {
	         return ResponseEntity.ok("{\"prefix\": \"" + prefixConfig1.getPrefix() + "\"}");
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
	 
	 
	 
	 
	 @PostMapping("/getByOutwardNumberfromregister")
	 public ResponseEntity<?> getByOutwardNumber(@RequestParam String outwardNumber) {
	     try {
	         // Fetch records from outwardRegister by outwardNumber
	         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

	         if (outwardRegisters.isEmpty()) {
	             return ResponseEntity.notFound().build();
	         } else {
	             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
	             OutwardRegister outwardRegister = outwardRegisters.get(0);
	             return ResponseEntity.ok(outwardRegister);
	         }
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
	     }
	 }
	}
	 /*
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
    */
    
	
   
    

    
    /*
    @PostMapping("/getOutwardRegister_bydept_id")
    public ResponseEntity<?> getAllOutwardRegisters(@RequestParam String departmentId) {
        try {
            List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDepartmentIdOrderByIdDesc(departmentId);
            return ResponseEntity.ok(outwardRegisters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
        }
    }
    */
  
    

   
/*
    private String generateOutwardNumber() {
        // Fetch the latest prefix from the database
        PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByOrderByIdDesc();
        String prefix = prefixConfig != null ? prefixConfig.getPrefix() : "";

        // Find the last outward register entry to determine the next number
        OutwardRegister lastEntry = outwardRegisterRepository.findTopByOrderByIdDesc();
        int nextNumber;
        if (lastEntry == null || lastEntry.getOutwardNumber().length() <= prefix.length()) {
            nextNumber = 1;
        } else {
            try {
                nextNumber = Integer.parseInt(lastEntry.getOutwardNumber().substring(prefix.length())) + 1;
            } catch (NumberFormatException e) {
                nextNumber = 1; // Default to 1 if parsing fails
            }
        }

        // Format the number part to be 4 digits with leading zeros
        String formattedNumber = String.format("%04d", nextNumber);

        return prefix + formattedNumber;
    }
*/
    
  /*  
    @PostMapping("/save_outward_register")
    public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
        // Validate incoming request data
        if (requestData1 == null || requestData1.getOutwardRegister() == null || requestData1.getOutwardItemsList() == null) {
            return new ResponseEntity<>("{\"message\": \"Invalid request data\"}", HttpStatus.BAD_REQUEST);
        }

        // Parse data from request
        OutwardRegister outwardRegister = requestData1.getOutwardRegister();
        List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

        // Generate Outward Number
        outwardRegister.setOutwardNumber(generateOutwardNumber());

        // Save OutwardRegister
        OutwardRegister savedOutwardRegister = outwardRegisterRepository.save(outwardRegister);

        // Save OutwardItems
        for (String[] item : outwardItemsArray) {
            if (item.length < 3) {
                return new ResponseEntity<>("{\"message\": \"Data Not Saved...\",\"Id\": 1}", HttpStatus.BAD_REQUEST);
            }
            OutwardItems outwardItems = new OutwardItems();
            outwardItems.setOutwardRegister(savedOutwardRegister); // Use the saved OutwardRegister
            outwardItems.setButtNo(item[0]);
            outwardItems.setManufacturingNo(item[1]);
            outwardItems.setCount(item[2]);
            outwardItemsRepository.save(outwardItems);
        }

        return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
    }*/

 ///("{\"message\": \"Failed to save weapon data\",\"Id\": 1}");
 
    
    

 
/*
 @PostMapping("/update_prefix_outward")
 public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
     PrefixConfig1 prefixConfig1 = prefixConfig1Repository.findTopByOrderByIdDesc();
     if (prefixConfig1 != null) {
         prefixConfig1.setPrefix(newPrefix);
     } else {
         prefixConfig1 = new PrefixConfig1();
         prefixConfig1.setPrefix(newPrefix);
     }
     prefixConfig1Repository.save(prefixConfig1);
     return new ResponseEntity<>("{\"message\": \"Prefix updated successfully\", \"id\": 0}", HttpStatus.OK);
 }
 */
 



 
 
 /*
@PostMapping("/getByBetweenDate_outward")
public ResponseEntity<?> getByDateRange(
       @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String startDate,
       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String endDate) {
   try {
       List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByDateBetween(startDate.toString(), endDate.toString());

       if (outwardRegisters.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(Collections.singletonMap("message", "No records found for the given date range."));
       } else {
           return ResponseEntity.ok(outwardRegisters);
       }
   } catch (Exception e) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
   }
}
*/
 
 
 /*
 @PostMapping("/getByOutwardNumberfromregister1")
 public ResponseEntity<?> getByOutwardNumber1(@RequestParam String outwardNumber) {
     try {
         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
             OutwardRegister outwardRegister = outwardRegisters.get(0);

             // Fetch related OutwardItems using outwardRegister's ID
             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

             return ResponseEntity.ok(outwardItems);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }*/
 




/*
private String generateOutwardNumber(String departmentId) {
    PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByDepartmentIdOrderByIdDesc(departmentId);
    String prefix = (prefixConfig != null && prefixConfig.getPrefix() != null) ? prefixConfig.getPrefix() : "";

    if (!prefix.isEmpty() && !prefix.endsWith("/")) {
        prefix += "/";
    }

    OutwardRegister lastEntry = outwardRegisterRepository.findTopByDepartmentIdOrderByIdDesc(departmentId);
    int nextNumber = 1;

    if (lastEntry != null) {
        String lastOutwardNumber = lastEntry.getOutwardNumber();
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

//@Transactional
@PostMapping("/save_outward_register")
public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData) {
    try {
        OutwardRegister outwardRegister = requestData.getOutwardRegister();
        List<String[]> outwardItemsArray = requestData.getOutwardItemsList();

        String departmentId = outwardRegister.getDepartmentId();
        String outwardNumber = generateOutwardNumber(departmentId);
        outwardRegister.setOutwardNumber(outwardNumber);

        outwardRegisterRepository.save(outwardRegister);

        for (String[] item : outwardItemsArray) {
            OutwardItems outwardItems = new OutwardItems();
            outwardItems.setOutwardRegister(outwardRegister);
            outwardItems.setButtNo(item[0]);
            outwardItems.setManufacturingNo(item[1]);
            outwardItems.setCount(item[2]);
            outwardItemsRepository.save(outwardItems);
        }

        return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("{\"message\": \"Data not saved successfully\",\"Id\": 1,\"error\":\"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/



/*
//working
@PostMapping("/getAllOutwardRegisters")
public ResponseEntity<?> getAllOutwardRegisters() {
    try {
        // Fetch all records from outwardRegister
        List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findAll();
        
        return ResponseEntity.ok(outwardRegisters);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
    }
}
*/


/*
private String generateOutwardNumber() {
    // Fetch the latest prefix from the database
    PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByOrderByIdDesc();
    String prefix = prefixConfig != null ? prefixConfig.getPrefix() : "DEFAULT";

    // Find the last outward register entry to determine the next number
    OutwardRegister lastEntry = outwardRegisterRepository.findTopByOrderByIdDesc();
    int nextNumber;
    if (lastEntry == null || lastEntry.getOutwardNumber().length() <= prefix.length()) {
        nextNumber = 1;
    } else {
        try {
            nextNumber = Integer.parseInt(lastEntry.getOutwardNumber().substring(prefix.length())) + 1;
        } catch (NumberFormatException e) {
            nextNumber = 1; // Default to 1 if parsing fails
        }
    }

    return prefix + nextNumber;
}





@PostMapping("/save_outward_register")
public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
    // Validate incoming request data
    if (requestData1 == null || requestData1.getOutwardRegister() == null || requestData1.getOutwardItemsList() == null) {
        return new ResponseEntity<>("{\"message\": \"Invalid request data\"}", HttpStatus.BAD_REQUEST);
    }

    // Parse data from request
    OutwardRegister outwardRegister = requestData1.getOutwardRegister();
    List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

    // Generate Outward Number
    outwardRegister.setOutwardNumber(generateOutwardNumber());

    // Save OutwardRegister
    OutwardRegister savedOutwardRegister = outwardRegisterRepository.save(outwardRegister);

    // Save OutwardItems
    for (String[] item : outwardItemsArray) {
        if (item.length < 3) {
            return new ResponseEntity<>("{\"message\": \"Data Not Saved...\",\"Id\": 1}", HttpStatus.BAD_REQUEST);
        }
        OutwardItems outwardItems = new OutwardItems();
        outwardItems.setOutwardRegister(savedOutwardRegister); // Use the saved OutwardRegister
        outwardItems.setButtNo(item[0]);
        outwardItems.setManufacturingNo(item[1]);
        outwardItems.setCount(item[2]);
        outwardItemsRepository.save(outwardItems);
    }

    return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.OK);
}



 

@PostMapping("/update_prefix_outward")
public ResponseEntity<String> updatePrefix(@RequestBody String newPrefix) {
    PrefixConfig1 prefixConfig1 = prefixConfig1Repository.findTopByOrderByIdDesc();
    if (prefixConfig1 != null) {
        prefixConfig1.setPrefix(newPrefix);
    } else {
        prefixConfig1 = new PrefixConfig1();
        prefixConfig1.setPrefix(newPrefix);
    }
    prefixConfig1Repository.save(prefixConfig1);
    return new ResponseEntity<>("{\"message\": \"Prefix updated successfully\" id:0\"}",HttpStatus.OK);
}

*/



/* 
//new code
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false)
                  .setUseTrailingSlashMatch(false)
                  .setUrlPathHelper(new UrlPathHelper() {{
                      setRemoveSemicolonContent(false);
                      setUrlDecode(false); // Disable default URL decoding
                  }});
    }
}
*/

 
 /*
 //working but square bracket in json
 @PostMapping("/getByOutwardNumberfromregister")
 public ResponseEntity<?> getByOutwardNumber(@RequestParam String outwardNumber) {
     try {
         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             return ResponseEntity.ok(outwardRegisters);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }*/
 

 /*
 //working
 @PostMapping("/getByOutwardNumberfromregister/{outwardNumber}")
 public ResponseEntity<?> getByOutwardNumber(@PathVariable String outwardNumber) {
     try {
         // Fetch records from OutwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Return the first OutwardRegister entity
             OutwardRegister outwardRegister = outwardRegisters.get(0);
             return ResponseEntity.ok(outwardRegister);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }*/
 
 
 
 /*
 //working 
  private String generateOutwardNumber() {
      // Fetch the latest prefix from the database
      PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByOrderByIdDesc();
      String prefix = prefixConfig != null ? prefixConfig.getPrefix() : "DEFAULT";

      // Find the last outward register entry to determine the next number
      OutwardRegister lastEntry = outwardRegisterRepository.findTopByOrderByIdDesc();
      int nextNumber = lastEntry == null ? 1 : Integer.parseInt(lastEntry.getOutwardNumber().substring(prefix.length())) + 1;

      return prefix + nextNumber;
  }



  @PostMapping("/save_outward_register")
  public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
      // Parse data from request
      OutwardRegister outwardRegister = requestData1.getOutwardRegister();
      List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

      // Generate Outward Number
      outwardRegister.setOutwardNumber(generateOutwardNumber());

      // Save OutwardRegister
      OutwardRegister savedOutwardRegister = outwardRegisterRepository.save(outwardRegister);

      // Save OutwardItems
      for (String[] item : outwardItemsArray) {
          OutwardItems outwardItems = new OutwardItems();
          outwardItems.setOutwardRegister(savedOutwardRegister); // Use the saved OutwardRegister
          outwardItems.setButtNo(item[0]);
          outwardItems.setManufacturingNo(item[1]);
          outwardItems.setCount(item[2]);
          outwardItemsRepository.save(outwardItems);
      }

      return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": " + savedOutwardRegister.getId() + "}", HttpStatus.OK);
  }
  */
  
 
 
 /*
 //working
@PostMapping("/getByOutwardNumberfromregister/{outwardNumber}")
public ResponseEntity<?> getByOutwardNumber(@PathVariable String outwardNumber) {
   try {
       // Fetch records from OutwardRegister by outwardNumber
       List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);
       
       if (outwardRegisters.isEmpty()) {
           return ResponseEntity.notFound().build();
       } else {
           return ResponseEntity.ok(outwardRegisters);
       }
   } catch (Exception e) {
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
   }
}*/





 
 
 
 
 /* private String generateOutwardNumber() {
      // Fetch the latest prefix from the database
  	PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByOrderByIdDesc();
      String prefix = prefixConfig != null ? prefixConfig.getPrefix() : "DEFAULT";

      // Find the last outward register entry to determine the next number
      OutwardRegister lastEntry = outwardRegisterRepository.findTopByOrderByIdDesc();
      int nextNumber = lastEntry == null ? 1 : Integer.parseInt(lastEntry.getOutwardNumber().substring(prefix.length())) + 1;

      return prefix + nextNumber;
  }

  @PostMapping("/save_outward_register")
  public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
      // Parse data from request
      OutwardRegister outwardRegister = requestData1.getOutwardRegister();
      List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

      // Generate Outward Number
      outwardRegister.setOutwardNumber(generateOutwardNumber());

      // Save OutwardRegister
      OutwardRegister savedOutwardRegister = outwardRegisterRepository.save(outwardRegister);

      // Save OutwardItems
      for (String[] item : outwardItemsArray) {
          OutwardItems outwardItems = new OutwardItems();
          outwardItems.setOutwardRegister(savedOutwardRegister); // Use the saved OutwardRegister
          outwardItems.setButtNo(item[0]);
          outwardItems.setManufacturingNo(item[1]);
          outwardItems.setCount(item[2]);
          outwardItemsRepository.save(outwardItems);
      }

      return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": " + savedOutwardRegister.getId() + "}", HttpStatus.OK);
  }


  @PostMapping("/update_prefix_outward")
  public ResponseEntity<String> updateOutwardPrefix(@RequestBody String newPrefix) {
      PrefixConfig1 prefixConfig1 = prefixConfig1Repository.findTopByOrderByIdDesc();
      if (prefixConfig1 != null) {
          prefixConfig1.setPrefix(newPrefix);
      } else {
          prefixConfig1 = new PrefixConfig1();
          prefixConfig1.setPrefix(newPrefix);
      }
      prefixConfig1Repository.save(prefixConfig1);
      return new ResponseEntity<>("{\"message\": \"Outward prefix updated successfully\"}", HttpStatus.OK);
  }


  @PostMapping("/get_prefix_outward")
  public ResponseEntity<String> getCurrentOutwardPrefix() {
      PrefixConfig1 prefixConfig = prefixConfig1Repository.findTopByOrderByIdDesc();
      if (prefixConfig != null) {
          return ResponseEntity.ok("{\"prefix\": \"" + prefixConfig.getPrefix() + "\"}");
      } else {
          return ResponseEntity.notFound().build();
      }
  }*/
  
  


/*
//working
@PostMapping("/save_outward_register")
public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
   // Parse data from request
   OutwardRegister outwardRegister = requestData1.getOutwardRegister();
   List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

   // Save OutwardRegister (Outward number will be auto-generated)
   OutwardRegister savedOutwardRegister = outwardRegisterRepository.save(outwardRegister);

   // Save OutwardItems
   for (String[] item : outwardItemsArray) {
       OutwardItems outwardItems = new OutwardItems();
       outwardItems.setOutwardRegister(savedOutwardRegister); // Use the saved OutwardRegister
       outwardItems.setButtNo(item[0]);
       outwardItems.setManufacturingNo(item[1]);
       outwardItems.setCount(item[2]);
       outwardItemsRepository.save(outwardItems);
   }

   return new ResponseEntity<>("{\"message\": \"Data saved successfully\",\"Id\": " + savedOutwardRegister.getId() + "}", HttpStatus.OK);
}*/




/*
@PostMapping("/save_outward_register")
  public ResponseEntity<String> saveData(@RequestBody SaveDataRequest1 requestData1) {
      // Parse data from request
      OutwardRegister outwardRegister = requestData1.getOutwardRegister();
      List<String[]> outwardItemsArray = requestData1.getOutwardItemsList();

      // Save InwardRegister
      outwardRegisterRepository.save(outwardRegister);

      // Save InwardItems
      for (String[] item : outwardItemsArray) {
          OutwardItems outwardItems = new OutwardItems();
          outwardItems.setOutwardRegister(outwardRegister);
          outwardItems.setButtNo(item[0]);
          outwardItems.setManufacturingNo(item[1]);
          outwardItems.setCount(item[2]);
          //inwardItems.setLocation(item[3]);
         // inwardItems.setDate(LocalDateTime.now());
          outwardItemsRepository.save(outwardItems);
      }

      return new ResponseEntity<>("{\"message\": \" Data saved successfully\",\"Id\": 0}", HttpStatus.OK);
  }
*/

 
 
 
 
 
 /*
 //working ok but nout use
 @PostMapping("/getByOutwardNumberfromregister1")
 public ResponseEntity<?> getByOutwardNumber1(@RequestParam String outwardNumber) {
     try {
         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
             OutwardRegister outwardRegister = outwardRegisters.get(0);

             // Fetch related OutwardItems using outwardRegister's ID
             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

             // Create response object
             OutwardRegisterResponse response = new OutwardRegisterResponse();
             response.setOutwardRegister(outwardRegister);
             response.setOutwardItems(outwardItems);

             return ResponseEntity.ok(response);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }*/
 
 
 
 
 /* 
  //working half 
  
 @PostMapping("/getByOutwardNumberfromregister1")
 public ResponseEntity<?> getByOutwardNumber1(@RequestParam String outwardNumber) {
     try {
         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
             OutwardRegister outwardRegister = outwardRegisters.get(0);

             // Fetch related OutwardItems using outwardRegister's ID
             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

             // Create response object
             OutwardRegisterResponse response = new OutwardRegisterResponse();
             response.setOutwardRegister(outwardRegister);
             response.setOutwardItems(outwardItems);

             return ResponseEntity.ok(response);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }
 */
 
 
 
/* working half
@PostMapping("/getByOutwardNumberfromregister1")
public ResponseEntity<?> getByOutwardNumber1(@RequestParam String outwardNumber) {
    try {
        // Fetch records from outwardRegister by outwardNumber
        List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

        if (outwardRegisters.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            // Get the first OutwardRegister entity (assuming outwardNumber is unique)
            OutwardRegister outwardRegister = outwardRegisters.get(0);

            // Fetch related OutwardItems using outwardRegister's ID
            List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

            return ResponseEntity.ok(outwardItems);
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
    }
} 
 */
 
 

 /*
 @PostMapping("/getByOutwardNumberfromregister1/{outwardNumber}")
 public ResponseEntity<?> getByOutwardNumber1(@PathVariable String outwardNumber) {
     try {
         // Decode the outwardNumber to handle encoded slashes
         String decodedOutwardNumber = UriUtils.decode(outwardNumber, StandardCharsets.UTF_8);

         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(decodedOutwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
             OutwardRegister outwardRegister = outwardRegisters.get(0);

             // Fetch related OutwardItems using outwardRegister's ID
             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

             return ResponseEntity.ok(outwardItems);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }*/
 
 
 
 
/*
 //working ok
 @PostMapping("/getByOutwardNumberfromregister1/{outwardNumber}")
 public ResponseEntity<?> getByOutwardNumber1(@PathVariable String outwardNumber) {
     try {
         // Fetch records from outwardRegister by outwardNumber
         List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

         if (outwardRegisters.isEmpty()) {
             return ResponseEntity.notFound().build();
         } else {
             // Get the first OutwardRegister entity (assuming outwardNumber is unique)
             OutwardRegister outwardRegister = outwardRegisters.get(0);

             // Fetch related OutwardItems using outwardRegister's ID
             List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());

             return ResponseEntity.ok(outwardItems);
         }
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
     }
 }
 */
 
 
 /*
 //working
 @PostMapping("/getByOutwardNumberfromregister1/{outwardNumber}")
   public ResponseEntity<?> getByOutwardNumber1(@PathVariable String outwardNumber) {
       try {
           // Fetch records from outwardRegister by outwardNumber
           List<OutwardRegister> outwardRegisters = outwardRegisterRepository.findByOutwardNumber(outwardNumber);

           if (outwardRegisters.isEmpty()) {
               return ResponseEntity.notFound().build();
           } else {
               // Get the first OutwardRegister entity (assuming outwardNumber is unique)
               OutwardRegister outwardRegister = outwardRegisters.get(0);
               
               // Fetch related InwardItems using outwardRegister's ID
               List<OutwardItems> outwardItems = outwardItemsRepository.findByOutwardRegisterId(outwardRegister.getId());
               
               return ResponseEntity.ok(outwardItems);
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Collections.singletonMap("error", "Failed to fetch data: " + e.getMessage()));
       }
   }*/

	 

