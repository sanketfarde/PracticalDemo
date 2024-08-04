package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.ReturnMagzine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnMagzineRepository extends JpaRepository<ReturnMagzine, Long> {

  //  List<ReturnMagzine> findByDistributionType(String distributionType);
}
