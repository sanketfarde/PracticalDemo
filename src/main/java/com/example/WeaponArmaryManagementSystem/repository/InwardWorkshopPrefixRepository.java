package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.InwardWorkshopPrefix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InwardWorkshopPrefixRepository extends JpaRepository<InwardWorkshopPrefix, Long> {

    InwardWorkshopPrefix findTopByOrderByIdDesc();
}
