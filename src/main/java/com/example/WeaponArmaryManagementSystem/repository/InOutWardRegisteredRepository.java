package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WeaponArmaryManagementSystem.model.InoutwardRegistered;

@Repository
public interface InOutWardRegisteredRepository extends JpaRepository<InoutwardRegistered, Long>{
	
	List<InoutwardRegistered> findByStatus(String status);
}
