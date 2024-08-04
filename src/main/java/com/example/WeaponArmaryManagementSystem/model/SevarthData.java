package com.example.WeaponArmaryManagementSystem.model;
import jakarta.persistence.*;

@Entity
@Table(name = "sevarth_data")
public class SevarthData {

    @Id
    @Column(name = "sevarth_id")
    private String sevarthId;

    @Column(name = "dateof_birth")
    private String dateOfBirth;

    @Column(name = "dateof_retairment")
    private String dateOfRetairement;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @Column(name = "designation")
    private String designation;

    @Column(name = "bucckle_no")
    private String bucckleNo;

    @Column(name = "person_name")
    private String personName;

    public String getSevarthId() {
        return sevarthId;
    }

    public void setSevarthId(String sevarthId) {
        this.sevarthId = sevarthId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfRetairement() {
        return dateOfRetairement;
    }

    public void setDateOfRetairement(String dateOfRetairement) {
        this.dateOfRetairement = dateOfRetairement;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBucckleNo() {
        return bucckleNo;
    }

    public void setBucckleNo(String bucckleNo) {
        this.bucckleNo = bucckleNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
