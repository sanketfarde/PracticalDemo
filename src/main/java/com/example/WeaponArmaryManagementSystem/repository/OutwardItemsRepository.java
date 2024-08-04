package com.example.WeaponArmaryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeaponArmaryManagementSystem.model.InwardItems;
import com.example.WeaponArmaryManagementSystem.model.InwardRegister;
import com.example.WeaponArmaryManagementSystem.model.OutwardItems;

public interface OutwardItemsRepository extends JpaRepository<OutwardItems,Integer>{

//	List<OutwardItems> findByOutwardRegisterId(Long id);
		
	  List<OutwardItems> findByOutwardRegisterId(Long outwardRegisterId);

}
