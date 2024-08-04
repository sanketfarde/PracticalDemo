
package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_user")
public class WareHouseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name="emp_no",unique = true)
    private String employeeNo;
    
    @Column(name = "date_ofjoining")
    private String dateOfJoining;
    
    @Column(name="status")
    private boolean status;
    
    @Column(name = "email",unique = true)
    private String email;
    
    @Column(name="phone",unique = true)
    private String phone;
    
    @Column(name = "password")
    private String password;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_id")
    private Long departmentId;
    
    @Column(name = "otp")
    private String otp;
    
    @Column(name = "posting_date")
    private String postingDate;

	@Column(name = "address")
    private String address;
    
    @Column(name = "buckle_no")
    private String buckleNo;
     
    @Column(name = "otp-verified-at")
    private LocalDateTime otpVerifiedAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name="created_at")
    private LocalDateTime createdAt;
    
    @Column(name="employee_id")
    private String employeeId;

    @Column(name="employee_name")
    private String employeeName;
    
    @Column(name="employee_designation")
    private String employeeDesignation;
    
	@Column(name="employee_postingDate")
    private String employeePostingDate;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    public String getBuckleNo() {
		return buckleNo;
	}

	public void setBuckleNo(String buckleNo) {
		this.buckleNo = buckleNo;
	}

	public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }



    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpVerifiedAt() {
		return otpVerifiedAt;
	}

	public void setOtpVerifiedAt(LocalDateTime localDateTime) {
		this.otpVerifiedAt = localDateTime;
	}

	public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

	public WareHouseUser get() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOtp(int otp2) {
		// TODO Auto-generated method stub
		
	}

	public boolean isVerified() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setVerified(boolean b) {
		// TODO Auto-generated method stub
		
	}

}

