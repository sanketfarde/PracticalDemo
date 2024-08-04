package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.Dto.WeaponReportDto;
import com.example.WeaponArmaryManagementSystem.model.WeaponReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface WeaponReportRepo extends JpaRepository<WeaponReport,Integer> {

	Optional<WeaponReport> findByDate(LocalDate date);

	List<WeaponReport> findAllByDate(LocalDate date);

    List<WeaponReport> findByDateBetween(LocalDate startDate, LocalDate endDate);

	WeaponReportDto save(WeaponReportDto weaponReportDto);

	@Query("SELECT w FROM WeaponReport w ORDER BY w.date DESC")
	List<WeaponReport> findAllOrderByDateDesc();

}
