package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.WorkshopInOutward;
import com.example.WeaponArmaryManagementSystem.repository.WorkshopInOutwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopInOutwardRepository workshopInOutwardRepository;

   /* public List<WorkshopInOutward> getReportsByDateRange(String startDate, String endDate) {
        return workshopInOutwardRepository.findByDateBetween(startDate, endDate);
    }*/

    public List<WorkshopInOutward> getReportsByDateRange(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return workshopInOutwardRepository.findByStatus("0");
    }

    public List<WorkshopInOutward> getReportsByDateRange1(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return workshopInOutwardRepository.findByStatus("1");
        }
}
