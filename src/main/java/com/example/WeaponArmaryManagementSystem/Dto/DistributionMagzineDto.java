package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;

public class DistributionMagzineDto {
	
    private Long id;
    private String weaponType;
    private String weaponName;
    private String roundName;
  /*  private List<String> buttNo;
    private List<String> manufacturingNo;*/

	private List<String> buttNo = new ArrayList<>();
	private List<String> manufacturingNo = new ArrayList<>();
    private String weaponLocation;
    private String mobileNumber;
	private String sevarthId;
	private String distributedBy;
	private List<String> accessories;
    private String distributionType;
    private String weaponCondition;
    private String identityType;
    private String identityNo;
    private String permanentAddress;
    private String temporaryAddress;
    private String designation;
    private String bucckleNo;
    private String birthDate;
    private String dateOfRetirement;
    private String posting;
    private String policeStationName;
    private String individualName;
    private String depositeRound;
    private String weaponCheckedBy;
    private String servicingDate;
    private String distributionDate;
    private String distributionTime;
    private String submittedWeapon;
    private String recievedWeaponCondition;
    private String recievedWeaponCheckedBy;
    private String isWeaponDamage;
    private String recievedWeaponDate;
    private String recievedWeaponTime;
    private String usedRound;
    private String submittedRound;
    private String defectedRound;
    private String recievedRoundCondition;
    private String recievedroundCheckedBy;
    private String recievedRoundDate;
    private String recievedRoundTime;
    private String issueRemark;
    private String submittedRemark;
    private String totalWeapon;
 //   private String totalRound;
	private String distributeEmployeeId;
	private String distributeEmployeeName;
	private String distributeEmployeeDesignation;
	private String distributeEmployeePostingDate;
	private String recievedEmployeeId;
	private String recievedEmployeeName;
	private String recievedEmployeeDesignation;
	private String recievedEmployeePostingDate;
    private String status;
	private String returnWeapon;
	private String balanceWeapon;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
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

	public DistributionMagzineDto() {
        // Default constructor
    }



	  public DistributionMagzineDto(DistributionMagzine distributionMagzine) {
    	this.id = distributionMagzine.getId();
        this.weaponType = distributionMagzine.getWeaponType();
        this.weaponName = distributionMagzine.getWeaponName();
        this.roundName = distributionMagzine.getRoundName();
     /*   this.buttNo = Collections.singletonList(distributionMagzine.getButtNo());
        this.manufacturingNo = Collections.singletonList(distributionMagzine.getManufacturingNo());*/
		  this.buttNo = distributionMagzine.getButtNoAsList();
		  this.manufacturingNo = distributionMagzine.getManufacturingNoAsList();
        this.weaponLocation = distributionMagzine.getWeaponLocation();
        this.mobileNumber = distributionMagzine.getMobileNumber();
		this.sevarthId = distributionMagzine.getSevarthId();
		this.distributedBy = distributionMagzine.getDistributedBy();
		this.accessories = distributionMagzine.getAccessoriesList();
        this.distributionType = distributionMagzine.getDistributionType();
        this.weaponCondition = distributionMagzine.getWeaponCondition();
        this.identityType = distributionMagzine.getIdentityType();
        this.identityNo = distributionMagzine.getIdentityNo();
        this.permanentAddress = distributionMagzine.getPermanentAddress();
        this.temporaryAddress = distributionMagzine.getTemporaryAddress();
        this.designation = distributionMagzine.getDesignation();
        this.bucckleNo = distributionMagzine.getBucckleNo();
        this.birthDate = distributionMagzine.getBirthDate();
        this.dateOfRetirement = distributionMagzine.getDateOfRetirement();
        this.posting = distributionMagzine.getPosting();
        this.policeStationName = distributionMagzine.getPoliceStationName();
        this.individualName = distributionMagzine.getIndividualName();
        this.depositeRound = distributionMagzine.getDepositeRound();
        this.weaponCheckedBy = distributionMagzine.getWeaponCheckedBy();
        this.servicingDate = distributionMagzine.getServicingDate();
        this.distributionDate = distributionMagzine.getDistributionDate();
        this.distributionTime = distributionMagzine.getDistributionTime();
        this.submittedWeapon = distributionMagzine.getSubmittedWeapon();
        this.recievedWeaponCondition = distributionMagzine.getRecievedWeaponCondition();
        this.recievedWeaponCheckedBy = distributionMagzine.getRecievedWeaponCheckedBy();
        this.isWeaponDamage = distributionMagzine.getIsWeaponDamage();
        this.recievedWeaponDate = distributionMagzine.getRecievedWeaponDate();
        this.recievedWeaponTime = distributionMagzine.getRecievedWeaponTime();
        this.usedRound = distributionMagzine.getUsedRound();
        this.submittedRound = distributionMagzine.getSubmittedRound();
        this.defectedRound = distributionMagzine.getDefectedRound();
        this.recievedRoundCondition = distributionMagzine.getRecievedRoundCondition();
        this.recievedroundCheckedBy = distributionMagzine.getRecievedroundCheckedBy();
        this.recievedRoundDate = distributionMagzine.getRecievedRoundDate();
        this.recievedRoundTime = distributionMagzine.getRecievedRoundTime();
        this.issueRemark = distributionMagzine.getIssueRemark();
        this.submittedRemark = distributionMagzine.getSubmittedRemark();
        this.totalWeapon = distributionMagzine.getTotalWeapon();
    //    this.totalRound = distributionMagzine.getTotalRound();
        this.distributeEmployeeId = distributionMagzine.getDistributeEmployeeId();
        this.distributeEmployeeName = distributionMagzine.getDistributeEmployeeName();
        this.distributeEmployeeDesignation = distributionMagzine.getDistributeEmployeeDesignation();
        this.distributeEmployeePostingDate = distributionMagzine.getDistributeEmployeePostingDate();
        this.recievedEmployeeId = distributionMagzine.getRecievedEmployeeId();
        this.recievedEmployeeName = distributionMagzine.getRecievedEmployeeName();
        this.recievedEmployeeDesignation = distributionMagzine.getRecievedEmployeeDesignation();
        this.recievedEmployeePostingDate = distributionMagzine.getRecievedEmployeePostingDate();
        this.status = distributionMagzine.getStatus();
		this.returnWeapon = distributionMagzine.getReturnWeapon();
		this.balanceWeapon = distributionMagzine.getBalanceWeapon();
        this.createdAt = distributionMagzine.getCreatedAt();
        this.updatedAt = distributionMagzine.getUpdatedAt();
    }





     // Getters and setters 
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	/*public String getButtNo() {
		return buttNo;
	}

	public void setButtNo(String buttNo) {
		this.buttNo = buttNo;
	}*/

	public List<String> getButtNo() {
		return buttNo;
	}

	public void setButtNo(List<String> buttNo) {
		this.buttNo = buttNo;
	}

	/*public String getManufacturingNo() {
		return manufacturingNo;
	}

	public void setManufacturingNo(String manufacturingNo) {
		this.manufacturingNo = manufacturingNo;
	}*/

	public List<String> getManufacturingNo() {
		return manufacturingNo;
	}

	public void setManufacturingNo(List<String> manufacturingNo) {
		this.manufacturingNo = manufacturingNo;
	}

	public String getWeaponLocation() {
		return weaponLocation;
	}

	public void setWeaponLocation(String weaponLocation) {
		this.weaponLocation = weaponLocation;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSevarthId() {
		return sevarthId;
	}

	public void setSevarthId(String sevarthId) {
		this.sevarthId = sevarthId;
	}

	public String getDistributedBy() {
		return distributedBy;
	}

	public void setDistributedBy(String distributedBy) {
		this.distributedBy = distributedBy;
	}

	public List<String> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<String> accessories) {
		this.accessories = accessories;
	}

	public String getDistributionType() {
		return distributionType;
	}

	public void setDistributionType(String distributionType) {
		this.distributionType = distributionType;
	}

	public String getWeaponCondition() {
		return weaponCondition;
	}

	public void setWeaponCondition(String weaponCondition) {
		this.weaponCondition = weaponCondition;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getDateOfRetirement() {
		return dateOfRetirement;
	}

	public void setDateOfRetirement(String dateOfRetirement) {
		this.dateOfRetirement = dateOfRetirement;
	}

	public String getPosting() {
		return posting;
	}

	public void setPosting(String posting) {
		this.posting = posting;
	}

	public String getPoliceStationName() {
		return policeStationName;
	}

	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}

	public String getIndividualName() {
		return individualName;
	}

	public void setIndividualName(String individualName) {
		this.individualName = individualName;
	}

	public String getDepositeRound() {
		return depositeRound;
	}

	public void setDepositeRound(String depositeRound) {
		this.depositeRound = depositeRound;
	}

	public String getWeaponCheckedBy() {
		return weaponCheckedBy;
	}

	public void setWeaponCheckedBy(String weaponCheckedBy) {
		this.weaponCheckedBy = weaponCheckedBy;
	}

	public String getServicingDate() {
		return servicingDate;
	}

	public void setServicingDate(String servicingDate) {
		this.servicingDate = servicingDate;
	}

	public String getDistributionDate() {
		return distributionDate;
	}

	public void setDistributionDate(String distributionDate) {
		this.distributionDate = distributionDate;
	}

	public String getDistributionTime() {
		return distributionTime;
	}

	public void setDistributionTime(String distributionTime) {
		this.distributionTime = distributionTime;
	}	
	
	public String getSubmittedWeapon() {
		return submittedWeapon;
	}

	public void setSubmittedWeapon(String submittedWeapon) {
		this.submittedWeapon = submittedWeapon;
	}

	public String getRecievedWeaponCondition() {
		return recievedWeaponCondition;
	}

	public void setRecievedWeaponCondition(String recievedWeaponCondition) {
		this.recievedWeaponCondition = recievedWeaponCondition;
	}

	public String getRecievedWeaponCheckedBy() {
		return recievedWeaponCheckedBy;
	}

	public void setRecievedWeaponCheckedBy(String recievedWeaponCheckedBy) {
		this.recievedWeaponCheckedBy = recievedWeaponCheckedBy;
	}

	public String getIsWeaponDamage() {
		return isWeaponDamage;
	}

	public void setIsWeaponDamage(String isWeaponDamage) {
		this.isWeaponDamage = isWeaponDamage;
	}

	public String getRecievedWeaponDate() {
		return recievedWeaponDate;
	}

	public void setRecievedWeaponDate(String recievedWeaponDate) {
		this.recievedWeaponDate = recievedWeaponDate;
	}

	public String getRecievedWeaponTime() {
		return recievedWeaponTime;
	}

	public void setRecievedWeaponTime(String recievedWeaponTime) {
		this.recievedWeaponTime = recievedWeaponTime;
	}

	public String getUsedRound() {
		return usedRound;
	}

	public void setUsedRound(String usedRound) {
		this.usedRound = usedRound;
	}

	public String getSubmittedRound() {
		return submittedRound;
	}

	public void setSubmittedRound(String submittedRound) {
		this.submittedRound = submittedRound;
	}

	public String getDefectedRound() {
		return defectedRound;
	}

	public void setDefectedRound(String defectedRound) {
		this.defectedRound = defectedRound;
	}

	public String getRecievedRoundCondition() {
		return recievedRoundCondition;
	}

	public void setRecievedRoundCondition(String recievedRoundCondition) {
		this.recievedRoundCondition = recievedRoundCondition;
	}

	public String getRecievedroundCheckedBy() {
		return recievedroundCheckedBy;
	}

	public void setRecievedroundCheckedBy(String recievedroundCheckedBy) {
		this.recievedroundCheckedBy = recievedroundCheckedBy;
	}

	public String getRecievedRoundDate() {
		return recievedRoundDate;
	}

	public void setRecievedRoundDate(String recievedRoundDate) {
		this.recievedRoundDate = recievedRoundDate;
	}

	public String getRecievedRoundTime() {
		return recievedRoundTime;
	}

	public void setRecievedRoundTime(String recievedRoundTime) {
		this.recievedRoundTime = recievedRoundTime;
	}	

	public String getIssueRemark() {
		return issueRemark;
	}

	public void setIssueRemark(String issueRemark) {
		this.issueRemark = issueRemark;
	}

	public String getSubmittedRemark() {
		return submittedRemark;
	}

	public void setSubmittedRemark(String submittedRemark) {
		this.submittedRemark = submittedRemark;
	}
	
	public String getTotalWeapon() {
		return totalWeapon;
	}

	public void setTotalWeapon(String totalWeapon) {
		this.totalWeapon = totalWeapon;
	}	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getDistributeEmployeeId() {
		return distributeEmployeeId;
	}

	public void setDistributeEmployeeId(String distributeEmployeeId) {
		this.distributeEmployeeId = distributeEmployeeId;
	}

	public String getDistributeEmployeeName() {
		return distributeEmployeeName;
	}

	public void setDistributeEmployeeName(String distributeEmployeeName) {
		this.distributeEmployeeName = distributeEmployeeName;
	}

	public String getDistributeEmployeeDesignation() {
		return distributeEmployeeDesignation;
	}

	public void setDistributeEmployeeDesignation(String distributeEmployeeDesignation) {
		this.distributeEmployeeDesignation = distributeEmployeeDesignation;
	}

	public String getDistributeEmployeePostingDate() {
		return distributeEmployeePostingDate;
	}

	public void setDistributeEmployeePostingDate(String distributeEmployeePostingDate) {
		this.distributeEmployeePostingDate = distributeEmployeePostingDate;
	}

	public String getRecievedEmployeeId() {
		return recievedEmployeeId;
	}

	public void setRecievedEmployeeId(String recievedEmployeeId) {
		this.recievedEmployeeId = recievedEmployeeId;
	}

	public String getRecievedEmployeeName() {
		return recievedEmployeeName;
	}

	public void setRecievedEmployeeName(String recievedEmployeeName) {
		this.recievedEmployeeName = recievedEmployeeName;
	}

	public String getRecievedEmployeeDesignation() {
		return recievedEmployeeDesignation;
	}

	public void setRecievedEmployeeDesignation(String recievedEmployeeDesignation) {
		this.recievedEmployeeDesignation = recievedEmployeeDesignation;
	}

	public String getRecievedEmployeePostingDate() {
		return recievedEmployeePostingDate;
	}

	public void setRecievedEmployeePostingDate(String recievedEmployeePostingDate) {
		this.recievedEmployeePostingDate = recievedEmployeePostingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnWeapon() {
		return returnWeapon;
	}

	public void setReturnWeapon(String returnWeapon) {
		this.returnWeapon = returnWeapon;
	}

	public String getBalanceWeapon() {
		return balanceWeapon;
	}

	public void setBalanceWeapon(String balanceWeapon) {
		this.balanceWeapon = balanceWeapon;
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
