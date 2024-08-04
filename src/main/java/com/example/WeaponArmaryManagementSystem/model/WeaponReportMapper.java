package com.example.WeaponArmaryManagementSystem.model;

import com.example.WeaponArmaryManagementSystem.Dto.WeaponReportDto;

public class WeaponReportMapper {
    
    public static WeaponReport toEntity(WeaponReportDto dto) {
    	
        WeaponReport entity = new WeaponReport();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());
        entity.setButtNo(String.join(",", dto.getButtNo())); // Convert List to comma-separated String
        entity.setManufacturerNo(String.join(",", dto.getManufacturerNo())); // Convert List to comma-separated String
        entity.setWeaponCondition(String.join(",", dto.getWeaponCondition())); // Convert List to comma-separated String
        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
} 

/* import com.example.weapon.dto.WeaponReportDto;
import com.example.weapon.entity.WeaponReport;

public class WeaponReportMapper {

    public static WeaponReport toEntity(WeaponReportDto dto) {
        WeaponReport entity = new WeaponReport();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());
        entity.setButtNo(dto.getButtNo());
        entity.setManufacturerNo(dto.getManufacturerNo());
        entity.setWeaponCondition(dto.getWeaponCondition());
        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }
} */

/*import com.example.weapon.dto.WeaponReportDto;
import com.example.weapon.entity.WeaponReport;
import java.util.ArrayList;
import java.util.List;

public class WeaponReportMapper {

    public static WeaponReport toEntity(WeaponReportDto dto) {
        WeaponReport entity = new WeaponReport();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());
        
        // Assuming you want to handle null lists gracefully
        entity.setButtNo(dto.getButtNo() != null ? dto.getButtNo() : new ArrayList<>());
        entity.setManufacturerNo(dto.getManufacturerNo() != null ? dto.getManufacturerNo() : new ArrayList<>());
        entity.setWeaponCondition(dto.getWeaponCondition() != null ? dto.getWeaponCondition() : new ArrayList<>());

        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        
        return entity;
    }

    public static WeaponReportDto toDto(WeaponReport entity) {
        return new WeaponReportDto(
            entity.getId(),
            entity.getDate(),
            entity.getWeaponType(),
            entity.getButtNo(),
            entity.getManufacturerNo(),
            entity.getWeaponCondition(),
            entity.getDescription(),
            entity.getEmployeeId(),
            entity.getEmployeeName(),
            entity.getEmployeeDesignation(),
            entity.getEmployeePostingDate(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}*/


//package com.example.weapon.mapper;

/* import com.example.weapon.dto.WeaponReportDto;
import com.example.weapon.entity.WeaponReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeaponReportMapper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

    public static WeaponReport toEntity(WeaponReportDto dto) {
        WeaponReport entity = new WeaponReport();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());

        try {
            entity.setButtNo(objectMapper.writeValueAsString(dto.getButtNo()));
            entity.setManufacturerNo(objectMapper.writeValueAsString(dto.getManufacturerNo()));
            entity.setWeaponCondition(objectMapper.writeValueAsString(dto.getWeaponCondition()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON string", e);
        }

        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }
} */

/*import com.example.weapon.dto.WeaponReportDto;
import com.example.weapon.entity.WeaponReport;
import com.example.weapon.service.JsonListConverter;

public class WeaponReportMapper {

    private static final JsonListConverter jsonListConverter = new JsonListConverter();

    public static WeaponReport toEntity(WeaponReportDto dto) {
        WeaponReport entity = new WeaponReport();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());
        entity.setButtNo(jsonListConverter.convertToDatabaseColumn(dto.getButtNo()));
        entity.setManufacturerNo(jsonListConverter.convertToDatabaseColumn(dto.getManufacturerNo()));
        entity.setWeaponCondition(jsonListConverter.convertToDatabaseColumn(dto.getWeaponCondition()));
        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public static WeaponReportDto toDto(WeaponReport entity) {
        return new WeaponReportDto(
            entity.getId(),
            entity.getDate(),
            entity.getWeaponType(),
            jsonListConverter.convertToEntityAttribute(entity.getButtNo()),
            jsonListConverter.convertToEntityAttribute(entity.getManufacturerNo()),
            jsonListConverter.convertToEntityAttribute(entity.getWeaponCondition()),
            entity.getDescription(),
            entity.getEmployeeId(),
            entity.getEmployeeName(),
            entity.getEmployeeDesignation(),
            entity.getEmployeePostingDate(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
} */



/*import com.example.weapon.dto.WeaponReportDto;
import com.example.weapon.entity.WeaponReport;
import com.example.weapon.service.JsonListConverter;

public class WeaponReportMapper {

    private static final JsonListConverter jsonListConverter = new JsonListConverter();

    public static WeaponReport toEntity(WeaponReportDto dto) {
        WeaponReport entity = new WeaponReport();
        entity.setDate(dto.getDate());
        entity.setWeaponType(dto.getWeaponType());
        entity.setButtNo(jsonListConverter.convertToDatabaseColumn(dto.getButtNo()));
        entity.setManufacturerNo(jsonListConverter.convertToDatabaseColumn(dto.getManufacturerNo()));
        entity.setWeaponCondition(jsonListConverter.convertToDatabaseColumn(dto.getWeaponCondition()));
        entity.setDescription(dto.getDescription());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setEmployeeName(dto.getEmployeeName());
        entity.setEmployeeDesignation(dto.getEmployeeDesignation());
        entity.setEmployeePostingDate(dto.getEmployeePostingDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    public static WeaponReportDto toDto(WeaponReport entity) {
        WeaponReportDto dto = new WeaponReportDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setWeaponType(entity.getWeaponType());
        dto.setButtNo(jsonListConverter.convertToEntityAttribute(entity.getButtNo()));
        dto.setManufacturerNo(jsonListConverter.convertToEntityAttribute(entity.getManufacturerNo()));
        dto.setWeaponCondition(jsonListConverter.convertToEntityAttribute(entity.getWeaponCondition()));
        dto.setDescription(entity.getDescription());
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setEmployeeName(entity.getEmployeeName());
        dto.setEmployeeDesignation(entity.getEmployeeDesignation());
        dto.setEmployeePostingDate(entity.getEmployeePostingDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }
} */



