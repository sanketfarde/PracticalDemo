package com.example.WeaponArmaryManagementSystem.Service.Impl;


import com.example.WeaponArmaryManagementSystem.Dto.WeaponReportDto;
import com.example.WeaponArmaryManagementSystem.model.WeaponReport;
import com.example.WeaponArmaryManagementSystem.model.WeaponReportMapper;
import com.example.WeaponArmaryManagementSystem.repository.WeaponReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeaponReportService {
	
	@Autowired
    private WeaponReportRepo weaponReportRepo;

    public List<WeaponReport> getReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        return weaponReportRepo.findByDateBetween(startDate, endDate);
    }

    public void saveWeaponReport(WeaponReportDto weaponReportDto) {
        // Convert DTO to entity
        WeaponReport weaponReport = WeaponReportMapper.toEntity(weaponReportDto);

        // Set createdAt and updatedAt timestamps
        LocalDateTime now = LocalDateTime.now();
        weaponReport.setCreatedAt(now);
        weaponReport.setUpdatedAt(now);

        // Save the entity
        weaponReportRepo.save(weaponReport);
    }

}
