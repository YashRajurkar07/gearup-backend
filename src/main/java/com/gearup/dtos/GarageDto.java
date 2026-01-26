package com.gearup.dtos;

import java.time.LocalTime;

import org.hibernate.validator.constraints.Length;

import com.gearup.entities.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarageDto {

	@NotBlank(message = "Garage Name Is Required")
	@Size(min=2, max=50)
	private String garageName;
	
	private Long ownerId;
	
	private Address address;
	
	@NotBlank(message = "Mobile Number is Required for Communication Purposes")
	@Length(max = 10, message = "Moblie Number Must be 10 Digits Only")
	@Pattern(regexp = "^[0-9]{10}$")
	private String garagePhone;
	
	private int totalMechanics;
	
	private LocalTime openingTime;
	
	private LocalTime closingTime;
	
	@NotNull
	private boolean isActive;
}
