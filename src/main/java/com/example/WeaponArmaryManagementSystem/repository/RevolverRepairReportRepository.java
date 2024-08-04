package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.RevolverRepairReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
//public interface RevolverRepairReportRepository extends JpaRepository<RevolverRepairReport, Long>{
	public interface RevolverRepairReportRepository extends JpaRepository<RevolverRepairReport, Long> {

	List<RevolverRepairReport> findAllByDate(LocalDate date);

    List<RevolverRepairReport> findByDateBetween(LocalDate startDate, LocalDate endDate);

	@Query("SELECT w FROM RevolverRepairReport w ORDER BY w.date DESC")
	List<RevolverRepairReport> findAllOrderByDateDesc();

	@Query("SELECT r FROM RevolverRepairReport r WHERE r.buttNo = :buttNo")
	List<RevolverRepairReport> findAllByButtNo(@Param("buttNo") String buttNo);

}
