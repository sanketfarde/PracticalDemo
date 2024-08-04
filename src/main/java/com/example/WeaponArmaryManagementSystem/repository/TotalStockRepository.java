package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WeaponArmaryManagementSystem.model.TotalStock;

import java.util.List;

@Repository
public interface TotalStockRepository extends JpaRepository<TotalStock, Long> {

	//TotalStock findByMaterialName(String materialName);

	TotalStock findByWeaponNameAndRoundName(String weaponName, String roundName);

	/*TotalStock findByWeaponName(String weaponName);

	TotalStock findByRoundName(String roundName);*/

	List<TotalStock> findByWeaponName(String weaponName);
	List<TotalStock> findByRoundName(String roundName);
}
