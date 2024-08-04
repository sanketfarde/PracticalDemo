package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.BellArmory;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BellOfArmsReportRepository extends JpaRepository<BellOfArmsReport,Long> {

  List<BellOfArmsReport>  findByButtNo(String buttNo);

    List<BellOfArmsReport> findByDepositedDate(String  depositedDate);

  List<BellOfArmsReport> findByDepositedDateBetween(String startDate, String endDate);



}
