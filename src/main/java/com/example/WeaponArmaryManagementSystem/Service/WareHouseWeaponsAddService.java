package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.WareHouseWeaponsAdd;

import java.util.List;

public interface WareHouseWeaponsAddService {

    void saveWareHouseWeaponsAdd(String weaponName);

    void saveWareHouseWeaponsAdd(WareHouseWeaponsAdd wareHouseWeaponsAdd);

    List<WareHouseWeaponsAdd> getAllWareHouseWeaponsAdd();

    WareHouseWeaponsAdd getWareHouseWeaponsAddById(Integer id);

    void deleteWareHouseWeaponsAddById(Integer id);

}
