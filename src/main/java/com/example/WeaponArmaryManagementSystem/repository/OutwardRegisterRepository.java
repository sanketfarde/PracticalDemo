package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WeaponArmaryManagementSystem.model.InwardRegister;
import com.example.WeaponArmaryManagementSystem.model.OutwardRegister;

public interface OutwardRegisterRepository extends JpaRepository<OutwardRegister,Integer>{

	//List<OutwardRegister> findByOutwardNumber(String outwardNumber);

	List<OutwardRegister> findByDate(String string);

	List<OutwardRegister> findByDateBetween(String string, String string2);

	OutwardRegister findTopByOrderByIdDesc();
	
	List<OutwardRegister> findByOutwardNumber(String outwardNumber);
	
	   @Query("SELECT or FROM OutwardRegister or ORDER BY or.id DESC")
	    List<OutwardRegister> findAllOrderByIdDesc();

	OutwardRegister findTopByDepartmentIdOrderByIdDesc(String departmentId);

	List<OutwardRegister> findByDateBetweenAndDepartmentId(String startDate, String endDate, String departmentId);

	List<OutwardRegister> findByDepartmentIdOrderByIdDesc(String departmentId);

}
