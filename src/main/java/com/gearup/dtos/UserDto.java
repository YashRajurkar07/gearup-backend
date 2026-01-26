package com.gearup.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.gearup.entities.Address;
import com.gearup.entities.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	@NotBlank(message = "First Name Is Required")
	@NotEmpty
	@Size(min=2, max=50)
	private String firstName;
	
	@NotBlank(message = "Last Name Is Required")
	@NotEmpty
	@Size(min=2, max=50)
	private String lastName;
	
	@Email(message = "Invalid Email Format")
	@NotBlank
	@NotEmpty
	private String email;
	
	@NotBlank(message = "Mobile Number is Required for Communication Purposes")
	@NotEmpty
	@Length(max = 10, message = "Moblie Number Must be 10 Digits Only")
	private String mobileNumber;
	
	@Past(message = "Date Of Birth Is Invalid")
	private LocalDate dateOfBirth;
	
	private Address address;
	
	private Gender gender;
	
	@NotNull
	private boolean isActive;
	
//	@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Invalid Password Format")
	private String password;
	
}
