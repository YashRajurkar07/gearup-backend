package com.gearup.dtos;

import java.time.LocalDate;

import com.gearup.entities.Address;
import com.gearup.entities.Gender;
import com.gearup.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobileNumber;
	
	private LocalDate dateOfBirth;
	
	private Address address;
	
	private Gender gender;
	
	private boolean isActive;
	
	private String password;
	
}
