package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name="totalWeaponSummary")
public class TotalWeaponSummary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "mumbai_police_adhibha")
    private int mumbaiPoliceAdhibha;

    @Column(name = "police_station_distribution")
    private int policeStationDistribution;
    
    @Column(name="PTS_distribution")
    private int ptsDistribution;
    
    @Column(name="calina")
    private int calina;
    
    @Column(name="weapon_storage_office")
    private int weaponStorageOffice;
    
    @Column(name="workshop")
    private int workshop;
    
    @Column(name="pune_R_R")
    private int Punerr;
    
    @Column(name="police_station_CR")
    private int policeStationcr;
    
    @Column(name="onduty")
    private int onduty;
    
    @Column(name="godown_repair")
    private int godownRepair;
    
    @Column(name="godown_non_repair")
    private int godownNonRepair;
    
    @Column(name="coach_repair")
    private int coachRepair;
    
    @Column(name="coach_non_repair")
    private int coachNonRepair;
    
    @Column(name="date")
    private Date date;

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

	public TotalWeaponSummary() {
        
    }

	public TotalWeaponSummary(Integer id, int mumbaiPoliceAdhibha, int policeStationDistribution, int ptsDistribution,
                              int calina, int weaponStorageOffice, int workshop, int punerr, int policeStationcr, int onduty,
                              int godownRepair, int godownNonRepair, int coachRepair, int coachNonRepair, Date date) {
		super();
		this.id = id;
		this.mumbaiPoliceAdhibha = mumbaiPoliceAdhibha;
		this.policeStationDistribution = policeStationDistribution;
		this.ptsDistribution = ptsDistribution;
		this.calina = calina;
		this.weaponStorageOffice = weaponStorageOffice;
		this.workshop = workshop;
		this.Punerr = punerr;
		this.policeStationcr = policeStationcr;
		this.onduty = onduty;
		this.godownRepair = godownRepair;
		this.godownNonRepair = godownNonRepair;
		this.coachRepair = coachRepair;
		this.coachNonRepair = coachNonRepair;
		this.date = date;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMumbaiPoliceAdhibha() {
		return mumbaiPoliceAdhibha;
	}

	public void setMumbaiPoliceAdhibha(int mumbaiPoliceAdhibha) {
		this.mumbaiPoliceAdhibha = mumbaiPoliceAdhibha;
	}

	public int getPoliceStationDistribution() {
		return policeStationDistribution;
	}

	public void setPoliceStationDistribution(int policeStationDistribution) {
		this.policeStationDistribution = policeStationDistribution;
	}

	public int getPtsDistribution() {
		return ptsDistribution;
	}

	public void setPtsDistribution(int ptsDistribution) {
		this.ptsDistribution = ptsDistribution;
	}

	public int getCalina() {
		return calina;
	}

	public void setCalina(int calina) {
		this.calina = calina;
	}

	public int getWeaponStorageOffice() {
		return weaponStorageOffice;
	}

	public void setWeaponStorageOffice(int weaponStorageOffice) {
		this.weaponStorageOffice = weaponStorageOffice;
	}

	public int getWorkshop() {
		return workshop;
	}

	public void setWorkshop(int workshop) {
		this.workshop = workshop;
	}

	public int getPunerr() {
		return Punerr;
	}

	public void setPunerr(int punerr) {
		Punerr = punerr;
	}

	public int getPoliceStationcr() {
		return policeStationcr;
	}

	public void setPoliceStationcr(int policeStationcr) {
		this.policeStationcr = policeStationcr;
	}

	public int getOnduty() {
		return onduty;
	}

	public void setOnduty(int onduty) {
		this.onduty = onduty;
	}

	public int getGodownRepair() {
		return godownRepair;
	}

	public void setGodownRepair(int godownRepair) {
		this.godownRepair = godownRepair;
	}

	public int getGodownNonRepair() {
		return godownNonRepair;
	}

	public void setGodownNonRepair(int godownNonRepair) {
		this.godownNonRepair = godownNonRepair;
	}

	public int getCoachRepair() {
		return coachRepair;
	}

	public void setCoachRepair(int coachRepair) {
		this.coachRepair = coachRepair;
	}

	public int getCoachNonRepair() {
		return coachNonRepair;
	}

	public void setCoachNonRepair(int coachNonRepair) {
		this.coachNonRepair = coachNonRepair;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "TotalWeaponSummary [id=" + id + ", mumbaiPoliceAdhibha=" + mumbaiPoliceAdhibha
				+ ", policeStationDistribution=" + policeStationDistribution + ", ptsDistribution=" + ptsDistribution
				+ ", calina=" + calina + ", weaponStorageOffice=" + weaponStorageOffice + ", workshop=" + workshop
				+ ", Punerr=" + Punerr + ", policeStationcr=" + policeStationcr + ", onduty=" + onduty
				+ ", godownRepair=" + godownRepair + ", godownNonRepair=" + godownNonRepair + ", coachRepair="
				+ coachRepair + ", coachNonRepair=" + coachNonRepair + ", date=" + date + "]";
	}  
   
}
