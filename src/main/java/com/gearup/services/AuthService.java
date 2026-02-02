package com.gearup.services;

import com.gearup.dtos.AuthenticationResult;
import com.gearup.security.requestDto.LoginRequest;
import com.gearup.security.requestDto.SignupRequest;

public interface AuthService {
	
	AuthenticationResult login(LoginRequest loginRequest);

    String register(SignupRequest signUpRequest);

}
