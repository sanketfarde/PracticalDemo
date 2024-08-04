package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.PoliceStationTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationTotalRepository extends JpaRepository<PoliceStationTotal, Long> {
  //  PoliceStationTotal findByPoliceStationNameAndWeaponNameAndRoundName(String policeStationName, String weaponName, String roundName);

    PoliceStationTotal findByPoliceStationIdAndWeaponName(String policeStationId, String weaponName);

    PoliceStationTotal findByPoliceStationIdAndRoundName(String policeStationId, String roundName);
}
