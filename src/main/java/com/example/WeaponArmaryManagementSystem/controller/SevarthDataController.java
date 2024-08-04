package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.SevarthDataService;
import com.example.WeaponArmaryManagementSystem.model.SevarthData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sevarth")
public class SevarthDataController {

    @Autowired
    private SevarthDataService sevarthDataService;

    @PostMapping("/save")
    public ResponseEntity<SevarthData> saveSevarthData(@RequestBody SevarthData sevarthData) {
        SevarthData savedData = sevarthDataService.saveSevarthData(sevarthData);
        return ResponseEntity.ok(savedData);
    }

    @PostMapping("/{sevarthId}")
    public ResponseEntity<SevarthData> getSevarthDataById(@PathVariable String sevarthId) {
       SevarthData sevarthData = sevarthDataService.getSevarthDataById(sevarthId);
        if (sevarthData != null) {
            return ResponseEntity.ok(sevarthData);
      } else {
            return ResponseEntity.notFound().build();
       }
   }



    @PostMapping("/all")
    public ResponseEntity<List<SevarthData>> getAllSevarthData() {
        List<SevarthData> sevarthDataList = sevarthDataService.getAllSevarthData();
        return ResponseEntity.ok(sevarthDataList);
    }
}
