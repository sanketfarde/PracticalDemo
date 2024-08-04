package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="tbl_service_totaldailyreport")
public class TotalsDailyReportOfService {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private String date;
    
    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "total_previous_count")
    private String totalPreviousCount;
    
    @Column(name = "total_received_count")
    private String totalReceivedCount;
   
    @Column(name = "total_issued_count")
    private String totalIssuedCount;
    
    @Column(name = "total_balanced_count")
    private String totalBalancedCount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getTotalPreviousCount() {
		return totalPreviousCount;
	}

	public void setTotalPreviousCount(String totalPreviousCount) {
		this.totalPreviousCount = totalPreviousCount;
	}

	public String getTotalReceivedCount() {
		return totalReceivedCount;
	}

	public void setTotalReceivedCount(String totalReceivedCount) {
		this.totalReceivedCount = totalReceivedCount;
	}

	public String getTotalIssuedCount() {
		return totalIssuedCount;
	}

	public void setTotalIssuedCount(String totalIssuedCount) {
		this.totalIssuedCount = totalIssuedCount;
	}

	public String getTotalBalancedCount() {
		return totalBalancedCount;
	}

	public void setTotalBalancedCount(String totalBalancedCount) {
		this.totalBalancedCount = totalBalancedCount;
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
