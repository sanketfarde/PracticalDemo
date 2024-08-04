package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "outward_register")
public class OutwardRegisterService {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	//@Column(name = "outward_number", unique=true)
	
	@Column(name = "outward_number")
	private String outwardNumber;
	
	@Column(name = "weapon_name")
    private String weaponName;
	
	@Column(name = "date")
    private String date;
    
    @Column(name = "location")
    private String location;
    
	@Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    private String quantity;

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

	public String getOutwardNumber() {
		return outwardNumber;
	}

	public void setOutwardNumber(String outwardNumber) {
		this.outwardNumber = outwardNumber;
	}
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInwardNumber() {
		return outwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.outwardNumber = inwardNumber;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
    
}
