package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.DmButtNoAndManufacturingNo;
import com.example.WeaponArmaryManagementSystem.repository.DmButtNoAndManufacturingNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DmButtNoAndManufacturingNoService {

    @Autowired
    private DmButtNoAndManufacturingNoRepository dmButtNoAndManufacturingNoRepository;

    public List<String> getButtNosByDistributionId(Long distributionId) {
        return dmButtNoAndManufacturingNoRepository.findByDistributionId(distributionId)
                .stream()
                .map(DmButtNoAndManufacturingNo::getButtNo)
                .collect(Collectors.toList());
    }

    public List<String> getManufacturingNosByDistributionId(Long distributionId) {
        return dmButtNoAndManufacturingNoRepository.findByDistributionId(distributionId)
                .stream()
                .map(DmButtNoAndManufacturingNo::getManufacturingNo)
                .collect(Collectors.toList());
    }

    public List<DmButtNoAndManufacturingNo> getByDistributionId(Long distributionId) {
        return dmButtNoAndManufacturingNoRepository.findByDistributionId(distributionId);
    }
}
