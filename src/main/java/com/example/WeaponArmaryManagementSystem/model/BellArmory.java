package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bellarmory")
public class BellArmory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weapon_date")
    private String date;

    @Column(name = "weapon_name", columnDefinition = "TEXT")
    private String weaponName;

    @Column(name = "mumbai_police_adhibhar",columnDefinition = "TEXT")
    private String mumbaiPoliceAdhibhar;

    @Column(name = "police_thane_vitaran",columnDefinition = "TEXT")
    private String policeThaneVitaran;

    @Column(name = "taddev_godown_distribution",columnDefinition = "TEXT")
    private String taddevGodownDistribution;

    @Column(name = "calina",columnDefinition = "TEXT")
    private String caLiNa;

    @Column(name = "pune_r",columnDefinition = "TEXT")
    private String puneR;

    @Column(name = "pune_deposited",columnDefinition = "TEXT")
    private String puneDeposited;

    @Column(name = "naygaon_weapon_stock",columnDefinition = "TEXT")
    private String naygaonWeaponStock;

    @Column(name = "police_thane_cr",columnDefinition = "TEXT")
    private String policeThaneCR;

    @Column(name = "godown_goodstockavailable",columnDefinition = "TEXT")
    private String godownGoodstockAvailable;

    @Column(name = "godowndamagestockavailable",columnDefinition = "TEXT")
    private String godownDamageStockAvailable;

    @Column(name = "coach_goodstockavailable",columnDefinition = "TEXT")
    private String coachGoodstockAvailable;

    @Column(name = "coachdamagestockavailable",columnDefinition = "TEXT")
    private String coachDamageStockAvailable;

    @Column(name = "workshop",columnDefinition = "TEXT")
    private String workshop;

    @Column(name = "on_duty",columnDefinition = "TEXT")
    private String onDuty;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
/* @Column(name = "godown_total",columnDefinition = "TEXT")
    private String godownTotal;

    @Column(name = "coach_total",columnDefinition = "TEXT")
    private String coachTotal;*/

  
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

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getMumbaiPoliceAdhibhar() {
        return mumbaiPoliceAdhibhar;
    }

    public void setMumbaiPoliceAdhibhar(String mumbaiPoliceAdhibhar) {
        this.mumbaiPoliceAdhibhar = mumbaiPoliceAdhibhar;
    }

    public String getPoliceThaneVitaran() {
        return policeThaneVitaran;
    }

    public void setPoliceThaneVitaran(String policeThaneVitaran) {
        this.policeThaneVitaran = policeThaneVitaran;
    }

    public String getTaddevGodownDistribution() {
        return taddevGodownDistribution;
    }

    public void setTaddevGodownDistribution(String taddevGodownDistribution) {
        this.taddevGodownDistribution = taddevGodownDistribution;
    }

    public String getCaLiNa() {
        return caLiNa;
    }

    public void setCaLiNa(String caLiNa) {
        this.caLiNa = caLiNa;
    }

    public String getPuneR() {
        return puneR;
    }

    public void setPuneR(String puneR) {
        this.puneR = puneR;
    }

    public String getPuneDeposited() {
        return puneDeposited;
    }

    public void setPuneDeposited(String puneDeposited) {
        this.puneDeposited = puneDeposited;
    }

    public String getNaygaonWeaponStock() {
        return naygaonWeaponStock;
    }

    public void setNaygaonWeaponStock(String naygaonWeaponStock) {
        this.naygaonWeaponStock = naygaonWeaponStock;
    }

    public String getPoliceThaneCR() {
        return policeThaneCR;
    }

    public void setPoliceThaneCR(String policeThaneCR) {
        this.policeThaneCR = policeThaneCR;
    }

    public String getGodownGoodstockAvailable() {
        return godownGoodstockAvailable;
    }

    public void setGodownGoodstockAvailable(String godownGoodstockAvailable) {
        this.godownGoodstockAvailable = godownGoodstockAvailable;
    }

    public String getGodownDamageStockAvailable() {
        return godownDamageStockAvailable;
    }

    public void setGodownDamageStockAvailable(String godownDamageStockAvailable) {
        this.godownDamageStockAvailable = godownDamageStockAvailable;
    }

    public String getCoachGoodstockAvailable() {
        return coachGoodstockAvailable;
    }

    public void setCoachGoodstockAvailable(String coachGoodstockAvailable) {
        this.coachGoodstockAvailable = coachGoodstockAvailable;
    }

    public String getCoachDamageStockAvailable() {
        return coachDamageStockAvailable;
    }

    public void setCoachDamageStockAvailable(String coachDamageStockAvailable) {
        this.coachDamageStockAvailable = coachDamageStockAvailable;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(String onDuty) {
        this.onDuty = onDuty;
    }

    public void setWeapon1(String weaponName) {

    }

    public void setWeapon2(String weaponName) {

    }

    public void setWeapon3(String weaponName) {

    }

    public void setWeapon4(String weaponName) {

    }
}