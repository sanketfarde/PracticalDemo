package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;
import java.util.List;

public class BellArmoryDTO {
	
    private String date;
    private List<String> weaponName;
    private List<String> mumbaiPoliceAdhibhar;
    private List<String> policeThaneVitaran;
    private List<String> taddevGodownDistribution;
    private List<String> caLiNa;
    private List<String> puneR;
    private List<String> puneDeposited;
    private List<String> naygaonWeaponStock;
    private List<String> policeThaneCR;
    private List<String> godownGoodstockAvailable;
    private List<String> godownDamageStockAvailable;
    private List<String> coachGoodstockAvailable;
    private List<String> coachDamageStockAvailable;
    private List<String> workshop;
    private List<String> onDuty;
    private List<String> godownTotal;
    private List<String> coachTotal;
    private LocalDateTime receivedBy; 
    
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
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

	private String employeePostingDate;
    //as per viplav on 01/06/2024

    public LocalDateTime getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(LocalDateTime receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(List<String> weaponName) {
        this.weaponName = weaponName;
    }

    public List<String> getMumbaiPoliceAdhibhar() {
        return mumbaiPoliceAdhibhar;
    }

    public void setMumbaiPoliceAdhibhar(List<String> mumbaiPoliceAdhibhar) {
        this.mumbaiPoliceAdhibhar = mumbaiPoliceAdhibhar;
    }

    public List<String> getPoliceThaneVitaran() {
        return policeThaneVitaran;
    }

    public void setPoliceThaneVitaran(List<String> policeThaneVitaran) {
        this.policeThaneVitaran = policeThaneVitaran;
    }

    public List<String> getTaddevGodownDistribution() {
        return taddevGodownDistribution;
    }

    public void setTaddevGodownDistribution(List<String> taddevGodownDistribution) {
        this.taddevGodownDistribution = taddevGodownDistribution;
    }

    public List<String> getCaLiNa() {
        return caLiNa;
    }

    public void setCaLiNa(List<String> caLiNa) {
        this.caLiNa = caLiNa;
    }

    public List<String> getPuneR() {
        return puneR;
    }

    public void setPuneR(List<String> puneR) {
        this.puneR = puneR;
    }

    public List<String> getPuneDeposited() {
        return puneDeposited;
    }

    public void setPuneDeposited(List<String> puneDeposited) {
        this.puneDeposited = puneDeposited;
    }

    public List<String> getNaygaonWeaponStock() {
        return naygaonWeaponStock;
    }

    public void setNaygaonWeaponStock(List<String> naygaonWeaponStock) {
        this.naygaonWeaponStock = naygaonWeaponStock;
    }

    public List<String> getPoliceThaneCR() {
        return policeThaneCR;
    }

    public void setPoliceThaneCR(List<String> policeThaneCR) {
        this.policeThaneCR = policeThaneCR;
    }

    public List<String> getGodownGoodstockAvailable() {
        return godownGoodstockAvailable;
    }

    public void setGodownGoodstockAvailable(List<String> godownGoodstockAvailable) {
        this.godownGoodstockAvailable = godownGoodstockAvailable;
    }

    public List<String> getGodownDamageStockAvailable() {
        return godownDamageStockAvailable;
    }

    public void setGodownDamageStockAvailable(List<String> godownDamageStockAvailable) {
        this.godownDamageStockAvailable = godownDamageStockAvailable;
    }

    public List<String> getCoachGoodstockAvailable() {
        return coachGoodstockAvailable;
    }

    public void setCoachGoodstockAvailable(List<String> coachGoodstockAvailable) {
        this.coachGoodstockAvailable = coachGoodstockAvailable;
    }

    public List<String> getCoachDamageStockAvailable() {
        return coachDamageStockAvailable;
    }

    public void setCoachDamageStockAvailable(List<String> coachDamageStockAvailable) {
        this.coachDamageStockAvailable = coachDamageStockAvailable;
    }

    public List<String> getWorkshop() {
        return workshop;
    }

    public void setWorkshop(List<String> workshop) {
        this.workshop = workshop;
    }

    public List<String> getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(List<String> onDuty) {
        this.onDuty = onDuty;
    }

    public List<String> getGodownTotal() {
        return godownTotal;
    }

    public void setGodownTotal(List<String> godownTotal) {
        this.godownTotal = godownTotal;
    }

    public List<String> getCoachTotal() {
        return coachTotal;
    }

    public void setCoachTotal(List<String> coachTotal) {
        this.coachTotal = coachTotal;
    }
}
