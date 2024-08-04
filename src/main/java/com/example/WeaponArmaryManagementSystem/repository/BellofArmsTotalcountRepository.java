package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.BellofArmsTotalcount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BellofArmsTotalcountRepository extends JpaRepository<BellofArmsTotalcount,Long> {
	
    BellofArmsTotalcount findByDate(String date);

    BellofArmsTotalcount findByDateAndWeaponName(String date, String weaponName);

}
