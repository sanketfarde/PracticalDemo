package com.example.WeaponArmaryManagementSystem.Dto;

public class CommonInfo {
    private String date;
    private String remark;
    private String godownName;
    private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;

    public CommonInfo(String date, String remark, String godownName, String employeeId, String employeeName,
                      String employeeDesignation, String employeePostingDate) {
        this.date = date;
        this.remark = remark;
        this.godownName = godownName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
        this.employeePostingDate = employeePostingDate;
    }

    public CommonInfo() {
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

    public String getGodownName() {
        return godownName;
    }

    public void setGodownName(String godownName) {
        this.godownName = godownName;
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
}
