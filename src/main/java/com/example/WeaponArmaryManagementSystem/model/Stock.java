package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "butt_no")
	private String buttNo;
	
//	@Column(name = "material_name")
//  private String materialName;
	
	@Column(name = "weapon_name")
	private String weaponName;
	
	@Column(name = "round_name")
	private String roundName;
	
	@Column(name = "manufacturer_name")
	private String manufacturerName;
	
	@Column(name = "manufacturer_no")
	private String manufacturerNo;
	
	@Column(name = "in_date")
	private String inDate;
	
	@Column(name = "reference_no")
	private String referenceNo;
	
	@Column(name = "issued_authority")
	private String issuedAuthority;
	
	@Column(name = "vouchar_no")
	private String voucharNo;
	
	@Column(name = "weapon_type")
	private String weaponType;
	
	@Column(name = "carat_no")
	private String caratNo;
	
	@Column(name = "box_no")
	private String boxNo;

	@Column(name = "lot_no")
	private String lotNo;
	
	@Column(name = "total_quantity")
	private String totalQuantity;
	
	@Column(name = "balanced_quantity")
	private String balancedQuantity;
	
	@Column(name = "expiry_date")
	private String expiryDate;
	
	@Column(name = "recieved_quantity")
	private String recievedQuantity;
	
	@Column(name = "recieved_by")
	private String recievedBy;
	
	@Column(name = "stock_in_employee_id")
	private String stockInEmployeeId;
	
	@Column(name = "stock_in_employee_name")
	private String stockInEmployeeName;
	
	@Column(name = "stock_in_employee_designation")
	private String stockInEmployeeDesignation;
	
	@Column(name = "stock_in_employee_posting_date")
	private String stockInEmployeePostingDate;
	
	@Column(name = "status")
	private String status;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getButtNo() {
		return buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
	}
	
	public String getManufacturerName() {
		return manufacturerName;
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

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getIssuedAuthority() {
		return issuedAuthority;
	}

	public void setIssuedAuthority(String issuedAuthority) {
		this.issuedAuthority = issuedAuthority;
	}

	public String getVoucharNo() {
		return voucharNo;
	}

	public void setVoucharNo(String voucharNo) {
		this.voucharNo = voucharNo;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getCaratNo() {
		return caratNo;
	}

	public void setCaratNo(String caratNo) {
		this.caratNo = caratNo;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getBalancedQuantity() {
		return balancedQuantity;
	}

	public void setBalancedQuantity(String balancedQuantity) {
		this.balancedQuantity = balancedQuantity;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRecievedQuantity() {
		return recievedQuantity;
	}

	public void setRecievedQuantity(String recievedQuantity) {
		this.recievedQuantity = recievedQuantity;
	}	
	
	public String getRecievedBy() {
		return recievedBy;
	}

	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}

	public String getStockInEmployeeId() {
		return stockInEmployeeId;
	}

	public void setStockInEmployeeId(String stockInEmployeeId) {
		this.stockInEmployeeId = stockInEmployeeId;
	}

	public String getStockInEmployeeName() {
		return stockInEmployeeName;
	}

	public void setStockInEmployeeName(String stockInEmployeeName) {
		this.stockInEmployeeName = stockInEmployeeName;
	}

	public String getStockInEmployeeDesignation() {
		return stockInEmployeeDesignation;
	}

	public void setStockInEmployeeDesignation(String stockInEmployeeDesignation) {
		this.stockInEmployeeDesignation = stockInEmployeeDesignation;
	}

	public String getStockInEmployeePostingDate() {
		return stockInEmployeePostingDate;
	}

	public void setStockInEmployeePostingDate(String stockInEmployeePostingDate) {
		this.stockInEmployeePostingDate = stockInEmployeePostingDate;
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
