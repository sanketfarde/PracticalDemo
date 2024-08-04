package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.OrdinanceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinanceOfficerRepository extends JpaRepository<OrdinanceOfficer,Long> {

    List<OrdinanceOfficer> findByReceivedDateIsNull();

    List<OrdinanceOfficer> findAllByReceivedDateIsNotNull();
    
    List<OrdinanceOfficer> findByReceivedDateIsNotNull();

    @Query("SELECT o FROM OrdinanceOfficer o WHERE o.receivedDate BETWEEN :startDate AND :endDate")
    List<OrdinanceOfficer> findByReceivedDateBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
