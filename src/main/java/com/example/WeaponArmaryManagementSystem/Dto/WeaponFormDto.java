package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

public class WeaponFormDto {
     
    private String weaponName;
    private String weaponType;
	private String weaponStatus;
	private  String date;
    private LocalDateTime createdAt;    //created by vikas on 30/05/2024
    

	private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeePostingDate() {
		return employeePostingDate;
	}

	public void setEmployeePostingDate(String employeePostingDate) {
		this.employeePostingDate = employeePostingDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getWeaponStatus() {
		return weaponStatus;
	}

	public void setWeaponStatus(String weaponStatus) {
		this.weaponStatus = weaponStatus;
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