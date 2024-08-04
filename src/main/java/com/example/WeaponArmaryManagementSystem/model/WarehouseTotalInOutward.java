package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_warehouse_total_inoutward")
public class WarehouseTotalInOutward {
	
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
	
	@Column(name = "expiry_date")
	private String expiryDate;


	@Column(name = "buttno")
	private String buttNo;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;


	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;

	public String getButtNo() {
		return buttNo;
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

	public WarehouseTotalInOutward() {
		super();
	}

	public WarehouseTotalInOutward(Long id, String weaponName, String roundName,
								   Integer totalStock, Integer distributionStock,
								   Integer availableStock, String expiryDate, String buttNo) {
		this.id = id;
		this.weaponName = weaponName;
		this.roundName = roundName;
		this.totalStock = totalStock;
		this.distributionStock = distributionStock;
		this.availableStock = availableStock;
		this.expiryDate = expiryDate;
		this.buttNo = buttNo;
	}

	public String getButtNo(String buttNo) {
		return this.buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
				  
}
