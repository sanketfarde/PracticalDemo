package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.WeaponArmaryManagementSystem.model.WarehouseTotalInOutward;

public interface WarehouseTotalInOutwardRepository extends JpaRepository<WarehouseTotalInOutward, Long> {
	
    WarehouseTotalInOutward findByWeaponNameAndRoundName(String weaponName, String roundName);

//	WarehouseTotalInOutward findByWeaponName(String weaponName);

//	WarehouseTotalInOutward findByRoundName(String roundName);
	
	 List<WarehouseTotalInOutward> findByWeaponName(String weaponName);
	 List<WarehouseTotalInOutward> findByRoundName(String roundName);
	 
	 @Query("SELECT SUM(w.totalStock) FROM WarehouseTotalInOutward w WHERE w.weaponName IS NOT NULL")
	    Integer findTotalStockWhereWeaponNameIsPresent();
	 
	 @Query("SELECT SUM(w.totalStock) FROM WarehouseTotalInOutward w WHERE w.roundName IS NOT NULL")
	    Integer findTotalStockWhereRoundNameIsPresent();
	 
	 @Query("SELECT SUM(w.availableStock) FROM WarehouseTotalInOutward w WHERE w.weaponName IS NOT NULL")
	    Integer findTotalAvailableStockWhereWeaponNameIsPresent();
	 
	 @Query("SELECT SUM(w.availableStock) FROM WarehouseTotalInOutward w WHERE w.roundName IS NOT NULL")
	    Integer findTotalAvailableStockWhereRoundNameIsPresent();
	 
	 @Query("SELECT SUM(w.distributionStock) FROM WarehouseTotalInOutward w WHERE w.weaponName IS NOT NULL")
	    Integer findTotalDistributionStockWhereWeaponNameIsPresent();
	 
	 @Query("SELECT SUM(w.distributionStock) FROM WarehouseTotalInOutward w WHERE w.roundName IS NOT NULL")
	    Integer findTotalDistributionStockWhereRoundNameIsPresent();
	 
}
