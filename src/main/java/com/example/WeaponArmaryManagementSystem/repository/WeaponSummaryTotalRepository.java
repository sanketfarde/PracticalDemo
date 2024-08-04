package com.example.WeaponArmaryManagementSystem.repository;
import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReportTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponSummaryTotalRepository extends JpaRepository<WeaponsDailyReportTotal,Long> {
	
    List<WeaponsDailyReportTotal> findByDate(String date);
    
    WeaponsDailyReportTotal findByDateAndWeaponType(String date, String weaponType);

}