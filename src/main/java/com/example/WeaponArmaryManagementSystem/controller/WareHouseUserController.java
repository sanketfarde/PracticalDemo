package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Dto.WareHouseUserDto;
import com.example.WeaponArmaryManagementSystem.Service.WareHouseUserService;
import com.example.WeaponArmaryManagementSystem.model.Designation;
import com.example.WeaponArmaryManagementSystem.model.WareHouseUser;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseUserRepository;
import com.example.WeaponArmaryManagementSystem.repository.WarehouseInOutwardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/warehouseuser")
@CrossOrigin(origins = "*")
public class WareHouseUserController {
	

    @Autowired
    private WareHouseUserRepository wareHouseUserRepository;

    @Autowired
    private WareHouseUserService wareHouseUserService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @PostMapping("/update_user_password")
    public ResponseEntity<?> changePassword(@RequestParam("phone") String phone, 
                                             @RequestParam("oldPassword") String oldPassword, 
                                             @RequestParam("newPassword") String newPassword) {
        try {
            WareHouseUser optionalUser = wareHouseUserRepository.findByPhone(phone);
            if (optionalUser != null) {
                WareHouseUser user = optionalUser;
         
                if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    wareHouseUserRepository.save(user);
                    return ResponseEntity.ok("{\"message\": \"Password reset successful\"}");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Incorrect old password\"}");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"User not found\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to reset password\"}");
        }
    }
    
    
    
    @PostMapping("/save")
    public ResponseEntity<String> saveWareHouseUser(@RequestBody WareHouseUser wareHouseUser) {
        try {
            // Check if email is already registered
            WareHouseUser existingUserByEmail = wareHouseUserService.findByEmail(wareHouseUser.getEmail());
            if (existingUserByEmail != null) {
                return ResponseEntity.badRequest().body("{\"message\": \"Email Is Already Registered...\",\"Id\":4}");
            }

            // Check if phone number is already registered
            WareHouseUser existingUserByPhone = wareHouseUserService.findByPhone(wareHouseUser.getPhone());
            if (existingUserByPhone != null) {
                return ResponseEntity.badRequest().body("{\"message\": \"Phone No is Already Registered...\",\"Id\":3}");
            }

            // Check if employee number is already registered
            WareHouseUser existingUserByEmployeeNo = wareHouseUserService.findByEmployeeNo(wareHouseUser.getEmployeeNo());
            if (existingUserByEmployeeNo != null) {
                return ResponseEntity.badRequest().body("{\"message\": \"Employee no is Already Registered...\",\"Id\":2}");
            }

            // Save the WareHouseUser after passing all checks
            wareHouseUserService.saveWareHouseUser(wareHouseUser);
            return ResponseEntity.ok("{\"message\": \"User Saved Successfully...\",\"Id\":0}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Failed To Save ...\",\"Id\":1}");
        }
    }
    
    
    @PostMapping("/updateuserById/{id}")
    public ResponseEntity<String> updateWareHouseUserById(@PathVariable Long id, @RequestBody WareHouseUser updatedUser) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                wareHouseUser.setName(updatedUser.getName());
                wareHouseUser.setDesignation(updatedUser.getDesignation());
                wareHouseUser.setEmployeeNo(updatedUser.getEmployeeNo());
                wareHouseUser.setDateOfJoining(updatedUser.getDateOfJoining());
                wareHouseUser.setStatus(updatedUser.isStatus());
                wareHouseUser.setEmail(updatedUser.getEmail());
                wareHouseUser.setPhone(updatedUser.getPhone());
                wareHouseUser.setPassword(updatedUser.getPassword());
                wareHouseUser.setDepartmentName(updatedUser.getDepartmentName());
                wareHouseUser.setDepartmentId(updatedUser.getDepartmentId());
                wareHouseUser.setOtp(updatedUser.getOtp());
                wareHouseUser.setOtpVerifiedAt(updatedUser.getOtpVerifiedAt());
                wareHouseUser.setAddress(updatedUser.getAddress());
                wareHouseUser.setPostingDate(updatedUser.getPostingDate());
                wareHouseUser.setBuckleNo(updatedUser.getBuckleNo());
                wareHouseUser.setEmployeeId(updatedUser.getEmployeeId());
                wareHouseUser.setEmployeeName(updatedUser.getEmployeeName());
                wareHouseUser.setEmployeeDesignation(updatedUser.getEmployeeDesignation());
                wareHouseUser.setEmployeePostingDate(updatedUser.getEmployeePostingDate());

                wareHouseUser.setUpdatedAt(LocalDateTime.now()); // Update the 'updatedAt' field

                wareHouseUserRepository.save(wareHouseUser);
                return ResponseEntity.ok("User updated successfully with id: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user with id: " + id);
        }
    }



    @PostMapping("/list")
    public ResponseEntity<List<WareHouseUserDto>> listAllWareHouseUsers() {
        List<WareHouseUser> allUsers = wareHouseUserService.getAllWareHouseUsers();

        // Filter the list to include only users with active status
        List<WareHouseUser> activeUsers = allUsers.stream()
                .filter(WareHouseUser::isStatus) // Assuming isStatus returns true for active users
                .collect(Collectors.toList());

        if (activeUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Convert entity list to DTO list
        List<WareHouseUserDto> userDtoList = activeUsers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtoList);
    }

    // Method to convert Entity to DTO
    private WareHouseUserDto convertToDto(WareHouseUser user) {
        WareHouseUserDto dto = new WareHouseUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDesignation(user.getDesignation());
        dto.setEmployeeNo(user.getEmployeeNo());
        dto.setDateOfJoining(user.getDateOfJoining());
        dto.setStatus(user.isStatus());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setBuckleNo(user.getBuckleNo());
        dto.setDepartmentName(user.getDepartmentName());
        dto.setPostingDate(user.getPostingDate());

        return dto;
    }

    
   /* @PostMapping("/list")
    public ResponseEntity<List<WareHouseUserDto>> listAllWareHouseUsers() {
        List<WareHouseUser> allUsers = wareHouseUserService.getAllWareHouseUsers();
        if (allUsers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Convert entity list to DTO list
        List<WareHouseUserDto> userDtoList = allUsers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDtoList);
    }

    // Method to convert Entity to DTO
    private WareHouseUserDto convertToDto(WareHouseUser user) {
        WareHouseUserDto dto = new WareHouseUserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setDesignation(user.getDesignation());
        dto.setEmployeeNo(user.getEmployeeNo());
        dto.setDateOfJoining(user.getDateOfJoining());
        dto.setStatus(user.isStatus());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setBuckleNo(user.getBuckleNo());
        dto.setDepartmentName(user.getDepartmentName());
        dto.setPostingDate(user.getPostingDate());
        
        return dto;
    }
    
  */
    
    
    
    @PostMapping("/getById/{id}")
    public ResponseEntity<WareHouseUser> getWareHouseUserById(@PathVariable Long id) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                return ResponseEntity.ok(wareHouseUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } 
    }

    @PostMapping("/getByDepartmentId/{departmentId}")
    public ResponseEntity<List<WareHouseUserDto>> getWareHouseUsersByDepartmentId(@PathVariable Long departmentId) {
        try {
            // Use the repository method that returns sorted data
            List<WareHouseUser> usersByDepartment = wareHouseUserRepository.findByDepartmentIdOrderByNameAsc(departmentId);

            if (usersByDepartment.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            // Convert entity list to DTO list
            List<WareHouseUserDto> userDtoList = usersByDepartment.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(userDtoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
    /*
    // by chandrika on 26/06/2024
    @PostMapping("/getuserpasswordbyid/{id}")
    public ResponseEntity<String> getPasswordById(@PathVariable Long id) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                String encodedPassword = wareHouseUser.getPassword();
                String decodedPassword = decode(encodedPassword);
                return ResponseEntity.ok(decodedPassword);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String decode(String encodedPassword) {
        try {
            String encryptionKey = "yourEncryptionKey"; // Replace with your actual key
            String encryptionIV = "yourEncryptionIV"; // Replace with your actual IV

            IvParameterSpec iv = new IvParameterSpec(encryptionIV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encodedPassword));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }*/
    
    /*
    // by chandrika on 26/06/2024
    @PostMapping("/getuserpasswordbyid/{id}")
    public ResponseEntity<String> getPasswordById(@PathVariable Long id, @RequestBody String rawPassword) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                String encodedPassword = wareHouseUser.getPassword();
                boolean passwordMatches = wareHouseUserService.checkPassword(rawPassword, encodedPassword);
                if (passwordMatches) {
                    return ResponseEntity.ok("Password is correct");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password is incorrect");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/
    
    /*
    //by chandrika on 26/06/2024
    @PostMapping("/getuserpasswordbyid/{id}")
    public ResponseEntity<String> getPasswordById(@PathVariable Long id) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                String encodedPassword = wareHouseUser.getPassword(); 
                String decodedPassword = decode(encodedPassword);
                return ResponseEntity.ok(decodedPassword);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String decode(String encodedPassword) {
        return encodedPassword;
    }
    */
    

  
/* @PostMapping("/update_password")
public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody WareHouseUser updatedPassword) {
     Optional<WareHouseUser> existingDesignationOptional = WareHouseUserRepository.findById(id);
 */
    
    
    /*
    @PostMapping("/getuserpassword/{id}")
    public ResponseEntity<String> getPassword(@PathVariable Long id) {
        try {
            Optional<WareHouseUser> userOptional = wareHouseUserService.findById(id);
            if (userOptional.isPresent()) {
                WareHouseUser user = userOptional.get();
                return ResponseEntity.ok("{\"password\": \"" + user.getPassword() + "\", \"Id\":0}");
            } else {
                return ResponseEntity.badRequest().body("{\"message\": \"User Not Found...\",\"Id\":2}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Failed To Get Password...\",\"Id\":1}");
        }
    }
    */
    

    
    
   /*
    @PostMapping("/updateuserById/{id}")
    public ResponseEntity<WareHouseUser> updateWareHouseUserById(@PathVariable Long id, @RequestBody WareHouseUser updatedUser) {
        try {
            Optional<WareHouseUser> wareHouseUserOptional = wareHouseUserRepository.findById(id);
            if (wareHouseUserOptional.isPresent()) {
                WareHouseUser wareHouseUser = wareHouseUserOptional.get();
                wareHouseUser.setName(updatedUser.getName());
                wareHouseUser.setDesignation(updatedUser.getDesignation());
                wareHouseUser.setEmployeeNo(updatedUser.getEmployeeNo());
                wareHouseUser.setDateOfJoining(updatedUser.getDateOfJoining());
                wareHouseUser.setStatus(updatedUser.isStatus());
                wareHouseUser.setEmail(updatedUser.getEmail());
                wareHouseUser.setPhone(updatedUser.getPhone());
                wareHouseUser.setPassword(updatedUser.getPassword());
                wareHouseUser.setDepartmentName(updatedUser.getDepartmentName());
                wareHouseUser.setDepartmentId(updatedUser.getDepartmentId());
                wareHouseUser.setOtp(updatedUser.getOtp());
                wareHouseUser.setOtpVerifiedAt(updatedUser.getOtpVerifiedAt());
                wareHouseUser.setAddress(updatedUser.getAddress());
                wareHouseUser.setPostingDate(updatedUser.getPostingDate());
                wareHouseUser.setBuckleNo(updatedUser.getBuckleNo());
                wareHouseUser.setEmployeeId(updatedUser.getEmployeeId());
                wareHouseUser.setEmployeeName(updatedUser.getEmployeeName());
                wareHouseUser.setEmployeeDesignation(updatedUser.getEmployeeDesignation());
                wareHouseUser.setEmployeePostingDate(updatedUser.getEmployeePostingDate());
                
                wareHouseUser.setUpdatedAt(LocalDateTime.now()); // Update the 'updatedAt' field
                
                WareHouseUser savedUser = wareHouseUserRepository.save(wareHouseUser);
                return ResponseEntity.body("updated");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/

