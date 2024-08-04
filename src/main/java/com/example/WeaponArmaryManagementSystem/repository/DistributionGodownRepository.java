package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.DistributionGodown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface DistributionGodownRepository extends JpaRepository<DistributionGodown,Integer> {
	
	List<DistributionGodown> findByDateAndGodownName(String date, String godownName);

    // 11-06-2024 bell of arm new requirement
    Optional<DistributionGodown> findByWeaponNameAndGodownName(String weaponName, String godownName);

    @Query("SELECT DISTINCT d.weaponName FROM DistributionGodown d WHERE d.date = :date AND d.godownName = :godownName")
    List<String> findWeaponNamesByDateAndGodownName(String date, String godownName);


    @Query("SELECT d.weaponName AS weaponName, SUM(d.quantity) AS totalQuantity " +
            "FROM DistributionGodown d " +
            "WHERE d.date = :date AND d.godownName = :godownName " +
            "GROUP BY d.weaponName")
    List<DistributionGodownSummary> findTotalQuantityByDateAndGodownName(@Param("date") String date, @Param("godownName") String godownName);

    @Query("SELECT d.weaponName, SUM(d.quantity) FROM DistributionGodown d WHERE d.date BETWEEN :startDate AND :endDate GROUP BY d.weaponName")
    List<Object[]> findTotalQuantityByWeaponNameBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<DistributionGodown> findByDateBetween(String startDate, String endDate);

    List<DistributionGodown> findByDate(String date);

    /*@Query("SELECT d.date AS date, d.weaponName AS weaponName, SUM(d.quantity) AS totalQuantity " +
            "FROM DistributionGodown d " +
            "GROUP BY d.date, d.weaponName")
    List<DistributionGodownSummary> findTotalQuantityPerDateAndWeaponName();*/


    @Query("SELECT d.date AS date, d.weaponName AS weaponName, SUM(d.quantity) AS totalQuantity " +
            "FROM DistributionGodown d " +
            "GROUP BY d.date, d.weaponName")
    List<DistributionGodownSummary> findTotalQuantityPerDateAndWeaponName();

    @Query("SELECT d.weaponName AS weaponName, SUM(d.quantity) AS totalQuantity " +
            "FROM DistributionGodown d " +
            "WHERE d.date = :date " +
            "GROUP BY d.weaponName")
    List<DistributionGodownSummary> findTotalQuantityByDate(@Param("date") String date);

	//List<Object[]> findWeaponWiseTotalByDateAndGodownName(String date, String godownName);
	
}
