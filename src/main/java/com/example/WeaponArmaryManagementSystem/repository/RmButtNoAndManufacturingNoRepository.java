package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.model.OutwardItems;
import com.example.WeaponArmaryManagementSystem.model.RmButtNoAndManufacturingNo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RmButtNoAndManufacturingNoRepository extends JpaRepository<RmButtNoAndManufacturingNo, Long> {

    int countByReturnIdAndButtNoIsNotNull(Long returnId);

    int countByReturnIdAndManufacturingNoIsNotNull(Long returnId);

    List<RmButtNoAndManufacturingNo> findByReturnId(Long returnId);
}
