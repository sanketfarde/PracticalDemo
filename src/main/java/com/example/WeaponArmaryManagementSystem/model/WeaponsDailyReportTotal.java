package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;


@Entity
@Table(name = "weaponsdailyreport")
public class WeaponsDailyReportTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "weapon_type")
    private String weaponType;

    @Column(name = "count_total_quantity")
    private String countTotalQuantity;

    @Column(name = "count_total_distributed")
    private String countTotalDistributed;

    @Column(name = "total_good_condition_magazine")
    private String totalGoodConditionMagazine;

    @Column(name = "total_good_condition_godown")
    private String totalGoodConditionGodown;

    @Column(name = "total_good_condition_temporary_submitted")
    private String totalGoodConditionTemporarySubmitted;

    @Column(name = "total_unrepair_magazine")
    private String totalUnrepairMagazine;

    @Column(name = "totalunrepair_godown")
    private String totalUnrepairGodown;

    @Column(name = "total_pune_repair")
    private String totalPuneRepair;

    @Column(name = "total_inquiry")
    private String totalInquiry;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name")
    private String employeeName;


    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_posting_date")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
    
    public String getCountTotalQuantity() {
        return countTotalQuantity;
    }

    public void setCountTotalQuantity(String countTotalQuantity) {
        this.countTotalQuantity = countTotalQuantity;
    }

    public String getCountTotalDistributed() {
        return countTotalDistributed;
    }

    public void setCountTotalDistributed(String countTotalDistributed) {
        this.countTotalDistributed = countTotalDistributed;
    }

    public String getTotalGoodConditionMagazine() {
        return totalGoodConditionMagazine;
    }

    public void setTotalGoodConditionMagazine(String totalGoodConditionMagazine) {
        this.totalGoodConditionMagazine = totalGoodConditionMagazine;
    }

    public String getTotalGoodConditionGodown() {
        return totalGoodConditionGodown;
    }

    public void setTotalGoodConditionGodown(String totalGoodConditionGodown) {
        this.totalGoodConditionGodown = totalGoodConditionGodown;
    }

    public String getTotalGoodConditionTemporarySubmitted() {
        return totalGoodConditionTemporarySubmitted;
    }

    public void setTotalGoodConditionTemporarySubmitted(String totalGoodConditionTemporarySubmitted) {
        this.totalGoodConditionTemporarySubmitted = totalGoodConditionTemporarySubmitted;
    }

    public String getTotalUnrepairMagazine() {
        return totalUnrepairMagazine;
    }

    public void setTotalUnrepairMagazine(String totalUnrepairMagazine) {
        this.totalUnrepairMagazine = totalUnrepairMagazine;
    }

    public String getTotalUnrepairGodown() {
        return totalUnrepairGodown;
    }

    public void setTotalUnrepairGodown(String totalUnrepairGodown) {
        this.totalUnrepairGodown = totalUnrepairGodown;
    }

    public String getTotalPuneRepair() {
        return totalPuneRepair;
    }

    public void setTotalPuneRepair(String totalPuneRepair) {
        this.totalPuneRepair = totalPuneRepair;
    }

    public String getTotalInquiry() {
        return totalInquiry;
    }

    public void setTotalInquiry(String totalInquiry) {
        this.totalInquiry = totalInquiry;
    }
    // Constructors, getters, setters
}
