package com.example.WeaponArmaryManagementSystem.Dto;



import java.util.List;

public class AddTeargasWeaponsDto {



    private  List<String> weaponName;
    private  List<String> weaponCount;
    private String date;
    private String remark;

    public List<String> getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(List<String> weaponName) {
        this.weaponName = weaponName;
    }

    public List<String> getWeaponCount() {
        return weaponCount;
    }

    public void setWeaponCount(List<String> weaponCount) {
        this.weaponCount = weaponCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
