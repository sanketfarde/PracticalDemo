package com.example.WeaponArmaryManagementSystem.Dto;

import java.util.List;

public class DistributionRequestWrapper {
    private List<DistributionRequest> requests;
    private String godownName;

    // Getters and Setters
    public List<DistributionRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<DistributionRequest> requests) {
        this.requests = requests;
    }

    public String getGodownName() {
        return godownName;
    }

    public void setGodownName(String godownName) {
        this.godownName = godownName;
    }
}
