package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.TotalsDailyReportOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalsDailyReportOfServiceRepository extends JpaRepository<TotalsDailyReportOfService,Integer> {
  
	List<TotalsDailyReportOfService> findByDate(String date);

	TotalsDailyReportOfService findByWeaponName(String weaponName);

	TotalsDailyReportOfService findByWeaponNameAndDate(String weaponName, String date);

	TotalsDailyReportOfService findFirstByDateOrderByCreatedAtDesc(String date);

	List<TotalsDailyReportOfService> findAllByDate(String date);

	List<TotalsDailyReportOfService> findByDateBetween(String startDate, String endDate);

	@Query("SELECT w FROM TotalsDailyReportOfService w ORDER BY w.date DESC")
	List<TotalsDailyReportOfService> findAllOrderByDateDesc();

//	TotalsDailyReportOfService findFirstByDateOrderByCreatedAtDesc(Date date);
	
	//TotalsDailyReportOfService findFirstByDateOrderByCreatedAtDesc(String date);

}
