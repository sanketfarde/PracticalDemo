package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReturnMagzineDto {
    private Long id;
    private String weaponType;
    private String weaponName;
    private String roundName;
    private List<String> buttNo;
    private List<String> manufacturingNo;
    private String weaponLocation;
    private String mobileNumber;
    private String sevarthId;
    private String distributedBy;
    private List<String> accessories;
    private List<String> returnAccessories;
    private String distributionType;
    private String policeStationName;
    private String individualName;
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
    private String distributeEmployeeId;
    private String distributeEmployeeName;
    private String distributeEmployeeDesignation;
    private String distributeEmployeePostingDate;
    private String recievedEmployeeId;
    private String recievedEmployeeName;
    private String recievedEmployeeDesignation;
    private String recievedEmployeePostingDate;
    private String status;
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
    }

    public String getManufacturingNo() {
        return manufacturingNo;
    }

    public void setManufacturingNo(String manufacturingNo) {
        this.manufacturingNo = manufacturingNo;
    }*/

    public List<String> getButtNo() {
        return buttNo;
    }

    public void setButtNo(List<String> buttNo) {
        this.buttNo = buttNo;
    }

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

    /*public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getReturnAccessories() {
        return returnAccessories;
    }

    public void setReturnAccessories(String returnAccessories) {
        this.returnAccessories = returnAccessories;
    }*/

    public List<String> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<String> accessories) {
        this.accessories = accessories;
    }

    public List<String> getReturnAccessories() {
        return returnAccessories;
    }

    public void setReturnAccessories(List<String> returnAccessories) {
        this.returnAccessories = returnAccessories;
    }

    public String getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(String distributionType) {
        this.distributionType = distributionType;
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
}
