package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.Dto.AddTeargasWeaponsDto;
import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;

import java.util.List;
import java.util.Optional;

public interface AddTeargasWeaponsService {

    void saveTeargasWeapons(AddTeargasWeaponsDto addTeargasWeaponsDto);

    List<AddTeargasWeapons> getAllTeargasWeapons();

    Optional<AddTeargasWeapons> getTeargasWeaponById(Long id);






    /*    List<AddTeargasWeapons> getTeargasWeaponsBetweenDates(LocalDate startDate, LocalDate endDate);*/



}
