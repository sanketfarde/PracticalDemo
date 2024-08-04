package com.example.WeaponArmaryManagementSystem.Service.Impl;


import com.example.WeaponArmaryManagementSystem.Service.WareHouseWeaponsAddService;
import com.example.WeaponArmaryManagementSystem.model.TearGasSummary;
import com.example.WeaponArmaryManagementSystem.model.WareHouseWeaponsAdd;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseWeaponsAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareHouseWeaponsAddServiceImpl implements WareHouseWeaponsAddService {

    @Autowired
    private WareHouseWeaponsAddRepository wareHouseWeaponsAddRepository;

    @Override
    public void saveWareHouseWeaponsAdd(String weaponName) {

    }
    

    @Override
    public void saveWareHouseWeaponsAdd(WareHouseWeaponsAdd wareHouseWeaponsAdd) {
        // Check if a record with the same weaponName already exists
        if (wareHouseWeaponsAddRepository.existsByWeaponName(wareHouseWeaponsAdd.getWeaponName())) {
            throw new IllegalStateException("{\"message\": \"This weapon already exists...Id=2\"}");
        }

        // Set created and updated timestamps
        wareHouseWeaponsAdd.setCreatedAt(LocalDateTime.now());
        wareHouseWeaponsAdd.setUpdatedAt(LocalDateTime.now());

        // Save the wareHouseWeaponsAdd entity
        wareHouseWeaponsAddRepository.save(wareHouseWeaponsAdd);
    }


    //New Get all for the decending order for the list of weapons
    @Override
    public List<WareHouseWeaponsAdd> getAllWareHouseWeaponsAdd() {
        List<WareHouseWeaponsAdd> allSummaries = wareHouseWeaponsAddRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(WareHouseWeaponsAdd::getId).reversed())
                .collect(Collectors.toList());
    }


    @Override
    public WareHouseWeaponsAdd getWareHouseWeaponsAddById(Integer id) {
        return wareHouseWeaponsAddRepository.findById(id).orElse(null);
    }


    @Override
    public void deleteWareHouseWeaponsAddById(Integer id) {
        wareHouseWeaponsAddRepository.deleteById(id);
    }
}
