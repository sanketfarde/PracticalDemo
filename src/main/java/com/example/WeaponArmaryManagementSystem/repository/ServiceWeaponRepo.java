package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.ServiceWeapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ServiceWeaponRepo extends JpaRepository<ServiceWeapon, Integer> {
	
    Optional<ServiceWeapon> findById(Integer weapon_id);

    Optional<ServiceWeapon> findByWeaponName(String weaponName);

    @Query("SELECT s.weaponName FROM ServiceWeapon s WHERE s.weaponType = 'Weapon' AND s.status = 'Active' ORDER BY s.weaponName ASC")
    List<String> findActiveWeaponNames();

    @Query("SELECT s.weaponName FROM ServiceWeapon s WHERE s.weaponType = 'Round' AND s.status = 'Active' ORDER BY s.weaponName ASC")
    List<String> findActiveRoundNames();
}
