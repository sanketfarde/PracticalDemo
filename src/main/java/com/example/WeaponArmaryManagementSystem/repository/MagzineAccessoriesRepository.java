package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.MagzineAccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagzineAccessoriesRepository extends JpaRepository<MagzineAccessories, Long> {

    @Query("SELECT d FROM MagzineAccessories d ORDER BY d.accessoriesName ASC")
    List<MagzineAccessories> findAllSortedByAccessoriesNames();

    @Query("SELECT s.accessoriesName FROM MagzineAccessories s WHERE s.status = 'Active' ORDER BY s.accessoriesName ASC")
    List<String> findActiveAccessoriesNames();

 //   @Query("SELECT new com.example.WeaponArmaryManagementSystem.dto.MagzineAccessoriesDTO(s.id, s.accessoriesName) FROM MagzineAccessories s WHERE s.status = 'Active' ORDER BY s.accessoriesName ASC")
 //   List<MagzineAccessoriesDTO> findActiveAccessoriesNames();
}
