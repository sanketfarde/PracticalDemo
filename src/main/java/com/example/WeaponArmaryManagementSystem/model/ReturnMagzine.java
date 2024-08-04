package com.example.WeaponArmaryManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tbl_return_magazine")
public class ReturnMagzine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "weapon_type")
    private String weaponType;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "round_name")
    private String roundName;

    @Column(name = "butt_no", columnDefinition = "TEXT")
    private String buttNo = "";

    @Column(name = "manufacturing_no", columnDefinition = "TEXT")
    private String manufacturingNo = "";

    @Column(name = "weapon_location")
    private String weaponLocation;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "sevarth_id")
    private String sevarthId;

    @Column(name = "distributed_by")
    private String distributedBy;

    @Column(name = "accessories", columnDefinition = "TEXT")
    private String accessories = "";

    @Column(name = "return_accessories", columnDefinition = "TEXT")
    private String returnAccessories = "";

    @Column(name = "distribution_type")
    private String distributionType;

    @Column(name = "police_station_name")
    private String policeStationName;

    @Column(name = "individual_name")
    private String individualName;

    @Column(name = "weapon_condition")
    private String weaponCondition;

    @Column(name = "identity_type")
    private String identityType;

    @Column(name = "identity_no")
    private String identityNo;

    @Column(name = "permanent_address")
    private String permanentAddress;

    @Column(name = "temporary_address")
    private String temporaryAddress;

    @Column(name = "designation")
    private String designation;

    @Column(name = "bucckle_no")
    private String bucckleNo;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "date_of_retirement")
    private String dateOfRetirement;

    @Column(name = "posting")
    private String posting;

    @Column(name = "deposite_round")
    private String depositeRound;

    @Column(name = "weapon_checked_by")
    private String weaponCheckedBy;

    @Column(name = "servicing_date")
    private String servicingDate;

    @Column(name = "distribution_date")
    private String distributionDate;

    @Column(name = "distribution_time")
    private String distributionTime;

    @Column(name = "submitted_weapon")
    private String submittedWeapon;

    @Column(name = "recieved_weapon_condition")
    private String recievedWeaponCondition;

    @Column(name = "recieved_weapon_checked_by")
    private String recievedWeaponCheckedBy;

    @Column(name = "is_weapon_damage")
    private String isWeaponDamage;

    @Column(name = "recieved_weapon_date")
    private String recievedWeaponDate;

    @Column(name = "recieved_weapon_time")
    private String recievedWeaponTime;

    @Column(name = "used_round")
    private String usedRound;

    @Column(name = "submitted_round")
    private String submittedRound;

    @Column(name = "defected_round")
    private String defectedRound;

    @Column(name = "recieved_round_condition")
    private String recievedRoundCondition;

    @Column(name = "recieved_round_checked_by")
    private String recievedroundCheckedBy;

    @Column(name = "recieved_round_date")
    private String recievedRoundDate;

    @Column(name = "recieved_round_time")
    private String recievedRoundTime;

    @Column(name = "issue_remark")
    private String issueRemark;

    @Column(name = "submitted_remark")
    private String submittedRemark;

    @Column(name = "total_weapon")
    private String totalWeapon;

    @Column(name = "distribute_employee_id")
    private String distributeEmployeeId;

    @Column(name = "distribute_employee_name")
    private String distributeEmployeeName;

    @Column(name = "distribute_employee_designation")
    private String distributeEmployeeDesignation;

    @Column(name = "distribute_employee_posting_date")
    private String distributeEmployeePostingDate;

    @Column(name = "recieved_employee_id")
    private String recievedEmployeeId;

    @Column(name = "recieved_employee_name")
    private String recievedEmployeeName;

    @Column(name = "recieved_employee_designation")
    private String recievedEmployeeDesignation;

    @Column(name = "recieved_employee_posting_date")
    private String recievedEmployeePostingDate;

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

    public String getAccessories() {
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


    public List<String> getReturnAccessoriesAsList() {
        return Arrays.asList(returnAccessories.split(","));
    }

    public void setReturnAccessoriesAsList(List<String> returnAccessoriesList) {
        this.returnAccessories = String.join(",", returnAccessories);
    }



    // Add methods to convert List to String and vice versa
    public List<String> getButtNoAsList() {
        return Arrays.asList(buttNo.split(","));
    }

    public void setButtNoAsList(List<String> buttNoList) {
        this.buttNo = String.join(",", buttNoList);
    }

    public List<String> getManufacturingNoAsList() {
        return Arrays.asList(manufacturingNo.split(","));
    }

    public void setManufacturingNoAsList(List<String> manufacturingNoList) {
        this.manufacturingNo = String.join(",", manufacturingNoList);
    }
}

