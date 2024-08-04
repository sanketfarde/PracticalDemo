package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.PoliceStationRegistration;
import com.example.WeaponArmaryManagementSystem.repository.PoliceStationRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationRegistrationService {

    @Autowired
    private PoliceStationRegistrationRepository policeStationRegistrationRepository;

   /* public List<String> getPoliceStationNamesWithNonZeroBalanceWeapon() {
        return policeStationRegistrationRepository.findPoliceStationNamesByNonZeroBalanceWeapon();
    }*/

    public String getPoliceStationNameById(Long policeStationId) {
        Optional<PoliceStationRegistration> policeStation = policeStationRegistrationRepository.findById(policeStationId);
        return policeStation.map(PoliceStationRegistration::getName).orElse(null);
    }

   /* public String getPoliceStationNameById(Long policeStationId) {
        Optional<PoliceStationRegistration> policeStation = policeStationRegistrationRepository.findById(policeStationId);
        return policeStation.map(PoliceStationRegistration::getName).orElse(null);
    }*/
}
