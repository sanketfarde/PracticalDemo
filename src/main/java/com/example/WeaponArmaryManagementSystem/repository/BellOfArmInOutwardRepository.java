package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.BellOfArmInOutward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BellOfArmInOutwardRepository extends JpaRepository<BellOfArmInOutward, Long> {
    List<BellOfArmInOutward> findByStatus(String status);

    BellOfArmInOutward findTopByOrderByIdDesc();

    List<BellOfArmInOutward> findByDateBetween(String startDate, String endDate);

    List<BellOfArmInOutward> findAllByDate(String date);

    List<BellOfArmInOutward> findByStatusAndDateBetween(String string, String startDate, String endDate);
}
