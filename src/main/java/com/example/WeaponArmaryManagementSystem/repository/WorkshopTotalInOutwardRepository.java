package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.WorkshopTotalInOutward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopTotalInOutwardRepository extends JpaRepository<WorkshopTotalInOutward, Long> {


    WorkshopTotalInOutward findByWeaponNameAndRoundName(String weaponName, String roundName);

//	WarehouseTotalInOutward findByWeaponName(String weaponName);

//	WarehouseTotalInOutward findByRoundName(String roundName);

    List<WorkshopTotalInOutward> findByWeaponName(String weaponName);
    List<WorkshopTotalInOutward> findByRoundName(String roundName);

    @Query("SELECT SUM(w.totalStock) FROM WorkshopTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.totalStock) FROM WorkshopTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM WorkshopTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalAvailableStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM WorkshopTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalAvailableStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM WorkshopTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalDistributionStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM WorkshopTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalDistributionStockWhereRoundNameIsPresent();

}
