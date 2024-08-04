package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.BellOfArmInOutward;
import com.example.WeaponArmaryManagementSystem.repository.BellOfArmInOutwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BellOfArmService {
    @Autowired
    private BellOfArmInOutwardRepository bellOfArmInOutwardRepository;

   /* public List<BellOfArmInOutward> getReportsByDateRange(String startDate, String endDate) {
        return bellOfArmInOutwardRepository.findByDateBetween(startDate, endDate);
    }
   */

    public List<BellOfArmInOutward> getReportsByDateRange(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return bellOfArmInOutwardRepository.findByStatus("0");
    }

    public List<BellOfArmInOutward> getReportsByDateRange1(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return bellOfArmInOutwardRepository.findByStatus("1");
        }
}
