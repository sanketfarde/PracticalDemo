package com.example.WeaponArmaryManagementSystem.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.example.WeaponArmaryManagementSystem.Dto.BellArmoryDTO;
//import com.example.WeaponArmaryManagementSystem.controller.Page;
import com.example.WeaponArmaryManagementSystem.model.BellArmory;
import com.example.WeaponArmaryManagementSystem.model.BellOfArmsReport;

public interface BellofArmoryService {

   // void saveBellArmory(BellArmoryDTO bellArmoryDTO);

    List<Map<String, Object>> getBellArmoriesAndTotalsBetweenDates(String startDate, String endDate);

    List<BellArmory> getAllBellArmories();

    List<BellArmory> getBellArmoriesBetweenDates(String startDate, String endDate);
  //  List<BellArmory> getBellArmoriesBetweenDates(LocalDate startDate, LocalDate endDate);

  //  List<BellArmory> getBellArmoriesByDate(String date);
   List<BellArmory> getBellArmoriesByDate(String date);
  
    Map<String, Object> getBellArmoriesAndTotalsByDate(String date);

   // BellArmory saveBellArmory(BellArmory bellArmory);

    BellArmory saveBellArmory(BellArmoryDTO bellArmoryDTO);

    // this methods for the Bellofarmory Reports
    BellOfArmsReport saveBellOfArmsReport(BellOfArmsReport bellOfArmsReport);
   List<BellOfArmsReport>  getByButtNo(String buttNo);
    List<BellOfArmsReport> getAllBellOfArmsReports();
    List<BellOfArmsReport> getByDepositedDate(LocalDate depositedDate);

    List<BellOfArmsReport> getBetweenDates(String startDate, String endDate);


    //Page<BellArmory> getAllBellArmories(PageRequest paging);

    List<BellArmory> getBellArmoriesByWeaponNameAndDate(String weaponName, String date);

    List<BellArmory> getBellArmoriesByWeaponNamesAndDate(List<String> weaponNames, String date);

    BellArmory getBellArmoryById(Long id);

}
