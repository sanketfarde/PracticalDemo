package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_total_stock")
public class TotalStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "weapon_name")
	private String weaponName;
	
	@Column(name = "round_name")
	private String roundName;
	
	@Column(name = "total_stock")
    private Integer totalStock;
	
	@Column(name = "distribution_stock")
    private Integer distributionStock;
	
	@Column(name = "available_stock")
    private Integer availableStock;
	
	@Column(name = "expired_stock")
    private Integer expiredStock;

	@Column(name = "employee_id")
	private String employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
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
		
}
