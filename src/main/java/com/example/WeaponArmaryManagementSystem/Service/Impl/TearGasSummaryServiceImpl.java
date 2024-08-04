// this method is for the purpose of updating the data in the totals table table and maintaining the accuracy of the table.

/*

package com.example.WeaponArmaryManagementSystem.Service.Impl;
import com.example.WeaponArmaryManagementSystem.Service.TearGasSummaryService;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummaryTotal;
import com.example.WeaponArmaryManagementSystem.repository.TearGasSummaryRepository;
import com.example.WeaponArmaryManagementSystem.repository.TearGasSummaryTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TearGasSummaryServiceImpl implements TearGasSummaryService {

    private final TearGasSummaryRepository tearGasSummaryRepository;
    private final TearGasSummaryTotalRepository tearGasSummaryTotalRepository;

    @Autowired
    public TearGasSummaryServiceImpl(TearGasSummaryRepository tearGasSummaryRepository, TearGasSummaryTotalRepository tearGasSummaryTotalRepository) {
        this.tearGasSummaryRepository = tearGasSummaryRepository;
        this.tearGasSummaryTotalRepository = tearGasSummaryTotalRepository;
    }

    @Override
    public TearGasSummary saveOrUpdateTearGasSummary(TearGasSummary tearGasSummary) {
        String date = tearGasSummary.getDate();
        String teargasAmunities = tearGasSummary.getTeargasAmunities();

        // Retrieve existing tear gas summary for the given date and teargasAmunities
        TearGasSummary existingSummary = tearGasSummaryRepository.findByDateAndTeargasAmunities(date, teargasAmunities);

        if (existingSummary != null) {
            // If an existing summary is found, update it with new data
            updateExistingTearGasSummary(existingSummary, tearGasSummary);
            tearGasSummaryRepository.save(existingSummary);
        } else {
            // If no existing summary, save a new one
            tearGasSummary.setCreatedAt(LocalDateTime.now());
            tearGasSummary = tearGasSummaryRepository.save(tearGasSummary);
        }

        // Update the totals
        updateTotals(date);

        return existingSummary != null ? existingSummary : tearGasSummary;
    }

    @Override
    public Map<String, Object> getSummaryAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        List<TearGasSummary> tearGasSummaries = tearGasSummaryRepository.findByDate(date);
        List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(date);

        result.put("tearGasSummaries", tearGasSummaries);
        result.put("tearGasSummaryTotals", tearGasSummaryTotals);

        return result;
    }

    @Override
    public List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String startDate, String endDate) {
        List<TearGasSummary> tearGasSummaries = tearGasSummaryRepository.findByDateBetween(startDate, endDate);
        return formatData(tearGasSummaries);
    }

    @Override
    public List<TearGasSummary> getAllTearGasSummaries() {
        return tearGasSummaryRepository.findAll();
    }

    @Override
    public List<TearGasSummaryTotal> getAllTearGasSummaryTotals() {
        return tearGasSummaryTotalRepository.findAll();
    }

    @Override
    public TearGasSummaryTotal saveOrUpdateTearGasSummaryTotal(TearGasSummaryTotal tearGasSummaryTotal) {
        return tearGasSummaryTotalRepository.save(tearGasSummaryTotal);
    }

    private List<Map<String, Object>> formatData(List<TearGasSummary> summaries) {
        List<Map<String, Object>> formattedData = new ArrayList<>();
        for (TearGasSummary summary : summaries) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", summary.getDate());
            map.put("teargasAmunities", summary.getTeargasAmunities());
            map.put("Total Surcharge", summary.getTotalSurcharge());
            map.put("Shakha Vitran Active", summary.getShakhaVitranActive());
            map.put("Shakha Vitran Expired", summary.getShakhaVitranExpired());
            map.put("Gas Godown Stock Active", summary.getGasDodownStockActive());
            map.put("Gas Godown Stock Expired", summary.getGasDodownStockExpired());
            map.put("Total", summary.getTotal());
            map.put("Total All", summary.getTotalAll());

            // Add other fields as needed
            formattedData.add(map);
        }
        return formattedData;
    }

    private void updateExistingTearGasSummary(TearGasSummary existingSummary, TearGasSummary newSummary) {
        existingSummary.setTeargasAmunities(newSummary.getTeargasAmunities());
        existingSummary.setTotalSurcharge(newSummary.getTotalSurcharge());
        existingSummary.setShakhaVitranActive(newSummary.getShakhaVitranActive());
        existingSummary.setShakhaVitranExpired(newSummary.getShakhaVitranExpired());
        existingSummary.setGasDodownStockActive(newSummary.getGasDodownStockActive());
        existingSummary.setGasDodownStockExpired(newSummary.getGasDodownStockExpired());
        existingSummary.setTotal(newSummary.getTotal());
        existingSummary.setTotalAll(newSummary.getTotalAll());
        existingSummary.setUpdatedAt(LocalDateTime.now());
        // Update other fields from newSummary as needed
    }

    private void updateTotals(String date) {
        try {
            // Fetch all TearGasSummary entries for the given date
            List<TearGasSummary> summaries = tearGasSummaryRepository.findByDate(date);

            // Initialize total object
            TearGasSummaryTotal total = tearGasSummaryTotalRepository.findByDate(date).stream().findFirst().orElse(null);
            if (total == null) {
                total = new TearGasSummaryTotal();
                total.setDate(date);
            }

            // Initialize total counters
            int totalTeargasAmunities = 0;
            int totalSurcharge = 0;
            int totalShakhaVitranActive = 0;
            int totalShakhaVitranExpired = 0;
            int totalGasDodownStockActive = 0;
            int totalGasDodownStockExpired = 0;
            int totalOverall = 0;
            int totalAll = 0;

            // Calculate totals
            for (TearGasSummary summary : summaries) {
                // totalTeargasAmunities += parseIntFromStr(summary.getTeargasAmunities());
                totalSurcharge += parseIntFromStr(summary.getTotalSurcharge());
                totalShakhaVitranActive += parseIntFromStr(summary.getShakhaVitranActive());
                totalShakhaVitranExpired += parseIntFromStr(summary.getShakhaVitranExpired());
                totalGasDodownStockActive += parseIntFromStr(summary.getGasDodownStockActive());
                totalGasDodownStockExpired += parseIntFromStr(summary.getGasDodownStockExpired());
                totalOverall += parseIntFromStr(summary.getTotal());
                totalAll += parseIntFromStr(summary.getTotalAll());
            }

            // Set the calculated totals to the total object
            //  total.setTotalTeargasAmunities(String.valueOf(totalTeargasAmunities));
            total.setCountTotalSurcharge(String.valueOf(totalSurcharge));
            total.setTotalShakhaVitranActive(String.valueOf(totalShakhaVitranActive));
            total.setTotalShakhaVitranExpired(String.valueOf(totalShakhaVitranExpired));
            total.setTotalGasDodownStockActive(String.valueOf(totalGasDodownStockActive));
            total.setTotalGasDodownStockExpired(String.valueOf(totalGasDodownStockExpired));
            total.setTotal(String.valueOf(totalOverall));
            total.setCountTotalAll(String.valueOf(totalAll));

            // Save the updated total
            tearGasSummaryTotalRepository.save(total);
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
import com.example.WeaponArmaryManagementSystem.Service.TearGasSummaryService;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummaryTotal;
import com.example.WeaponArmaryManagementSystem.repository.TearGasSummaryRepository;
import com.example.WeaponArmaryManagementSystem.repository.TearGasSummaryTotalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TearGasSummaryServiceImpl implements TearGasSummaryService {

    private final TearGasSummaryRepository tearGasSummaryRepository;
    private final TearGasSummaryTotalRepository tearGasSummaryTotalRepository;

    @Autowired
    public TearGasSummaryServiceImpl(TearGasSummaryRepository tearGasSummaryRepository, TearGasSummaryTotalRepository tearGasSummaryTotalRepository) {
        this.tearGasSummaryRepository = tearGasSummaryRepository;
        this.tearGasSummaryTotalRepository = tearGasSummaryTotalRepository;
    }
    
    
    
    @Override
    public List<TearGasSummaryTotal> getAllTearGasSummariesTotal() {
        List<TearGasSummaryTotal> allSummaries = tearGasSummaryTotalRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(TearGasSummaryTotal::getId, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }
    


//  this is working method to save before check the entry
    @Override
    public TearGasSummary saveOrUpdateTearGasSummary(TearGasSummary tearGasSummary) {
        String date = tearGasSummary.getDate();
        String teargasAmunities = tearGasSummary.getTeargasAmunities();

        // Retrieve existing tear gas summary for the given date and teargasAmunities
        TearGasSummary existingSummary = tearGasSummaryRepository.findByDateAndTeargasAmunities(date, teargasAmunities);

        if (existingSummary != null) {
            // If an existing summary is found with the same date and teargasAmunities, throw an exception
            throw new IllegalArgumentException("Tear gas summary for the given date and teargas amunities already exists.");
        } else {
            // If no existing summary with the same date and teargasAmunities, save a new one
            tearGasSummary.setCreatedAt(LocalDateTime.now());
            tearGasSummary = tearGasSummaryRepository.save(tearGasSummary);
            // Update the totals
            updateTotals(tearGasSummary);
            return tearGasSummary;
        }
    }

    
    @Override
    public Map<String, Object> getSummaryAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        List<TearGasSummary> tearGasSummaries = tearGasSummaryRepository.findByDate(date);
        List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(date);

        result.put("tearGasSummaries", tearGasSummaries);
        result.put("tearGasSummaryTotals", tearGasSummaryTotals);

        return result;
    }

    
    @Override
    public List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String startDate, String endDate) {
        List<TearGasSummary> tearGasSummaries = tearGasSummaryRepository.findByDateBetween(startDate, endDate);
        return formatData(tearGasSummaries);
    }


    // for the Id in the reverse order.......
    @Override
    public List<TearGasSummary> getAllTearGasSummaries() {
        List<TearGasSummary> allSummaries = tearGasSummaryRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(TearGasSummary::getId).reversed())
                .collect(Collectors.toList());
    }


    @Override
    public List<TearGasSummaryTotal> getAllTearGasSummaryTotals() {
        return tearGasSummaryTotalRepository.findAll();
    }

    
    @Override
    public TearGasSummaryTotal saveOrUpdateTearGasSummaryTotal(TearGasSummaryTotal tearGasSummaryTotal) {
        return tearGasSummaryTotalRepository.save(tearGasSummaryTotal);
    }
    

    private List<Map<String, Object>> formatData(List<TearGasSummary> summaries) {
        List<Map<String, Object>> formattedData = new ArrayList<>();
        for (TearGasSummary summary : summaries) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", summary.getDate());
            map.put("teargasAmunities", summary.getTeargasAmunities());
            map.put("Total Surcharge", summary.getTotalSurcharge());
            map.put("Shakha Vitran Active", summary.getShakhaVitranActive());
            map.put("Shakha Vitran Expired",summary.getShakhaVitranExpired());
            map.put("Gas Godown Stock Active", summary.getGasDodownStockActive());
            map.put("Gas Godown Stock Expired", summary.getGasDodownStockExpired());
            map.put("Type",summary.getTeargasType());
            map.put("Total",summary.getTotal());
            map.put("Total All",summary.getTotalAll());

            // Add other fields as needed
            formattedData.add(map);
        }
        return formattedData;
    }

    
    private void updateTotals(TearGasSummary tearGasSummary) {
        String date = tearGasSummary.getDate();

        try {
            // Fetch the total entry based on date
            List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(date);

            TearGasSummaryTotal tearGasSummaryTotal;
            if (tearGasSummaryTotals.isEmpty()) {
                // If no entry exists for the given date, create a new one
                tearGasSummaryTotal = new TearGasSummaryTotal();
                tearGasSummaryTotal.setDate(date);
            } else {
                // Get the first total from the list (assuming there should be only one total for each date)
                tearGasSummaryTotal = tearGasSummaryTotals.get(0);
            }

            // Update the totals
            tearGasSummaryTotal.setTotalTeargasAmunities(add(tearGasSummary.getTeargasAmunities(), tearGasSummaryTotal.getTotalTeargasAmunities()));
            tearGasSummaryTotal.setCountTotalSurcharge(add(tearGasSummary.getTotalSurcharge(), tearGasSummaryTotal.getCountTotalSurcharge()));
            tearGasSummaryTotal.setTotalShakhaVitranActive(add(tearGasSummary.getShakhaVitranActive(), tearGasSummaryTotal.getTotalShakhaVitranActive()));
            tearGasSummaryTotal.setTotalShakhaVitranExpired(add(tearGasSummary.getShakhaVitranExpired(), tearGasSummaryTotal.getTotalShakhaVitranExpired()));
            tearGasSummaryTotal.setTotalGasDodownStockActive(add(tearGasSummary.getGasDodownStockActive(), tearGasSummaryTotal.getTotalGasDodownStockActive()));
            tearGasSummaryTotal.setTotalGasDodownStockExpired(add(tearGasSummary.getGasDodownStockExpired(), tearGasSummaryTotal.getTotalGasDodownStockExpired()));
            tearGasSummaryTotal.setTotal(add(tearGasSummary.getTotal(), tearGasSummaryTotal.getTotal()));
            tearGasSummaryTotal.setCountTotalAll(add(tearGasSummary.getTotalAll(), tearGasSummaryTotal.getCountTotalAll()));

            tearGasSummaryTotalRepository.save(tearGasSummaryTotal); // Save the updated total
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






/*  @Override
  public TearGasSummary saveOrUpdateTearGasSummary(TearGasSummary tearGasSummary) {
      // Save the provided tearGasSummary
      tearGasSummary = tearGasSummaryRepository.save(tearGasSummary);
      // Update the totals
      updateTotals(tearGasSummary);
      return tearGasSummary;
  }*/


/*    @Override
    public List<TearGasSummary> getAllTearGasSummaries() {
         return tearGasSummaryRepository.findAll();
    }*/


    // This is for the return the list in the latest entry first........
 /*   @Override
    public List<TearGasSummary> getAllTearGasSummaries() {
        List<TearGasSummary> allSummaries = tearGasSummaryRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(TearGasSummary::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }*/

/*
package com.example.Emanyata.Service.Impl;

import com.example.Emanyata.Entity.TearGasSummary;
import com.example.Emanyata.Entity.TearGasSummaryTotal;
import com.example.Emanyata.Repo.TearGasSummaryRepository;
import com.example.Emanyata.Repo.TearGasSummaryTotalRepository;
import com.example.Emanyata.Service.TearGasSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TearGasSummaryServiceImpl implements TearGasSummaryService {

    private final TearGasSummaryRepository tearGasSummaryRepository;
    private final TearGasSummaryTotalRepository tearGasSummaryTotalRepository;

    @Autowired
    public TearGasSummaryServiceImpl(TearGasSummaryRepository tearGasSummaryRepository, TearGasSummaryTotalRepository tearGasSummaryTotalRepository) {
        this.tearGasSummaryRepository = tearGasSummaryRepository;
        this.tearGasSummaryTotalRepository = tearGasSummaryTotalRepository;
    }

    @Override
    public TearGasSummary saveOrUpdateTearGasSummary(TearGasSummary tearGasSummary) {
        String date = tearGasSummary.getDate(); // Assuming TearGasSummary has a getDate() method
        // Retrieve existing tear gas summary for the given date
        TearGasSummary existingSummary = tearGasSummaryRepository.findByDate(date);

        // Check if there is an existing summary
        if (existingSummary != null) {
            // If an existing summary is found, update it with new data
            existingSummary.setTeargasAmunities(tearGasSummary.getTeargasAmunities());
            existingSummary.setTotalSurcharge(tearGasSummary.getTotalSurcharge());
            existingSummary.setShakhaVitranActive(tearGasSummary.getShakhaVitranActive());
            existingSummary.setShakhaVitranExpired(tearGasSummary.getShakhaVitranExpired());
            existingSummary.setGasDodownStockActive(tearGasSummary.getGasDodownStockActive());
            existingSummary.setGasDodownStockExpired(tearGasSummary.getGasDodownStockExpired());
            existingSummary.setTotal(tearGasSummary.getTotal());
            tearGasSummaryRepository.save(existingSummary); // Save the updated summary
        } else {
            // If no existing summary, save a new one
            existingSummary = tearGasSummaryRepository.save(tearGasSummary);
        }

        // Update the totals
        updateTotals(existingSummary);
        return existingSummary;
    }

    @Override
    public Map<String, Object> getSummaryAndTotalsByDate(String date) {
        Map<String, Object> result = new HashMap<>();

        // Fetch tear gas summary for the given date
        TearGasSummary tearGasSummary = tearGasSummaryRepository.findByDate(date);

        // Fetch tear gas summary totals for the given date
        List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(date);

        // Add both to the result map
        result.put("tearGasSummary", tearGasSummary);
        result.put("tearGasSummaryTotals", tearGasSummaryTotals);

        return result;
    }

    @Override
    public List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String startDate, String endDate) {
        List<TearGasSummary> tearGasSummaries = tearGasSummaryRepository.findByDateBetween(startDate, endDate);
        List<Map<String, Object>> formattedData = new ArrayList<>();

        for (TearGasSummary tearGasSummary : tearGasSummaries) {
            Map<String, Object> summaryAndTotals = new HashMap<>();
            summaryAndTotals.put("tearGasSummary", tearGasSummary);
            List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(tearGasSummary.getDate());
            summaryAndTotals.put("tearGasSummaryTotals", tearGasSummaryTotals);
            formattedData.add(summaryAndTotals);
        }

        return formattedData;
    }

    @Override
    public List<TearGasSummary> getAllTearGasSummaries() {
        return tearGasSummaryRepository.findAll();
    }

    @Override
    public List<TearGasSummaryTotal> getAllTearGasSummaryTotals() {
        return tearGasSummaryTotalRepository.findAll();
    }

    @Override
    public TearGasSummaryTotal saveOrUpdateTearGasSummaryTotal(TearGasSummaryTotal tearGasSummaryTotal) {
        return tearGasSummaryTotalRepository.save(tearGasSummaryTotal);
    }


    private void updateTotals(TearGasSummary tearGasSummary) {
        String date = tearGasSummary.getDate();

        try {
            // Fetch the total entry based on date
            List<TearGasSummaryTotal> tearGasSummaryTotals = tearGasSummaryTotalRepository.findByDate(date);

            TearGasSummaryTotal tearGasSummaryTotal;
            if (tearGasSummaryTotals.isEmpty()) {
                // If no entry exists for the given date, create a new one
                tearGasSummaryTotal = new TearGasSummaryTotal();
                tearGasSummaryTotal.setDate(date);
            } else {
                // Get the first total from the list (assuming there should be only one total for each date)
                tearGasSummaryTotal = tearGasSummaryTotals.get(0);
            }

            // Update the totals
            tearGasSummaryTotal.setTotalTeargasAmunities(add(tearGasSummary.getTeargasAmunities(), tearGasSummaryTotal.getTotalTeargasAmunities()));
            tearGasSummaryTotal.setCountTotalSurcharge(add(tearGasSummary.getTotalSurcharge(), tearGasSummaryTotal.getCountTotalSurcharge()));
            tearGasSummaryTotal.setTotalShakhaVitranActive(add(tearGasSummary.getShakhaVitranActive(), tearGasSummaryTotal.getTotalShakhaVitranActive()));
            tearGasSummaryTotal.setTotalShakhaVitranExpired(add(tearGasSummary.getShakhaVitranExpired(), tearGasSummaryTotal.getTotalShakhaVitranExpired()));
            tearGasSummaryTotal.setTotalGasDodownStockActive(add(tearGasSummary.getGasDodownStockActive(), tearGasSummaryTotal.getTotalGasDodownStockActive()));
            tearGasSummaryTotal.setTotalGasDodownStockExpired(add(tearGasSummary.getGasDodownStockExpired(), tearGasSummaryTotal.getTotalGasDodownStockExpired()));
            tearGasSummaryTotal.setTotal(add(tearGasSummary.getTotal(), tearGasSummaryTotal.getTotal()));
            tearGasSummaryTotal.setCountTotalAll(add("1", tearGasSummaryTotal.getCountTotalAll())); // Increment by 1 for each summary

            tearGasSummaryTotalRepository.save(tearGasSummaryTotal); // Save the updated total
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
*/
