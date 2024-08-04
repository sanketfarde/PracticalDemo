 package com.example.WeaponArmaryManagementSystem.controller;

import com.example.WeaponArmaryManagementSystem.Dto.LongWeaponTypesResponseDto;
import com.example.WeaponArmaryManagementSystem.Dto.ShortWeaponTypesResponseDto;
import com.example.WeaponArmaryManagementSystem.Dto.SparePartDto;
import com.example.WeaponArmaryManagementSystem.Dto.WeaponFormDto;
import com.example.WeaponArmaryManagementSystem.model.*;
import com.example.WeaponArmaryManagementSystem.repository.SparePartRepo;
import com.example.WeaponArmaryManagementSystem.repository.WeaponFormLongRepo;
import com.example.WeaponArmaryManagementSystem.repository.WeaponFormRepo;
import com.example.WeaponArmaryManagementSystem.repository.WeaponFormShortRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// chandrika's project
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("")
public class WeaponFormController {
	
	    @Autowired
	    private WeaponFormRepo weaponFormRepo;
	    
	    @Autowired
	    private WeaponFormLongRepo weaponFormLongRepo;
	    
	    @Autowired
	    private WeaponFormShortRepo weaponFormShortRepo;
	    
	    @Autowired
	    private SparePartRepo sparePartRepo;
	    
	    //by chandrika on 07/06/2024
	    @PostMapping("/longWeaponTypesbyDate")
		 public ResponseEntity<?> getLongWeaponTypes1() {
		     try {
		         List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAllByInDateDesc();
		         List<WeaponFormLong> longWeaponTypes = allWeaponForms.stream()
		                 .filter(form -> {
		                     String weaponType = form.getWeaponType();
		                     return weaponType != null && weaponType.equalsIgnoreCase("long");
		                 })
		                 .collect(Collectors.toList());
		         if (longWeaponTypes.isEmpty()) {
		             return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
		         }

		         LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypes, LocalDate.now());
		         return new ResponseEntity<>(responseDto, HttpStatus.OK);
		     } catch (Exception e) {
		         return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		     }
		 }
	    
	    
	    
	   //07/06/2024 by chandrika 
	    @PostMapping("/shortWeaponTypesbyDate")
		 public ResponseEntity<?> getShortWeaponTypes1() {
		     try {
		         List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAllByOrderByDateDesc(); // Modified method call
		         List<WeaponFormShort> shortWeaponTypes = allWeaponForms.stream()
		                 .filter(form -> {
		                     String weaponType = form.getWeaponType();
		                     return weaponType != null && weaponType.equalsIgnoreCase("short");
		                 })
		                 .collect(Collectors.toList());
		         if (shortWeaponTypes.isEmpty()) {
		             return new ResponseEntity<>("{\"message\": \"No Short Weapon Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
		         }

		         ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypes, LocalDate.now());
		         return new ResponseEntity<>(responseDto, HttpStatus.OK);
		     } catch (Exception e) {
		         return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		     }
		 }
	    
	    
	    
	    @PostMapping("/updateSparePart/{id}")
	    public ResponseEntity<String> updateSparePart(@PathVariable Integer id, @RequestBody SparePartDto sparePartDto) {
	        try {
	            Optional<SparePart> sparePartOptional = sparePartRepo.findById(id);
	            if (sparePartOptional.isPresent()) {
	                SparePart sparePart = sparePartOptional.get();
	                sparePart.setSparePartName(sparePartDto.getSparePartName());
	                sparePart.setWeaponName(sparePartDto.getWeaponName());
	                sparePart.setStatus(sparePartDto.getStatus());
	                sparePart.setWeaponType(sparePartDto.getWeaponType());
	                sparePart.setCreatedAt(LocalDateTime.now());  // or keep the original createdAt if not to be updated
	                sparePartRepo.save(sparePart);

	                return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\": 0}", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"SparePart not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Data Failed To Update...\",\"Id\": 2, \"error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    

	
	
	   @PostMapping("/updateWeaponForm/{id}")
	    public ResponseEntity<String> updateWeapon(@PathVariable Integer id, @RequestBody WeaponFormDto weaponFormDto) {
	        try {
	            Optional<WeaponForm> optionalWeaponForm = weaponFormRepo.findById(id);
	            if (optionalWeaponForm.isPresent()) {
	                WeaponForm existingWeaponForm = optionalWeaponForm.get();
	                existingWeaponForm.setWeaponName(weaponFormDto.getWeaponName());
	                existingWeaponForm.setWeaponType(weaponFormDto.getWeaponType());
	             //   existingWeaponForm.setStatus(weaponFormDto.getStatus()); // Ensure your DTO has the 'status' field
	                existingWeaponForm.setWeaponStatus(weaponFormDto.getWeaponStatus()); // Ensure your DTO has the 'weaponStatus' field
	                existingWeaponForm.setUpdatedAt(LocalDateTime.now());

	                weaponFormRepo.save(existingWeaponForm);
	                return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\": " + id + "}", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Weapon Form not found...\",\"Id\": " + id + "}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": " + id + ",\"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    
	   
	   
	   @PostMapping("/getSparePartby_id/{id}")
	    public ResponseEntity<?> getSparePartById(@PathVariable Integer id) {
	        try {
	            Optional<SparePart> optionalSparePart = sparePartRepo.findById(id);
	            if (optionalSparePart.isPresent()) {
	                SparePart sparePart = optionalSparePart.get();
	                SparePartDto sparePartDto = new SparePartDto();
	                sparePartDto.setSparePartName(sparePart.getSparePartName());
	                sparePartDto.setWeaponName(sparePart.getWeaponName());
	                sparePartDto.setStatus(sparePart.getStatus());
	                sparePartDto.setWeaponType(sparePart.getWeaponType());
	                sparePartDto.setId(sparePart.getId());   //created by vikas as per avinash on 30/5/2024

	                return new ResponseEntity<>(sparePartDto, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Spare Part not found...\",\"Id\": " + id + "}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": " + id + ",\"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	   
	   

	   @PostMapping("/getWeaponby_id/{id}")
	    public ResponseEntity<?> getWeaponFormById(@PathVariable Integer id) {
	        try {
	            Optional<WeaponForm> optionalWeaponForm = weaponFormRepo.findById(id);
	            if (optionalWeaponForm.isPresent()) {
	                WeaponForm weaponForm = optionalWeaponForm.get();
	                WeaponFormDto weaponFormDto = new WeaponFormDto();
	                weaponFormDto.setWeaponName(weaponForm.getWeaponName());
	                weaponFormDto.setWeaponType(weaponForm.getWeaponType());
	              //  weaponFormDto.setStatus(weaponForm.getStatus());
	                weaponFormDto.setWeaponStatus(weaponForm.getWeaponStatus());

	                return new ResponseEntity<>(weaponFormDto, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Weapon Form not found...\",\"Id\": " + id + "}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": " + id + ",\"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   

		@PostMapping("/addWeaponForm")
		public ResponseEntity<String> addWeapon(@RequestBody WeaponFormDto weaponFormDto) {
			try {
				// Check if a weapon with the same name already exists
				if (weaponFormRepo.existsByWeaponName(weaponFormDto.getWeaponName())) {
					return new ResponseEntity<>("{\"message\": \"Weapon Name Already exits...\",\"Id\": 2}", HttpStatus.CONFLICT);
				}

				// Create a new WeaponForm object and set its properties
				WeaponForm newWeaponForm = new WeaponForm();
				newWeaponForm.setWeaponName(weaponFormDto.getWeaponName());
				newWeaponForm.setWeaponType(weaponFormDto.getWeaponType());
				newWeaponForm.setStatus("0");
				newWeaponForm.setDate(weaponFormDto.getDate());
				newWeaponForm.setCreatedAt(LocalDateTime.now());
				newWeaponForm.setUpdatedAt(LocalDateTime.now());
				newWeaponForm.setWeaponStatus("active");
				// Set other properties if available in WeaponFormDto

				// Save the new weapon form
				weaponFormRepo.save(newWeaponForm);
				return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>("{\"message\": \"Data Not Saved Successfully...\",\"Id\": 1}", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


		@PostMapping("/addSparePart")
		public ResponseEntity<String> addSparePart(@RequestBody SparePartDto sparePartDto) {
			try {
				// Check if the spare part with the same weapon name and spare part name already exists
				Optional<SparePart> existingSparePart = sparePartRepo.findByWeaponNameAndSparePartName(
						sparePartDto.getWeaponName(), sparePartDto.getSparePartName());

				if (existingSparePart.isPresent()) {
					return new ResponseEntity<>("{\"message\": \"Spare part with the same weapon name and spare part name already exists.\",\"Id\": 1}", HttpStatus.CONFLICT);
				}

				// Create a new spare part
				SparePart sparePart = new SparePart();
				sparePart.setSparePartName(sparePartDto.getSparePartName());
				sparePart.setWeaponName(sparePartDto.getWeaponName());
				sparePart.setStatus("Active");
				sparePart.setWeaponType(sparePartDto.getWeaponType());
				sparePart.setCreatedAt(LocalDateTime.now());
				sparePartRepo.save(sparePart);

				return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>("{\"message\": \"Data Failed To Save...\",\"Id\": 2,\"error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		
		   
		   

			 @PostMapping("/sparePartsByDate")
			    public ResponseEntity<?> getSparePartsByDate(@RequestParam("date") String date) {
			        try {
			            LocalDate filterDate = LocalDate.parse(date);
			            LocalDateTime startOfDay = filterDate.atStartOfDay();
			            LocalDateTime endOfDay = filterDate.atTime(LocalTime.MAX);
			            
			            List<SparePart> spareParts = sparePartRepo.findByCreatedAtBetween(startOfDay, endOfDay);
			            if (spareParts.isEmpty()) {
			                return new ResponseEntity<>("{\"message\": \"No Spare Parts Found for the given date...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			            }
			            return new ResponseEntity<>(spareParts, HttpStatus.OK);
			        } catch (Exception e) {
			            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			        }
			    }

//modified for active inactive  10/06/2024
	// By Chandrika on 04/06/2024
@PostMapping("/longWeaponTypes")
public ResponseEntity<?> getLongWeaponTypes() {
	try {
		// Retrieve all weapon forms from the repository
		List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAll();

		// Filter for long weapon types with active status
		List<WeaponFormLong> longWeaponTypes = allWeaponForms.stream()
				.filter(form -> {
					String weaponType = form.getWeaponType();
					String weaponStatus = form.getWeaponStatus();
					// Check for non-null and case-insensitive equality
					return weaponType != null && weaponType.equalsIgnoreCase("long") &&
							weaponStatus != null && weaponStatus.equalsIgnoreCase("active");
				})
				.sorted(Comparator.comparing(WeaponFormLong::getWeaponName)) // Sort alphabetically
				.collect(Collectors.toList());

		// Check if no data found
		if (longWeaponTypes.isEmpty()) {
			return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
		}

		// Prepare response DTO
		LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypes, LocalDate.now());
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	} catch (Exception e) {
		// Handle exceptions
		return new ResponseEntity<>("{\"message\": \"An error occurred while fetching the data...\",\"Id\": 2} " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

	//modified for active inactive  10/06/2024
	// Changes by Chandrika on 05/06/2024
	@PostMapping("/shortWeaponTypes")
	public ResponseEntity<?> getShortWeaponTypes() {
		try {
			List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAll();
			List<WeaponFormShort> shortWeaponTypes = allWeaponForms.stream()
					.filter(form -> {
						String weaponType = form.getWeaponType();
						String weaponStatus = form.getWeaponStatus(); // Assuming there is a getStatus method
						return weaponType != null && weaponType.equalsIgnoreCase("short") &&
								weaponStatus != null && weaponStatus.equalsIgnoreCase("active"); // Filtering by status
					})
					.sorted(Comparator.comparing(WeaponFormShort::getWeaponName))
					.collect(Collectors.toList());
			if (shortWeaponTypes.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Short Weapon Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}

			ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypes, LocalDate.now());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2} " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//modified for active inactive  10/06/2024
	// By Manish on 08/06/2024
	@PostMapping("/listSpareParts")
	public ResponseEntity<?> getSpareParts() {
		try {
			List<SparePart> allSpareParts = sparePartRepo.findAllByOrderByCreatedAtDesc();
			List<SparePart> activeSpareParts = allSpareParts.stream()
					.filter(part -> {
						String status = part.getStatus(); // Assuming there is a getStatus method
						return status != null && status.equalsIgnoreCase("active"); // Filtering by status
					})
					.collect(Collectors.toList());
			if (activeSpareParts.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Spare Parts Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(activeSpareParts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"Failed to retrieve spare parts...\",\"Id\": 2} " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

			// all three methods are working but for active inactive commented out 10/06/2024

			 //by chandrika on 04/06/2024
		/*	 @PostMapping("/longWeaponTypes")
			 public ResponseEntity<?> getLongWeaponTypes() {
			     try {
			         List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAll();
			         List<WeaponFormLong> longWeaponTypes = allWeaponForms.stream()
			                 .filter(form -> {
			                     String weaponType = form.getWeaponType();
			                     return weaponType != null && weaponType.equalsIgnoreCase("long");
			                 })
			                 .sorted(Comparator.comparing(WeaponFormLong::getWeaponName)) // Sort alphabetically
			                 .collect(Collectors.toList());
			         if (longWeaponTypes.isEmpty()) {
			             return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			         }

			         LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypes, LocalDate.now());
			         return new ResponseEntity<>(responseDto, HttpStatus.OK);
			     } catch (Exception e) {
			         return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			     }
			 }*/
			 
	    /*
			//changes by chandrika on 05/06/2024
			 @PostMapping("/shortWeaponTypes")
				public ResponseEntity<?> getShortWeaponTypes() {
					try {
						List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAll();
						List<WeaponFormShort> shortWeaponTypes = allWeaponForms.stream()
								.filter(form -> {
									String weaponType = form.getWeaponType();
									return weaponType != null && weaponType.equalsIgnoreCase("short");
								})
								 .sorted(Comparator.comparing(WeaponFormShort::getWeaponName))
								.collect(Collectors.toList());
						if (shortWeaponTypes.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No Short Weapon Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
						}

						ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypes, LocalDate.now());
						return new ResponseEntity<>(responseDto, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

	//08/06/2024  by manish
	@PostMapping("/listSpareParts")
	public ResponseEntity<?> getSpareParts() {
		try {
			List<SparePart> spareParts = sparePartRepo.findAllByOrderByCreatedAtDesc();
			if (spareParts.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Spare Parts Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(spareParts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"failed to retrive spare part...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
			 */
			 /*
			//changes by chandrika on 05/06/2024 
				@PostMapping("/listSpareParts")
				public ResponseEntity<?> getSpareParts() {
				    try {
				        List<SparePart> spareParts = sparePartRepo.findAllByOrderBySparePartNameAsc(); 
				        if (spareParts.isEmpty()) {
				            return new ResponseEntity<>("{\"message\": \"No Spare Parts Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
				        }
				        return new ResponseEntity<>(spareParts, HttpStatus.OK);
				    } catch (Exception e) {
				        return new ResponseEntity<>("{\"message\": \"Failed to retrieve spare parts.\",\"Id\": 2, \"error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				    }
				}

			  */
			

				
				@PostMapping("/listSparePartsByWeaponName")
				public ResponseEntity<?> getSparePartsByWeaponName(@RequestParam String weaponName) {
					try {
						List<SparePart> spareParts = sparePartRepo.findByWeaponName(weaponName);
						if (spareParts.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No spare part Found For This Weapon...\",\"Id\": 1}" + weaponName, HttpStatus.NOT_FOUND);
						}
						return new ResponseEntity<>(spareParts, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"failed to retrive spare part...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
				

				// New methods
				@PostMapping("/weaponNamesByType")
				public ResponseEntity<?> getWeaponNamesByWeaponType(@RequestParam String weaponType) {
					try {
						List<String> weaponNames = weaponFormRepo.findWeaponNamesByWeaponType(weaponType);
						if (weaponNames.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No Weapons Found For This Type...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
						}
						return new ResponseEntity<>(weaponNames, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"Failed To Retrieve Weapon Names...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
				

				@PostMapping("/roundNamesByType")
				public ResponseEntity<?> getRoundNamesByWeaponType(@RequestParam String weaponType) {
					try {
						List<String> roundNames = weaponFormRepo.findRoundNamesByWeaponType(weaponType);
						if (roundNames.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No Rounds Found For This Type...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
						}
						return new ResponseEntity<>(roundNames, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"Failed To Retrieve Round Names...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				
				
			    
			    
			    @PostMapping("/longWeaponTypesByDate")
			    public ResponseEntity<?> getLongWeaponTypesByDate(@RequestParam("date") String date) {
			        try {
			            LocalDate filterDate = LocalDate.parse(date);
			            List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAll();
			            List<WeaponFormLong> longWeaponTypesByDate = allWeaponForms.stream()
			                    .filter(form -> {
			                        String weaponType = form.getWeaponType();
			                        LocalDateTime createdAt = form.getCreatedAt();
			                        return weaponType != null && weaponType.equalsIgnoreCase("long") &&
			                                createdAt != null && createdAt.toLocalDate().equals(filterDate);
			                    })
			                    .collect(Collectors.toList());
			            if (longWeaponTypesByDate.isEmpty()) {
			                return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found for the given date...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			            }

			            LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypesByDate, LocalDate.now());
			            return new ResponseEntity<>(responseDto, HttpStatus.OK);
			        } catch (Exception e) {
			            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			        }
			    }

			    
			    
			    @PostMapping("/shortWeaponTypesByDate")
			    public ResponseEntity<?> getShortWeaponTypesByDate(@RequestParam("date") String date) {
			        try {
			            LocalDate filterDate = LocalDate.parse(date);
			            List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAll();
			            List<WeaponFormShort> shortWeaponTypesByDate = allWeaponForms.stream()
			                    .filter(form -> {
			                        String weaponType = form.getWeaponType();
			                        LocalDateTime createdAt = form.getCreatedAt();
			                        return weaponType != null && weaponType.equalsIgnoreCase("short") &&
			                                createdAt != null && createdAt.toLocalDate().equals(filterDate);
			                    })
			                    .collect(Collectors.toList());
			            if (shortWeaponTypesByDate.isEmpty()) {
			                return new ResponseEntity<>("{\"message\": \"No Short Weapon Type Found for the given date...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			            }

			            ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypesByDate, LocalDate.now());
			            return new ResponseEntity<>(responseDto, HttpStatus.OK);
			        } catch (Exception e) {
			            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			        }
			    }

				@PostMapping("/longWeaponTypesBetweenDates")
				public ResponseEntity<?> getLongWeaponTypesBetweenDates(@RequestParam("startDate") String startDateStr,
																		@RequestParam("endDate") String endDateStr) {
					try {
						LocalDateTime startDateTime = LocalDate.parse(startDateStr).atStartOfDay();
						LocalDateTime endDateTime = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);
						List<WeaponFormLong> longWeaponTypesBetweenDates = weaponFormLongRepo.findByCreatedAtBetween(startDateTime, endDateTime);

						// Filter only long weapon types
						longWeaponTypesBetweenDates = longWeaponTypesBetweenDates.stream()
								.filter(weaponFormLong -> "long".equals(weaponFormLong.getWeaponType()))
								.collect(Collectors.toList());

						if (longWeaponTypesBetweenDates.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found between the given dates...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
						}

						LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypesBetweenDates, LocalDate.now());
						return new ResponseEntity<>(responseDto, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

				@PostMapping("/shortWeaponTypesBetweenDates")
				public ResponseEntity<?> getShortWeaponTypesBetweenDates(@RequestParam("startDate") String startDateStr,
																		 @RequestParam("endDate") String endDateStr) {
					try {
						LocalDateTime startDateTime = LocalDate.parse(startDateStr).atStartOfDay();
						LocalDateTime endDateTime = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);
						List<WeaponFormShort> shortWeaponTypesBetweenDates = weaponFormShortRepo.findByCreatedAtBetween(startDateTime, endDateTime);

						// Filter only short weapon types
						shortWeaponTypesBetweenDates = shortWeaponTypesBetweenDates.stream()
								.filter(weaponFormShort -> "short".equals(weaponFormShort.getWeaponType()))
								.collect(Collectors.toList());

						if (shortWeaponTypesBetweenDates.isEmpty()) {
							return new ResponseEntity<>("{\"message\": \"No Short Weapon Type Found between the given dates...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
						}

						ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypesBetweenDates, LocalDate.now());
						return new ResponseEntity<>(responseDto, HttpStatus.OK);
					} catch (Exception e) {
						return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			    

			}

			 
	    /*
	    @PostMapping("/updateSparePartByName/{name}")
	    public ResponseEntity<String> updateSparePartByName(@PathVariable String name, @RequestBody SparePartDto sparePartDto) {
	        try {
	            Optional<SparePart> sparePartOptional = sparePartRepo.findBySparePartName(name);
	            if (sparePartOptional.isPresent()) {
	                SparePart sparePart = sparePartOptional.get();
	                sparePart.setSparePartName(sparePartDto.getSparePartName());
	                sparePart.setWeaponName(sparePartDto.getWeaponName());
	                sparePart.setStatus(sparePartDto.getStatus());
	                sparePart.setWeaponType(sparePartDto.getWeaponType());
	                sparePart.setUpdatedAt(LocalDateTime.now());  // Use updatedAt for updates
	                sparePartRepo.save(sparePart);

	                return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\": 0}", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"SparePart not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Data Failed To Update...\",\"Id\": 2, \"error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/updateWeaponFormByName/{name}")
	    public ResponseEntity<String> updateWeaponFormByName(@PathVariable String name, @RequestBody WeaponFormDto weaponFormDto) {
	        try {
	            Optional<WeaponForm> optionalWeaponForm = weaponFormRepo.findByWeaponName1(name);
	            if (optionalWeaponForm.isPresent()) {
	                WeaponForm existingWeaponForm = optionalWeaponForm.get();
	                existingWeaponForm.setWeaponName(weaponFormDto.getWeaponName());
	                existingWeaponForm.setWeaponType(weaponFormDto.getWeaponType());
	                existingWeaponForm.setWeaponStatus(weaponFormDto.getWeaponStatus());
	                existingWeaponForm.setUpdatedAt(LocalDateTime.now());

	                weaponFormRepo.save(existingWeaponForm);
	                return new ResponseEntity<>("{\"message\": \"Data Updated Successfully...\",\"Id\": 0}", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Weapon Form not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2, \"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/getSparePartByName/{name}")
	    public ResponseEntity<?> getSparePartByName(@PathVariable String name) {
	        try {
	            Optional<SparePart> optionalSparePart = sparePartRepo.findBySparePartName(name);
	            if (optionalSparePart.isPresent()) {
	                SparePart sparePart = optionalSparePart.get();
	                SparePartDto sparePartDto = new SparePartDto();
	                sparePartDto.setSparePartName(sparePart.getSparePartName());
	                sparePartDto.setWeaponName(sparePart.getWeaponName());
	                sparePartDto.setStatus(sparePart.getStatus());
	                sparePartDto.setWeaponType(sparePart.getWeaponType());
	                sparePartDto.setId(sparePart.getId());

	                return new ResponseEntity<>(sparePartDto, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Spare Part not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2, \"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/getWeaponFormByName/{name}")
	    public ResponseEntity<?> getWeaponFormByName(@PathVariable String name) {
	        try {
	            Optional<WeaponForm> optionalWeaponForm = weaponFormRepo.findByWeaponName1(name);
	            if (optionalWeaponForm.isPresent()) {
	                WeaponForm weaponForm = optionalWeaponForm.get();
	                WeaponFormDto weaponFormDto = new WeaponFormDto();
	                weaponFormDto.setWeaponName(weaponForm.getWeaponName());
	                weaponFormDto.setWeaponType(weaponForm.getWeaponType());
	                weaponFormDto.setWeaponStatus(weaponForm.getWeaponStatus());

	                return new ResponseEntity<>(weaponFormDto, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("{\"message\": \"Weapon Form not found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Error Occurred...\",\"Id\": 2, \"Error\": \"" + e.getMessage() + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
*/
	 
	  
	   /* 
	    @PostMapping("/weapon-names")
	    public ResponseEntity<List<String>> getWeaponNamesByWeaponType() {
	        List<String> weaponNames = wareHouseWeaponsAddRepository.findWeaponNamesByWeaponType();
	        return ResponseEntity.ok(weaponNames);
	    }

	    @PostMapping("/round-names")
	    public ResponseEntity<List<String>> getRoundNamesByWeaponType() {
	        List<String> weaponNames = wareHouseWeaponsAddRepository.findRoundNamesByWeaponType();
	        return ResponseEntity.ok(weaponNames);
	    }
	    */
	    
	    
/*
	@PostMapping("/addWeaponForm")
	public ResponseEntity<String> addWeapon(@RequestBody WeaponFormDto weaponFormDto) {
		try {
			WeaponForm newWeaponForm = new WeaponForm();
			newWeaponForm.setWeaponName(weaponFormDto.getWeaponName());
			newWeaponForm.setWeaponType(weaponFormDto.getWeaponType());
			newWeaponForm.setStatus("0");
			newWeaponForm.setCreatedAt(LocalDateTime.now());
			newWeaponForm.setUpdatedAt(LocalDateTime.now());
			newWeaponForm.setWeaponStatus("active");
			// Set other properties if available in WeaponFormDto

			weaponFormRepo.save(newWeaponForm);
			return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 1}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
*/




/*	@PostMapping("/addSparePart")
	    public ResponseEntity<String> addSparePart(@RequestBody SparePartDto sparePartDto) {
	        try {
	            SparePart sparePart = new SparePart();
	            sparePart.setSparePartName(sparePartDto.getSparePartName());
				sparePart.setWeaponName(sparePartDto.getWeaponName());
	            sparePart.setStatus("Active");
				sparePart.setWeaponType(sparePartDto.getWeaponType());
				sparePart.setCreatedAt(LocalDateTime.now());
	            sparePartRepo.save(sparePart);
	            
	            return new ResponseEntity<>("{\"message\": \"Data Saved Successfully...\",\"Id\": 0}", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("{\"message\": \"Data Failed To Save...\",\"Id\": 1}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    */


	
	
	/*
	// this is for the return response with the current timestamp
	@PostMapping("/longWeaponTypes")
	public ResponseEntity<?> getLongWeaponTypes() {
		try {
			List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAll();
			List<WeaponFormLong> longWeaponTypes = allWeaponForms.stream()
					.filter(form -> {
						String weaponType = form.getWeaponType();
						return weaponType != null && weaponType.equalsIgnoreCase("long");
					})
					.collect(Collectors.toList());
			if (longWeaponTypes.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Long Weapon Type Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}

			LongWeaponTypesResponseDto responseDto = new LongWeaponTypesResponseDto(longWeaponTypes, LocalDate.now());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 */


		 
	
	/*
	// this is for the return response with the current timestamp
	@PostMapping("/shortWeaponTypes")
	public ResponseEntity<?> getShortWeaponTypes() {
		try {
			List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAll();
			List<WeaponFormShort> shortWeaponTypes = allWeaponForms.stream()
					.filter(form -> {
						String weaponType = form.getWeaponType();
						return weaponType != null && weaponType.equalsIgnoreCase("short");
					})
					.collect(Collectors.toList());
			if (shortWeaponTypes.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Short Weapon Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}

			ShortWeaponTypesResponseDto responseDto = new ShortWeaponTypesResponseDto(shortWeaponTypes, LocalDate.now());
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"No Weapon Found...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	    */
	 
/*
	@PostMapping("/updateWeaponStatus")
	public ResponseEntity<?> updateWeaponStatus(@RequestParam String weaponName, @RequestParam String newStatus) {
		try {
			Optional<WeaponForm> weaponFormOptional = Optional.ofNullable(weaponFormRepo.findByWeaponName(weaponName));
			if (!weaponFormOptional.isPresent()) {
				return new ResponseEntity<>("{\"message\": \"Weapon Not Found With This Name...\",\"Id\": 2}" + weaponName, HttpStatus.NOT_FOUND);
			}

			WeaponForm weaponForm = weaponFormOptional.get();
			weaponForm.setWeaponStatus(newStatus);
			weaponForm.setUpdatedAt(LocalDateTime.now());
			weaponFormRepo.save(weaponForm);

			return new ResponseEntity<>("{\"message\": \"Weapon Status Updated Successfully...\",\"Id\": 0}", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"Failed To  Weapon Status...\",\"Id\": 1}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	   

	
	
	 
	    
	 /*     
	@PostMapping("/listSpareParts")
	public ResponseEntity<?> getSpareParts() {
		try {
			List<SparePart> spareParts = sparePartRepo.findAllByOrderByCreatedAtDesc();
			if (spareParts.isEmpty()) {
				return new ResponseEntity<>("{\"message\": \"No Spare Parts Found...\",\"Id\": 1}", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(spareParts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("{\"message\": \"failed to retrive spare part...\",\"Id\": 2}" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	

/*@PostMapping("/longWeaponTypes")
public ResponseEntity<?> getLongWeaponTypes() {
    try {
        List<WeaponFormLong> allWeaponForms = weaponFormLongRepo.findAll();
        List<WeaponFormLong> longWeaponTypes = allWeaponForms.stream()
            .filter(form -> {
                String weaponType = form.getWeaponType();
                return weaponType != null && weaponType.equalsIgnoreCase("long");
            })
            .collect(Collectors.toList());
        if (longWeaponTypes.isEmpty()) {
            return new ResponseEntity<>("No long weapon types found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(longWeaponTypes, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to retrieve long weapon types: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/

/*   @PostMapping("/listSpareParts")
public ResponseEntity<?> getSpareParts() {
    try {
        List<SparePart> spareParts = sparePartRepo.findAll();
        if (spareParts.isEmpty()) {
            return new ResponseEntity<>("No spare parts found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(spareParts, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to retrieve spare parts: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/

/*  @PostMapping("/shortWeaponTypes")
public ResponseEntity<?> getShortWeaponTypes() {
    try {
        List<WeaponFormShort> allWeaponForms = weaponFormShortRepo.findAll();
        List<WeaponFormShort> shortWeaponTypes = allWeaponForms.stream()
            .filter(form -> {
                String weaponType = form.getWeaponType();
                return weaponType != null && weaponType.equalsIgnoreCase("short");
            })
            .collect(Collectors.toList());
        if (shortWeaponTypes.isEmpty()) {
            return new ResponseEntity<>("No short weapon types found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shortWeaponTypes, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Failed to retrieve short weapon types: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}*/
