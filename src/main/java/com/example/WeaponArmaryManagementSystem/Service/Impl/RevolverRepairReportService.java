package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.RevolverRepairReport;
import com.example.WeaponArmaryManagementSystem.repository.RevolverRepairReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevolverRepairReportService {

	@Autowired
    private RevolverRepairReportRepository revolverRepairReportRepository;

    public List<RevolverRepairReport> getReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        return revolverRepairReportRepository.findByDateBetween(startDate, endDate);
    }
}
