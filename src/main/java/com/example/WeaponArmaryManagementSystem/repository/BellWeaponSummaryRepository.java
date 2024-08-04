package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.BellWeaponSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BellWeaponSummaryRepository extends JpaRepository<BellWeaponSummary,Integer> {
	
//	void save(BellWeaponSummary weaponSummary);

	void save(BellWeaponSummaryRepository bellweaponRepository);	

}
