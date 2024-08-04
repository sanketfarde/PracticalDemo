package com.example.WeaponArmaryManagementSystem.Service.Impl;


import com.example.WeaponArmaryManagementSystem.Service.WareHouseUserService;
import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseUserRepository;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class WareHouseUserServiceImpl implements WareHouseUserService {

    @Autowired
    private WareHouseUserRepository wareHouseUserRepository;

    
    @Value("${otp.length}")
    private int otpLength;
   
    public WareHouseUser saveWareHouseUser(WareHouseUser wareHouseUser) {
        // Perform any validation or business logic checks here
        String encryptedPassword = encryptPassword(wareHouseUser.getPassword());
        wareHouseUser.setPassword(encryptedPassword);
        wareHouseUser.setStatus(true);
        wareHouseUser.setCreatedAt(LocalDateTime.now());
        wareHouseUser.setUpdatedAt(LocalDateTime.now());
        // Save the wareHouseUser entity
        return wareHouseUserRepository.save(wareHouseUser);
    }


    @Override
    public WareHouseUser findByEmail(String email) {
        return wareHouseUserRepository.findByEmail(email);
    }

    @Override
    public WareHouseUser findByPhone(String phone) {
        return wareHouseUserRepository.findByPhone(phone);
    }

    @Override
    public WareHouseUser findByEmployeeNo(String employeeNo) {
        return wareHouseUserRepository.findByEmployeeNo(employeeNo);
    }
    
    
    @Override
    public List<WareHouseUser> getAllWareHouseUsers() {
        List<WareHouseUser> allSummaries = wareHouseUserRepository.findAll();
        return allSummaries.stream()
                .sorted(Comparator.comparing(WareHouseUser::getId).reversed())
                .collect(Collectors.toList());
    }
    
    
    
/*
    @Override
    public List<WareHouseUser> getAllWareHouseUsers() {
        return wareHouseUserRepository.findAll();
    }*/

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }



	@Override
	public void verify(String email, String otp) {
		// TODO Auto-generated method stub
		
	}


	@Override
    public boolean existsByEmail(String email) {
        return wareHouseUserRepository.existsByEmail(email);
    }

    @Override
    public void saveOTP(String email, String otp) {
        WareHouseUser user = wareHouseUserRepository.findByEmail(email);
        if (user != null) {
            user.setOtp(otp);
            wareHouseUserRepository.save(user);
        }
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        WareHouseUser user = wareHouseUserRepository.findByEmailAndOtp(email, otp);
        return user != null;
    }

    @Override
    public void updateOTPVerifiedAt(String email) {
        WareHouseUser user = wareHouseUserRepository.findByEmail(email);
        if (user != null) {
            user.setOtpVerifiedAt(LocalDateTime.now());
            wareHouseUserRepository.save(user);
        }
    }

    @Override
    public void changePassword(String email, String newPassword) {
        WareHouseUser user = wareHouseUserRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword);
            wareHouseUserRepository.save(user);
        }
    }

    @Override
    public String generateOTP() {
        // Generate OTP logic here
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }


	@Override
	public Optional<WareHouseUser> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


	@Override
	public boolean checkPassword(String rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	
}
	
	



/*
import com.example.WeaponArmaryManagementSystem.Service.WareHouseUserService;
import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WareHouseUserServiceImpl implements WareHouseUserService {

    @Autowired
    private WareHouseUserRepository wareHouseUserRepository;

    public WareHouseUser saveWareHouseUser(WareHouseUser wareHouseUser) {
        // Perform any validation or business logic checks here
        String encryptedPassword = encryptPassword(wareHouseUser.getPassword());
        wareHouseUser.setPassword(encryptedPassword);
        wareHouseUser.setCreatedAt(LocalDateTime.now());
        wareHouseUser.setUpdatedAt(LocalDateTime.now());
        // Save the wareHouseUser entity
        return wareHouseUserRepository.save(wareHouseUser);
    }



    @Override
    public WareHouseUser findByEmail(String email) {
        return wareHouseUserRepository.findByEmail(email);
    }

    
    @Override
    public WareHouseUser findByPhone(String phone) {
        return wareHouseUserRepository.findByPhone(phone);
    }

    
    @Override
    public WareHouseUser findByEmployeeNo(String employeeNo) {
        return wareHouseUserRepository.findByEmployeeNo(employeeNo);
    }

    
    @Override
    public List<WareHouseUser> getAllWareHouseUsers() {
        return wareHouseUserRepository.findAll();
    }
    

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}*/
