package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordinanceofficerreturndatatotal")
public class OrdinanceOfficerReturndataTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "region")
    private String region;

    @Column(name = "place_of_duty")
    private String placeOfDuty;

    @Column(name = "police_officer")
    private String policeOfficer;

    @Column(name = "butt_number")
    private String buttNumber;

    @Column(name = "weapon_type")
    private String weaponType;

    @Column(name = "bionettotal")
    private String bionetTotal;

    @Column(name = "magzinetotal")
    private String magzineTotal;

    @Column(name = "fillertotal")
    private String fillerTotal;

    @Column(name = "launchertotal")
    private String launcherTotal;

    //@Column(name = "weapon_date")
    //private String weaponDate;

    @Column(name = "weapon_date")
    private LocalDateTime weaponDate;

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

    //@Column(name = "received_time")
    //private String received_time;

    @Column(name = "remark")
    private String remark;

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

    public String getBionetTotal() {
        return bionetTotal;
    }

    public void setBionetTotal(String bionetTotal) {
        this.bionetTotal = bionetTotal;
    }

    public String getMagzineTotal() {
        return magzineTotal;
    }

    public void setMagzineTotal(String magzineTotal) {
        this.magzineTotal = magzineTotal;
    }

    public String getFillerTotal() {
        return fillerTotal;
    }

    public void setFillerTotal(String fillerTotal) {
        this.fillerTotal = fillerTotal;
    }

    public String getLauncherTotal() {
        return launcherTotal;
    }

    public void setLauncherTotal(String launcherTotal) {
        this.launcherTotal = launcherTotal;
    }

    public LocalDateTime getWeaponDate() {
        return weaponDate;
    }

    public void setWeaponDate(LocalDateTime weaponDate) {
        this.weaponDate = weaponDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
