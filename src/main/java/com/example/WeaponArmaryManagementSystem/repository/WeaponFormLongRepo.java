package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.WeaponFormLong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeaponFormLongRepo extends JpaRepository<WeaponFormLong,Integer> {

    List<WeaponFormLong> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT form FROM WeaponFormLong form ORDER BY form.date DESC")
    List<WeaponFormLong> findAllByInDateDesc();

}
