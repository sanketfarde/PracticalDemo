package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

public interface DistributionMagzineRepositoryCustom {
    List<String> findDistinctPoliceStationsWithWeaponName();
    List<String> findMatchingPoliceStationNames();
    List<String> findDistinctPoliceStationsWithMatchingNames();
}
