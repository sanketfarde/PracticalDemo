package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.model.RmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.repository.DmButtNoAndManufacturingNoRepository;
import com.example.WeaponArmaryManagementSystem.repository.RmButtNoAndManufacturingNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RmButtNoAndManufacturingNoService {

    @Autowired
    private RmButtNoAndManufacturingNoRepository rmButtNoAndManufacturingNoRepository;

    public List<String> getButtNosByReturnId(Long returnId) {
        return rmButtNoAndManufacturingNoRepository.findByReturnId(returnId)
                .stream()
                .map(RmButtNoAndManufacturingNo::getButtNo)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturingNosByReturnId(Long returnId) {
        return rmButtNoAndManufacturingNoRepository.findByReturnId(returnId)
                .stream()
                .map(RmButtNoAndManufacturingNo::getManufacturingNo)
                .collect(Collectors.toList());
    }

    public List<RmButtNoAndManufacturingNo> getByReturnId(Long returnId) {
        return rmButtNoAndManufacturingNoRepository.findByReturnId(returnId);
    }
}
