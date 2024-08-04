package com.example.WeaponArmaryManagementSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "police_station_Registration") //by vikas on 10/06/2024
public class PoliceStationRegistration {

		
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "police_station_id")
	    private Long policeStationId;
	    
	    @Column(name="name")
	    private String name;
	     
	    @Column(name = "status")
	    private String status;
	    
	    @Column(name = "address")
	    private String address;
	    
	    @Column(name = "department_id")
	    private Long departmentId;
	    
		@Column(name = "email",unique = true)
	    private String email;
	    
	    @Column(name="phone",unique = true)
	    private String phone;
	    
	   // @Column(name = "password")
	   // private String password;
	    

	    public Long getDepartmentId() {
	 			return departmentId;
	 		}

	 		public void setDepartmentId(Long departmentId) {
	 			this.departmentId = departmentId;
	 		}

	    
		public Long getPoliceStationId() {
			return policeStationId;
		}

		public void setPoliceStationId(Long policeStationId) {
			this.policeStationId = policeStationId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		
	    
}