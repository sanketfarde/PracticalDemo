package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="tbl_service_dailyreport")
public class DailyReportOfService {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "date")
	    private String date;
	    
	    @Column(name = "weapon_name")
	    private String weaponName;
	    
	    @Column(name = "weapon_condition")
	    private String weaponCondition;

	    @Column(name = "previous_count")
	    private String previousCount;
	    
	    @Column(name = "received_count")
	    private String receivedCount;
	   
	    @Column(name = "issued_count")
	    private String issuedCount;
	    
        @Column(name = "balanced_count")
        private String balancedCount;
        
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
    	
        
        public DailyReportOfService() {
        	
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

		public String getWeaponCondition() {
			return weaponCondition;
		}

		public void setWeaponCondition(String weaponCondition) {
			this.weaponCondition = weaponCondition;
		}

		public String getPreviousCount() {
			return previousCount;
		}

		public void setPreviousCount(String previousCount) {
			this.previousCount = previousCount;
		}

		public String getReceivedCount() {
			return receivedCount;
		}

		public void setReceivedCount(String receivedCount) {
			this.receivedCount = receivedCount;
		}

		public String getIssuedCount() {
			return issuedCount;
		}

		public void setIssuedCount(String issuedCount) {
			this.issuedCount = issuedCount;
		}

		public String getBalancedCount() {
			return balancedCount;
		}

		public void setBalancedCount(String balancedCount) {
			this.balancedCount = balancedCount;
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
