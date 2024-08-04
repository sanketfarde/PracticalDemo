package com.example.WeaponArmaryManagementSystem.repository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.WeaponArmaryManagementSystem.model.WarehouseInOutward;

@Repository
public interface WarehouseInOutwardRepository extends JpaRepository<WarehouseInOutward, Long> {
	
	 List<WarehouseInOutward> findByStatus(String status);
	 
	 WarehouseInOutward findTopByOrderByIdDesc();
	 
	 List<WarehouseInOutward> findByDateBetween(String startDate, String endDate);

	List<WarehouseInOutward> findAllByDate(String date);

	List<WarehouseInOutward> findByStatusAndDateBetween(String string, String startDate, String endDate);
	 
}
