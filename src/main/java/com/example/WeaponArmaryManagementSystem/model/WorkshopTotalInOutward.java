package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_workshop_total_inoutward")
public class WorkshopTotalInOutward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "round_name")
    private String roundName;

    @Column(name = "total_stock")
    private Integer totalStock;

    @Column(name = "distribution_stock")
    private Integer distributionStock;

    @Column(name = "available_stock")
    private Integer availableStock;

    @Column(name = "expiry_date")
    private String expiryDate;

    public WorkshopTotalInOutward() {
    }

    public WorkshopTotalInOutward(Long id, String weaponName, String roundName, Integer totalStock,
                                  Integer distributionStock, Integer availableStock, String expiryDate) {
        this.id = id;
        this.weaponName = weaponName;
        this.roundName = roundName;
        this.totalStock = totalStock;
        this.distributionStock = distributionStock;
        this.availableStock = availableStock;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public Integer getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(Integer totalStock) {
        this.totalStock = totalStock;
    }

    public Integer getDistributionStock() {
        return distributionStock;
    }

    public void setDistributionStock(Integer distributionStock) {
        this.distributionStock = distributionStock;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
