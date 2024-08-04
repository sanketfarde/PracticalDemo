package com.example.WeaponArmaryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeaponArmaryManagementSystem.model.PoliceStationRegistration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRegistrationRepository extends JpaRepository<PoliceStationRegistration, Long>{

    /*@Query("SELECT p.name FROM PoliceStationRegistration p WHERE p.policeStationId IN " +
            "(SELECT d.policeStationName FROM DistributionMagzine d WHERE d.balanceWeapon <> '0' AND d.balanceWeapon IS NOT NULL)")
    List<String> findPoliceStationNamesByNonZeroBalanceWeapon();*/

    /*@Query("SELECT ps.name FROM PoliceStationRegistration ps WHERE ps.weaponBalance > 0")
    List<String> findPoliceStationNamesByNonZeroBalanceWeapon();*/

    /*@Query("SELECT p.name FROM PoliceStationRegistration p WHERE p.id IN " +
            "(SELECT d.policeStationName FROM DistributionMagzine d WHERE d.balanceWeapon > 0 AND d.weaponName IS NOT NULL)")
    List<String> findPoliceStationNamesByNonZeroBalanceWeapon();*/

    List<PoliceStationRegistration> findByNameIn(List<String> policeStationNames);

    @Query("SELECT psr FROM PoliceStationRegistration psr WHERE psr.name IN :policeStationNames")
    List<PoliceStationRegistration> findByPoliceStationNames(List<String> policeStationNames);

    List<PoliceStationRegistration> findByPoliceStationIdIn(List<Long> policeStationIds);

    List<PoliceStationRegistration> findAllByNameIn(List<String> policeStationNames);

    List<PoliceStationRegistration> findAllByPoliceStationIdIn(List<Long> ids);
}
