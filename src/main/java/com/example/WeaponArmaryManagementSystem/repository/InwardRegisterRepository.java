package com.example.WeaponArmaryManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.WeaponArmaryManagementSystem.model.InwardRegister;

@Repository
public interface InwardRegisterRepository extends JpaRepository<InwardRegister,Integer>{

	List<InwardRegister> findByInwardNumber(String inwardNumber);
	
	List<InwardRegister> findByDateBetweenAndDepartmentId(String startDate, String endDate, String departmentId);

	List<InwardRegister> findByDate(String date);

	//List<InwardRegister> findByDateBetween(String string, String string2);

	InwardRegister findTopByOrderByIdDesc();
	
	  @Query("SELECT ir FROM InwardRegister ir ORDER BY ir.id DESC")
	    List<InwardRegister> findAllOrderByIdDesc();

	InwardRegister findTopByDepartmentIdOrderByIdDesc(String departmentId);
	 List<InwardRegister> findByDepartmentIdOrderByIdDesc(String departmentId);
	
}


	  //@Query("SELECT ir FROM InwardRegister ir ORDER BY ir.date DESC")
	   // List<InwardRegister> findAllOrderByDateDesc();

	//List<InwardRegister> findByDate(String date);

	//List<InwardRegister> findByInwardNumber(String inwardNumber);

	//List<InwardRegister> findByDate(String date);
	// @Query("SELECT ir FROM InwardRegister ir JOIN ir.inwardItemsList ii WHERE ir.date = :date")
	  //  List<InwardRegister> findByDate(@Param("date") String date);

//	List<InwardRegister> findByDate(LocalDate date);
	


