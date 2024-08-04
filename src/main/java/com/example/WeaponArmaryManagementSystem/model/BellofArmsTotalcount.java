package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bellofArmsTotalcount ")
public class BellofArmsTotalcount {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weapon_date")
    private String date;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "totalmumbai_police_adhibhar")
    private String totalMumbaiPoliceAdhibhar;

    @Column(name = "totalpolice_thane_vitaran")
    private String total_policeThaneVitaran;

    @Column(name = "totaltaddev_godown_distribution")
    private String totalTaddevGodownDistribution;

    @Column(name = "total_calina")
    private String totalCaLiNa;

    @Column(name = "totalpune_r")
    private String totalPuneR;

    @Column(name = "totalpune_deposited")
    private String totalPuneDeposited;

    @Column(name = "totalnaygaon_weapon_stock")
    private String totalNaygaonWeaponStock;

    @Column(name = "totalpolice_thane_cr")
    private String totalPoliceThaneCR;

    @Column(name = "totalgodown_goodstockavailable")
    private String totalGodownGoodstockAvailable;

    @Column(name = "total_godowndamagestockavailable")
    private String totalGodownDamageStockAvailable;

    @Column(name = "totalcoach_goodstockavailable")
    private String totalCoachGoodstockAvailable;

    @Column(name = "total_coachdamagestockavailable")
    private String totalCoachDamageStockAvailable;

    @Column(name = "total_workshop")
    private String totalWorkshop;

    @Column(name = "totalon_duty")
    private String totalOnDuty;

    @Column(name = "godown_total")
    private String godownTotal;

    @Column(name = "totalcoach_total")
    private String totalCoachTotal;

    
    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
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

    public String getTotalMumbaiPoliceAdhibhar() {
        return totalMumbaiPoliceAdhibhar;
    }

    public void setTotalMumbaiPoliceAdhibhar(String totalMumbaiPoliceAdhibhar) {
        this.totalMumbaiPoliceAdhibhar = totalMumbaiPoliceAdhibhar;
    }

    public String getTotal_policeThaneVitaran() {
        return total_policeThaneVitaran;
    }

    public void setTotal_policeThaneVitaran(String total_policeThaneVitaran) {
        this.total_policeThaneVitaran = total_policeThaneVitaran;
    }

    public String getTotalTaddevGodownDistribution() {
        return totalTaddevGodownDistribution;
    }

    public void setTotalTaddevGodownDistribution(String totalTaddevGodownDistribution) {
        this.totalTaddevGodownDistribution = totalTaddevGodownDistribution;
    }

    public String getTotalCaLiNa() {
        return totalCaLiNa;
    }

    public void setTotalCaLiNa(String totalCaLiNa) {
        this.totalCaLiNa = totalCaLiNa;
    }

    public String getTotalPuneR() {
        return totalPuneR;
    }

    public void setTotalPuneR(String totalPuneR) {
        this.totalPuneR = totalPuneR;
    }

    public String getTotalPuneDeposited() {
        return totalPuneDeposited;
    }

    public void setTotalPuneDeposited(String totalPuneDeposited) {
        this.totalPuneDeposited = totalPuneDeposited;
    }

    public String getTotalNaygaonWeaponStock() {
        return totalNaygaonWeaponStock;
    }

    public void setTotalNaygaonWeaponStock(String totalNaygaonWeaponStock) {
        this.totalNaygaonWeaponStock = totalNaygaonWeaponStock;
    }

    public String getTotalPoliceThaneCR() {
        return totalPoliceThaneCR;
    }

    public void setTotalPoliceThaneCR(String totalPoliceThaneCR) {
        this.totalPoliceThaneCR = totalPoliceThaneCR;
    }

    public String getTotalGodownGoodstockAvailable() {
        return totalGodownGoodstockAvailable;
    }

    public void setTotalGodownGoodstockAvailable(String totalGodownGoodstockAvailable) {
        this.totalGodownGoodstockAvailable = totalGodownGoodstockAvailable;
    }

    public String getTotalGodownDamageStockAvailable() {
        return totalGodownDamageStockAvailable;
    }

    public void setTotalGodownDamageStockAvailable(String totalGodownDamageStockAvailable) {
        this.totalGodownDamageStockAvailable = totalGodownDamageStockAvailable;
    }

    public String getTotalCoachGoodstockAvailable() {
        return totalCoachGoodstockAvailable;
    }

    public void setTotalCoachGoodstockAvailable(String totalCoachGoodstockAvailable) {
        this.totalCoachGoodstockAvailable = totalCoachGoodstockAvailable;
    }

    public String getTotalCoachDamageStockAvailable() {
        return totalCoachDamageStockAvailable;
    }

    public void setTotalCoachDamageStockAvailable(String totalCoachDamageStockAvailable) {
        this.totalCoachDamageStockAvailable = totalCoachDamageStockAvailable;
    }

    public String getTotalWorkshop() {
        return totalWorkshop;
    }

    public void setTotalWorkshop(String totalWorkshop) {
        this.totalWorkshop = totalWorkshop;
    }

    public String getTotalOnDuty() {
        return totalOnDuty;
    }

    public void setTotalOnDuty(String totalOnDuty) {
        this.totalOnDuty = totalOnDuty;
    }

    public String getGodownTotal() {
        return godownTotal;
    }

    public void setGodownTotal(String godownTotal) {
        this.godownTotal = godownTotal;
    }

    public String getTotalCoachTotal() {
        return totalCoachTotal;
    }

    public void setTotalCoachTotal(String totalCoachTotal) {
        this.totalCoachTotal = totalCoachTotal;
    }
}
