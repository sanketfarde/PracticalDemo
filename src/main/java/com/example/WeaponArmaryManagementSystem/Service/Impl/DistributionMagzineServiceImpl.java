package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.PoliceStationRegistration;
import com.example.WeaponArmaryManagementSystem.repository.DistributionMagzineRepository;
import com.example.WeaponArmaryManagementSystem.repository.PoliceStationRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DistributionMagzineServiceImpl extends DistributionMagzineService {

    @Autowired
    private DistributionMagzineRepository distributionMagzineRepository;

    @Autowired
    private PoliceStationRegistrationRepository policeStationRegistrationRepository;

    @Override
    public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        List<String> policeStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        if (policeStationNames.isEmpty()) {
            return Collections.emptyList();
        }

        return policeStationRegistrationRepository.findAllByNameIn(policeStationNames);
    }

    @Override
    public List<String> getWeaponNamesByPoliceStationName(String policeStationName) {
        return distributionMagzineRepository.findWeaponNamesByPoliceStationName(policeStationName);
    }

    /*@Override
    public List<String> getWeaponNamesByPoliceStationName(String policeStationName) {
        return distributionMagzineRepository.findWeaponNamesByPoliceStationName(policeStationName);
    }*/

}