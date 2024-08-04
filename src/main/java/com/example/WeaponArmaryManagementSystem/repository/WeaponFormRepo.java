package com.example.WeaponArmaryManagementSystem.repository;



import com.example.WeaponArmaryManagementSystem.model.WeaponForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeaponFormRepo extends JpaRepository<WeaponForm,Integer>{

	List<WeaponForm> findByWeaponType(String string);
	List<WeaponForm> findAll();
	WeaponForm findByWeaponName(String weaponName);

//	@Query("SELECT wf.weaponName FROM WeaponForm wf WHERE wf.weaponType = :weaponType")
@Query("SELECT w.weaponName FROM WeaponForm w WHERE w.weaponType = 'weapon'")
List<String> findWeaponNamesByWeaponType(@Param("weaponType") String weaponType);

//	@Query("SELECT wf.roundName FROM WeaponForm wf WHERE wf.weaponType = :weaponType")
@Query("SELECT w.weaponName FROM WeaponForm w WHERE w.weaponType = 'round'")
List<String> findRoundNamesByWeaponType(@Param("weaponType") String weaponType);

    boolean existsByWeaponName(String weaponName);

//Optional<WeaponForm> findByWeaponName1(String name);

}
