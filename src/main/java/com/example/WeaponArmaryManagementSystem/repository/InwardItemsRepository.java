package com.example.WeaponArmaryManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WeaponArmaryManagementSystem.model.InwardItems;
import com.example.WeaponArmaryManagementSystem.model.InwardRegister;

public interface InwardItemsRepository extends JpaRepository<InwardItems,Integer> {

	List<InwardItems> findByInwardRegisterId(Long id);

}
	//List<InwardItems> findByDate(LocalDate date);
	 //  @Query("SELECT new com.example.WeaponArmaryManagementSystem.model.InwardItems(i.id, i.buttNo, i.manufacturingNo, i.count, i.createdAt) FROM InwardItems i")
	  //  List<InwardItems> findAllWithoutDate();
	//List<InwardItems> findByInwardRegister_InwardNumber(String inwardNumber);

	//List<InwardItems> findByDate(LocalDate date);



	//List<InwardItems> findByInwardRegister(InwardRegister inwardRegister);

	//List<InwardItems> findByDate(String date);

//	List<InwardItems> findByInwardRegisterId(Long id);

//	List<InwardItems> findByDate(String date);

	//List<InwardItems> findByInwardRegister_InwardNumber(String inwardNumber);

	//List<InwardItems> findByDate(LocalDate date);

	//List<InwardItems> findByInwardRegister(InwardRegister inwardRegister);
	
	
	


