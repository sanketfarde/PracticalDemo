package com.example.WeaponArmaryManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.WeaponArmaryManagementSystem.model.TotalStock;
import com.example.WeaponArmaryManagementSystem.repository.TotalStockRepository;

@RestController
@RequestMapping("/totalStock")
@CrossOrigin(origins = "*")
public class TotalStockController {

	@Autowired
	private TotalStockRepository totalStockRepository;
	
	
	@PostMapping("/list")
    public List<TotalStock> getAllTotalStock() {
	   return totalStockRepository.findAll();
	  }
}
