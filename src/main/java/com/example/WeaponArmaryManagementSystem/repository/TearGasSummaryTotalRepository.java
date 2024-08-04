package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.TearGasSummaryTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TearGasSummaryTotalRepository extends JpaRepository<TearGasSummaryTotal, Integer> {
	
    List<TearGasSummaryTotal> findByDate(String date);

    // Define custom query methods if needed
}
