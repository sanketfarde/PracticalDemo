package com.example.WeaponArmaryManagementSystem.Dto;

import com.example.WeaponArmaryManagementSystem.model.WeaponFormLong;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LongWeaponTypesResponseDto {
	
    private List<WeaponFormLong> longWeaponTypes;
    private LocalDate currentDateTime;

    public LongWeaponTypesResponseDto(List<WeaponFormLong> longWeaponTypes, LocalDate currentDateTime) {
        this.longWeaponTypes = longWeaponTypes;
        this.currentDateTime = currentDateTime;
    }

    public List<WeaponFormLong> getLongWeaponTypes() {
        return longWeaponTypes;
    }

    public void setLongWeaponTypes(List<WeaponFormLong> longWeaponTypes) {
        this.longWeaponTypes = longWeaponTypes;
    }

    public LocalDate getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDate currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
