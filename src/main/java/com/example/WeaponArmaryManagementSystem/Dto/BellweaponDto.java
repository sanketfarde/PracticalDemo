package com.example.WeaponArmaryManagementSystem.Dto;



public class BellweaponDto {
	
    private Long id;
    private String weaponName;
    private String weapon1;
    private String weapon2;
    private String weapon3;
    private String weapon4;
    private String weaponType;
    private String status;
    
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

	public BellweaponDto() {
    	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

