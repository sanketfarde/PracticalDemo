package com.example.WeaponArmaryManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.WeaponArmaryManagementSystem.model.Stock;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    List<Stock> findAllByWeaponName(String weaponName);

    List<Stock> findAllByRoundName(String roundName);

    @Query("SELECT s FROM Stock s ORDER BY s.inDate DESC")
    List<Stock> findAllByInDateDesc();

    @Query("SELECT d FROM Stock d WHERE d.inDate BETWEEN :startDate AND :endDate")
    List<Stock> findByDateBetween(String startDate, String endDate);

    @Query("SELECT SUM(CAST(s.recievedQuantity AS long)) FROM Stock s WHERE s.expiryDate < :currentDate AND s.weaponName IS NOT NULL")
    Long countExpiredWeapons(@Param("currentDate") String currentDate);

    @Query("SELECT SUM(CAST(s.recievedQuantity AS long)) FROM Stock s WHERE s.expiryDate < :currentDate AND s.roundName IS NOT NULL")
    Long countExpiredRounds(@Param("currentDate") String currentDate);

    @Query(value = "SELECT d FROM Stock d WHERE d.expiryDate BETWEEN ?1 AND ?2")
    List<Stock> findAlert(String startDate, String endDate);
}
