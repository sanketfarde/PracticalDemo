package com.example.WeaponArmaryManagementSystem.model;

import java.util.List;

public class SaveDataRequest1 {
	
    private OutwardRegister outwardRegister;
    private List<String[]> outwardItemsList;
    
	public OutwardRegister getOutwardRegister() {
		return outwardRegister;
	}
	public void setOutwardRegister(OutwardRegister outwardRegister) {
		this.outwardRegister = outwardRegister;
	}
	public List<String[]> getOutwardItemsList() {
		return outwardItemsList;
	}
	public void setOutwardItemsList(List<String[]> outwardItemsList) {
		this.outwardItemsList = outwardItemsList;
	} 
    
}
