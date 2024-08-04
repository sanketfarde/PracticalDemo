package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "inward_items_register")
public class InwardItems {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "butt_no")
     private String buttNo;
     
     @Column(name = "manufacturing_no")
     private String manufacturingNo;
     
     @Column(name = "count")
     private String count;
     
   //  @Column(name = "department_id ")
    // private String departmentId ;
     
    // @Column(name = "inward_number")
    // private String inwardNumber;

	@ManyToOne
     @JoinColumn(name = "inward_register_id") // This should match the column name in your database
     private InwardRegister inwardRegister; // Reference to InwardRegister

   //  @JoinColumn(name = "inward_number")
   //  private InwardRegister inwardRegister; // Reference to InwardRegister


	// Getter and setter for inwardRegister
     public InwardRegister getInwardRegister() {
         return inwardRegister;
     }

     public void setInwardRegister(InwardRegister inwardRegister) {
         this.inwardRegister = inwardRegister;
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
 
}

	/*
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "butt_no")
	    private String buttNo;

	    @Column(name = "manufacturing_no")
	    private String manufacturingNo;

	    @Column(name = "count")
	    private String count;

	    @Column(name = "department_id")
	    private String departmentId;

	    @Column(name = "inward_number")
	    private String inwardNumber;

	    @ManyToOne
	    @JoinColumn(name = "inward_register_id")
	    private InwardRegister inwardRegister;

	    // Getters and setters...

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

	    public String getDepartmentId() {
	        return departmentId;
	    }

	    public void setDepartmentId(String departmentId) {
	        this.departmentId = departmentId;
	    }

	    public String getInwardNumber() {
	        return inwardNumber;
	    }

	    public void setInwardNumber(String inwardNumber) {
	        this.inwardNumber = inwardNumber;
	    }

	    public InwardRegister getInwardRegister() {
	        return inwardRegister;
	    }

	    public void setInwardRegister(InwardRegister inwardRegister) {
	        this.inwardRegister = inwardRegister;
	    }
	}*/




/*
@Column(name = "employee_id")
private String employeeId;

@Column(name = "employee_name")
private String employeeName;

@Column(name = "employee_designation")
private String employeeDesignation;

@Column(name = "employee_posting_date")
private String employeePostingDate;
*/