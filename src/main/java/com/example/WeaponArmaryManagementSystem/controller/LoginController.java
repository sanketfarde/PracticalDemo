package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Dto.LoginResponseDTO;
import com.example.WeaponArmaryManagementSystem.Service.EmailService;
import com.example.WeaponArmaryManagementSystem.Service.WareHouseUserService;
import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
		

    @Autowired
    private WareHouseUserService wareHouseUserService;
    

    @Autowired
    private WareHouseUserRepository wareHouseUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;
   

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            Optional<WareHouseUser> optionalUser = wareHouseUserRepository.findByEmailOrPhone(username, username);
            if (optionalUser.isPresent()) {
                WareHouseUser user = optionalUser.get();
                if (user.getCreatedAt() != null) {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        // Login successful
                        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
                        loginResponseDTO.setName(user.getName());
                        loginResponseDTO.setDesignation(user.getDesignation());
                        loginResponseDTO.setEmployeeNo(user.getEmployeeNo());
                        loginResponseDTO.setDateOfJoining(user.getDateOfJoining());
                       // loginResponseDTO.setStatus(user.isStatus());
                        loginResponseDTO.setEmail(user.getEmail());
                        loginResponseDTO.setPhone(user.getPhone());
                        loginResponseDTO.setDepartmentName(user.getDepartmentName());
                        loginResponseDTO.setDepartmentId(user.getDepartmentId());
                        // Set other necessary fields in loginResponseDTO if needed

                        // Return login response with HTTP status 200 OK
                        return ResponseEntity.ok("{\"message\": \"Login successful\",\"Id\": 0, \"data\": " + new ObjectMapper().writeValueAsString(loginResponseDTO) + "}");
                      //  return ResponseEntity.ok().body("{\"message\": \"Login Successfull...\",\"Id\": 0 " + new ObjectMapper().writeValueAsString(loginResponseDTO) + "}");
                    } else {
                        // Incorrect password
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Incorrect password\",\"Id\": 3}");
                    }
                } else {
                    // User not verified (assuming createdAt indicates verification)
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"User not verified\",\"Id\": 2}");
                }
            } else {
                // User not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\",\"Id\": 1}");
            }
        } catch (Exception e) {
            // Internal server error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to login\",\"Id\": 4}");
        }
    }
    
    @PostMapping("/verify-email")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        if (!wareHouseUserService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Email Not Found\",\"Id\": 1}");
        }

        String otp = wareHouseUserService.generateOTP();
        wareHouseUserService.saveOTP(email, otp);
        emailService.sendEmail(email, "Password Reset OTP", "Verification OTP is: " + otp);

        return ResponseEntity.ok("{\"message\": \"Otp sent Successfully\",\"Id\": 0}");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam("email") String email, @RequestParam("otp") String otp) {
        if (!wareHouseUserService.verifyOTP(email, otp)) {
            return ResponseEntity.badRequest().body("{\"message\": \"Invalid Otp\",\"Id\": 1}");
        }

        wareHouseUserService.updateOTPVerifiedAt(email);
        return ResponseEntity.ok("{\"message\": \"OTP Verified successfully\",\"Id\": 0}");
    }

   /* @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        wareHouseUserService.changePassword(email, password);
        return ResponseEntity.ok("{\"message\": \"Password Change Successfull...\",\"Id\": 0}");
    }
*/

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestParam Map<String, String> passwordData) {
        String email = passwordData.get("email");
        String newPassword = passwordData.get("newPassword");

        WareHouseUser user = wareHouseUserService.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>("{\"message\": \"Email Not Found\",\"Id\": 2}", HttpStatus.NOT_FOUND);
        }

        if (user.getOtpVerifiedAt() == null) {
            return new ResponseEntity<>("{\"message\": \"Otp Not Verified Verify Otp First..\",\"Id\": 1}", HttpStatus.BAD_REQUEST);
        }

        String encryptedPassword = encryptPassword(newPassword);
        user.setPassword(encryptedPassword);
        wareHouseUserRepository.save(user);

        resetOTPVerifiedAt(email);

        return new ResponseEntity<>("{\"message\": \"Changed Password successfully\",\"Id\": 0}", HttpStatus.OK);
    }

    // Method to encrypt the password using BCryptPasswordEncoder
    private String encryptPassword(String newPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(newPassword);
    }

    public void resetOTPVerifiedAt(String email) {
        WareHouseUser user = wareHouseUserService.findByEmail(email);
        if (user != null) {
            user.setOtpVerifiedAt(null);
            wareHouseUserRepository.save(user);
        }
    }

}
