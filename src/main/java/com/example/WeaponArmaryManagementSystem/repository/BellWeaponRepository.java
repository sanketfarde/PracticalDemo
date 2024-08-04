package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.BellWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BellWeaponRepository extends JpaRepository<BellWeapon,Long> {
		
	   @Query("SELECT DISTINCT bw.weaponName FROM BellWeapon bw")
	    List<String> findAllWeaponNames();

    List<BellWeapon> findByWeaponType(String distributionWeapon);

	List<BellWeapon> findByWeaponTypeAndStatus(String weaponType, String status);

}
    //List<BellWeaponSummary> findByDate(String date);

	//List<WeaponsDailyReport> findByDateBetween(String startDate, String endDate);

	//WeaponsDailyReport save(WeaponsDailyReport weaponsDailyReport);

	//WeaponsDailyReport findByDateAndWeaponType(String date, String weaponType);


