package com.example.WeaponArmaryManagementSystem.Dto;

import java.time.LocalDateTime;

public class StockDto {
    
    private Long id;
    private String buttNo;
    private String materialName;
    private String manufacturerName;
    private String manufacturerNo;
    private String inDate;
    private String referenceNo;
    private String issuedAuthority;
    private String voucharNo;
    private String weaponType;
    private String caratNo;
    private String boxNo;
    private String lotNo;
    private String totalQuantity;
    private String balancedQuantity;
    private String expiryDate;
    private String recievedQuantity;
    private String recievedBy;
    private String stockInEmployeeId;
    private String stockInEmployeeName;
    private String stockInEmployeeDesignation;
    private String stockInEmployeePostingDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    

	private String employeeId;
    private String employeeName;
    private String employeeDesignation;
    private String employeePostingDate;

    public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public String getEmployeePostingDate() {
		return employeePostingDate;
	}

	public void setEmployeePostingDate(String employeePostingDate) {
		this.employeePostingDate = employeePostingDate;
	}

	// Constructor
    public StockDto() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getButtNo() {
        return buttNo;
    }

    public void setButtNo(String buttNo) {
        this.buttNo = buttNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerNo() {
        return manufacturerNo;
    }

    public void setManufacturerNo(String manufacturerNo) {
        this.manufacturerNo = manufacturerNo;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getIssuedAuthority() {
        return issuedAuthority;
    }

    public void setIssuedAuthority(String issuedAuthority) {
        this.issuedAuthority = issuedAuthority;
    }

    public String getVoucharNo() {
        return voucharNo;
    }

    public void setVoucharNo(String voucharNo) {
        this.voucharNo = voucharNo;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getCaratNo() {
        return caratNo;
    }

    public void setCaratNo(String caratNo) {
        this.caratNo = caratNo;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getBalancedQuantity() {
        return balancedQuantity;
    }

    public void setBalancedQuantity(String balancedQuantity) {
        this.balancedQuantity = balancedQuantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRecievedQuantity() {
        return recievedQuantity;
    }

    public void setRecievedQuantity(String recievedQuantity) {
        this.recievedQuantity = recievedQuantity;
    }

	public String getRecievedBy() {
		return recievedBy;
	}

	public void setRecievedBy(String recievedBy) {
		this.recievedBy = recievedBy;
	}

	public String getStockInEmployeeId() {
		return stockInEmployeeId;
	}

	public void setStockInEmployeeId(String stockInEmployeeId) {
		this.stockInEmployeeId = stockInEmployeeId;
	}

	public String getStockInEmployeeName() {
		return stockInEmployeeName;
	}

	public void setStockInEmployeeName(String stockInEmployeeName) {
		this.stockInEmployeeName = stockInEmployeeName;
	}

	public String getStockInEmployeeDesignation() {
		return stockInEmployeeDesignation;
	}

	public void setStockInEmployeeDesignation(String stockInEmployeeDesignation) {
		this.stockInEmployeeDesignation = stockInEmployeeDesignation;
	}

	public String getStockInEmployeePostingDate() {
		return stockInEmployeePostingDate;
	}

	public void setStockInEmployeePostingDate(String stockInEmployeePostingDate) {
		this.stockInEmployeePostingDate = stockInEmployeePostingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}

