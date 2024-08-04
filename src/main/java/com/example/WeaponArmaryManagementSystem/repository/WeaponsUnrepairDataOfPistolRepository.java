package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.WeaponsUnrepairDataOfPistol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeaponsUnrepairDataOfPistolRepository extends JpaRepository<WeaponsUnrepairDataOfPistol, Long> {
   
	List<WeaponsUnrepairDataOfPistol> findByDate(String date);

    List<WeaponsUnrepairDataOfPistol> findByDateBetween(String startDate, String endDate);

    List<WeaponsUnrepairDataOfPistol> findByWeaponName(String weaponName);

    List<WeaponsUnrepairDataOfPistol> findByWeaponNameAndDate(String weaponName, String date);
}
