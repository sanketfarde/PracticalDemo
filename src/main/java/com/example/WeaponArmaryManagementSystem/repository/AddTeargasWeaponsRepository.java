package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AddTeargasWeaponsRepository extends JpaRepository<AddTeargasWeapons, Long> {
    List<AddTeargasWeapons> findByWeaponNameAndDate(String s, String date);



    /*    List<AddTeargasWeapons> findbeetweentwodates(LocalDate startDate, LocalDate endDate);*/

}
