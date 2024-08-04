package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.RmButtNoAndManufacturingNoService;
import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.model.RmButtNoAndManufacturingNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rmButtNoAndManufacturingNo")
@CrossOrigin(origins = "*")
public class RmButtNoAndManufacturingNoController {

    @Autowired
    private RmButtNoAndManufacturingNoService rmButtNoAndManufacturingNoService;

    @PostMapping("/getButtNosByReturnId/{id}")
    public ResponseEntity<List<String>> getButtNosByReturnId(@PathVariable Long id) {
        List<String> buttNos = rmButtNoAndManufacturingNoService.getButtNosByReturnId(id);
        if (buttNos == null || buttNos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buttNos);
    }

    @PostMapping("/getManufacturingNosByReturnId/{id}")
    public ResponseEntity<List<String>> getManufacturingNosByReturnId(@PathVariable Long id) {
        List<String> manufacturingNos = rmButtNoAndManufacturingNoService.getManufacturingNosByReturnId(id);
        if (manufacturingNos == null || manufacturingNos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(manufacturingNos);
    }

    @PostMapping("/getByReturnsId/{id}")
    public ResponseEntity<List<RmButtNoAndManufacturingNo>> getByReturnId(@PathVariable Long id) {
        List<RmButtNoAndManufacturingNo> records = rmButtNoAndManufacturingNoService.getByReturnId(id);
        if (records == null || records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }
}
