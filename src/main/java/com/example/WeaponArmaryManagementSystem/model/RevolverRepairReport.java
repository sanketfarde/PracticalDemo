package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="tbl_service_RevolverRepairReport")
public class RevolverRepairReport{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "revolver_repair_name")
    private String revolverRepairName;

    @Column(name = "butt_no")
    private String buttNo;
    
    @Column(name = "manufacturer_no")
    private String manufacturerNo;
    
    @Column(name = "department_location")
    private String departmentLocation;
    
    @Column(name = "preservation_report")
    private String preservationReport;
    
    @Column(name = "quarterly_report")
    private String quarterlyReport;
    
    @Column(name = "annual_report")
    private String annualReport;
  
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
    
    @Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getRevolverRepairName() {
		return revolverRepairName;
	}

	public void setRevolverRepairName(String revolverRepairName) {
		this.revolverRepairName = revolverRepairName;
	}

	public String getButtNo() {
		return buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
	}

	public String getManufacturerNo() {
		return manufacturerNo;
	}

	public void setManufacturerNo(String manufacturerNo) {
		this.manufacturerNo = manufacturerNo;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public String getPreservationReport() {
		return preservationReport;
	}

	public void setPreservationReport(String preservationReport) {
		this.preservationReport = preservationReport;
	}

	public String getQuarterlyReport() {
		return quarterlyReport;
	}

	public void setQuarterlyReport(String quarterlyReport) {
		this.quarterlyReport = quarterlyReport;
	}

	public String getAnnualReport() {
		return annualReport;
	}

	public void setAnnualReport(String annualReport) {
		this.annualReport = annualReport;
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
	
}
