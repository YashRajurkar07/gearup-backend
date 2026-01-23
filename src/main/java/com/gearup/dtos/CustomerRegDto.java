package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Address;
import com.gearup.entities.Gender;
import com.gearup.entities.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegDto {

	private UserDto userDto;
	
	private String licenseNumber;
}
