package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.OutwardWorkshopPrefix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutwardWorkshopPrefixRepository extends JpaRepository<OutwardWorkshopPrefix, Long> {

    OutwardWorkshopPrefix findTopByOrderByIdDesc();
}
