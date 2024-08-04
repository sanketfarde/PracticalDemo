package com.example.WeaponArmaryManagementSystem.Service.Impl;
import com.example.WeaponArmaryManagementSystem.Service.DailyReportOfServiceService;
import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import com.example.WeaponArmaryManagementSystem.repository.DailyReportOfServiceRepository;
import com.example.WeaponArmaryManagementSystem.repository.TotalsDailyReportOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DailyReportOfServiceServiceImpl implements DailyReportOfServiceService {

    private final DailyReportOfServiceRepository dailyReportOfServiceRepository;
    private final TotalsDailyReportOfServiceRepository totalsDailyReportOfServiceRepository;

    @Autowired
    public DailyReportOfServiceServiceImpl(DailyReportOfServiceRepository dailyReportOfServiceRepository, TotalsDailyReportOfServiceRepository totalsDailyReportOfServiceRepository) {
        this.dailyReportOfServiceRepository = dailyReportOfServiceRepository;
        this.totalsDailyReportOfServiceRepository = totalsDailyReportOfServiceRepository;
    }

    @Override
    public DailyReportOfService saveOrUpdateDailyReportOfService(DailyReportOfService dailyReportOfService) {
        String date = dailyReportOfService.getDate();
        String weaponName = dailyReportOfService.getWeaponName();

        // Retrieve existing report for the given date and service type
        DailyReportOfService existingReport = dailyReportOfServiceRepository.findByDateAndWeaponName(date, weaponName);

        // Check if there is an existing report
        if (existingReport != null) {
            // If an existing report is found, throw an exception to indicate that the entry already exists
            throw new IllegalArgumentException("Daily report of service for the given date and weapon name already exists.");
        } else {
            // If no existing report, save a new one
            dailyReportOfService.setCreatedAt(LocalDateTime.now());
            existingReport = dailyReportOfServiceRepository.save(dailyReportOfService);
        }

        // Update the totals
        updateTotals(existingReport);
        return existingReport;
    }

    @Override
    public TotalsDailyReportOfService saveOrUpdateTotalsDailyReportOfService(TotalsDailyReportOfService totalsDailyReportOfService) {
        return totalsDailyReportOfServiceRepository.save(totalsDailyReportOfService);
    }
    
    
    //06/06/2024 as per manish
    @Override
    public List<TotalsDailyReportOfService> getAllTotalsDailyReportsOfService() {
        List<TotalsDailyReportOfService> allSummaries = totalsDailyReportOfServiceRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(TotalsDailyReportOfService::getId).reversed())
                .collect(Collectors.toList());
    }
    
    //as per manish
    @Override
    public List<DailyReportOfService> getAllDailyReportsOfService() {
        List<DailyReportOfService> allSummaries = dailyReportOfServiceRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(DailyReportOfService::getId).reversed())
                .collect(Collectors.toList());
    }
    
    
/*
    @Override
    public List<DailyReportOfService> getAllDailyReportsOfService() {
        return dailyReportOfServiceRepository.findAll();
    }*/

    /*
    @Override
    public List<TotalsDailyReportOfService> getAllTotalsDailyReportsOfService() {
        return totalsDailyReportOfServiceRepository.findAll();
    }*/

    @Override
    public Map<String, Object> getSummaryAndTotalsByDate(String date) {
        // Implement this method as per your requirements
        return null;
    }

    
    @Override
    public List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String startDate, String endDate) {
        // Implement this method as per your requirements
        return null;
    }

    private void updateTotals(DailyReportOfService report) {
        String date = report.getDate();
        String weaponName = report.getWeaponName();

        try {
            // Fetch the total entry based on date
            List<TotalsDailyReportOfService> totalsDailyReportOfServices = totalsDailyReportOfServiceRepository.findByDate(date);

            TotalsDailyReportOfService totalsDailyReportOfService;
            if (totalsDailyReportOfServices.isEmpty()) {
                // If no entry exists for the given date, create a new one
                totalsDailyReportOfService = new TotalsDailyReportOfService();
                totalsDailyReportOfService.setDate(date);
                totalsDailyReportOfService.setWeaponName(weaponName);
                // Initialize the count fields with the values from the current report
                //--------------------------------
                totalsDailyReportOfService.setTotalReceivedCount(report.getReceivedCount());
                totalsDailyReportOfService.setTotalPreviousCount(report.getPreviousCount());
                totalsDailyReportOfService.setTotalBalancedCount(report.getBalancedCount());
                totalsDailyReportOfService.setTotalIssuedCount(report.getIssuedCount());
                // You can initialize other count fields here similarly
            } else {
                // Get the first total from the list (assuming there should be only one total for each date)
                totalsDailyReportOfService = totalsDailyReportOfServices.get(0);
                // Update the existing totals with the count values from the current report

                //-----------------------------------------------------
                
                totalsDailyReportOfService.setTotalReceivedCount(add(report.getReceivedCount(), totalsDailyReportOfService.getTotalReceivedCount()));
                totalsDailyReportOfService.setTotalPreviousCount(add(report.getPreviousCount(), totalsDailyReportOfService.getTotalPreviousCount()));
                totalsDailyReportOfService.setTotalBalancedCount(add(report.getBalancedCount(), totalsDailyReportOfService.getTotalBalancedCount()));
                totalsDailyReportOfService.setTotalIssuedCount(add(report.getIssuedCount(), totalsDailyReportOfService.getTotalIssuedCount()));
                // You can update other count fields here similarly
            }

            // Save the updated totals
            totalsDailyReportOfServiceRepository.save(totalsDailyReportOfService);
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
