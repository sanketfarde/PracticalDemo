package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

public class WeaponFormLongDto {
     
	
    private Integer id;
    private String weaponName;
    private String weaponType;
    private LocalDateTime createdAt;    //created on 30/05/2024 by vikas
   
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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