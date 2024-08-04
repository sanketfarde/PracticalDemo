package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.InwardBellOfArmPrefix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InwardBellOfArmPrefixRepository extends JpaRepository<InwardBellOfArmPrefix, Long> {
    InwardBellOfArmPrefix findTopByOrderByIdDesc();
}
