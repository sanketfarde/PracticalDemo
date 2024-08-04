package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//NOTE:
// Other than Repository  all other controller, service and service impl are include in the Bell of Armory.......

@Entity
@Table(name = "bell_of_arms_report")
public class BellOfArmsReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "butt_no")
    private String buttNo;

    @Column(name = "production_no")
    private String productionNo;

    @Column(name = "deposit_location")
    private String depositLocation;

    @Column(name = "deposit_date")
    private String depositedDate;

    @Column(name = "item_condition")
    private String itemCondition;

    @Column(name = "remark")
    private String remark;

    @Column(name = "location")
    private String location;
    
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

    //as per viplav by vikas on 01/06/2024
    @Column(name = "received_by")
    private String receivedBy;

     
    public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButtNo() {
        return buttNo;
    }

    public void setButtNo(String buttNo) {
        this.buttNo = buttNo;
    }

    public String getProductionNo() {
        return productionNo;
    }

    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo;
    }

    public String getDepositLocation() {
        return depositLocation;
    }

    public void setDepositLocation(String depositLocation) {
        this.depositLocation = depositLocation;
    }

    public String getDepositedDate() {
        return depositedDate;
    }

    public void setDepositedDate(String depositedDate) {
        this.depositedDate = depositedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
