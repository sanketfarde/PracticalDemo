package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.WeaponsDailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeaponRepository extends JpaRepository<WeaponsDailyReport,Integer> {
	
	@Query("SELECT DISTINCT w.weaponType FROM WeaponsDailyReport w WHERE w.date = :date")
    List<String> findDistinctCategoriesByDate(@Param("date") String date);
	
	   WeaponsDailyReport findByDateAndWeaponType(String date, String weaponType);

	    List<WeaponsDailyReport> findByDate(String date);

	    List<WeaponsDailyReport> findByDateBetween(String startDate, String endDate);

	}
	
    //  WeaponsDailyReport findByDate(String date);

    // WeaponsDailyReport findByWeaponType(String weaponType);
/*

    List<WeaponsDailyReport> findByDate(String date);

    WeaponsDailyReport findByDateAndWeaponType(String date, String weaponType);


    @Query("SELECT DISTINCT w.weaponType FROM WeaponsDailyReport w WHERE w.date = :date")
    List<String> findDistinctCategoriesByDate(@Param("date") String date);

    @Query("SELECT w.weaponType FROM WeaponsDailyReport w WHERE w.date = :date")
    List<String> findAllCategoriesByDate(@Param("date") String date);

    @Query("SELECT w.weaponType FROM WeaponsDailyReport w WHERE w.date = :date AND w.weaponType = :category")
    List<String> findValuesByDateAndCategory(@Param("date") String date, @Param("category") String category);
*/

    

/*    @Query("SELECT w FROM WeaponsDailyReport w WHERE w.date = :date AND w.weaponType = :category")
    List<WeaponsDailyReport> findByDateAndWeaponType(@Param("date") String date, @Param("category") String category);*/
//WeaponsDailyReport findByDateAndWeaponType(String date, String weaponType);




    // List<WeaponsDailyReport> findAllByDateAndWeaponType(String date, String weaponType);


 