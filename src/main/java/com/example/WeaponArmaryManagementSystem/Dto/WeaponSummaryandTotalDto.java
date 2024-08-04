package com.example.WeaponArmaryManagementSystem.Dto;

import com.example.WeaponArmaryManagementSystem.model.TotalWeaponSummary;
import com.example.WeaponArmaryManagementSystem.model.WeaponSummary;

public class WeaponSummaryandTotalDto {

    private WeaponSummary weaponSummary;
    private TotalWeaponSummary totalWeaponSummary;

    public WeaponSummaryandTotalDto(WeaponSummary weaponSummary, TotalWeaponSummary totalWeaponSummary) {
        this.weaponSummary = weaponSummary;
        this.totalWeaponSummary = totalWeaponSummary;
    }


    public WeaponSummary getWeaponSummary() {
        return weaponSummary;
    }

    public void setWeaponSummary(WeaponSummary weaponSummary) {
        this.weaponSummary = weaponSummary;
    }

    public TotalWeaponSummary getTotalWeaponSummary() {
        return totalWeaponSummary;
    }

    public void setTotalWeaponSummary(TotalWeaponSummary totalWeaponSummary) {
        this.totalWeaponSummary = totalWeaponSummary;
    }
}
