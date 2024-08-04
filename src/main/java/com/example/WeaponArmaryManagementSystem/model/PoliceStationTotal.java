package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "police_station_total")
public class PoliceStationTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //   @Column(name = "police_station_name")
    //   private String policeStationName;

    @Column(name = "police_station_id")
    private String policeStationId;

    @Column(name = "weapon_type")
    private String weaponStaticType;

    @Column(name = "round_type")
    private String roundStaticType;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "weapon_quantity")
    private String weaponQuantity;

    @Column(name = "round_name")
    private String roundName;

    @Column(name = "round_quantity")
    private String roundQuantity;

    public PoliceStationTotal() {
    }

    public PoliceStationTotal(Long id, String policeStationId, String weaponStaticType, String roundStaticType, String weaponName,
                              String weaponQuantity, String roundName, String roundQuantity) {
        this.id = id;
        this.policeStationId = policeStationId;
        this.weaponStaticType = weaponStaticType;
        this.roundStaticType = roundStaticType;
        this.weaponName = weaponName;
        this.weaponQuantity = weaponQuantity;
        this.roundName = roundName;
        this.roundQuantity = roundQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoliceStationId() {
        return policeStationId;
    }

    public void setPoliceStationId(String policeStationId) {
        this.policeStationId = policeStationId;
    }

    public String getWeaponStaticType() {
        return weaponStaticType;
    }

    public void setWeaponStaticType(String weaponStaticType) {
        this.weaponStaticType = weaponStaticType;
    }

    public String getRoundStaticType() {
        return roundStaticType;
    }

    public void setRoundStaticType(String roundStaticType) {
        this.roundStaticType = roundStaticType;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponQuantity() {
        return weaponQuantity;
    }

    public void setWeaponQuantity(String weaponQuantity) {
        this.weaponQuantity = weaponQuantity;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getRoundQuantity() {
        return roundQuantity;
    }

    public void setRoundQuantity(String roundQuantity) {
        this.roundQuantity = roundQuantity;
    }
}
