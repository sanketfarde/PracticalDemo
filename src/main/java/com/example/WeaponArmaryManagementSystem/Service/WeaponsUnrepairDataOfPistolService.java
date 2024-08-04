package com.example.WeaponArmaryManagementSystem.Service;



import com.example.WeaponArmaryManagementSystem.Dto.WeaponsUnrepairDataOfPistolDto;
import com.example.WeaponArmaryManagementSystem.model.WeaponsUnrepairDataOfPistol;

import java.util.List;

public interface WeaponsUnrepairDataOfPistolService {


    WeaponsUnrepairDataOfPistol saveUnrepairData(WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto);

    List<WeaponsUnrepairDataOfPistol> getUnrepairDataByDate(String date);

    List<WeaponsUnrepairDataOfPistol> getAllUnrepairData();

    List<WeaponsUnrepairDataOfPistol> getUnrepairDataBetweenDates(String startDate, String endDate);

    List<WeaponsUnrepairDataOfPistol> getUnrepairDataByWeaponName(String weaponName);

    List<WeaponsUnrepairDataOfPistol> getUnrepairDataByWeaponNameAndDate(String weaponName, String date);

    WeaponsUnrepairDataOfPistol updateUnrepairDataByWeaponNameAndDate(String weaponName, String date, WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto);

    List<WeaponsUnrepairDataOfPistol> findByWeaponNameAndDate(String weaponName,String date);
}
