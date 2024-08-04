package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

import com.example.WeaponArmaryManagementSystem.model.InoutwardRegistered;

public class InoutwardRegisteredDto {

	private Long id;
    private String incomeNo;
    private String buttNo;
    private String weaponName;
    private String roundName;
    private String manufacturerNo;
    private String date;
//    private String weaponType;
    private String caratNo;
    private String boxNo;
    private String lotNo;
    private String totalRound;
    private String totalWeapon;
    private String inoutwardRegisteredEmployeeId;
    private String inoutwardRegisteredEmployeeName;
    private String inoutwardRegisteredEmployeeDesignation;
    private String inoutwardRegisteredEmployeePostingDate;
    private String issueRemark;
    private String submittedRemark;
    private String status;
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

	public InoutwardRegisteredDto() {
    	
    }
    
    public InoutwardRegisteredDto(InoutwardRegistered inoutwardRegistered) {
        this.id = inoutwardRegistered.getId();
        this.incomeNo = inoutwardRegistered.getIncomeNo();
        this.buttNo = inoutwardRegistered.getButtNo();
        this.weaponName = inoutwardRegistered.getWeaponName();
        this.roundName = inoutwardRegistered.getRoundName();
        this.manufacturerNo = inoutwardRegistered.getManufacturerNo();
        this.date = inoutwardRegistered.getDate();
//        this.weaponType = inoutwardRegistered.getWeaponType();
        this.caratNo = inoutwardRegistered.getCaratNo();
        this.boxNo = inoutwardRegistered.getBoxNo();
        this.lotNo = inoutwardRegistered.getLotNo();
        this.totalRound = inoutwardRegistered.getTotalRound();
        this.totalWeapon = inoutwardRegistered.getTotalWeapon();
        this.inoutwardRegisteredEmployeeId = inoutwardRegistered.getInoutwardRegisteredEmployeeId();
        this.inoutwardRegisteredEmployeeName = inoutwardRegistered.getInoutwardRegisteredEmployeeName();
        this.inoutwardRegisteredEmployeeDesignation = inoutwardRegistered.getInoutwardRegisteredEmployeeDesignation();
        this.inoutwardRegisteredEmployeePostingDate = inoutwardRegistered.getInoutwardRegisteredEmployeePostingDate();
        this.issueRemark = inoutwardRegistered.getIssueRemark();
        this.submittedRemark = inoutwardRegistered.getSubmittedRemark();
        this.status = inoutwardRegistered.getStatus();
        this.createdAt = inoutwardRegistered.getCreatedAt();
        this.updatedAt = inoutwardRegistered.getUpdatedAt();
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
