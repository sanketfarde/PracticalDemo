package com.example.WeaponArmaryManagementSystem.Service;

import com.example.WeaponArmaryManagementSystem.model.DistributionMagzine;
import com.example.WeaponArmaryManagementSystem.model.ReturnMagzine;
import com.example.WeaponArmaryManagementSystem.repository.DistributionMagzineRepository;
import com.example.WeaponArmaryManagementSystem.repository.DmButtNoAndManufacturingNoRepository;
import com.example.WeaponArmaryManagementSystem.repository.ReturnMagzineRepository;
import com.example.WeaponArmaryManagementSystem.repository.RmButtNoAndManufacturingNoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnMagzineService {

    @Autowired
    private ReturnMagzineRepository returnMagzineRepository;

    @Autowired
    private RmButtNoAndManufacturingNoRepository rmButtNoAndManufacturingNoRepository;

  /*  public List<ReturnMagzine> getDistributionMagzinesByType(String distributionType) {
        return returnMagzineRepository.findByDistributionType(distributionType);
    }*/

    public int getButtNoCount(Long returnId) {
        return rmButtNoAndManufacturingNoRepository.countByReturnIdAndButtNoIsNotNull(returnId);
    }

    public int getManufacturingNoCount(Long returnId) {
        return rmButtNoAndManufacturingNoRepository.countByReturnIdAndManufacturingNoIsNotNull(returnId);
    }

    public List<ReturnMagzine> getAllReturnMagzines() {
        return returnMagzineRepository.findAll(); // Assuming returnMagzineRepository is your JpaRepository
    }


}
