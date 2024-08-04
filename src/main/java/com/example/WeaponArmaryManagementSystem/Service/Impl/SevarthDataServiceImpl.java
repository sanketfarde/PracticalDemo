package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.Service.SevarthDataService;
import com.example.WeaponArmaryManagementSystem.model.SevarthData;
import com.example.WeaponArmaryManagementSystem.repository.SevarthDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SevarthDataServiceImpl implements SevarthDataService {

    @Autowired
    private SevarthDataRepository sevarthDataRepository;

    @Override
    public SevarthData saveSevarthData(SevarthData sevarthData) {
        return sevarthDataRepository.save(sevarthData);
    }

    @Override
    public SevarthData getSevarthDataById(String sevarthId) {
        Optional<SevarthData> optionalData = sevarthDataRepository.findById(sevarthId);
        return optionalData.orElse(null); // Return the data if found, otherwise return null
    }

    @Override
    public List<SevarthData> getAllSevarthData() {
        return sevarthDataRepository.findAll();
    }
}
