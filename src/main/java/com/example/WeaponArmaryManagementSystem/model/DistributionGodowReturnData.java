package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "distribution_godown_return_data")
public class DistributionGodowReturnData {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "weapon_name")
	private String weaponName;
	
	@Column(name = "weapon_damage")
	private String weaponDamage;

	@Column(name = "distribute_date")
	private String distributeDate;

	@Column(name = "return_date")
	private String returnDate;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "remark")
	private String remark;

	@Column(name = "godown_name")
	private String godownName;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;

	@Column(name = "weapon_condition")
	private String weaponCondition;
	
	@Column(name = "submitted_by")
	private String submittedBy;
	
	

	public String getWeaponDamage() {
		return weaponDamage;
	}

	public void setWeaponDamage(String weaponDamage) {
		this.weaponDamage = weaponDamage;
	}
	
	public Long getId() {
		return id;
	}

	public String getDistributeDate() {
		return distributeDate;
	}

	public void setDistributeDate(String distributeDate) {
		this.distributeDate = distributeDate;
	}

	public String getWeaponCondition() {
		return weaponCondition;
	}

	public void setWeaponCondition(String weaponCondition) {
		this.weaponCondition = weaponCondition;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
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

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGodownName() {
		return godownName;
	}

	public void setGodownName(String godownName) {
		this.godownName = godownName;
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
