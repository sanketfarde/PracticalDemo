package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="weaponForm")

public class WeaponForm {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;
	  
	    @Column(name = "weapon_name",unique = true)
	    private String weaponName;
	    
	    @Column(name = "weapon_type")
	    private String weaponType;

	    @Column(name = "status")
	    private String status;

		@Column(name = "date")
		private String date;
	    
		@Column(name = "weapon_status")
		private String weaponStatus;

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

	public String getWeaponStatus() {
			return weaponStatus;
		}
	
		public void setWeaponStatus(String weaponStatus) {
			this.weaponStatus = weaponStatus;
		}
	
		public WeaponForm() {
	    	
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
	
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	
		public Integer getId() {
				return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getWeaponName() {
			return weaponName;
		}

		public void setWeaponName(String weaponName) {
			this.weaponName = weaponName;
		}

		public String getWeaponType() {
			return weaponType;
		}

		public void setWeaponType(String weaponType) {
			this.weaponType = weaponType;
		}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public WeaponForm(Integer id, String weaponName,
					  String weaponType, String status,
					  String date, String weaponStatus,
					  LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.weaponName = weaponName;
		this.weaponType = weaponType;
		this.status = status;
		this.date = date;
		this.weaponStatus = weaponStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


}
