package com.example.WeaponArmaryManagementSystem.repository;


import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WareHouseUserRepository extends JpaRepository<WareHouseUser,Long> {
	
    WareHouseUser findByPhone(String phone);

    WareHouseUser findByEmployeeNo(String employeeNo);

    WareHouseUser findByEmail(String email);

    Optional<WareHouseUser> findByEmailOrPhone(String username, String username1);

	WareHouseUser findByEmailAndOtp(String email, String otp);

	boolean existsByEmail(String email);

    List<WareHouseUser> findByDepartmentIdOrderByNameAsc(Long departmentId);

}
