package com.example.WeaponArmaryManagementSystem.Service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.WeaponArmaryManagementSystem.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WeaponArmaryManagementSystem.repository.StockRepository;

@Service
public class StockService {
	
    @Autowired
    private StockRepository stockRepository;

    public Long getExpiredWeaponsCount() {
        // Convert current date to the same format as your expiryDate strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = dateFormat.format(new Date());
        
        return stockRepository.countExpiredWeapons(currentDateStr);
    }
    
    public Long getExpiredRoundsCount() {
        // Convert current date to the same format as your expiryDate strings
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = dateFormat.format(new Date());
        
        return stockRepository.countExpiredRounds(currentDateStr);
    }

    public List<Stock> getReportsByDateRange(String startDate, String endDate) {
        return stockRepository.findByDateBetween(startDate, endDate);
    }
}
