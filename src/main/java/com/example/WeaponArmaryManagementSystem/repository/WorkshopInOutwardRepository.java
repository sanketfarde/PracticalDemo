package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.WorkshopInOutward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopInOutwardRepository extends JpaRepository<WorkshopInOutward, Long> {

    List<WorkshopInOutward> findByStatus(String status);

    WorkshopInOutward findTopByOrderByIdDesc();

    List<WorkshopInOutward> findByDateBetween(String startDate, String endDate);

    List<WorkshopInOutward> findAllByDate(String date);

    List<WorkshopInOutward> findByStatusAndDateBetween(String string, String startDate, String endDate);

}
