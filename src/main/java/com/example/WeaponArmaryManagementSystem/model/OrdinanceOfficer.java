package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "ordinance_data")
public class OrdinanceOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "region")
	private String region;

	@Column(name = "weapon_count")
	private String weaponCount;

	@Column(name = "place_of_duty")
	private String placeOfDuty;

	@Column(name = "police_officer")
	private String policeOfficer;

	@Column(name = "buckle_no")
	private String buckleNo;

	@Column(name = "weapon_issue")
	private String weaponIssue;

	@Column(name = "butt_number")
	private String buttNumber;

	@Column(name = "weapon_type")
	private String weaponType;

	@Column(name = "weapon_name")
	private String weaponName;

	@Column(name = "bionet")
	private String bionet;

	@Column(name = "magazine")
	private String magazine;

	@Column(name = "filler")
	private String filler;

	@Column(name = "launcher")
	private String launcher;

	//@Column(name = "weapon_date")
	//private String weaponDate;

	//by manish as per viplav
	@Column(name = "weapon_deposite_date_time")
	private LocalDateTime weaponDepositeDateTime;

	@Column(name = "received_weapon_condition")
	private String receivedWeaponCondition;

	@Column(name = "submitted_weapon")
	private String submittedWeapon;

	@Column(name = "checked_by")
	private String checkedBy;

	@Column(name = "is_weapon_damage")
	private String isWeaponDamage;

	@Column(name = "received_date")
	private String receivedDate;

//	@Column(name = "received_time")
//	private String received_time;

	@Column(name = "remark")
	private String remark;

	@Column(name = "status")
	private String status;

	//hidden inputs to save
	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;

	public OrdinanceOfficer() {
	}

	public OrdinanceOfficer(Long id, String region, String weaponCount, String placeOfDuty, String policeOfficer, String buckleNo, String weaponIssue, String buttNumber, String weaponType, String weaponName, String bionet, String magazine, String filler, String launcher, LocalDateTime weaponDepositeDateTime,
							String receivedWeaponCondition, String submittedWeapon, String checkedBy, String isWeaponDamage, String receivedDate, String remark,String status, String employeeId, String employeeName, String employeeDesignation, String employeePostingDate) {
		this.id = id;
		this.region = region;
		this.weaponCount = weaponCount;
		this.placeOfDuty = placeOfDuty;
		this.policeOfficer = policeOfficer;
		this.buckleNo = buckleNo;
		this.weaponIssue = weaponIssue;
		this.buttNumber = buttNumber;
		this.weaponType = weaponType;
		this.weaponName = weaponName;
		this.bionet = bionet;
		this.magazine = magazine;
		this.filler = filler;
		this.launcher = launcher;
		this.weaponDepositeDateTime = weaponDepositeDateTime;
		this.receivedWeaponCondition = receivedWeaponCondition;
		this.submittedWeapon = submittedWeapon;
		this.checkedBy = checkedBy;
		this.isWeaponDamage = isWeaponDamage;
		this.receivedDate = receivedDate;
		this.remark = remark;
		this.status = status;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeDesignation = employeeDesignation;
		this.employeePostingDate = employeePostingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getWeaponCount() {
		return weaponCount;
	}

	public void setWeaponCount(String weaponCount) {
		this.weaponCount = weaponCount;
	}

	public String getPlaceOfDuty() {
		return placeOfDuty;
	}

	public void setPlaceOfDuty(String placeOfDuty) {
		this.placeOfDuty = placeOfDuty;
	}

	public String getPoliceOfficer() {
		return policeOfficer;
	}

	public void setPoliceOfficer(String policeOfficer) {
		this.policeOfficer = policeOfficer;
	}

	public String getBuckleNo() {
		return buckleNo;
	}

	public void setBuckleNo(String buckleNo) {
		this.buckleNo = buckleNo;
	}

	public String getWeaponIssue() {
		return weaponIssue;
	}

	public void setWeaponIssue(String weaponIssue) {
		this.weaponIssue = weaponIssue;
	}

	public String getButtNumber() {
		return buttNumber;
	}

	public void setButtNumber(String buttNumber) {
		this.buttNumber = buttNumber;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getBionet() {
		return bionet;
	}

	public void setBionet(String bionet) {
		this.bionet = bionet;
	}

	public String getMagazine() {
		return magazine;
	}

	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getLauncher() {
		return launcher;
	}

	public void setLauncher(String launcher) {
		this.launcher = launcher;
	}

	public LocalDateTime getWeaponDepositeDateTime() {
		return weaponDepositeDateTime;
	}

	public void setWeaponDepositeDateTime(LocalDateTime weaponDepositeDateTime) {
		this.weaponDepositeDateTime = weaponDepositeDateTime;
	}

	public String getReceivedWeaponCondition() {
		return receivedWeaponCondition;
	}

	public void setReceivedWeaponCondition(String receivedWeaponCondition) {
		this.receivedWeaponCondition = receivedWeaponCondition;
	}

	public String getSubmittedWeapon() {
		return submittedWeapon;
	}

	public void setSubmittedWeapon(String submittedWeapon) {
		this.submittedWeapon = submittedWeapon;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	public String getIsWeaponDamage() {
		return isWeaponDamage;
	}

	public void setIsWeaponDamage(String isWeaponDamage) {
		this.isWeaponDamage = isWeaponDamage;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
