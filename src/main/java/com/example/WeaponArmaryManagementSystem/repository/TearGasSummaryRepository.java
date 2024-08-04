package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TearGasSummaryRepository extends JpaRepository<TearGasSummary, Integer> {
	
    List<TearGasSummary> findByDate(String date);

    List<TearGasSummary> findByDateBetween(String startDate, String endDate);

    TearGasSummary findByDateAndTeargasAmunities(String date, String teargasAmunities);

}
