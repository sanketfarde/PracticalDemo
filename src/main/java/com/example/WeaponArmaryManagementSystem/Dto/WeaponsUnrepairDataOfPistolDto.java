package com.example.WeaponArmaryManagementSystem.Dto;

import jakarta.persistence.Column;

import java.util.List;

public class WeaponsUnrepairDataOfPistolDto {
	
    private String date;
    private String received_by;  //changes as per viplav by vikas on 29/05/2024
    private String remark;
    private String updatedRemark;
    private String weaponName;
    private String weaponType;
    private List<String> partName;
    private List<String> quantity;
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;
    
    
    public String getUpdatedRemark() {
        return updatedRemark;
    }

    public void setUpdatedRemark(String updatedRemark) {
        this.updatedRemark = updatedRemark;
    }

    public String getReceived_by() {
		return received_by;
	}

	public void setReceived_by(String received_by) {
		this.received_by = received_by;
	}

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public List<String> getPartName() {
        return partName;
    }

    public void setPartName(List<String> partName) {
        this.partName = partName;
    }

    public List<String> getQuantity() {
        return quantity;
    }

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

    public void setQuantity(List<String> quantity) {
        this.quantity = quantity;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

    /*      private Map<String, Long> partName;*/

  /*    private Map<String, Long> quantity;*/

    /*  public Map<String, Long> getQuantity() {
          if (quantity == null) {
              quantity = new HashMap<>();
          }
          return quantity;
      }
  */

   /* public Map<String, Long> getPartName() {
        if (partName == null) {
            partName = new HashMap<>();
        }
        return partName;
    }*/




