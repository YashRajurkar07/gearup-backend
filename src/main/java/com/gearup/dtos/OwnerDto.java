package com.gearup.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDto {
	
	private UserDto userDetails;

	@Length(max = 10, message = "Moblie Number Must be 10 Digits Only")
	private String alternatePhone;
	
	@NotNull
	private boolean isVerified;
	
	@NotBlank(message = "Registration Number Is Required")
	@NotEmpty
	@Size(min=2, max=50)
	private String registrationNumber;

}
