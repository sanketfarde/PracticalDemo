package com.example.WeaponArmaryManagementSystem.Service;


import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;

import java.util.List;
import java.util.Optional;

public interface WareHouseUserService {
	
    WareHouseUser saveWareHouseUser(WareHouseUser wareHouseUser);

    WareHouseUser findByEmail(String email);

    WareHouseUser findByPhone(String phone);

    WareHouseUser findByEmployeeNo(String employeeNo);

    List<WareHouseUser> getAllWareHouseUsers();
    
   // WareHouseUser saveWareHouseUser(WareHouseUser wareHouseUser);

   // WareHouseUser findByEmail(String email);

  //  WareHouseUser findByPhone(String phone);

  //  WareHouseUser findByEmployeeNo(String employeeNo);

   // List<WareHouseUser> getAllWareHouseUsers();

	void verify(String email, String otp);


	boolean existsByEmail(String email);

    void saveOTP(String email, String otp);

    boolean verifyOTP(String email, String otp);

    void updateOTPVerifiedAt(String email);

    void changePassword(String email, String newPassword);

    String generateOTP();

	Optional<WareHouseUser> findById(Long id);

	boolean checkPassword(String rawPassword, String encodedPassword);
	
	

}
