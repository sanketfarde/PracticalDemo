package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;
import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmButtNoAndManufacturingNoRepository extends JpaRepository<DmButtNoAndManufacturingNo, Long> {
    int countByDistributionIdAndButtNoIsNotNull(Long distributionId);

    int countByDistributionIdAndManufacturingNoIsNotNull(Long distributionId);

    List<DmButtNoAndManufacturingNo> findByDistributionId(Long distributionId);
}
