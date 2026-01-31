package com.gearup.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	@NotBlank(message="Email Required")
	@Email(message="Email Format Invalid")
	private String userName;
	
	@NotBlank(message="Email Required")
	private String password;
	
}
