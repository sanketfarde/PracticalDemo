package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bellarmory")

public class BellWeaponSummary {
	
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

    @Column(name = "godown_total",columnDefinition = "TEXT")
    private String godownTotal;

    @Column(name = "coach_total",columnDefinition = "TEXT")
    private String coachTotal;


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

	public String getGodownTotal() {
		return godownTotal;
	}

	public void setGodownTotal(String godownTotal) {
		this.godownTotal = godownTotal;
	}

	public String getCoachTotal() {
		return coachTotal;
	}

	public void setCoachTotal(String coachTotal) {
		this.coachTotal = coachTotal;
	}

	public void setTotal(int total) {
		// TODO Auto-generated method stub
		
	} 
   
}

/*
@Column(name = "bayonet")
private String bayonet;

@Column(name = "magazine")
private String magazine;

@Column(name = "filler")
private String filler

private String weaponDate;
private String weaponName;
private String bayonet;
private String magazine;
private String filler;
private String grenadeLauncher;
private String mumbaiPoliceAdhibhar;
private String policeThaneVitaran;
private String taddevGodownDistribution;
private String caLiNa;
private String puneR;
private String puneDeposited;
private String naygaonWeaponStock;
private String policeThaneCR;
private String stockAvailableGood;
private String stockAvailableDamaged;
private String workshop;
private String onDuty;
private String stockAvailableGoodOne;
private String stockAvailableDamagedOne;
*/
// Getters and setters