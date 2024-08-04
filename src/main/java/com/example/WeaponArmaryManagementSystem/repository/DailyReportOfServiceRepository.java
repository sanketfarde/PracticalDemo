package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.DailyReportOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyReportOfServiceRepository extends JpaRepository<DailyReportOfService,Integer> {

    DailyReportOfService findByDateAndWeaponName(String date, String weaponName);

	DailyReportOfService findByWeaponNameAndDate(String weaponName, String date);

	DailyReportOfService findByWeaponName(String weaponName);

	List<DailyReportOfService> findAllByDate(String date);

    List<DailyReportOfService> findByDateBetween(String startDate, String endDate);

	@Query("SELECT w FROM DailyReportOfService w ORDER BY w.date DESC")
	List<DailyReportOfService> findAllOrderByDateDesc();

//	List<DailyReportOfService> findAllByDate(LocalDate date);

}