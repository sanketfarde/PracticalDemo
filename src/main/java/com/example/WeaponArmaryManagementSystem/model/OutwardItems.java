package com.example.WeaponArmaryManagementSystem.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "outward_items_register")
public class OutwardItems {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "butt_no")
     private String buttNo;
     
     @Column(name = "manufacturing_no")
     private String manufacturingNo;
     
     @Column(name = "count")
     private String count;


    
  // Getter and setter for inwardRegister
     @ManyToOne
     @JoinColumn(name = "outward_register_id") // This should match the column name in your database
     private OutwardRegister outwardRegister; // Reference to InwardRegister

     
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

	public OutwardRegister getOutwardRegister() {
		return outwardRegister;
	}

	public void setOutwardRegister(OutwardRegister outwardRegister) {
		this.outwardRegister = outwardRegister;
	}

}
