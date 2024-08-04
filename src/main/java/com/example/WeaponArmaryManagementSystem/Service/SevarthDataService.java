package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.SevarthData;
import java.util.List;

public interface SevarthDataService {
    SevarthData saveSevarthData(SevarthData sevarthData);
    SevarthData getSevarthDataById(String sevarthId);
    List<SevarthData> getAllSevarthData();
}
