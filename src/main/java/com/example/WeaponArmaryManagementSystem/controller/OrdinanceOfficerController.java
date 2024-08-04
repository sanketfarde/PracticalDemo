package com.example.WeaponArmaryManagementSystem.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.WeaponArmaryManagementSystem.model.OrdinanceOfficer;
import com.example.WeaponArmaryManagementSystem.repository.OrdinanceOfficerRepository;
import com.example.WeaponArmaryManagementSystem.repository.OrdinanceOfficerReturnDataTotalRepository;

@RestController
@CrossOrigin(origins = "*")
public class OrdinanceOfficerController {


/*    @Autowired
    private OrdinanceOfficerReturndataRepository ordinanceOfficerReturndataRepository;
*/
	
    @Autowired
    private OrdinanceOfficerReturnDataTotalRepository ordinanceOfficerReturnDataTotalRepository;


    private final OrdinanceOfficerRepository ordinanceOfficerRepository;
    @Autowired
    public OrdinanceOfficerController(OrdinanceOfficerRepository ordinanceOfficerRepository) {
        this.ordinanceOfficerRepository = ordinanceOfficerRepository;
    }
     
    
  
    
    //save ordinance distribution data
    @PostMapping("/save_ordinance_officers")
    public ResponseEntity<String> saveOrdinanceOfficer(@RequestBody OrdinanceOfficer ordinanceOfficer) {
      ordinanceOfficerRepository.save(ordinanceOfficer);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id=0\"}");
    }

    
    //save return ordinance data
    @PostMapping("/save_ordinance_return_data")
    public ResponseEntity<String> saveOrdinanceReturnData(@RequestBody OrdinanceOfficer ordinanceOfficer) {
        ordinanceOfficerRepository.save(ordinanceOfficer);
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data Saved Successfully...\",\"Id\":0}");
    }
    
    
    //05/06/2024 by vikas as per viplav
    // Get all data where receivedDate is present
    @PostMapping("/all_ordinance_officers_return_data")
    public ResponseEntity<List<OrdinanceOfficer>> getOrdinanceOfficersWithReceivedDate() {
        List<OrdinanceOfficer> officers = ordinanceOfficerRepository.findByReceivedDateIsNotNull();
        return ResponseEntity.ok(officers);
    }
    
    
    //show all saved data of distribution 
    @PostMapping("/all_distribution_ordinance_data")
    public List<OrdinanceOfficer> getOrdinanceOfficersWithoutReceivedDate() {
        return ordinanceOfficerRepository.findByReceivedDateIsNull();
    }

    // Get data between two receivedDate values
    @PostMapping("/ordinance_return_data_between_dates")
    public ResponseEntity<List<OrdinanceOfficer>> getOrdinanceOfficersBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
        List<OrdinanceOfficer> officers = ordinanceOfficerRepository.findByReceivedDateBetween(startDate, endDate);
        return ResponseEntity.ok(officers);
}
    
    //show data of particular record
    @PostMapping("/ordinance_officer_by_id/{id}")
    public ResponseEntity<?> getOrdinanceOfficerById(@PathVariable Long id) {
        Optional<OrdinanceOfficer> ordinanceOfficer = ordinanceOfficerRepository.findById(id);
        if (ordinanceOfficer.isPresent()) {
            return new ResponseEntity<>(ordinanceOfficer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"message\": \"Ordinance Officer not found...\",\"Id\":1}", HttpStatus.NOT_FOUND);
        }
    }



   /* @PostMapping("/save_ordinance_return_data")
    public ResponseEntity<String> saveOrdinanceReturnData(@RequestBody OrdinanceOfficerReturndata ordinanceOfficerReturndata) {
        // Save the ordinance officer return data
        ordinanceOfficerReturndataRepository.save(ordinanceOfficerReturndata);

        // Compute new totals from the input data, handling nulls
        long bionetTotal = parseIntOrDefault(ordinanceOfficerReturndata.getBionet(), 0);
        long magzineTotal = parseIntOrDefault(ordinanceOfficerReturndata.getMagzine(), 0);
        long fillerTotal = parseIntOrDefault(ordinanceOfficerReturndata.getFiller(), 0);
        long launcherTotal = parseIntOrDefault(ordinanceOfficerReturndata.getLauncher(), 0);

        // Fetch existing totals from the database
        OrdinanceOfficerReturndataTotal ordinanceOfficerReturnDataTotal = ordinanceOfficerReturnDataTotalRepository.findById(1L)
                .orElse(new OrdinanceOfficerReturndataTotal());

        // Update totals, handling nulls
        ordinanceOfficerReturnDataTotal.setBionetTotal(String.valueOf(parseIntOrDefault(ordinanceOfficerReturnDataTotal.getBionetTotal(), 0) + bionetTotal));
        ordinanceOfficerReturnDataTotal.setMagzineTotal(String.valueOf(parseIntOrDefault(ordinanceOfficerReturnDataTotal.getMagzineTotal(), 0) + magzineTotal));
        ordinanceOfficerReturnDataTotal.setFillerTotal(String.valueOf(parseIntOrDefault(ordinanceOfficerReturnDataTotal.getFillerTotal(), 0) + fillerTotal));
        ordinanceOfficerReturnDataTotal.setLauncherTotal(String.valueOf(parseIntOrDefault(ordinanceOfficerReturnDataTotal.getLauncherTotal(), 0) + launcherTotal));

        // Update other fields
        ordinanceOfficerReturnDataTotal.setBuckleNo(ordinanceOfficerReturndata.getBuckleNo());
        ordinanceOfficerReturnDataTotal.setWeaponIssue(ordinanceOfficerReturndata.getWeaponIssue());
        ordinanceOfficerReturnDataTotal.setRemark(ordinanceOfficerReturndata.getRemark());
        ordinanceOfficerReturnDataTotal.setReceivedDate(ordinanceOfficerReturndata.getReceivedDate());
        ordinanceOfficerReturnDataTotal.setReceivedWeaponCondition(ordinanceOfficerReturndata.getReceivedWeaponCondition());
        ordinanceOfficerReturnDataTotal.setSubmittedWeapon(ordinanceOfficerReturndata.getSubmittedWeapon());
        ordinanceOfficerReturnDataTotal.setCheckedBy(ordinanceOfficerReturndata.getCheckedBy());
        ordinanceOfficerReturnDataTotal.setIsWeaponDamage(ordinanceOfficerReturndata.getIsWeaponDamage());
        ordinanceOfficerReturnDataTotal.setPlaceOfDuty(ordinanceOfficerReturndata.getPlaceOfDuty());

        // Save the updated total data
        ordinanceOfficerReturnDataTotalRepository.save(ordinanceOfficerReturnDataTotal);

        // Return response
        return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data Saved Successfully...\",\"Id\":0 }");
    }

*/
   /*

   @PostMapping ("/all_return_ordinance_data")
   public ResponseEntity<OrdinanceOfficerReturnDataResponse> getAllOrdinanceOfficers() {
       try {
           List<OrdinanceOfficerReturndata> officers = ordinanceOfficerReturndataRepository.findAllByReceivedDateIsNotNull();
           if (officers.isEmpty()) {
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }

           // Sort officers by reverse ID
           officers.sort(Comparator.comparingLong(OrdinanceOfficerReturndata::getId).reversed());

           List<OrdinanceOfficerReturnDataDTO> result = new ArrayList<>();
           for (OrdinanceOfficerReturndata officer : officers) {
               OrdinanceOfficerReturnDataDTO dto = new OrdinanceOfficerReturnDataDTO();
               dto.setId(officer.getId());
               dto.setRegion(officer.getRegion());
               dto.setPlaceOfDuty(officer.getPlaceOfDuty());
               dto.setPoliceOfficer(officer.getPoliceOfficer());
               dto.setButtNumber(officer.getButtNumber());
               dto.setWeaponType(officer.getWeaponType());
               dto.setBionet(officer.getBionet());
               dto.setMagzine(officer.getMagzine());
               dto.setFiller(officer.getFiller());
               dto.setLauncher(officer.getLauncher());
               dto.setWeaponDate(officer.getWeaponDate());
               dto.setReceivedWeaponCondition(officer.getReceivedWeaponCondition());
               dto.setSubmittedWeapon(officer.getSubmittedWeapon());
               dto.setCheckedBy(officer.getCheckedBy());
               dto.setIsWeaponDamage(officer.getIsWeaponDamage());
               dto.setReceivedDate(officer.getReceivedDate());
               dto.setBuckleNo(officer.getBuckleNo());
               dto.setWeaponIssue(officer.getWeaponIssue());

               result.add(dto);
           }

           // Fetch total data
           OrdinanceOfficerReturndataTotal total = ordinanceOfficerReturnDataTotalRepository.findById(1L).orElse(null);
           OrdinanceOfficerReturnDataTotalDTO totalDTO = null;
           if (total != null) {
               totalDTO = new OrdinanceOfficerReturnDataTotalDTO();
               totalDTO.setBionetTotal(total.getBionetTotal());
               totalDTO.setMagzineTotal(total.getMagzineTotal());
               totalDTO.setFillerTotal(total.getFillerTotal());
               totalDTO.setLauncherTotal(total.getLauncherTotal());
           }

           // Create response
           OrdinanceOfficerReturnDataResponse response = new OrdinanceOfficerReturnDataResponse();
           response.setOrdinanceOfficerReturnDataList(result);
           response.setOrdinanceOfficerReturnDataTotal(totalDTO);

           return new ResponseEntity<>(response, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
*/


    /*    @PostMapping("/ordinance_return_data/{id}")
    public ResponseEntity<OrdinanceOfficerReturndata> getOrdinanceOfficerById(@PathVariable Long id) {
        try {
            OrdinanceOfficerReturndata officer = ordinanceOfficerReturndataRepository.findById(id).orElse(null);
            if (officer == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(officer, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*//*


    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return value == null ? defaultValue : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }


    }*/

/*    //show all return ordinance data
    @PostMapping("/all_return_ordinance_data")
    public ResponseEntity<List<OrdinanceOfficerReturndata>> getAllOrdinanceOfficers() {
        try {
            List<OrdinanceOfficerReturndata> officers = ordinanceOfficerReturndataRepository.findAllByReceivedDateIsNotNull();
            if (officers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(officers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

}
    
    /*
    @PostMapping("/all_distribution_ordinance_data/{id}")
    public ResponseEntity<OrdinanceOfficer> getOrdinanceOfficerById(@PathVariable Long id) {
        return ordinanceOfficerRepository.findById(id)
                .map(ordinanceOfficer -> new ResponseEntity<>(ordinanceOfficer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
    
    /*
    @PostMapping("/all_ordinance_officers")
    public List<OrdinanceOfficer> getAllOrdinanceOfficers() {
        return ordinanceOfficerRepository.findAll();
    }
   
   
    @PostMapping("/all_ordinance_officers")
    public List<OrdinanceOfficer> getAllOrdinanceOfficers() {
        return ordinanceOfficerRepository.findAll();
    }

    @GetMapping("/all_disribution_ordinance_officer/{id}")
    public ResponseEntity<OrdinanceOfficer> getOrdinanceOfficerById(@PathVariable Long id) {
        return ordinanceOfficerRepository.findById(id)
                .map(ordinanceOfficer -> new ResponseEntity<>(ordinanceOfficer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
    
    
    /*
    //working but butt no are duplicates
    @PostMapping("/ordinance_by_butt_number")
    public ResponseEntity<?> getOrdinanceOfficerByButtNumber(@RequestParam("buttNumber") String buttNumber) {
        Optional<OrdinanceOfficer> officer = ordinanceOfficerRepository.findByButtNumber(buttNumber);
        if (officer.isPresent()) {
            return new ResponseEntity<>(officer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"message\": \"Ordinance Officer Not Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
        }
    }*/
    

