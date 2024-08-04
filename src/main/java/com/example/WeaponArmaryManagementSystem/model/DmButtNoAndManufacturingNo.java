package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_dm_butt_no_and_manufacturing_no")
public class DmButtNoAndManufacturingNo {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private Long id;

     @Column(name = "butt_no")
     private String buttNo;

     @Column(name = "distribution_id")
     private Long distributionId;

     @Column(name = "manufacturing_no")
     private String manufacturingNo;

     @Column(name = "round_name")
     private String roundName;

     @Column(name = "weapon_name")
     private String weaponName;

     @Column(name = "weapon_type")
     private String weaponType;

     @Column(name = "distribution_status")
     private String distributionStatus;

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

     public Long getDistributionId() {
          return distributionId;
     }

     public void setDistributionId(Long distributionId) {
          this.distributionId = distributionId;
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

     public String getDistributionStatus() {
          return distributionStatus;
     }

     public void setDistributionStatus(String distributionStatus) {
          this.distributionStatus = distributionStatus;
     }
}
