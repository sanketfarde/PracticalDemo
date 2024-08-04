package com.example.WeaponArmaryManagementSystem.Service;


import com.example.WeaponArmaryManagementSystem.Dto.AddTeargasWeaponsDto;
import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummaryTotal;

import java.util.List;
import java.util.Map;

public interface TearGasSummaryService {
	
    TearGasSummary saveOrUpdateTearGasSummary(TearGasSummary tearGasSummary);

    Map<String, Object> getSummaryAndTotalsByDate(String date);

   // List<Map<String, Object>> getSummaryAndTotalsBetweenDates(LocalDate startDate, LocalDate endDate);

    List<TearGasSummary> getAllTearGasSummaries();

    List<TearGasSummaryTotal> getAllTearGasSummaryTotals();

    TearGasSummaryTotal saveOrUpdateTearGasSummaryTotal(TearGasSummaryTotal tearGasSummaryTotal);

    List<Map<String, Object>> getSummaryAndTotalsBetweenDates(String toString, String toString1);

	List<TearGasSummaryTotal> getAllTearGasSummariesTotal();



}
