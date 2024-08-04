package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WeaponArmaryManagementSystem.model.PrefixConfig;

public interface PrefixConfigRepository extends JpaRepository<PrefixConfig, Long> {
	
    PrefixConfig findTopByOrderByIdDesc();

	PrefixConfig findTopByDepartmentIdOrderByIdDesc(String departmentId);
	
	
	
}
