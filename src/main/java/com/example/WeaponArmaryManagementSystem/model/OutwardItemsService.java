package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "outward_items_register")
public class OutwardItemsService {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "butt_no")
     private String buttNo;
     
     @Column(name = "manufacturing_no")
     private String manufacturingNo;
     
     @Column(name = "count")
     private String count;
     
 	//@Column(name = "date")
     // private LocalDateTime date;
     
    // @Column(name = "created_at")
   //  private String createdAt;
    
  // Getter and setter for inwardRegister
     @ManyToOne
     @JoinColumn(name = "outward_register_id") // This should match the column name in your database
     private OutwardRegisterService outwardRegisterService; // Reference to InwardRegister

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_designation")
	private String employeeDesignation;

	@Column(name = "employee_posting_date")
	private String employeePostingDate;
	

	public OutwardRegisterService getOutwardRegisterService() {
		return outwardRegisterService;
	}

	public void setOutwardRegisterService(OutwardRegisterService outwardRegisterService) {
		this.outwardRegisterService = outwardRegisterService;
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

	public String getManufacturingNo() {
		return manufacturingNo;
	}

	public void setManufacturingNo(String manufacturingNo) {
		this.manufacturingNo = manufacturingNo;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public OutwardRegisterService getOutwardRegister() {
		return outwardRegisterService;
	}

	public void setOutwardRegister(OutwardRegisterService outwardRegisterService) {
		this.outwardRegisterService = outwardRegisterService;
	}

}
