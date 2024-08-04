package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.OutwardBellOfArmPrefix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutwardBellOfArmPrefixRepository extends JpaRepository<OutwardBellOfArmPrefix, Long> {
    OutwardBellOfArmPrefix findTopByOrderByIdDesc();
}
