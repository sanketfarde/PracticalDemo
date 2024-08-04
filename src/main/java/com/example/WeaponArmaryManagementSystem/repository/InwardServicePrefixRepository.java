package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WeaponArmaryManagementSystem.model.InwardServicePrefix;

public interface InwardServicePrefixRepository extends JpaRepository<InwardServicePrefix, Long> {
	
    InwardServicePrefix findTopByOrderByIdDesc();
}
