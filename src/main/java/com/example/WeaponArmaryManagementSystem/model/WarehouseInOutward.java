package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_warehouse_inoutward")
public class WarehouseInOutward {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "weapon_name")
    private String weaponName;
	
	@Column(name = "round_name")
	private String roundName;
	
	@Column(name = "inward_no")
	private String inwardNo;
	
	@Column(name = "outward_no")
	private String outwardNo;
	
	@Column(name = "weapon_type")
	private String weaponType;
	
	@Column(name = "manufacturer_name")
	private String manufacturerName;
	
	@Column(name = "manufacturer_no")
	private String manufacturerNo;
	
	@Column(name = "reference_no")
	private String referenceNo;
	
	@Column(name = "kit_committee_no")
	private String kitCommitteeNo;
	
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
	
	@Column(name = "recieved_quantity")
	private String recievedQuantity;
	
	@Column(name = "expiry_date")
	private String expiryDate;
	
	@Column(name = "recieved_by")
	private String recievedBy;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;
	
	@Column(name = "status")
	private String status;

	@Column(name = "buttno")
	private String buttNo;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
	public WarehouseInOutward() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getInwardNo() {
		return inwardNo;
	}

	public void setInwardNo(String inwardNo) {
		this.inwardNo = inwardNo;
	}

	public String getOutwardNo() {
		return outwardNo;
	}

	public void setOutwardNo(String outwardNo) {
		this.outwardNo = outwardNo;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getManufacturerName() {
		return manufacturerName;
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

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getKitCommitteeNo() {
		return kitCommitteeNo;
	}

	public void setKitCommitteeNo(String kitCommitteeNo) {
		this.kitCommitteeNo = kitCommitteeNo;
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

	public String getRecievedQuantity() {
		return recievedQuantity;
	}

	public void setRecievedQuantity(String recievedQuantity) {
		this.recievedQuantity = recievedQuantity;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getRecievedBy() {
		return recievedBy;
	}

	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getButtNo() {
		return buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
	}

	public WarehouseInOutward(Long id, String date, String weaponName, String roundName,
							  String inwardNo, String outwardNo, String weaponType,
							  String manufacturerName, String manufacturerNo,
							  String referenceNo, String kitCommitteeNo, String caratNo,
							  String boxNo, String lotNo, String totalQuantity,
							  String balancedQuantity, String recievedQuantity,
							  String expiryDate, String recievedBy,
							  String remark, String employeeId,
							  String employeeName, String employeeDesignation,
							  String employeePostingDate, String status,
							  String buttNo, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.date = date;
		this.weaponName = weaponName;
		this.roundName = roundName;
		this.inwardNo = inwardNo;
		this.outwardNo = outwardNo;
		this.weaponType = weaponType;
		this.manufacturerName = manufacturerName;
		this.manufacturerNo = manufacturerNo;
		this.referenceNo = referenceNo;
		this.kitCommitteeNo = kitCommitteeNo;
		this.caratNo = caratNo;
		this.boxNo = boxNo;
		this.lotNo = lotNo;
		this.totalQuantity = totalQuantity;
		this.balancedQuantity = balancedQuantity;
		this.recievedQuantity = recievedQuantity;
		this.expiryDate = expiryDate;
		this.recievedBy = recievedBy;
		this.remark = remark;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDesignation = employeeDesignation;
		this.employeePostingDate = employeePostingDate;
		this.status = status;
		this.buttNo = buttNo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
