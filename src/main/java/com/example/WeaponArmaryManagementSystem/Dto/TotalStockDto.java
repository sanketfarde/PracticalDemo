package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

public class TotalStockDto {

	private Long id;
    private String weaponName;
    private String roundName;
    private Integer totalStock;  // Changed to Integer for storing quantities
    private Integer distributionStock;
    private Integer availableStock;
    private Integer expiredStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWeaponName() {
		return weaponName;
	}
	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	public String getRoundName() {
		return roundName;
	}
	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}
	public Integer getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(Integer totalStock) {
		this.totalStock = totalStock;
	}
	public Integer getDistributionStock() {
		return distributionStock;
	}
	public void setDistributionStock(Integer distributionStock) {
		this.distributionStock = distributionStock;
	}
	public Integer getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(Integer availableStock) {
		this.availableStock = availableStock;
	}
	public Integer getExpiredStock() {
		return expiredStock;
	}
	public void setExpiredStock(Integer expiredStock) {
		this.expiredStock = expiredStock;
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
