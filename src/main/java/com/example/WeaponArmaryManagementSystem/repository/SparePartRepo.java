package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.SparePart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SparePartRepo extends JpaRepository<SparePart,Integer>{

    List<SparePart> findByWeaponName(String weaponName);

    List<SparePart> findAllByOrderByCreatedAtDesc();

	List<SparePart> findByCreatedAtBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

	Optional<SparePart> findBySparePartName(String name);

    Optional<SparePart> findByWeaponNameAndSparePartName(String weaponName, String sparePartName);

//	List<SparePart> findAllByOrderBySparePartNameAsc();

}
