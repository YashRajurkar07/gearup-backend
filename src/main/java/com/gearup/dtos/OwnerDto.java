package com.gearup.dtos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDto {
	
	private UserDto userDto;

	private String alternatePhone;
	
	private boolean isVerified;
	
	private String registrationNumber;

}
