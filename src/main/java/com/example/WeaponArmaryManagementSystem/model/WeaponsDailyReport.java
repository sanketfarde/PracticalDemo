package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
// for the warehouse department
@Entity
@Table(name = "weaponsdailyreport")
public class WeaponsDailyReport {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "employee_no")
    private Integer employeeNo;

    @Column(name = "designation")
    private String designation;

    @Column(name = "weapon_type")
    private String weaponType;

    @Column(name = "total_quantity")
    private String totalQuantity;

    @Column(name = "total_distributed")
    private String totalDistributed;

    @Column(name = "good_condition_magazine")
    private String goodConditionMagazine;

    @Column(name = "good_condition_godown")
    private String goodConditionGodown;

    @Column(name = "good_condition_temporary_submitted")
    private String goodConditionTemporarySubmitted;

    @Column(name = "unrepair_magazine")
    private String unrepairMagazine;

    @Column(name = "unrepair_godown")
    private String unrepairGodown;

    @Column(name = "pune_repair")
    private String puneRepair;

    @Column(name = "inquiry")
    private String inquiry;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_designation")
    private String employeeDesignation;

    @Column(name = "employee_posting_date")
    private String employeePostingDate;

    @Column(name = "date")
    private String date;

    @Column(name = "total")
    private Long total;

    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Integer employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalDistributed() {
        return totalDistributed;
    }

    public void setTotalDistributed(String totalDistributed) {
        this.totalDistributed = totalDistributed;
    }

    public String getGoodConditionMagazine() {
        return goodConditionMagazine;
    }

    public void setGoodConditionMagazine(String goodConditionMagazine) {
        this.goodConditionMagazine = goodConditionMagazine;
    }

    public String getGoodConditionGodown() {
        return goodConditionGodown;
    }

    public void setGoodConditionGodown(String goodConditionGodown) {
        this.goodConditionGodown = goodConditionGodown;
    }

    public String getGoodConditionTemporarySubmitted() {
        return goodConditionTemporarySubmitted;
    }

    public void setGoodConditionTemporarySubmitted(String goodConditionTemporarySubmitted) {
        this.goodConditionTemporarySubmitted = goodConditionTemporarySubmitted;
    }

    public String getUnrepairMagazine() {
        return unrepairMagazine;
    }

    public void setUnrepairMagazine(String unrepairMagazine) {
        this.unrepairMagazine = unrepairMagazine;
    }

    public String getUnrepairGodown() {
        return unrepairGodown;
    }

    public void setUnrepairGodown(String unrepairGodown) {
        this.unrepairGodown = unrepairGodown;
    }

    public String getPuneRepair() {
        return puneRepair;
    }

    public void setPuneRepair(String puneRepair) {
        this.puneRepair = puneRepair;
    }

    public String getInquiry() {
        return inquiry;
    }

    public void setInquiry(String inquiry) {
        this.inquiry = inquiry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}