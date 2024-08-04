package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.WareHouseWeaponsAddService;
import com.example.WeaponArmaryManagementSystem.model.WareHouseWeaponsAdd;
import com.example.WeaponArmaryManagementSystem.repository.WareHouseWeaponsAddRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouseweapons")
@CrossOrigin(origins = "*")
public class WareHouseWeaponsAddController {



    @Autowired
    private WareHouseWeaponsAddService wareHouseWeaponsAddService;
    
    
    @Autowired
    private WareHouseWeaponsAddRepository wareHouseWeaponsAddRepository;
    
    
    
    // 01/06/2024 by vikas as per manish
    @PostMapping("/round-names")
    public ResponseEntity<List<String>> getRoundNamesByWeaponType() {
        List<String> weaponNames = wareHouseWeaponsAddRepository.findRoundNamesByWeaponType();
        return ResponseEntity.ok(weaponNames);
    }
    
    
    /*
    // 01/06/2024 by vikas as per manish
   @PostMapping("/round-names")
   public ResponseEntity<List<String>> getRoundNamesByWeaponType() {
       List<String> weaponNames = wareHouseWeaponsAddRepository.findRoundNamesByWeaponType();
       return ResponseEntity.ok(weaponNames);
        }
   */
    
    
    // by vikas as per manish on 01/06/2024
    @PostMapping("/weapon-names")
    public ResponseEntity<List<String>> getWeaponNamesByWeaponType() {
        List<String> weaponNames = wareHouseWeaponsAddRepository.findWeaponNamesByWeaponType();
        return ResponseEntity.ok(weaponNames);
    }

    
 

    
    @PostMapping("/save")
    public ResponseEntity<String> saveWareHouseWeaponsAdd(@RequestBody WareHouseWeaponsAdd wareHouseWeaponsAdd) {
        try {
            wareHouseWeaponsAddService.saveWareHouseWeaponsAdd(wareHouseWeaponsAdd);
            return ResponseEntity.ok("{\"message\": \"Data Saved Successfully...\",\"Id\":0}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Failed to Save Data...\",\"Id\":1}");
        }
    }



    @PostMapping("/listall")
    public ResponseEntity<List<WareHouseWeaponsAdd>> getAllWareHouseWeaponsAdd() {
        List<WareHouseWeaponsAdd> weaponsList = wareHouseWeaponsAddService.getAllWareHouseWeaponsAdd();
        return ResponseEntity.ok(weaponsList);
    }

    
    
    @PostMapping("getbyId/{id}")
    public ResponseEntity<WareHouseWeaponsAdd> getWareHouseWeaponsAddById(@PathVariable Integer id) {
        WareHouseWeaponsAdd weaponsAdd = wareHouseWeaponsAddService.getWareHouseWeaponsAddById(id);
        if (weaponsAdd != null) {
            return ResponseEntity.ok(weaponsAdd);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

    @PostMapping("/updatebyId/{id}")
    public ResponseEntity<String> updateWareHouseWeaponsAdd(@PathVariable Integer id, @RequestBody WareHouseWeaponsAdd wareHouseWeaponsAdd) {
        WareHouseWeaponsAdd existingWeaponsAdd = wareHouseWeaponsAddService.getWareHouseWeaponsAddById(id);
        if (existingWeaponsAdd != null) {
            wareHouseWeaponsAdd.setId(id);
            wareHouseWeaponsAddService.saveWareHouseWeaponsAdd(String.valueOf(wareHouseWeaponsAdd));
            return ResponseEntity.ok("{\"message\": \"Updated Successfully...\",\"Id\":0}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @PostMapping("/deletebyId/{id}")
    public ResponseEntity<String> deleteWareHouseWeaponsAdd(@PathVariable Integer id) {
        wareHouseWeaponsAddService.deleteWareHouseWeaponsAddById(id);
        return ResponseEntity.ok("{\"message\": \"Deleted Successfully...\",\"Id\":0}");
    }

}
