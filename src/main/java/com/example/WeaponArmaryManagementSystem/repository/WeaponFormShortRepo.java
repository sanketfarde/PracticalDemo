package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.WeaponFormShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeaponFormShortRepo extends JpaRepository<WeaponFormShort,Integer> {

    List<WeaponFormShort> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<WeaponFormShort> findAllByOrderByDateDesc();

}
