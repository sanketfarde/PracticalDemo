package com.example.WeaponArmaryManagementSystem.Dto;

import java.util.List;

public class DistributionRequest {
    private CommonInfo commonInfo;
    private List<WeaponDistribution> weapons;

    public DistributionRequest() {
    }

    public DistributionRequest(CommonInfo commonInfo, List<WeaponDistribution> weapons) {
        this.commonInfo = commonInfo;
        this.weapons = weapons;
    }

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    public List<WeaponDistribution> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponDistribution> weapons) {
        this.weapons = weapons;
    }
}
