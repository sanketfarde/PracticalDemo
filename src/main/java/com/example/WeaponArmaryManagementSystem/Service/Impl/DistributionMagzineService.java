package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;
import com.example.WeaponArmaryManagementSystem.model.PoliceStationRegistration;
import com.example.WeaponArmaryManagementSystem.repository.DistributionMagzineRepository;
import com.example.WeaponArmaryManagementSystem.repository.DmButtNoAndManufacturingNoRepository;
import com.example.WeaponArmaryManagementSystem.repository.PoliceStationRegistrationRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import java.util.stream.Collectors;

@Service
public class DistributionMagzineService {

    @Autowired
    private DistributionMagzineRepository distributionMagzineRepository;

    @Autowired
    private DmButtNoAndManufacturingNoRepository dmButtNoAndManufacturingNoRepository;

    @Autowired
    private PoliceStationRegistrationRepository policeStationRegistrationRepository;

    /*public List<String> getWeaponNamesByPoliceStationName(String policeStationName) {
        return null;
    }*/

    public List<String> getWeaponNamesByPoliceStationName(String policeStationName) {
        return distributionMagzineRepository.findWeaponNamesByPoliceStationName(policeStationName);
    }

    public List<Long> getTotalWeaponByWeaponName(String weaponName) {
        return distributionMagzineRepository.getTotalWeaponByWeaponName(weaponName);
    }

    /*public List<String> getWeaponNamesByPoliceStationName(String policeStationName) {
        return distributionMagzineRepository.findDistinctWeaponNamesByPoliceStationName(policeStationName);
    }*/

    public Long getTotalWeaponsByPoliceStationAndWeaponName(String policeStationName, String weaponName) {
        return distributionMagzineRepository.getTotalWeaponByPoliceStationAndWeaponName(policeStationName, weaponName);
    }

   /* public List<String> getPoliceStationNamesByNonZeroBalanceWeapon() {
        return policeStationRegistrationRepository.findPoliceStationNamesByNonZeroBalanceWeapon();
    }*/

    /*
    public List<DistributionMagzine> getReportsByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        int status = 0; // Status to filter by
        return distributionMagzineRepository.findByReturnDateBetweenAndStatus(start, end, status);
    }

     */



    public List<String> getDistinctPoliceStationsWithWeaponName() {
        return distributionMagzineRepository.findDistinctPoliceStationsWithWeaponName();
    }

    public List<String> getMatchingPoliceStationNames() {
        return distributionMagzineRepository.findMatchingPoliceStationNames();
    }

    public List<DistributionMagzine> getDistributionMagzinesByType(String distributionType) {
        return distributionMagzineRepository.findByDistributionType(distributionType);
    }

    public List<String> getDistinctPoliceStationsWithMatchingNames() {
        return distributionMagzineRepository.findDistinctPoliceStationsWithMatchingNames();
    }

//    public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
//        // Step 1: Get distinct police station names from DistributionMagzine
//        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();
//
//        // Step 2: Fetch PoliceStationRegistration entities by matching names
//        return policeStationRegistrationRepository.findByNameIn(distinctPoliceStationNames);
//    }

    /*public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        List<PoliceStationRegistration> policeStations = policeStationRegistrationRepository.findByNameIn(distinctPoliceStationNames);

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/

   /* public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        List<PoliceStationRegistration> policeStations = policeStationRegistrationRepository.findByNameIn(trimmedNames);

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/

    /*public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Step 3: Query PoliceStationRegistration with trimmed names
        List<PoliceStationRegistration> policeStations = new ArrayList<>();
        if (!trimmedNames.isEmpty()) {
            policeStations = policeStationRegistrationRepository.findByPoliceStationIds(trimmedNames);
        }

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/

   /* public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Step 3: Query PoliceStationRegistration with trimmed names
        List<PoliceStationRegistration> policeStations = new ArrayList<>();
        if (!trimmedNames.isEmpty()) {
            policeStations = policeStationRegistrationRepository.findByPoliceStationNames(trimmedNames);
        }

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/

   /* public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Logging the trimmed names
        System.out.println("Trimmed Names: " + trimmedNames);

        // Step 3: Query PoliceStationRegistration with trimmed names
        List<PoliceStationRegistration> policeStations = new ArrayList<>();
        if (!trimmedNames.isEmpty()) {
            policeStations = policeStationRegistrationRepository.findByPoliceStationNames(trimmedNames);
        }

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/


   /* public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Logging the trimmed names
        System.out.println("Trimmed Names: " + trimmedNames);

        // Step 3: Query PoliceStationRegistration with trimmed names
        List<PoliceStationRegistration> policeStations = new ArrayList<>();
        if (!trimmedNames.isEmpty()) {
            policeStations = policeStationRegistrationRepository.findByPoliceStationNames(trimmedNames);
        }

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/


   /* public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Logging the distinct police station names
        System.out.println("Distinct Police Station Names: " + distinctPoliceStationNames);

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Logging the trimmed names
        System.out.println("Trimmed Names: " + trimmedNames);

        // Step 3: Query PoliceStationRegistration with trimmed names
        List<PoliceStationRegistration> policeStations = new ArrayList<>();
        if (!trimmedNames.isEmpty()) {
            policeStations = policeStationRegistrationRepository.findByPoliceStationNames(trimmedNames);
        }

        // Logging the retrieved police stations
        System.out.println("Retrieved Police Stations: " + policeStations);

        return policeStations;
    }*/

    /*public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        // Step 1: Retrieve distinct police station names from DistributionMagzine
        List<String> distinctPoliceStationNames = distributionMagzineRepository.findDistinctPoliceStationNames();

        // Step 2: Trim whitespace from each name
        List<String> trimmedNames = distinctPoliceStationNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());

        // Step 3: Query PoliceStationRegistration by name and get corresponding policeStationIds
        List<Long> policeStationIds = policeStationRegistrationRepository.findByPoliceStationNames(trimmedNames)
                .stream()
                .map(PoliceStationRegistration::getPoliceStationId)
                .collect(Collectors.toList());

        // Step 4: Fetch PoliceStationRegistration entities by policeStationIds
        List<PoliceStationRegistration> policeStations = policeStationRegistrationRepository.findByPoliceStationIdIn(policeStationIds);

        return policeStations;
    }*/


    private static final Logger logger = LoggerFactory.getLogger(DistributionMagzineServiceImpl.class);

  //  @Override
    public List<PoliceStationRegistration> getPoliceStationsFromDistributionMagzine() {
        List<String> policeStationNames = distributionMagzineRepository.findDistinctPoliceStationNamesWithWeaponName();
        logger.info("Distinct Police Station Names with Weapon Name: {}", policeStationNames);

        List<Long> policeStationIds = policeStationNames.stream()
                .map(name -> {
                    try {
                        return Long.parseLong(name.trim());
                    } catch (NumberFormatException e) {
                        logger.error("Failed to parse police station name: {}", name, e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info("Parsed Police Station IDs: {}", policeStationIds);

        if (policeStationIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<PoliceStationRegistration> policeStations = policeStationRegistrationRepository.findAllByPoliceStationIdIn(policeStationIds);
        logger.info("Retrieved Police Stations: {}", policeStations);

        return policeStations;
    }





  /*  public List<DistributionMagzine> getDistributionMagzinesByType(String distributionType) {
        return distributionMagzineRepository.findByDistributionType(distributionType);
    }*/


   /* public List<DistributionMagzine> getReportsByDateRange(String startDate, String endDate) {
        return distributionMagzineRepository.findByDateBetween(startDate, endDate);
    }*/
   // working
    public List<DistributionMagzine> getReportsByDateRange(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return distributionMagzineRepository.findByStatus("0");
    }

    public List<DistributionMagzine> getReportsByDateRange1(String startDate, String endDate) {
        // Assuming you have a method to fetch data based on date range, add logic here if needed

        // Fetch data where status is 0
        return distributionMagzineRepository.findByStatus("1");
    }

    /*public List<DistributionMagzine> getDistributionMagzinesByType(String distributionType) {
        return distributionMagzineRepository.findByDistributionType(distributionType);
    }*/

    public int getButtNoCount(Long distributionId) {
        return dmButtNoAndManufacturingNoRepository.countByDistributionIdAndButtNoIsNotNull(distributionId);
    }

    public int getManufacturingNoCount(Long distributionId) {
        return dmButtNoAndManufacturingNoRepository.countByDistributionIdAndManufacturingNoIsNotNull(distributionId);
    }

    /*public List<String> getPoliceStationNamesWithBalanceWeapon() {
        List<DistributionMagzine> distributionMagzines = distributionMagzineRepository.findByBalanceWeaponNotZeroAndNotNull();
        return distributionMagzines.stream()
                .map(dm -> policeStationRegistrationRepository.findById(Long.valueOf(dm.getPoliceStationName()))
                        .map(policeStation -> policeStation.getName())
                        .orElse(null))
                .filter(name -> name != null)
                .collect(Collectors.toList());
    }*/


    /*
    public List<DistributionMagzine> getDistributionReportsByDateRange(String startDate, String endDate) {
        return distributionMagzineRepository.findByDistributeDateBetween(startDate, endDate);
    }

    public List<DistributionMagzine> getReturnReportsByDateRange(String startDate, String endDate) {
        return distributionMagzineRepository.findByReturnDateBetween(startDate, endDate);
    }

     */

/*
    public List<DistributionMagzine> getDistributionReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = endDate.format(formatter);
        return distributionMagzineRepository.findByDistributeDateBetween(start, end);
    }

    public List<DistributionMagzine> getReturnReportsByDateRange(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = startDate.format(formatter);
        String end = endDate.format(formatter);
        return distributionMagzineRepository.findByReturnDateBetween(start, end);
    }

 */
}
