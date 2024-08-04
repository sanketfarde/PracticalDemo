package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.WeaponArmaryManagementSystem.model.Material;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends  JpaRepository<Material, Long>{

    @Query("SELECT d FROM Material d ORDER BY d.materialName ASC")
    List<Material> findAllSortedByMaterial();


    @Query("SELECT s.materialName FROM Material s WHERE s.weaponType = 'Weapon' AND s.status = 'Active' ORDER BY s.materialName ASC")
    List<String> findActiveWeaponNames();

    @Query("SELECT s.materialName FROM Material s WHERE s.weaponType = 'Round' AND s.status = 'Active' ORDER BY s.materialName ASC")
    List<String> findActiveRoundNames();


    @Query("SELECT m FROM Material m WHERE m.materialName = :materialName")
    Optional<Material> findByAccessoriesMaterialName(String materialName);

    Material findByWeaponType(String weaponType);
    Material findByMaterialName(String materialName);


}
