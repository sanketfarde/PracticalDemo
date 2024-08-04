package com.example.WeaponArmaryManagementSystem.Dto;

import java.util.List;

public class ReturnRequest {
    private CommonInfo commonInfo;
    private List<WeaponReturn> weapons;

    public ReturnRequest() {
    }

    public ReturnRequest(CommonInfo commonInfo, List<WeaponReturn> weapons) {
        this.commonInfo = commonInfo;
        this.weapons = weapons;
    }

    // Getters and Setters

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    public List<WeaponReturn> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponReturn> weapons) {
        this.weapons = weapons;
    }
}

