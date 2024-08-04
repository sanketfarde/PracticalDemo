package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.WareHouseWeaponsAdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WareHouseWeaponsAddRepository extends JpaRepository<WareHouseWeaponsAdd,Integer> {
	
    Optional<WareHouseWeaponsAdd> findByWeaponName(String weaponName);

    boolean existsByWeaponName(String weaponName);

    @Query("SELECT w.weaponName FROM WareHouseWeaponsAdd w WHERE w.weaponType = 'weapon'")
    List<String> findWeaponNamesByWeaponType();
    
    @Query("SELECT w.weaponName FROM WareHouseWeaponsAdd w WHERE w.weaponType = 'round'")
    List<String> findRoundNamesByWeaponType();

}
