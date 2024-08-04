package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import com.example.WeaponArmaryManagementSystem.repository.TotalsDailyReportOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalsDailyReportOfServiceServices {

	 @Autowired
	    private TotalsDailyReportOfServiceRepository totalsDailyReportOfServiceRepository;

	    public List<TotalsDailyReportOfService> getReportsByDateRange(String startDate, String endDate) {
	        return totalsDailyReportOfServiceRepository.findByDateBetween(startDate, endDate);
	    }

		
}
