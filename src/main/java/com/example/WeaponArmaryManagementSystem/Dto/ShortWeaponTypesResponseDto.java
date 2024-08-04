package com.example.WeaponArmaryManagementSystem.Dto;

import com.example.WeaponArmaryManagementSystem.model.WeaponFormShort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ShortWeaponTypesResponseDto {
	
    private List<WeaponFormShort> shortWeaponTypes;
    private LocalDate currentDateTime;

    
    public ShortWeaponTypesResponseDto(List<WeaponFormShort> shortWeaponTypes, LocalDate currentDateTime) {
        this.shortWeaponTypes = shortWeaponTypes;
        this.currentDateTime = currentDateTime;
    }

    // Getters and Setters
    public List<WeaponFormShort> getShortWeaponTypes() {
        return shortWeaponTypes;
    }

    public void setShortWeaponTypes(List<WeaponFormShort> shortWeaponTypes) {
        this.shortWeaponTypes = shortWeaponTypes;
    }

    public LocalDate getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDate currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
