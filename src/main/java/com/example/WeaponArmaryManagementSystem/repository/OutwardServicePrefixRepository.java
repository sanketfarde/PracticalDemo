package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.WeaponArmaryManagementSystem.model.OutwardServicePrefix;

public interface OutwardServicePrefixRepository extends JpaRepository<OutwardServicePrefix, Long>{

	OutwardServicePrefix findTopByOrderByIdDesc();

}
