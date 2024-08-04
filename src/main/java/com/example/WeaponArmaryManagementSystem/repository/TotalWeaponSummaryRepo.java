package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.TotalWeaponSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface TotalWeaponSummaryRepo extends JpaRepository<TotalWeaponSummary, Integer> {
    
    Optional<TotalWeaponSummary> findByDate(Date date);

	Optional<TotalWeaponSummary> findByDate(LocalDate date);

	List<TotalWeaponSummary> findAllByDate(LocalDate date);

	Optional<TotalWeaponSummary> findByDate(java.util.Date date);

}
