package com.gearup.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRegDto {

	private UserDto userDetails;
	
	@NotBlank(message = "License Number Is Required")
	private String licenseNumber;
	
}
