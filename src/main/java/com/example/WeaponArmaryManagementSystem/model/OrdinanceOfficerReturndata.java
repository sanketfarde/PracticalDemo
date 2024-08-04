package com.example.WeaponArmaryManagementSystem.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "OrdinanceOfficerReturndata")
public class OrdinanceOfficerReturndata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "weapon_count")
    private String weaponCount;
    @Column(name = "place_of_duty")
    private String placeOfDuty;

    @Column(name = "police_officer")
    private String policeOfficer;

    @Column(name = "butt_number")
    private String buttNumber;

    @Column(name = "weapon_type")
    private String weaponType;
    @Column(name = "bionet")
    private String bionet;

    @Column(name = "magzine")
    private String magzine;

    @Column(name = "filler")
    private String filler;

    @Column(name = "launcher")
    private String launcher;

    //@Column(name = "weapon_date")
    //private String weaponDate;

    @Column(name = "weapon_date")
    private String weaponDate;

    @Column(name = "received_weapon_condition")
    private String receivedWeaponCondition;

    @Column(name = "submitted_weapon")
    private String submittedWeapon;

    @Column(name = "checked_by")
    private String checkedBy;

    @Column(name = "is_weapon_damage")
    private String isWeaponDamage;

    @Column(name = "received_date")
    private String receivedDate;


    @Column(name = "buckle_no")
    private String buckleNo;

    @Column(name = "weapon_issue")
    private String weaponIssue;

    @Column(name = "received_time")
    private String received_time;

    @Column(name = "remark")
    private String remark;


    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name")
    private String employeeName;


    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_posting_date")
    private String employeePostingDate;

    public String getWeaponCount() {
        return weaponCount;
    }

    public void setWeaponCount(String weaponCount) {
        this.weaponCount = weaponCount;
    }

    public String getReceived_time() {
        return received_time;
    }

    public void setReceived_time(String received_time) {
        this.received_time = received_time;
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

    public String getWeaponDate() {
        return weaponDate;
    }

    public void setWeaponDate(String weaponDate) {
        this.weaponDate = weaponDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlaceOfDuty() {
        return placeOfDuty;
    }

    public void setPlaceOfDuty(String placeOfDuty) {
        this.placeOfDuty = placeOfDuty;
    }

    public String getPoliceOfficer() {
        return policeOfficer;
    }

    public void setPoliceOfficer(String policeOfficer) {
        this.policeOfficer = policeOfficer;
    }

    public String getButtNumber() {
        return buttNumber;
    }

    public void setButtNumber(String buttNumber) {
        this.buttNumber = buttNumber;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getBionet() {
        return bionet;
    }

    public void setBionet(String bionet) {
        this.bionet = bionet;
    }

    public String getMagzine() {
        return magzine;
    }

    public void setMagzine(String magzine) {
        this.magzine = magzine;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher;
    }




    public String getReceivedWeaponCondition() {
        return receivedWeaponCondition;
    }

    public void setReceivedWeaponCondition(String receivedWeaponCondition) {
        this.receivedWeaponCondition = receivedWeaponCondition;
    }

    public String getSubmittedWeapon() {
        return submittedWeapon;
    }

    public void setSubmittedWeapon(String submittedWeapon) {
        this.submittedWeapon = submittedWeapon;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getIsWeaponDamage() {
        return isWeaponDamage;
    }

    public void setIsWeaponDamage(String isWeaponDamage) {
        this.isWeaponDamage = isWeaponDamage;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuckleNo() {
        return buckleNo;
    }

    public void setBuckleNo(String buckleNo) {
        this.buckleNo = buckleNo;
    }

    public String getWeaponIssue() {
        return weaponIssue;
    }

    public void setWeaponIssue(String weaponIssue) {
        this.weaponIssue = weaponIssue;
    }
}
