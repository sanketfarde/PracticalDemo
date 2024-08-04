package com.example.WeaponArmaryManagementSystem.Service;


import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DailyReportOfServiceService {
	
    DailyReportOfService saveOrUpdateDailyReportOfService(DailyReportOfService dailyReportOfService);
    
    TotalsDailyReportOfService saveOrUpdateTotalsDailyReportOfService(TotalsDailyReportOfService totalsDailyReportOfService);
   
    List<DailyReportOfService> getAllDailyReportsOfService();
   
    List<TotalsDailyReportOfService> getAllTotalsDailyReportsOfService();
   
    Map<String, Object> getSummaryAndTotalsByDate(String date);
   
    List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String startDate, String endDate);  
    
} 

