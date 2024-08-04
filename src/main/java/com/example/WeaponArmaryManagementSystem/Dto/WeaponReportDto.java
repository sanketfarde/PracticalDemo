package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class WeaponReportDto {
	
    private Integer id;
    private LocalDate date;
    private String weaponType;
    private List<String> buttNo;
    private List<String> manufacturerNo;
    private List<String> weaponCondition;
    private String description;
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    
    
    public WeaponReportDto(Integer id, LocalDate date, String weaponType, List<String> buttNo, List<String> manufacturerNo,
			List<String> weaponCondition, String description, String employeeId, String employeeName,
			String employeeDesignation, String employeePostingDate, String status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.date = date;
		this.weaponType = weaponType;
		this.buttNo = buttNo;
		this.manufacturerNo = manufacturerNo;
		this.weaponCondition = weaponCondition;
		this.description = description;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDesignation = employeeDesignation;
		this.employeePostingDate = employeePostingDate;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
    
	// Getters and Setters

	public String getWeaponType() {
		return weaponType;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	
	public List<String> getButtNo() {
		return buttNo;
	}
	
	public void setButtNo(List<String> buttNo) {
		this.buttNo = buttNo;
	}
	
	public List<String> getManufacturerNo() {
		return manufacturerNo;
	}
	
	public void setManufacturerNo(List<String> manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}
	
	public List<String> getWeaponCondition() {
		return weaponCondition;
	}
	
	public void setWeaponCondition(List<String> weaponCondition) {
		this.weaponCondition = weaponCondition;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
	 	 
	
