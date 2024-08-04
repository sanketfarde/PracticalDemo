package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.WeaponSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface WeaponSummaryRepo extends JpaRepository<WeaponSummary,Integer>{

	Optional<WeaponSummary> findByWeaponNameAndDate(String weaponName, Date date);

	List<WeaponSummary> findAllByDate(Date date);

	List<WeaponSummary> findByDate(Date sqlDate);

	List<WeaponSummary> findByDateBetween(java.util.Date startDate, java.util.Date endDate);

	List<WeaponSummary> findAllByDate(LocalDate date);

	Optional<WeaponSummary> findByDate(LocalDate date);

	


	

}
