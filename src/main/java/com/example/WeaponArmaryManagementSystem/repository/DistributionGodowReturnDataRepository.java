package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeaponArmaryManagementSystem.model.DistributionGodowReturnData;

public interface DistributionGodowReturnDataRepository extends JpaRepository<DistributionGodowReturnData,Integer> {

	List<DistributionGodowReturnData> findByReturnDateAndGodownName(String returnDate, String godownName);

	List<DistributionGodowReturnData> findByReturnDateBetween(String startDate, String endDate);

/*	List<DistributionGodowReturnData> findBydistributeDateAndGodownName(String distributeDate, String godownName);*/

	List<DistributionGodowReturnData> findBydistributeDateAndGodownName(String distributeDate, String godownName);
}
