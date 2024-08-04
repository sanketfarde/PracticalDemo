package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.Dto.WeaponsUnrepairDataOfPistolDto;
import com.example.WeaponArmaryManagementSystem.Exception.DuplicateEntryException;
import com.example.WeaponArmaryManagementSystem.Service.WeaponsUnrepairDataOfPistolService;
import com.example.WeaponArmaryManagementSystem.model.WeaponForm;
import com.example.WeaponArmaryManagementSystem.model.WeaponsUnrepairDataOfPistol;
import com.example.WeaponArmaryManagementSystem.repository.WeaponFormRepo;
import com.example.WeaponArmaryManagementSystem.repository.WeaponsUnrepairDataOfPistolRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeaponsUnrepairDataOfPistolServiceImpl implements WeaponsUnrepairDataOfPistolService {

    @Autowired
    private WeaponsUnrepairDataOfPistolRepository weaponsUnrepairDataOfPistolRepository;

    @Autowired
    private WeaponFormRepo weaponFormRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public WeaponsUnrepairDataOfPistol saveUnrepairData(WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        // Check if an entry with the same weaponName and date already exists
        List<WeaponsUnrepairDataOfPistol> existingRecords = weaponsUnrepairDataOfPistolRepository
                .findByWeaponNameAndDate(weaponsUnrepairDataOfPistolDto.getWeaponName(), weaponsUnrepairDataOfPistolDto.getDate());

        if (!existingRecords.isEmpty()) {
            // If an entry exists, throw an exception
            throw new DuplicateEntryException("Weapon name and date already entered for the same day.");
        }

        // If no entry exists, create a new record
        WeaponsUnrepairDataOfPistol record = new WeaponsUnrepairDataOfPistol();
        record.setDate(weaponsUnrepairDataOfPistolDto.getDate());
        record.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
        record.setRemark(weaponsUnrepairDataOfPistolDto.getRemark());
        record.setWeaponType(weaponsUnrepairDataOfPistolDto.getWeaponType());
        record.setCreatedAt(LocalDateTime.now());
        record.setUpdatedAt(LocalDateTime.now());

        try {
            String partNameJson = objectMapper.writeValueAsString(weaponsUnrepairDataOfPistolDto.getPartName());
            String quantityJson = objectMapper.writeValueAsString(weaponsUnrepairDataOfPistolDto.getQuantity());
            record.setQuantity(quantityJson);
            record.setPartName(partNameJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert part names to JSON", e);
        }

        WeaponsUnrepairDataOfPistol savedRecord = weaponsUnrepairDataOfPistolRepository.save(record);
        updateWeaponStatus(record.getWeaponName(), weaponsUnrepairDataOfPistolDto.getDate());

        return savedRecord;
    }


    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataByDate(String date) {
        return weaponsUnrepairDataOfPistolRepository.findByDate(date);
    }


    //06/06/2024
    @Override
    public List<WeaponsUnrepairDataOfPistol> getAllUnrepairData() {
        List<WeaponsUnrepairDataOfPistol> allSummaries = weaponsUnrepairDataOfPistolRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(WeaponsUnrepairDataOfPistol::getId).reversed())
                .collect(Collectors.toList());
    }
    
    
    
    
    /*
    @Override
    public List<WeaponsUnrepairDataOfPistol> getAllUnrepairData() {
        return weaponsUnrepairDataOfPistolRepository.findAll();
    }*/

    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataBetweenDates(String startDate, String endDate) {
        return weaponsUnrepairDataOfPistolRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataByWeaponName(String weaponName) {
        return weaponsUnrepairDataOfPistolRepository.findByWeaponName(weaponName);
    }

    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataByWeaponNameAndDate(String weaponName, String date) {
        return weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
    }

    private void updateWeaponStatus(String weaponName, String date) {
        WeaponForm weaponForm = weaponFormRepository.findByWeaponName(weaponName);
        if (weaponForm != null) {
            List<WeaponsUnrepairDataOfPistol> unrepairDataList = weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
            if (!unrepairDataList.isEmpty()) {
                weaponForm.setStatus("1");
            } else {
                weaponForm.setStatus("0");
            }
            weaponFormRepository.save(weaponForm);
        }
    }
    //08/06/2024
    public WeaponsUnrepairDataOfPistol updateUnrepairDataByWeaponNameAndDate(String weaponName, String date, WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        List<WeaponsUnrepairDataOfPistol> records = weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
        if (records.isEmpty()) {
            throw new NoSuchElementException("No data found for the specified weapon name and date");
        }

        WeaponsUnrepairDataOfPistol recordToUpdate = records.get(0);

        // Update fields as needed
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setWeaponType(weaponsUnrepairDataOfPistolDto.getWeaponType());
        recordToUpdate.setDate(weaponsUnrepairDataOfPistolDto.getDate());
        recordToUpdate.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setEmployeeId(weaponsUnrepairDataOfPistolDto.getEmployeeId());
        recordToUpdate.setUpdatedAt(LocalDateTime.now());
        recordToUpdate.setEmployeeDesignation(weaponsUnrepairDataOfPistolDto.getEmployeeDesignation());
        recordToUpdate.setEmployeeName(weaponsUnrepairDataOfPistolDto.getEmployeeName());
        recordToUpdate.setEmployeePostingDate(weaponsUnrepairDataOfPistolDto.getEmployeePostingDate());

        // Update lists of part names and quantities
        List<String> updatedPartNameList = weaponsUnrepairDataOfPistolDto.getPartName();
        List<String> updatedQuantityList = weaponsUnrepairDataOfPistolDto.getQuantity();

        if (updatedPartNameList != null && updatedQuantityList != null) {
            // Ensure both lists are of the same size
            if (updatedPartNameList.size() != updatedQuantityList.size()) {
                throw new IllegalArgumentException("Part name list size must match quantity list size");
            }

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String partNameJson = objectMapper.writeValueAsString(updatedPartNameList);
                String quantityJson = objectMapper.writeValueAsString(updatedQuantityList);
                recordToUpdate.setPartName(partNameJson);
                recordToUpdate.setQuantity(quantityJson);
            } catch (Exception e) {
                throw new RuntimeException("Error converting part names and quantities to JSON", e);
            }
        } else {
            // Handle null lists or lists of different sizes if needed
            // In this case, do nothing or log a message
        }

        // Add other field updates here

        return weaponsUnrepairDataOfPistolRepository.save(recordToUpdate);
    }
    //07/06/2024 modified as per makarand
    @Override
    public List<WeaponsUnrepairDataOfPistol> findByWeaponNameAndDate(String weaponName, String date) {
        return weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
}
}
    /*
    //as per manish on 06/06/2024
    public WeaponsUnrepairDataOfPistol updateUnrepairDataByWeaponNameAndDate(String weaponName, String date, WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        List<WeaponsUnrepairDataOfPistol> records = weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
        if (records.isEmpty()) {
            throw new NoSuchElementException("No data found for the specified weapon name and date");
        }

        WeaponsUnrepairDataOfPistol recordToUpdate = records.get(0);

        // Update fields as needed
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setWeaponType(weaponsUnrepairDataOfPistolDto.getWeaponType());
        recordToUpdate.setDate(weaponsUnrepairDataOfPistolDto.getDate());
        recordToUpdate.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setEmployeeId(weaponsUnrepairDataOfPistolDto.getEmployeeId());
        recordToUpdate.setUpdatedAt(LocalDateTime.now());
        recordToUpdate.setEmployeeDesignation(weaponsUnrepairDataOfPistolDto.getEmployeeDesignation());
        recordToUpdate.setEmployeeName(weaponsUnrepairDataOfPistolDto.getEmployeeName());
        recordToUpdate.setEmployeePostingDate(weaponsUnrepairDataOfPistolDto.getEmployeePostingDate());

        // Update lists of part names and quantities
        List<String> updatedPartNameList = weaponsUnrepairDataOfPistolDto.getPartName();
        List<String> updatedQuantityList = weaponsUnrepairDataOfPistolDto.getQuantity();

        if (updatedPartNameList != null && updatedQuantityList != null) {
            // Ensure both lists are of the same size
            if (updatedPartNameList.size() != updatedQuantityList.size()) {
                throw new IllegalArgumentException("Part name list size must match quantity list size");
            }

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String partNameJson = objectMapper.writeValueAsString(updatedPartNameList);
                String quantityJson = objectMapper.writeValueAsString(updatedQuantityList);
                recordToUpdate.setPartName(partNameJson);
                recordToUpdate.setQuantity(quantityJson);
            } catch (Exception e) {
                throw new RuntimeException("Error converting part names and quantities to JSON", e);
            }
        } else {
            // Handle null lists or lists of different sizes if needed
            // In this case, do nothing or log a message
        }

        // Add other field updates here

        return weaponsUnrepairDataOfPistolRepository.save(recordToUpdate);
    }
}

     */
    
    
    /*
    // as per manish on 06/06/2024
    public WeaponsUnrepairDataOfPistol updateUnrepairDataByWeaponNameAndDate(String weaponName, String date, WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        List<WeaponsUnrepairDataOfPistol> records = weaponsUnrepairDataOfPistolRepository.findByWeaponNameAndDate(weaponName, date);
        if (records.isEmpty()) {
            throw new NoSuchElementException("No data found for the specified weapon name and date");
        }

        WeaponsUnrepairDataOfPistol recordToUpdate = records.get(0);

        // Update fields as needed
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setWeaponType(weaponsUnrepairDataOfPistolDto.getWeaponType());
        recordToUpdate.setDate(weaponsUnrepairDataOfPistolDto.getDate());
        recordToUpdate.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
        recordToUpdate.setUpdatedRemark(weaponsUnrepairDataOfPistolDto.getUpdatedRemark());
        recordToUpdate.setEmployeeId(weaponsUnrepairDataOfPistolDto.getEmployeeId());
        recordToUpdate.setUpdatedAt(LocalDateTime.now());
        recordToUpdate.setEmployeeDesignation(weaponsUnrepairDataOfPistolDto.getEmployeeDesignation());
        recordToUpdate.setEmployeeName(weaponsUnrepairDataOfPistolDto.getEmployeeName());
        recordToUpdate.setEmployeePostingDate(weaponsUnrepairDataOfPistolDto.getEmployeePostingDate());

        // Update lists of part names and quantities
        List<String> updatedPartNameList = weaponsUnrepairDataOfPistolDto.getPartName();
        List<String> updatedQuantityList = weaponsUnrepairDataOfPistolDto.getQuantity();

        if (updatedPartNameList != null && updatedQuantityList != null) {
            // Ensure both lists are of the same size
            if (updatedPartNameList.size() != updatedQuantityList.size()) {
                throw new IllegalArgumentException("Part name list size must match quantity list size");
            }

            // Update the lists in the entity
            recordToUpdate.setPartName(String.valueOf(updatedPartNameList));
            recordToUpdate.setQuantity(String.valueOf(updatedQuantityList));
        } else {
            // Handle null lists or lists of different sizes if needed
        }

        // Add other field updates here

        return weaponsUnrepairDataOfPistolRepository.save(recordToUpdate);
    }
}*/



/*

@Override
@Transactional
public WeaponsUnrepairDataOfPistol saveUnrepairData(WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
    WeaponsUnrepairDataOfPistol newRecord = new WeaponsUnrepairDataOfPistol();
    newRecord.setDate(weaponsUnrepairDataOfPistolDto.getDate());
    newRecord.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
    newRecord.setRemark(weaponsUnrepairDataOfPistolDto.getRemark());
    newRecord.setCreatedAt(LocalDateTime.now());
    newRecord.setUpdatedAt(LocalDateTime.now());

    try {
        String partNameJson = objectMapper.writeValueAsString(weaponsUnrepairDataOfPistolDto.getPartName());
        newRecord.setPartName(partNameJson);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to convert part names to JSON", e);
    }

    WeaponsUnrepairDataOfPistol savedRecord = weaponsUnrepairDataOfPistolRepository.save(newRecord);
    updateWeaponStatus(newRecord.getWeaponName(), weaponsUnrepairDataOfPistolDto.getDate());

    return savedRecord;
}
*/
/*

import com.example.Emanyata.Dto.WeaponsUnrepairDataOfPistolDto;
import com.example.Emanyata.Entity.WeaponsUnrepairDataOfPistol;
import com.example.Emanyata.Repo.WeaponsUnrepairDataOfPistolRepository;
import com.example.Emanyata.Service.WeaponsUnrepairDataOfPistolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeaponsUnrepairDataOfPistolServiceImpl implements WeaponsUnrepairDataOfPistolService {

    @Autowired
    private WeaponsUnrepairDataOfPistolRepository weaponsUnrepairDataOfPistolRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public WeaponsUnrepairDataOfPistol saveUnrepairData(WeaponsUnrepairDataOfPistolDto weaponsUnrepairDataOfPistolDto) {
        WeaponsUnrepairDataOfPistol newRecord = new WeaponsUnrepairDataOfPistol();
        newRecord.setDate(weaponsUnrepairDataOfPistolDto.getDate());
        newRecord.setWeaponName(weaponsUnrepairDataOfPistolDto.getWeaponName());
        newRecord.setRemark(weaponsUnrepairDataOfPistolDto.getRemark());
        newRecord.setCreatedAt(LocalDateTime.now());
        newRecord.setUpdatedAt(LocalDateTime.now());

        try {
            String partNameJson = objectMapper.writeValueAsString(weaponsUnrepairDataOfPistolDto.getPartName());
            newRecord.setPartName(partNameJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to convert part names to JSON", e);
        }

        return weaponsUnrepairDataOfPistolRepository.save(newRecord);
    }



    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataByDate(String date) {
        return weaponsUnrepairDataOfPistolRepository.findByDate(date);
    }

    @Override
    public List<WeaponsUnrepairDataOfPistol> getAllUnrepairData() {
        return weaponsUnrepairDataOfPistolRepository.findAll();
    }

    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataBetweenDates(String startDate, String endDate) {
        List<WeaponsUnrepairDataOfPistol> weaponsUnrepairDataOfPistols = weaponsUnrepairDataOfPistolRepository.findByDateBetween(startDate, endDate);
        return (weaponsUnrepairDataOfPistols);
    }

    @Override
    public List<WeaponsUnrepairDataOfPistol> getUnrepairDataByWeaponName(String weaponName) {
        return weaponsUnrepairDataOfPistolRepository.findByWeaponName(weaponName);
    }
}
*/
