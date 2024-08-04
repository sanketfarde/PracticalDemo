package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionMagzineRepository extends JpaRepository<DistributionMagzine, Long>, DistributionMagzineRepositoryCustom{

  /*@Query("SELECT DISTINCT d.policeStationName FROM DistributionMagzine d")
  List<String> findDistinctPoliceStationNames();*/

  @Query("SELECT DISTINCT dm.policeStationName FROM DistributionMagzine dm")
  List<String> findDistinctPoliceStationNames();

  @Query("SELECT DISTINCT d.policeStationName FROM DistributionMagzine d WHERE d.weaponName IS NOT NULL AND d.weaponName <> ''")
  List<String> findDistinctPoliceStationNamesWithWeaponName();

 /* @Query("SELECT dm.weaponName FROM DistributionMagzine dm WHERE dm.policeStationName = :policeStationName")
  List<String> findWeaponNamesByPoliceStationName(String policeStationName);*/
 @Query("SELECT DISTINCT dm.weaponName FROM DistributionMagzine dm WHERE dm.policeStationName = :policeStationName")
 List<String> findWeaponNamesByPoliceStationName(String policeStationName);

 /* @Query("SELECT dm.weaponName FROM DistributionMagzine dm WHERE dm.policeStationName = :policeStationName")
  List<String> findWeaponNamesByPoliceStationName(String policeStationName);
*/

    @Query("SELECT SUM(dm.totalWeapon) FROM DistributionMagzine dm WHERE dm.weaponName = :weaponName GROUP BY dm.policeStationName")
    List<Long> getTotalWeaponByWeaponName(@Param("weaponName") String weaponName);

    @Query("SELECT DISTINCT dm.weaponName FROM DistributionMagzine dm WHERE dm.policeStationName = :policeStationName")
    List<String> findDistinctWeaponNamesByPoliceStationName(@Param("policeStationName") String policeStationName);

    @Query("SELECT SUM(dm.totalWeapon) FROM DistributionMagzine dm WHERE dm.policeStationName = :policeStationName AND dm.weaponName = :weaponName")
    Long getTotalWeaponByPoliceStationAndWeaponName(@Param("policeStationName") String policeStationName, @Param("weaponName") String weaponName);

	List<DistributionMagzine> findByStatus(String status);

    List<DistributionMagzine> findByDistributionDateBetween(String distributionStartDate, String distributionEndDate);

    List<DistributionMagzine> findByRecievedWeaponDateBetween(String receivedStartDate, String receivedEndDate);

    List<DistributionMagzine> findByButtNo(String buttNo);

    List<DistributionMagzine> findByWeaponName(String weaponName);

    List<DistributionMagzine> findByDistributionType(String distributionType);


  // New method to find records by distribution_type
  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionType = 'Police Station/adhibhar'")
  List<DistributionMagzine> findByDistributionType();

  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionType = 'Individual'")
  List<DistributionMagzine> findByIndividualDistributionType();

    // working
  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate")
  List<DistributionMagzine> findByDateBetween(String startDate, String endDate);


  List<DistributionMagzine> findByStatusAndDistributionDateBetween(String string, String startDate, String endDate);

  List<DistributionMagzine> findByDistributionTypeAndDistributionDateBetween(String string, String startDate, String endDate);

 /* @Query("SELECT dm FROM DistributionMagzine dm WHERE dm.balanceWeapon != '0'")
  List<DistributionMagzine> findByBalanceWeaponNotZeroAndNotNull();*/



//  List<DistributionMagzine> findByReturnDateBetweenAndStatus(LocalDate startDate, LocalDate endDate, int status);

/*
  //  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate AND d.status = 0")
    @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate AND d.status = 0")
    List<DistributionMagzine> findByDistributeDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate AND d.status = 1")
    List<DistributionMagzine> findByReturnDateBetween(@Param("startDate") String startDate,@Param("endDate") String endDate);

 */
  /*

  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate AND d.status = 0")
  List<DistributionMagzine> findByDistributeDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

  @Query("SELECT d FROM DistributionMagzine d WHERE d.distributionDate BETWEEN :startDate AND :endDate AND d.status = 1")
  List<DistributionMagzine> findByReturnDateBetween(@Param("startDate") String startDate,@Param("endDate") String endDate);


   */

  List<DistributionMagzine> findByPoliceStationName(String policeStationName);

    List<DistributionMagzine> findByMobileNumber(String mobileNumber);

    List<DistributionMagzine> findByIdentityNo(String identityNo);

    List<DistributionMagzine> findByDateOfRetirement(String dateOfRetirement);

    List<DistributionMagzine> findByManufacturingNo(String manufacturingNo);

    List<DistributionMagzine> findByServicingDate(String servicingDate);

    @Query(value = "SELECT d FROM DistributionMagzine d WHERE d.dateOfRetirement BETWEEN ?1 AND ?2")
    List<DistributionMagzine> findAlerts(String startDate, String endDate);

    @Query(value = "SELECT d FROM DistributionMagzine d WHERE d.servicingDate BETWEEN ?1 AND ?2")
    List<DistributionMagzine> findAlert(String startDate, String endDate);

    List<DistributionMagzine> findByWeaponNameAndStatus(String weaponName, String s);

    List<DistributionMagzine> findByRoundNameAndStatus(String roundName, String s);

    @Query("SELECT SUM(w.totalStock) FROM TotalStock w WHERE w.weaponName IS NOT NULL")
    Integer findTotalStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.totalStock) FROM TotalStock w WHERE w.roundName IS NOT NULL")
    Integer findTotalStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM TotalStock w WHERE w.weaponName IS NOT NULL")
    Integer findTotalAvailableStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.availableStock) FROM TotalStock w WHERE w.roundName IS NOT NULL")
    Integer findTotalAvailableStockWhereRoundNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM TotalStock w WHERE w.weaponName IS NOT NULL")
    Integer findTotalDistributionStockWhereWeaponNameIsPresent();

    @Query("SELECT SUM(w.distributionStock) FROM TotalStock w WHERE w.roundName IS NOT NULL")
    Integer findTotalDistributionStockWhereRoundNameIsPresent();

    List<DistributionMagzine> findByButtNoInAndManufacturingNoIn(List<String> buttNo, List<String> manufacturingNo);

    List<DistributionMagzine> findByButtNoIn(List<String> buttNo);

    List<DistributionMagzine> findByManufacturingNoIn(List<String> manufacturingNo);

    List<DistributionMagzine> findByButtNoAndManufacturingNo(String buttNo, String manufacturingNo);

   // List<DistributionMagzine> findByButtNoInAndManufacturingNoIn(List<String> buttNo, List<String> manufacturingNo);

    List<DistributionMagzine> findByButtNoInAndManufacturingNoInAndWeaponNameAndPoliceStationNameOrIndividualName(
            List<String> buttNos, List<String> manufacturingNos, String weaponName, String policeStationName, String individualName);

}


