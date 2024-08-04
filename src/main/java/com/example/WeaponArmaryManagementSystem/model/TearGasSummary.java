package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "teargassummary")

public class TearGasSummary {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teargas_amunities")
    private String teargasAmunities;
    
    @Column(name = "total_surcharge")
    private String totalSurcharge;
    
    @Column(name = "shakha_vitran_active")
    private String shakhaVitranActive;
    
    @Column(name = "shakha_vitran_expired")
    private String shakhaVitranExpired;
    
    @Column(name = "gasdodownstockactive")
    private String gasDodownStockActive;
    
    @Column(name = "gasdodownstockexpired")
    private String gasDodownStockExpired;

    @Column(name = "remark")
    private String remark;

    @Column(name = "teargas_type")
    private String teargasType;

    @Column(name = "total")
    private String total;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "total_all")
    private String totalAll;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeargasAmunities() {
        return teargasAmunities;
    }

    public void setTeargasAmunities(String teargasAmunities) {
        this.teargasAmunities = teargasAmunities;
    }

    public String getTotalSurcharge() {
        return totalSurcharge;
    }

    public void setTotalSurcharge(String totalSurcharge) {
        this.totalSurcharge = totalSurcharge;
    }

    public String getShakhaVitranActive() {
        return shakhaVitranActive;
    }

    public void setShakhaVitranActive(String shakhaVitranActive) {
        this.shakhaVitranActive = shakhaVitranActive;
    }

    public String getShakhaVitranExpired() {
        return shakhaVitranExpired;
    }

    public void setShakhaVitranExpired(String shakhaVitranExpired) {
        this.shakhaVitranExpired = shakhaVitranExpired;
    }

    public String getGasDodownStockActive() {
        return gasDodownStockActive;
    }

    public void setGasDodownStockActive(String gasDodownStockActive) {
        this.gasDodownStockActive = gasDodownStockActive;
    }

    public String getGasDodownStockExpired() {
        return gasDodownStockExpired;
    }

    public void setGasDodownStockExpired(String gasDodownStockExpired) {
        this.gasDodownStockExpired = gasDodownStockExpired;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(String totalAll) {
        this.totalAll = totalAll;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTeargasType() {
        return teargasType;
    }

    public void setTeargasType(String teargasType) {
        this.teargasType = teargasType;
    }
}
