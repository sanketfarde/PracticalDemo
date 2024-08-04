package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeaponArmaryManagementSystem.model.PrefixConfig;
import com.example.WeaponArmaryManagementSystem.model.PrefixConfig1;

public interface PrefixConfig1Repository extends JpaRepository<PrefixConfig1, Long> {
	
	PrefixConfig1 findTopByOrderByIdDesc();

	PrefixConfig1 findTopByDepartmentIdOrderByIdDesc(String departmentId);

	//void save(PrefixConfig prefixConfig);

}
