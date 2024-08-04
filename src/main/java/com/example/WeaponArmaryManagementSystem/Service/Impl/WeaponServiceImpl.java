// This is for the update the total count for tht total table as per the count +/- so this is the accurate mehtod but not required r
//right now
/*
package com.example.WeaponArmaryManagementSystem.Service.Impl;
import com.example.WeaponArmaryManagementSystem.Service.WeaponService;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReport;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReportTotal;
import com.example.WeaponArmaryManagementSystem.repository.WeaponRepository;
import com.example.WeaponArmaryManagementSystem.repository.WeaponSummaryTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponSummaryTotalRepository weaponSummaryTotalRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository, WeaponSummaryTotalRepository weaponSummaryTotalRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponSummaryTotalRepository = weaponSummaryTotalRepository;
    }

    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        String date = weaponsDailyReport.getDate();
        String weaponType = weaponsDailyReport.getWeaponType();

        // Retrieve existing report for the given date and weapon type
        WeaponsDailyReport existingReport = weaponRepository.findByDateAndWeaponType(date, weaponType);

        if (existingReport != null) {
            // If an existing report is found, update it with new data
            updateExistingWeaponReport(existingReport, weaponsDailyReport);
            weaponRepository.save(existingReport);
        } else {
            // If no existing report, save a new one
            weaponsDailyReport.setCreatedAt(LocalDateTime.now());
            existingReport = weaponRepository.save(weaponsDailyReport);
        }

        // Update the totals
        updateTotals(date);

        return existingReport != null ? existingReport : weaponsDailyReport;
    }

    @Override
    public Map<String, Object> getWeaponsAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        // Fetch weapons daily reports for the given date
        List<WeaponsDailyReport> weaponsDailyReports = weaponRepository.findByDate(date);

        // Fetch all totals for the given date
        List<
                WeaponsDailyReportTotal> weaponsDailyReportTotals = weaponSummaryTotalRepository.findByDate(date);

        // Add both to the result map
        result.put("weapons", weaponsDailyReports);
        result.put("totals", weaponsDailyReportTotals);

        return result;
    }

    @Override
    public List<Map<String, Object>> getWeaponsAndTotalsBetweenDates(String startDate, String endDate) {
        List<WeaponsDailyReport> weaponsDailyReports = weaponRepository.findByDateBetween(startDate, endDate);
        return formatData(weaponsDailyReports);
    }

    @Override
    public List<WeaponsDailyReport> getAllWeaponsDailyReports() {
        return weaponRepository.findAll();
    }

    private List<Map<String, Object>> formatData(List<WeaponsDailyReport> reports) {
        List<Map<String, Object>> formattedData = new ArrayList<>();
        for (WeaponsDailyReport report : reports) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", report.getDate());
            map.put("weaponType", report.getWeaponType());
            map.put("goodConditionMagazine", report.getGoodConditionMagazine());
            map.put("goodConditionGodown", report.getGoodConditionGodown());
            map.put("goodConditionTemporarySubmitted", report.getGoodConditionTemporarySubmitted());
            map.put("unrepairMagazine", report.getUnrepairMagazine());
            map.put("unrepairGodown", report.getUnrepairGodown());
            map.put("puneRepair", report.getPuneRepair());
            map.put("inquiry", report.getInquiry());
            map.put("totalQuantity", report.getTotalQuantity());
            map.put("totalDistributed", report.getTotalDistributed());
            // Add other fields as needed
            formattedData.add(map);
        }
        return formattedData;
    }

    private void updateExistingWeaponReport(WeaponsDailyReport existingReport, WeaponsDailyReport newReport) {
        existingReport.setWeaponType(newReport.getWeaponType());
        existingReport.setGoodConditionMagazine(newReport.getGoodConditionMagazine());
        existingReport.setGoodConditionGodown(newReport.getGoodConditionGodown());
        existingReport.setGoodConditionTemporarySubmitted(newReport.getGoodConditionTemporarySubmitted());
        existingReport.setUnrepairMagazine(newReport.getUnrepairMagazine());
        existingReport.setUnrepairGodown(newReport.getUnrepairGodown());
        existingReport.setPuneRepair(newReport.getPuneRepair());
        existingReport.setInquiry(newReport.getInquiry());
        existingReport.setTotalQuantity(newReport.getTotalQuantity());
        existingReport.setTotalDistributed(newReport.getTotalDistributed());
        existingReport.setUpdatedAt(LocalDateTime.now());
        // Update other fields from newReport as needed
    }

    private void updateTotals(String date) {
        try {
            // Fetch all WeaponsDailyReport entries for the given date
            List<WeaponsDailyReport> reports = weaponRepository.findByDate(date);

            // Initialize total object
            WeaponsDailyReportTotal total = weaponSummaryTotalRepository.findByDate(date).stream().findFirst().orElse(null);
            if (total == null) {
                total = new WeaponsDailyReportTotal();
                total.setDate(date);
            }

            // Initialize total counters
            int totalGoodConditionMagazine = 0;
            int totalGoodConditionGodown = 0;
            int totalGoodConditionTemporarySubmitted = 0;
            int totalUnrepairMagazine = 0;
            int totalUnrepairGodown = 0;
            int totalPuneRepair = 0;
            int totalInquiry = 0;
            int totalQuantity = 0;
            int totalDistributed = 0;

            // Calculate totals
            for (WeaponsDailyReport report : reports) {
                totalGoodConditionMagazine += parseIntFromStr(report.getGoodConditionMagazine());
                totalGoodConditionGodown += parseIntFromStr(report.getGoodConditionGodown());
                totalGoodConditionTemporarySubmitted += parseIntFromStr(report.getGoodConditionTemporarySubmitted());
                totalUnrepairMagazine += parseIntFromStr(report.getUnrepairMagazine());
                totalUnrepairGodown += parseIntFromStr(report.getUnrepairGodown());
                totalPuneRepair += parseIntFromStr(report.getPuneRepair());
                totalInquiry += parseIntFromStr(report.getInquiry());
                totalQuantity += parseIntFromStr(report.getTotalQuantity());
                totalDistributed += parseIntFromStr(report.getTotalDistributed());
            }

            // Set the calculated totals to the total object
            total.setTotalGoodConditionMagazine(String.valueOf(totalGoodConditionMagazine));
            total.setTotalGoodConditionGodown(String.valueOf(totalGoodConditionGodown));
            total.setTotalGoodConditionTemporarySubmitted(String.valueOf(totalGoodConditionTemporarySubmitted));
            total.setTotalUnrepairMagazine(String.valueOf(totalUnrepairMagazine));
            total.setTotalUnrepairGodown(String.valueOf(totalUnrepairGodown));
            total.setTotalPuneRepair(String.valueOf(totalPuneRepair));
            total.setTotalInquiry(String.valueOf(totalInquiry));
            total.setCountTotalQuantity(String.valueOf(totalQuantity));
            total.setCountTotalDistributed(String.valueOf(totalDistributed));

            // Save the updated total
            weaponSummaryTotalRepository.save(total);
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
}

*/

package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.Service.WeaponService;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReport;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReportTotal;
import com.example.WeaponArmaryManagementSystem.repository.WeaponRepository;
import com.example.WeaponArmaryManagementSystem.repository.WeaponSummaryTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponSummaryTotalRepository weaponSummaryTotalRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository, WeaponSummaryTotalRepository weaponSummaryTotalRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponSummaryTotalRepository = weaponSummaryTotalRepository;
    }
    
    
    @Override
    public Map<String, Object> getWeaponsAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        // Fetch weapons daily reports for the given date
        List<WeaponsDailyReport> weaponsDailyReports = weaponRepository.findByDate(date);

        // Fetch all totals for the given date
        List<WeaponsDailyReportTotal> weaponsDailyReportTotals = weaponSummaryTotalRepository.findByDate(date);

        // Add both to the result map
        result.put("weapons", weaponsDailyReports);
        result.put("totals", weaponsDailyReportTotals);

        return result;
    }
    
    

    @Override
    public List<Map<String, Object>> getWeaponsAndTotalsBetweenDates(String startDate, String endDate) {
        List<WeaponsDailyReport> weaponsDailyReports = weaponRepository.findByDateBetween(startDate, endDate);
        // You can fetch totals or perform other operations as needed
        // List<Total> totals = totalRepository.findByDateBetween(startDate, endDate);

        // You can format the data as needed and return it
        return formatData(weaponsDailyReports);
    }

    
    @Override
    public List<WeaponsDailyReport> getAllWeaponsDailyReports() {
        List<WeaponsDailyReport> allSummaries = weaponRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(WeaponsDailyReport::getId).reversed())
                .collect(Collectors.toList());
    }

    private List<Map<String, Object>> formatData(List<WeaponsDailyReport> reports) {
        // Format the data as needed
        // Example: convert entities to a format suitable for the response
        // Here, I'm simply converting entities to maps
        List<Map<String, Object>> formattedData = new ArrayList<>();
        for (WeaponsDailyReport report : reports) {
            Map<String, Object> map = new HashMap<>();
            // Add necessary fields to the map
            map.put("date", report.getDate());
            map.put("weaponType", report.getWeaponType());
            // Add other fields as needed
            formattedData.add(map);
        }
        return formattedData;
    }

    
    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        String date = weaponsDailyReport.getDate();
        String weaponType = weaponsDailyReport.getWeaponType();

        // Retrieve existing report for the given date and weapon type
        WeaponsDailyReport existingReport = weaponRepository.findByDateAndWeaponType(date, weaponType);

        // Check if there is an existing report
        if (existingReport != null) {
            // If an existing report is found, throw an exception to indicate that the entry already exists
            throw new IllegalArgumentException("Weapons daily report for the given date and weapon type already exists.");
        } else {
            // If no existing report, save a new one
            weaponsDailyReport.setCreatedAt(LocalDateTime.now());
            existingReport = weaponRepository.save(weaponsDailyReport);
        }

        // Update the totals
        updateTotals(existingReport);
        return existingReport;
    }


    private void updateTotals(WeaponsDailyReport report) {
        String date = report.getDate();
        String weaponType = report.getWeaponType();

        try {
            // Fetch the total entry based on date
            List<WeaponsDailyReportTotal> weaponsDailyReportTotals = weaponSummaryTotalRepository.findByDate(date);

            WeaponsDailyReportTotal weaponsDailyReportTotal;
            if (weaponsDailyReportTotals.isEmpty()) {
                // If no entry exists for the given date, create a new one
                weaponsDailyReportTotal = new WeaponsDailyReportTotal();
                weaponsDailyReportTotal.setDate(date);
                weaponsDailyReportTotal.setWeaponType(weaponType);
            } else {
                // Get the first total from the list (assuming there should be only one total for each date)
                weaponsDailyReportTotal = weaponsDailyReportTotals.get(0);
            }

            // Update the totals
            weaponsDailyReportTotal.setTotalGoodConditionMagazine(add(report.getGoodConditionMagazine(), weaponsDailyReportTotal.getTotalGoodConditionMagazine()));
            weaponsDailyReportTotal.setTotalGoodConditionGodown(add(report.getGoodConditionGodown(), weaponsDailyReportTotal.getTotalGoodConditionGodown()));
            weaponsDailyReportTotal.setTotalGoodConditionTemporarySubmitted(add(report.getGoodConditionTemporarySubmitted(), weaponsDailyReportTotal.getTotalGoodConditionTemporarySubmitted()));
            weaponsDailyReportTotal.setTotalUnrepairMagazine(add(report.getUnrepairMagazine(), weaponsDailyReportTotal.getTotalUnrepairMagazine()));
            weaponsDailyReportTotal.setTotalUnrepairGodown(add(report.getUnrepairGodown(), weaponsDailyReportTotal.getTotalUnrepairGodown()));
            weaponsDailyReportTotal.setTotalPuneRepair(add(report.getPuneRepair(), weaponsDailyReportTotal.getTotalPuneRepair()));
            weaponsDailyReportTotal.setTotalInquiry(add(report.getInquiry(), weaponsDailyReportTotal.getTotalInquiry()));
            weaponsDailyReportTotal.setCountTotalQuantity(add(report.getTotalQuantity(), weaponsDailyReportTotal.getCountTotalQuantity()));
            weaponsDailyReportTotal.setCountTotalDistributed(add(report.getTotalDistributed(), weaponsDailyReportTotal.getCountTotalDistributed()));
            // Add other fields as needed

            weaponSummaryTotalRepository.save(weaponsDailyReportTotal); // Save the updated total
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }


    private String add(String newValue, String totalValue) {
        try {
            int newVal = newValue != null ? Integer.parseInt(newValue) : 0;
            int total = totalValue != null ? Integer.parseInt(totalValue) : 0;
            return String.valueOf(total + newVal);
        } catch (NumberFormatException e) {
            return totalValue;
        }
    }
}


 /*   // this is for the update the entry for the given date if the data is available
   @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        String date = weaponsDailyReport.getDate();
        String weaponType = weaponsDailyReport.getWeaponType();

        // Retrieve existing report for the given date and weapon type
        WeaponsDailyReport existingReport = weaponRepository.findByDateAndWeaponType(date, weaponType);

        // Check if there is an existing report
        if (existingReport != null) {
            // If an existing report is found, update it with new data
            existingReport.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingReport.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingReport.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingReport.setUnrepairMagazine(weaponsDailyReport.getUnrepairMagazine());
            existingReport.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingReport.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingReport.setInquiry(weaponsDailyReport.getInquiry());
            existingReport.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingReport.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            weaponRepository.save(existingReport); // Save the updated report
        } else {
            // If no existing report, save a new one
            weaponsDailyReport.setCreatedAt(LocalDateTime.now());
            existingReport = weaponRepository.save(weaponsDailyReport);
        }

        // Update the totals
        updateTotals(existingReport);
        return existingReport;
    }

    */

   /* @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        String date = weaponsDailyReport.getDate();
        String weaponType = weaponsDailyReport.getWeaponType();

        // Retrieve existing reports for the given date and weapon type
        List<WeaponsDailyReport> existingReports = (List<WeaponsDailyReport>) weaponRepository.findByDateAndWeaponType(date, weaponType);

        // Check if there is an existing report
        if (!existingReports.isEmpty()) {
            // If multiple existing reports are found, handle as needed (e.g., update the first one)
            // For simplicity, let's update the first one
            WeaponsDailyReport existingReport = existingReports.get(0);
            existingReport.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingReport.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingReport.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingReport.setUnrepairMagazine(weaponsDailyReport.getUnrepairMagazine());
            existingReport.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingReport.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingReport.setInquiry(weaponsDailyReport.getInquiry());
            existingReport.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingReport.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            weaponRepository.save(existingReport); // Save the updated report
            return existingReport;
        } else {
            // If no existing report, save a new one
            weaponsDailyReport.setCreatedAt(LocalDateTime.now());
            WeaponsDailyReport newReport = weaponRepository.save(weaponsDailyReport);

            // Update the totals
            updateTotals(newReport);

            return newReport;
        }
    }
*/


/*

package com.example.Emanyata.Service.Impl;

import com.example.Emanyata.Entity.Total;
import com.example.Emanyata.Entity.WeaponsDailyReport;
import com.example.Emanyata.Repo.TotalRepository;
import com.example.Emanyata.Repo.WeaponRepository;
import com.example.Emanyata.Service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {

    private final WeaponRepository weaponRepository;
    private final TotalRepository totalRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository, TotalRepository totalRepository) {
        this.weaponRepository = weaponRepository;
        this.totalRepository = totalRepository;
    }

    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        weaponsDailyReport.setCreatedAt(LocalDateTime.now());
        WeaponsDailyReport savedReport = weaponRepository.save(weaponsDailyReport);
        updateTotals(savedReport);
        return savedReport;
    }

    @Override
    public List<WeaponsDailyReport> getByDate(String date) {
        return weaponRepository.findByDate(date);
    }

    private void updateTotals(WeaponsDailyReport report) {
        String date = report.getDate();
        Total total = totalRepository.findByDate(date);

        if (total == null) {
            total = new Total();
            total.setDate(date);
        }

        total.setTotalGoodConditionMagazine(add(report.getGoodConditionMagazine(), total.getTotalGoodConditionMagazine()));
        total.setTotalGoodConditionGodown(add(report.getGoodConditionGodown(), total.getTotalGoodConditionGodown()));
        total.setTotalGoodConditionTemporarySubmitted(add(report.getGoodConditionTemporarySubmitted(), total.getTotalGoodConditionTemporarySubmitted()));
        total.setTotalUnrepairMagazine(add(report.getUnrepairMagazine(), total.getTotalUnrepairMagazine()));
        total.setTotalUnrepairGodown(add(report.getUnrepairGodown(), total.getTotalUnrepairGodown()));
        total.setTotalPuneRepair(add(report.getPuneRepair(), total.getTotalPuneRepair()));
        total.setTotalInquiry(add(report.getInquiry(), total.getTotalInquiry()));
        total.setCountTotalQuantity(add(report.getTotalQuantity(), total.getCountTotalQuantity()));
        total.setCountTotalDistributed(add(report.getTotalDistributed(),total.getCountTotalDistributed()));
        total.setCountTotalQuantity(add(report.getTotalQuantity(),total.getCountTotalQuantity()));
        // Add other fields as needed

        totalRepository.save(total);
    }

    private String add(String newValue, String totalValue) {
        try {
            int newVal = newValue != null ? Integer.parseInt(newValue) : 0;
            int total = totalValue != null ? Integer.parseInt(totalValue) : 0;
            return String.valueOf(total + newVal);
        } catch (NumberFormatException e) {
            return totalValue;
        }
    }
}

*/



/*
package com.example.Emanyata.Service.Impl;

import com.example.Emanyata.Entity.WeaponsDailyReport;
import com.example.Emanyata.Repo.WeaponRepository;
import com.example.Emanyata.Service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

    @Service
    public class WeaponServiceImpl implements WeaponService {

        private final WeaponRepository weaponRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        // Check if an entry with the same weapon type already exists
        WeaponsDailyReport existingWeapon = weaponRepository.findByWeaponType(weaponsDailyReport.getWeaponType());
        if (existingWeapon != null) {
            updateTotals(existingWeapon, weaponsDailyReport);
            // If an entry with the same weapon type exists, update the existing data
            existingWeapon.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingWeapon.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            existingWeapon.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingWeapon.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingWeapon.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingWeapon.setUnrepairMagazine(weaponsDailyReport.getUnrepairMagazine());
            existingWeapon.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingWeapon.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingWeapon.setInquiry(weaponsDailyReport.getInquiry());
            existingWeapon.setCreatedAt(LocalDateTime.now());
            existingWeapon.setTotal(weaponsDailyReport.getTotal());
            return weaponRepository.save(existingWeapon);
        } else {
            // If no entry with the same weapon type exists, save the provided data
            updateTotals(weaponsDailyReport, weaponsDailyReport);
            return weaponRepository.save(weaponsDailyReport);
        }
    }

    @Override
    public List<WeaponsDailyReport> getByDate(String date) {
        return weaponRepository.findByDate(date);
    }

    private WeaponsDailyReport saveNewWeapon(WeaponsDailyReport weaponsDailyReport) {
        // Save the new entry and return it
        weaponsDailyReport.setCreatedAt(LocalDateTime.now());
        return weaponRepository.save(weaponsDailyReport);
    }

    private void updateTotals(WeaponsDailyReport existingWeapon, WeaponsDailyReport newWeapon) {
        // Update totals for each column

        existingWeapon.setTotalGoodConditionMagazine(calculateTotal(existingWeapon.getGoodConditionMagazine(), newWeapon.getGoodConditionMagazine()));
        existingWeapon.setTotalGoodConditionGodown(calculateTotal(existingWeapon.getGoodConditionGodown(), newWeapon.getGoodConditionGodown()));
        existingWeapon.setTotalGoodConditionTemporarySubmitted(calculateTotal(existingWeapon.getGoodConditionTemporarySubmitted(), newWeapon.getGoodConditionTemporarySubmitted()));
        existingWeapon.setTotalUnrepairMagazine(calculateTotal(existingWeapon.getUnrepairMagazine(), newWeapon.getUnrepairMagazine()));
        existingWeapon.setTotalUnrepairGodown(calculateTotal(existingWeapon.getUnrepairGodown(), newWeapon.getUnrepairGodown()));
        existingWeapon.setTotalPuneRepair(calculateTotal(existingWeapon.getPuneRepair(), newWeapon.getPuneRepair()));
        existingWeapon.setTotalInquiry(calculateTotal(existingWeapon.getInquiry(), newWeapon.getInquiry()));
        existingWeapon.setCountTotalQuantity(calculateTotal(existingWeapon.getTotalQuantity(),newWeapon.getTotalQuantity()));
        existingWeapon.setCountTotalDistributed(calculateTotal(existingWeapon.getTotalDistributed(),newWeapon.getTotalDistributed()));
        existingWeapon.setCreatedAt(LocalDateTime.now());
    }

    private String calculateTotal(String existingValue, String newValue) {
        try {
            int existing = existingValue != null ? Integer.parseInt(existingValue) : 0;
            int newVal = newValue != null ? Integer.parseInt(newValue) : 0;
            return String.valueOf(existing + newVal);
        } catch (NumberFormatException e) {
            // If parsing fails, return the existing value
            return existingValue;
        }
    }

}
*/


  /*  @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        // Check if the date already exists
        WeaponsDailyReport existingWeapon = weaponRepository.findByDate(weaponsDailyReport.getDate());
        if (existingWeapon != null) {
            // If date exists, update the existing data
            existingWeapon.setWeaponType(weaponsDailyReport.getWeaponType());
            existingWeapon.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingWeapon.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            existingWeapon.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingWeapon.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingWeapon.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingWeapon.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingWeapon.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingWeapon.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingWeapon.setInquiry(weaponsDailyReport.getInquiry());
            return weaponRepository.save(existingWeapon);
        } else {
            // If date doesn't exist, save the provided data
            return weaponRepository.save(weaponsDailyReport);
        }*/
/*
    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        // Check if the date already exists
        WeaponsDailyReport savedReport = weaponRepository.save(weaponsDailyReport);
        WeaponsDailyReport existingWeapon = weaponRepository.findByDate(weaponsDailyReport.getDate());
        if (existingWeapon != null) {
            // If date exists, update the existing data
            existingWeapon.setWeaponType(weaponsDailyReport.getWeaponType());
            existingWeapon.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingWeapon.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            existingWeapon.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingWeapon.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingWeapon.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingWeapon.setUnrepairMagazine(weaponsDailyReport.getUnrepairMagazine());
            existingWeapon.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingWeapon.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingWeapon.setInquiry(weaponsDailyReport.getInquiry());
            existingWeapon.setCreatedAt(LocalDateTime.now());

            calculateTotals(savedReport);
            return weaponRepository.save(existingWeapon);
        } else {
            // If date doesn't exist, save the provided data
            return weaponRepository.save(weaponsDailyReport);
        }


    }

    private void calculateTotals(WeaponsDailyReport report) {
        int totalGoodCondition = Integer.parseInt(report.getGoodConditionMagazine()) +
                Integer.parseInt(report.getGoodConditionGodown()) +
                Integer.parseInt(report.getGoodConditionTemporarySubmitted());

        int totalUnrepair = Integer.parseInt(report.getUnrepairMagazine()) +
                Integer.parseInt(report.getUnrepairGodown());

        int totalPuneRepair = Integer.parseInt(report.getPuneRepair());

        // Set the calculated totals in the report object
        report.setTotalGoodConditionMagazine(String.valueOf(totalGoodCondition));
        report.setCountTotalQuantity(String.valueOf(totalUnrepair));
        report.setTotalPuneRepair(String.valueOf(totalPuneRepair));
    }
*/


/*    @Override
    public WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport) {
        // Check if the date already exists
        WeaponsDailyReport savedReport = weaponRepository.save(weaponsDailyReport);
        WeaponsDailyReport existingWeapon = weaponRepository.findByDate(weaponsDailyReport.getDate());
        if (existingWeapon != null) {
            // If date exists, update the existing data
            existingWeapon.setEmployeeNo(weaponsDailyReport.getEmployeeNo());
            existingWeapon.setDesignation(weaponsDailyReport.getDesignation());
            existingWeapon.setWeaponType(weaponsDailyReport.getWeaponType());
            existingWeapon.setTotalQuantity(weaponsDailyReport.getTotalQuantity());
            existingWeapon.setTotalDistributed(weaponsDailyReport.getTotalDistributed());
            existingWeapon.setGoodConditionMagazine(weaponsDailyReport.getGoodConditionMagazine());
            existingWeapon.setGoodConditionGodown(weaponsDailyReport.getGoodConditionGodown());
            existingWeapon.setGoodConditionTemporarySubmitted(weaponsDailyReport.getGoodConditionTemporarySubmitted());
            existingWeapon.setUnrepairMagazine(weaponsDailyReport.getUnrepairMagazine());
            existingWeapon.setUnrepairGodown(weaponsDailyReport.getUnrepairGodown());
            existingWeapon.setPuneRepair(weaponsDailyReport.getPuneRepair());
            existingWeapon.setInquiry(weaponsDailyReport.getInquiry());
            existingWeapon.setCreatedAt(LocalDateTime.now());

            calculateTotals(existingWeapon); // Calculate totals on existingWeapon
            return weaponRepository.save(existingWeapon);
        } else {
            // If date doesn't exist, save the provided data
            calculateTotals(savedReport); // Calculate totals on savedReport
            return weaponRepository.save(savedReport);
        }
    }

    private void calculateTotals(WeaponsDailyReport report) {
        try {
            // Calculate totals for each column separately
            int totalQuantity = Integer.parseInt(report.getTotalQuantity());
            int totalDistributed = Integer.parseInt(report.getTotalDistributed());
            int totalGoodConditionMagazine = Integer.parseInt(report.getGoodConditionMagazine());
            int totalGoodConditionGodown = Integer.parseInt(report.getGoodConditionGodown());
            int totalGoodConditionTemporarySubmitted = Integer.parseInt(report.getGoodConditionTemporarySubmitted());
            int totalUnrepairMagazine = Integer.parseInt(report.getUnrepairMagazine());
            int totalUnrepairGodown = Integer.parseInt(report.getUnrepairGodown());
            int totalPuneRepair = Integer.parseInt(report.getPuneRepair());
            int totalInquiry = Integer.parseInt(report.getInquiry());

            // Set the calculated totals in the report object
            report.setTotalQuantity(String.valueOf(totalQuantity));
            report.setTotalDistributed(String.valueOf(totalDistributed));
            report.setTotalGoodConditionMagazine(String.valueOf(totalGoodConditionMagazine));
            report.setTotalGoodConditionGodown(String.valueOf(totalGoodConditionGodown));
            report.setTotalGoodConditionTemporarySubmitted(String.valueOf(totalGoodConditionTemporarySubmitted));
            report.setTotalUnrepairMagazine(String.valueOf(totalUnrepairMagazine));
            report.setTotalUnrepairGodown(String.valueOf(totalUnrepairGodown));
            report.setTotalPuneRepair(String.valueOf(totalPuneRepair));
            report.setTotalInquiry(String.valueOf(totalInquiry));

            // Calculate and set the total for weapon type separately
            int totalWeaponType = totalQuantity + totalDistributed;
            report.setTotalWeaponType(String.valueOf(totalWeaponType));
        } catch (NumberFormatException e) {
            // Handle the case where a field cannot be parsed to an integer
            // Log the error or perform any other appropriate action
            e.printStackTrace();
        }
    }*/



