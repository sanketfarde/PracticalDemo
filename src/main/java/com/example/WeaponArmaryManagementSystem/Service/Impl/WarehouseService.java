package com.example.WeaponArmaryManagementSystem.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WeaponArmaryManagementSystem.model.WarehouseInOutward;
import com.example.WeaponArmaryManagementSystem.repository.WarehouseInOutwardRepository;



@Service
public class WarehouseService {

    @Autowired
    private WarehouseInOutwardRepository warehouseInOutwardRepository;

   /* public List<WarehouseInOutward> getReportsByDateRange(String startDate, String endDate) {
        return warehouseInOutwardRepository.findByDateBetween(startDate, endDate);
    }*/

    public List<WarehouseInOutward> getReportsByDateRange(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return warehouseInOutwardRepository.findByStatus("0");
    }

    public List<WarehouseInOutward> getReportsByDateRange1(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return warehouseInOutwardRepository.findByStatus("1");
    }
} 