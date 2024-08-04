package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bell_big_weapon")
public class BellWeapon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weapon_name", unique = true)
    private String weaponName;

    @Column(name = "weapon1")
    private String weapon1;
    
    @Column(name = "weapon2")
    private String weapon2;
    
    @Column(name = "weapon3")
    private String weapon3;
    
    @Column(name = "weapon4")
    private String weapon4;

	@Column(name= "status")
    private String status;

	@Column(name = "weapon_type")
	private String weaponType;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
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

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	
	public String getWeapon4() {
		return weapon4;
	}

	public void setWeapon4(String weapon4) {
		this.weapon4 = weapon4;
	}
	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWeapon1() {
		return weapon1;
	}

	public void setWeapon1(String weapon1) {
		this.weapon1 = weapon1;
	}

	public String getWeapon2() {
		return weapon2;
	}

	public void setWeapon2(String weapon2) {
		this.weapon2 = weapon2;
	}

	public String getWeapon3() {
		return weapon3;
	}

	public void setWeapon3(String weapon3) {
		this.weapon3 = weapon3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}