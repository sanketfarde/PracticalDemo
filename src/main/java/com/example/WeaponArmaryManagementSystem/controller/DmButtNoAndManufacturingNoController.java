package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Service.DmButtNoAndManufacturingNoService;
import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;
import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.repository.DmButtNoAndManufacturingNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dmButtNoAndManufacturingNo")
@CrossOrigin(origins = "*")
public class DmButtNoAndManufacturingNoController {

    @Autowired
    private DmButtNoAndManufacturingNoRepository dmButtNoAndManufacturingNoRepository;

    @Autowired
    private DmButtNoAndManufacturingNoService dmButtNoAndManufacturingNoService;

    @PostMapping("/getByDistributionId/{id}")
    public ResponseEntity<DmButtNoAndManufacturingNo> getDmButtNoAndManufacturingNoById(@PathVariable Long id) {
        Optional<DmButtNoAndManufacturingNo> dmButtNoAndManufacturingNo = dmButtNoAndManufacturingNoRepository.findById(id);
        return dmButtNoAndManufacturingNo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping("/getButtNosByDistributionId/{id}")
    public ResponseEntity<List<String>> getButtNosByDistributionId(@PathVariable Long id) {
        List<String> buttNos = dmButtNoAndManufacturingNoService.getButtNosByDistributionId(id);
        if (buttNos == null || buttNos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buttNos);
    }

    @PostMapping("/getManufacturingNosByDistributionId/{id}")
    public ResponseEntity<List<String>> getManufacturingNosByDistributionId(@PathVariable Long id) {
        List<String> manufacturingNos = dmButtNoAndManufacturingNoService.getManufacturingNosByDistributionId(id);
        if (manufacturingNos == null || manufacturingNos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(manufacturingNos);
    }

    @PostMapping("/getByDistributionsId/{id}")
    public ResponseEntity<List<DmButtNoAndManufacturingNo>> getByDistributionId(@PathVariable Long id) {
        List<DmButtNoAndManufacturingNo> records = dmButtNoAndManufacturingNoService.getByDistributionId(id);
        if (records == null || records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }
}
