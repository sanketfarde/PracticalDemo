package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.RepaireRevolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepaireRevolverRepository extends JpaRepository<RepaireRevolver,Integer>{

	Optional<RepaireRevolver> findById(Long id);

    Optional<RepaireRevolver> findByRevolverName(String revolverName);

    List<RepaireRevolver> findByStatus(String status);
}
