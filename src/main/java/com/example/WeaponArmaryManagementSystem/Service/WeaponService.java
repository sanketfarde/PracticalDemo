package com.example.WeaponArmaryManagementSystem.Service;


import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReport;

import java.util.List;
import java.util.Map;

public interface WeaponService {
    // Map<String, Object> getWeaponsAndTotals();
    WeaponsDailyReport saveOrUpdateWeapon(WeaponsDailyReport weaponsDailyReport);

    Map<String, Object> getWeaponsAndTotalsByDate(String date);

    List<Map<String, Object>> getWeaponsAndTotalsBetweenDates(String startDate, String endDate);


    List<WeaponsDailyReport> getAllWeaponsDailyReports();
}