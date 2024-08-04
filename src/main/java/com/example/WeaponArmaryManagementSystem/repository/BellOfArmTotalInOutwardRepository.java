package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.BellOfArmTotalInOutward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BellOfArmTotalInOutwardRepository extends JpaRepository<BellOfArmTotalInOutward, Long> {
    BellOfArmTotalInOutward findByWeaponNameAndRoundName(String weaponName, String roundName);

//	WarehouseTotalInOutward findByWeaponName(String weaponName);

//	WarehouseTotalInOutward findByRoundName(String roundName);

    List<BellOfArmTotalInOutward> findByWeaponName(String weaponName);
    List<BellOfArmTotalInOutward> findByRoundName(String roundName);


    // this is with new modifications for bell of arms 11-06-2024
    Optional<BellOfArmTotalInOutward> findOneByWeaponName(String weaponName);

    @Query("SELECT SUM(w.totalStock) FROM BellOfArmTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.totalStock) FROM BellOfArmTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM BellOfArmTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalAvailableStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM BellOfArmTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalAvailableStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM BellOfArmTotalInOutward w WHERE w.weaponName IS NOT NULL")
    Integer findTotalDistributionStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM BellOfArmTotalInOutward w WHERE w.roundName IS NOT NULL")
    Integer findTotalDistributionStockWhereRoundNameIsPresent();

}
