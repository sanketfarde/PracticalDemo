package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

public class WeaponFormShortDto {
     
    private Integer id;
    private String weaponName;
    private String weaponType;
    private LocalDateTime createdAt;    //created by vikas on 30/05/2024
   
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getWeaponName() {
		return weaponName;
	}
	
	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	
	public String getWeaponType() {
		return weaponType;
	}
	
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
		
	}
	
}