package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.BellArmory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BellArmoryRepository extends JpaRepository<BellArmory, Long> {
	
    List<BellArmory> findByDate(String date);

    boolean existsByWeaponNameAndDate(String toString, String date);

   // List<BellArmory> findByDateBetween(String startDate, String endDate);

    BellArmory findByDateAndWeaponName(String date, String weaponName);

    List<BellArmory> findAllByDateBetween(String startDate, String endDate);

    List<BellArmory> findByWeaponNameAndDate(String weaponName, String date);

}
