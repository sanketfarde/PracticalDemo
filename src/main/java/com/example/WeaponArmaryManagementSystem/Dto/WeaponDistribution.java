package com.example.WeaponArmaryManagementSystem.Dto;

public class WeaponDistribution {
    private String weaponName;
    private Integer quantity;

    public WeaponDistribution() {
    }

    public WeaponDistribution(String weaponName, Integer quantity) {
        this.weaponName = weaponName;
        this.quantity = quantity;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
