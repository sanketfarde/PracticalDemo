package com.example.WeaponArmaryManagementSystem.repository;

import com.example.WeaponArmaryManagementSystem.model.OrdinanceOfficerReturndataTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdinanceOfficerReturnDataTotalRepository extends JpaRepository<OrdinanceOfficerReturndataTotal,Long> {

}
