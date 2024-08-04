package com.example.WeaponArmaryManagementSystem.model;

import java.util.List;

public class SaveDataRequest {
	
    private InwardRegister inwardRegister;
    private List<String[]> inwardItemsList;



    public InwardRegister getInwardRegister() {
        return inwardRegister;
    }

    public void setInwardRegister(InwardRegister inwardRegister) {
        this.inwardRegister = inwardRegister;
    }

    public List<String[]> getInwardItemsList() {
        return inwardItemsList;
    }

    public void setInwardItemsList(List<String[]> inwardItemsList) {
        this.inwardItemsList = inwardItemsList;
    }
}
