package com.example.WeaponArmaryManagementSystem.controller;


import com.example.WeaponArmaryManagementSystem.Dto.AddTeargasWeaponsDto;
import com.example.WeaponArmaryManagementSystem.Service.AddTeargasWeaponsService;
import com.example.WeaponArmaryManagementSystem.model.AddTeargasWeapons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/teargasweapons")
public class AddTeargasWeaponsController {



    @Autowired
    private AddTeargasWeaponsService addTeargasWeaponsService;


    @PostMapping("/save")
    public ResponseEntity<String> saveBellArmory(@RequestBody AddTeargasWeaponsDto addTeargasWeaponsDto) {
        try {
            addTeargasWeaponsService.saveTeargasWeapons(addTeargasWeaponsDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Data saved successfully...Id=0\"}");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Data saved successfully...Id=0\"}" + ex.getMessage());
        }
    }


    @PostMapping("/getall")
    public ResponseEntity<List<AddTeargasWeapons>> getAllTeargasWeapons() {
        List<AddTeargasWeapons> teargasWeapons = addTeargasWeaponsService.getAllTeargasWeapons();
        return ResponseEntity.ok(teargasWeapons);
    }


    @PostMapping("/{id}")
    public ResponseEntity<AddTeargasWeapons> getTeargasWeaponById(@PathVariable("id") Long id) {
        try {
            Optional<AddTeargasWeapons> teargasWeaponOptional = addTeargasWeaponsService.getTeargasWeaponById(id);
            if (teargasWeaponOptional.isPresent()) {
                AddTeargasWeapons teargasWeapon = teargasWeaponOptional.get();
                return ResponseEntity.ok(teargasWeapon);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
