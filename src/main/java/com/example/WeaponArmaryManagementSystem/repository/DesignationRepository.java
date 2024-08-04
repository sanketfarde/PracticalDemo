package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
    boolean existsByDesignation(String designation);

    @Query("SELECT d FROM Designation d ORDER BY d.designation ASC")
    List<Designation> findAllSortedByDesignation();

    @Query("SELECT s.designation FROM Designation s WHERE s.status = 'Active' ORDER BY s.designation ASC")
    List<String> findActiveDesignations();
}
