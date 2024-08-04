package com.example.WeaponArmaryManagementSystem.controller;



import com.example.WeaponArmaryManagementSystem.model.Department;
import com.example.WeaponArmaryManagementSystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveDepartment(@RequestBody Department department) {
        try {
            LocalDateTime now = LocalDateTime.now();
            department.setCreatedAt(now);
            department.setUpdatedAt(now);
            Department savedDepartment = departmentRepository.save(department);
            return new ResponseEntity<>("{\"message\": \"Department saved successfully\", \"Id\": 0}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Failed to save department\", \"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getById/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    
    @PostMapping("/updatebyid/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        try {
            Optional<Department> optionalDepartment = departmentRepository.findById(id);
            if (optionalDepartment.isPresent()) {
                Department existingDepartment = optionalDepartment.get();
                existingDepartment.setEmployeeId(departmentDetails.getEmployeeId());
                existingDepartment.setEmployeeName(departmentDetails.getEmployeeName());
                existingDepartment.setEmployeeDesignation(departmentDetails.getEmployeeDesignation());
                existingDepartment.setEmployeePostingDate(departmentDetails.getEmployeePostingDate());
                existingDepartment.setDepartment(departmentDetails.getDepartment());
                existingDepartment.setStatus(departmentDetails.getStatus());
                existingDepartment.setUpdatedAt(LocalDateTime.now());
                departmentRepository.save(existingDepartment);
                return new ResponseEntity<>("{\"message\": \"Department updated successfully\", \"Id\": 0}", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("{\"message\": \"Department not found\", \"Id\": 1}", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{\"message\": \"Failed to update department\", \"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
