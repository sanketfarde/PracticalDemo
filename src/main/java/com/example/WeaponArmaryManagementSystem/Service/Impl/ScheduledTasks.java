package com.example.WeaponArmaryManagementSystem.Service.Impl;

import com.example.WeaponArmaryManagementSystem.model.WeaponForm;
import com.example.WeaponArmaryManagementSystem.repository.WeaponFormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTasks {

    @Autowired
    private WeaponFormRepo weaponFormRepository;

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means "at midnight every day"
    public void resetWeaponStatusForNewDay() {
        List<WeaponForm> weaponForms = weaponFormRepository.findAll();
        for (WeaponForm weaponForm : weaponForms) {
            weaponForm.setStatus("0");
            weaponFormRepository.save(weaponForm);
        }
    }
}
