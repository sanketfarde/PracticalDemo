package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_material")
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "material_name", unique = true)
	private String materialName;

	@Column(name = "accessories", columnDefinition = "TEXT")
	private String accessories;

	@Column(name = "weapon_department_type")
	private String weaponDepartmentType;

	@Column(name = "department_id")
	private String departmentId;
	
	@Column(name = "weapon_type")
	private String weaponType;
	
	@Column(name = "status")
	private String status;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;
	
	@Column(name = "employee_posting_date")
	private String employeePostingDate;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Transient
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getWeaponDepartmentType() {
		return weaponDepartmentType;
	}

	public void setWeaponDepartmentType(String weaponDepartmentType) {
		this.weaponDepartmentType = weaponDepartmentType;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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


	// Additional methods to handle list of accessories
	public List<String> getAccessoriesList() {
		try {
			return objectMapper.readValue(this.accessories, List.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to convert JSON string to list", e);
		}
	}

	public void setAccessoriesList(List<String> accessories) {
		try {
			this.accessories = objectMapper.writeValueAsString(accessories);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to convert list to JSON string", e);
		}
	}



	/*
	@JsonIgnore
	public Optional<List<String>> getAccessoriesAsList() {
		if (accessories == null || accessories.trim().isEmpty()) {
			return Optional.empty();
		}
		try {
			return Optional.of(objectMapper.readValue(accessories, List.class));
		} catch (JsonProcessingException e) {
			return Optional.empty();
		}
	}

	public void setAccessoriesAsList(List<String> accessoriesList) {
		try {
			this.accessories = objectMapper.writeValueAsString(accessoriesList);
		} catch (JsonProcessingException e) {
			this.accessories = "";
		}
	}

	// Add a getter for accessoriesList for JSON serialization
	@Transient
	public List<String> getAccessoriesList() {
		return getAccessoriesAsList().orElse(Collections.emptyList());
	}


	 */

	/*	// Additional methods to handle list of accessories
	public List<String> getAccessoriesList() {
		return Arrays.asList(this.accessoriesName.split(","));
	}

	public void setAccessoriesList(List<String> accessories) {
		this.accessoriesName = String.join(",", accessories);
	}

 */

}
