package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_rm_butt_no_and_manufacturing_no")
public class RmButtNoAndManufacturingNo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "butt_no")
    private String buttNo;

    @Column(name = "return_id")
    private Long returnId;

    @Column(name = "manufacturing_no")
    private String manufacturingNo;

    @Column(name = "round_name")
    private String roundName;

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "weapon_type")
    private String weaponType;

    @Column(name = "return_status")
    private String returnStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButtNo() {
        return buttNo;
    }

    public void setButtNo(String buttNo) {
        this.buttNo = buttNo;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getManufacturingNo() {
        return manufacturingNo;
    }

    public void setManufacturingNo(String manufacturingNo) {
        this.manufacturingNo = manufacturingNo;
    }

    public String getRoundName() {
        return roundName;
    }

    public void setRoundName(String roundName) {
        this.roundName = roundName;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}
