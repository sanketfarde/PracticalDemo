package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prefix_config")
public class PrefixConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "employee_id")
    private String employeeId;

	@Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_posting_date")
    private String employeePostingDate;
    
	@Column(name = "department_id")
	private String departmentId;


    public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
