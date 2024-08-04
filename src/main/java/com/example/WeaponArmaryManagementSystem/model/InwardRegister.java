package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "inward_register")
public class InwardRegister {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "inward_number", unique = true)
	private String inwardNumber;
	
    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "date")
    private String date;
    
    @Column(name = "location")
    private String location; 

	@Column(name = "description")
    private String description;
    
    @Column(name = "quantity")
    private String quantity;
    
    @Column(name = "emp_id")
    private String empId;
    
    @Column(name = "emp_designation")
    private String empDesignation;
    
    @Column(name = "emp_name")
    private String empName;
    
    @Column(name = "posting_date")
    private String postingDate;
    
    @Column(name = "police_station")
    private String policeStation;
    
    //@Column(name = "individual")
   // private String individual;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "bucckle_no")
    private String bucckleNo;
    
    @Column(name = "received_by")
    private String receivedBy;
    
    @Column(name = "department_id ")
    private String departmentId ;

    @Column(name = "received_from")
	private String receivedFrom;

	@Column(name = "person_name")
	private String personName;

	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "weapon_type")
	private String weaponType;
	
	@Column(name = "round_name")
	private String roundName;

	@Column(name = "carat_no")
	private String caratNo;
	
	@Column(name = "box_no")
	private String boxNo;
	
	@Column(name = "lot_no")
	private String lotNo;

	
	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public String getCaratNo() {
		return caratNo;
	}

	public void setCaratNo(String caratNo) {
		this.caratNo = caratNo;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

    
    public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBucckleNo() {
		return bucckleNo;
	}

	public void setBucckleNo(String bucckleNo) {
		this.bucckleNo = bucckleNo;
	}

	public String getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    public String getInwardNumber() {
		return inwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.inwardNumber = inwardNumber;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}

