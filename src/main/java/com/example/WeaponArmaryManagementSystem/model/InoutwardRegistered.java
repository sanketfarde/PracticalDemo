package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_inoutward_registered")
public class InoutwardRegistered {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "income_no")
	private String incomeNo;
	
	@Column(name = "butt_no")
	private String buttNo;
	
	@Column(name = "weapon_name")
	private String weaponName;
	
	@Column(name = "round_name")
	private String roundName;
	
	@Column(name = "manufacturer_no")
	private String manufacturerNo;
	
	@Column(name = "date")
	private String date;	
	
//	@Column(name = "weapon_type")
//	private String weaponType;
	
	@Column(name = "carat_no")
	private String caratNo;
	
	@Column(name = "box_no")
	private String boxNo;

	@Column(name = "lot_no")
	private String lotNo;
	
	@Column(name = "total_round")
	private String totalRound;
	
	@Column(name = "total_weapon")
	private String totalWeapon;
	
	@Column(name = "inoutward_registered_employee_id")
	private String inoutwardRegisteredEmployeeId;
	
	@Column(name = "inoutward_registered_employee_name")
	private String inoutwardRegisteredEmployeeName;
	
	@Column(name = "inoutward_registered_employee_designation")
	private String inoutwardRegisteredEmployeeDesignation;
	
	@Column(name = "inoutward_registered_employee_posting_date")
	private String inoutwardRegisteredEmployeePostingDate;
	
	@Column(name = "issue_remark")
	private String issueRemark;
	
	@Column(name = "submitted_remark")
	private String submittedRemark;
	
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

	public String getIncomeNo() {
		return incomeNo;
	}

	public void setIncomeNo(String incomeNo) {
		this.incomeNo = incomeNo;
	}

	public String getButtNo() {
		return buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
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

	public String getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
	public String getTotalRound() {
		return totalRound;
	}

	public void setTotalRound(String totalRound) {
		this.totalRound = totalRound;
	}

	public String getTotalWeapon() {
		return totalWeapon;
	}

	public void setTotalWeapon(String totalWeapon) {
		this.totalWeapon = totalWeapon;
	}

	public String getInoutwardRegisteredEmployeeId() {
		return inoutwardRegisteredEmployeeId;
	}

	public void setInoutwardRegisteredEmployeeId(String inoutwardRegisteredEmployeeId) {
		this.inoutwardRegisteredEmployeeId = inoutwardRegisteredEmployeeId;
	}

	public String getInoutwardRegisteredEmployeeName() {
		return inoutwardRegisteredEmployeeName;
	}

	public void setInoutwardRegisteredEmployeeName(String inoutwardRegisteredEmployeeName) {
		this.inoutwardRegisteredEmployeeName = inoutwardRegisteredEmployeeName;
	}

	public String getInoutwardRegisteredEmployeeDesignation() {
		return inoutwardRegisteredEmployeeDesignation;
	}

	public void setInoutwardRegisteredEmployeeDesignation(String inoutwardRegisteredEmployeeDesignation) {
		this.inoutwardRegisteredEmployeeDesignation = inoutwardRegisteredEmployeeDesignation;
	}

	public String getInoutwardRegisteredEmployeePostingDate() {
		return inoutwardRegisteredEmployeePostingDate;
	}

	public void setInoutwardRegisteredEmployeePostingDate(String inoutwardRegisteredEmployeePostingDate) {
		this.inoutwardRegisteredEmployeePostingDate = inoutwardRegisteredEmployeePostingDate;
	}

	public String getIssueRemark() {
		return issueRemark;
	}

	public void setIssueRemark(String issueRemark) {
		this.issueRemark = issueRemark;
	}

	public String getSubmittedRemark() {
		return submittedRemark;
	}

	public void setSubmittedRemark(String submittedRemark) {
		this.submittedRemark = submittedRemark;
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
