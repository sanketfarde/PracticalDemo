package com.example.WeaponArmaryManagementSystem.Dto;

public class WeaponReturn {
    private String weaponName;
    private int quantity;

    public WeaponReturn() {
    }

    public WeaponReturn(String weaponName, int quantity) {
        this.weaponName = weaponName;
        this.quantity = quantity;
    }

    // Getters and Setters


    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

