package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="sparePart")
public class SparePart {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "sparepart_name")
	    private String sparePartName;
	    
	    @Column(name = "status")
	    private String status;

		@Column(name = "weapon_name")
		private String weaponName;

	     @Column(name = "weapon_type")
	     private String weaponType;

	     @Column(name = "created_at" )
		 private LocalDateTime createdAt;

	@Column(name = "updated_at" )
	private LocalDateTime updatedAt;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;


	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;


	     
   /*  @Column(name = "weaponType")
	 	private String weaponType;
	     
	     @Column(name = "employee_id")
	     private String employeeId;
	    	
	    @Column(name = "employee_name")
	    private String employeeName;
	    	
	    @Column(name = "employee_designation")
	    private String employeeDesignation;
	    	
	    @Column(name = "employee_posting_date")
	    private String employeePostingDate;
	*/

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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
		public String getWeaponType() {
			return weaponType;
		}
	
		public void setWeaponType(String weaponType) {
			this.weaponType = weaponType;
		}
	
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
	
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	
		public String getWeaponName() {
			return weaponName;
		}
	
		public void setWeaponName(String weaponName) {
			this.weaponName = weaponName;
		}
    
		public SparePart() {
		
		}

	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getSparePartName() {
			return sparePartName;
		}

		public void setSparePartName(String sparePartName) {
			this.sparePartName = sparePartName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public SparePart(Integer id, String sparePartName, String status, String weaponName) {
			this.id = id;
			this.sparePartName = sparePartName;
			this.status = status;
			this.weaponName = weaponName;
		}
		
	@Override
	public String toString() {
		return "SparePart{" +
				"id=" + id +
				", sparePartName='" + sparePartName + '\'' +
				", status='" + status + '\'' +
				", weaponName='" + weaponName + '\'' +
				'}';

	}
}
