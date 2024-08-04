package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import com.example.WeaponArmaryManagementSystem.repository.DailyReportOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyReportOfServiceServices {
    
    @Autowired
    private DailyReportOfServiceRepository dailyReportOfServiceRepository;

    public List<DailyReportOfService> getReportsByDateRange(String startDate, String endDate) {
        return dailyReportOfServiceRepository.findByDateBetween(startDate, endDate);
    }
}

