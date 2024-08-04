package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.Dto.AddTeargasWeaponsDto;
import com.example.WeaponArmaryManagementSystem.Exception.DuplicateEntryException;
import com.example.WeaponArmaryManagementSystem.Service.AddTeargasWeaponsService;
import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;
import com.example.WeaponArmaryManagementSystem.repository.AddTeargasWeaponsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AddTeargasWeaponsServiceImpl implements AddTeargasWeaponsService {


    @Autowired
    private AddTeargasWeaponsRepository addTeargasWeaponsRepository;
    @Override
    @Transactional
    public void saveTeargasWeapons(AddTeargasWeaponsDto addTeargasWeaponsDto) {
        // Check if an entry with the same weaponName and date already exists
        List<AddTeargasWeapons> existingRecords = addTeargasWeaponsRepository
                .findByWeaponNameAndDate(addTeargasWeaponsDto.getDate(), addTeargasWeaponsDto.getDate());

        if (!existingRecords.isEmpty()) {
            // If an entry exists, throw an exception
            throw new DuplicateEntryException("Weapon name and date already entered for the same day.");
        }

        // If no entry exists, create a new record
        AddTeargasWeapons record = new AddTeargasWeapons();
        record.setDate(addTeargasWeaponsDto.getDate());
       // record.setWeaponName(addTeargasWeaponsDto.getWeaponName());
        record.setRemark(addTeargasWeaponsDto.getRemark());
       // record.setWeaponName(addTeargasWeaponsDto.getWeaponName()); // Assuming Teargas Weapon is the default weapon type
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String partNameJson = objectMapper.writeValueAsString(addTeargasWeaponsDto.getWeaponCount());
            String quantityJson = objectMapper.writeValueAsString(addTeargasWeaponsDto.getWeaponName());
            record.setWeaponName(quantityJson);
            record.setWeaponCount(partNameJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert part names to JSON", e);
        }

        AddTeargasWeapons savedRecord = addTeargasWeaponsRepository.save(record);

    }

   /* @Override
    public List<AddTeargasWeapons> getAllTeargasWeapons() {
        return addTeargasWeaponsRepository.findAll();
    }*/

    @Override
    public List<AddTeargasWeapons> getAllTeargasWeapons() {
        List<AddTeargasWeapons> allSummaries = addTeargasWeaponsRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(AddTeargasWeapons::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddTeargasWeapons> getTeargasWeaponById(Long id) {
        return addTeargasWeaponsRepository.findById(id);
    }

}



/*    @Override
    public List<AddTeargasWeapons> getTeargasWeaponsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return addTeargasWeaponsRepository.findbeetweentwodates(startDate, endDate);
    }*/




