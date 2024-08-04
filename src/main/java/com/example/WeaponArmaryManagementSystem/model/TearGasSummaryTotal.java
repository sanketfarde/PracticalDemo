package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teargas_summarytotal")
public class TearGasSummaryTotal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_teargas_amunities")
    private String totalTeargasAmunities;

    @Column(name = "count_total_surcharge")
    private String countTotalSurcharge;

    @Column(name = "total_shakha_vitran_active")
    private String totalShakhaVitranActive;

    @Column(name = "total_shakha_vitran_expired")
    private String totalShakhaVitranExpired;

    @Column(name = "total_gasdodownstockactive")
    private String totalGasDodownStockActive;

    @Column(name = "total_gasdodownstockexpired")
    private String totalGasDodownStockExpired;

    @Column(name = "count_total")
    private String total;

    @Column(name = "date")
    private String date;

    @Column(name = "count_total_all")
    private String countTotalAll;


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

    public String getTotalTeargasAmunities() {
        return totalTeargasAmunities;
    }

    public void setTotalTeargasAmunities(String totalTeargasAmunities) {
        this.totalTeargasAmunities = totalTeargasAmunities;
    }

    public String getCountTotalSurcharge() {
        return countTotalSurcharge;
    }

    public void setCountTotalSurcharge(String countTotalSurcharge) {
        this.countTotalSurcharge = countTotalSurcharge;
    }

    public String getTotalShakhaVitranActive() {
        return totalShakhaVitranActive;
    }

    public void setTotalShakhaVitranActive(String totalShakhaVitranActive) {
        this.totalShakhaVitranActive = totalShakhaVitranActive;
    }

    public String getTotalShakhaVitranExpired() {
        return totalShakhaVitranExpired;
    }

    public void setTotalShakhaVitranExpired(String totalShakhaVitranExpired) {
        this.totalShakhaVitranExpired = totalShakhaVitranExpired;
    }

    public String getTotalGasDodownStockActive() {
        return totalGasDodownStockActive;
    }

    public void setTotalGasDodownStockActive(String totalGasDodownStockActive) {
        this.totalGasDodownStockActive = totalGasDodownStockActive;
    }

    public String getTotalGasDodownStockExpired() {
        return totalGasDodownStockExpired;
    }

    public void setTotalGasDodownStockExpired(String totalGasDodownStockExpired) {
        this.totalGasDodownStockExpired = totalGasDodownStockExpired;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCountTotalAll() {
        return countTotalAll;
    }

    public void setCountTotalAll(String countTotalAll) {
        this.countTotalAll = countTotalAll;
    }
}
