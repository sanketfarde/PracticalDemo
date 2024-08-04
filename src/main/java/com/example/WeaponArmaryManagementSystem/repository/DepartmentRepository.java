package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WeaponArmaryManagementSystem.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}



