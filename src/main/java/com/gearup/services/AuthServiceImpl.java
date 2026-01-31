package com.gearup.services;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gearup.dtos.AuthenticationResult;
import com.gearup.entities.User;
import com.gearup.entities.UserRole;
import com.gearup.repositories.UserRepository;
import com.gearup.security.jwt.JwtUtils;
import com.gearup.security.requestDto.LoginRequest;
import com.gearup.security.requestDto.SignupRequest;
import com.gearup.security.services.UserDetailsImpl;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public AuthenticationResult login(LoginRequest request) {
    	
        // 1. Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // 2. Set Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Generate Token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // 4. Get User Details
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        // The Authority String 
        String roleName = userDetails.getAuthorities().iterator().next().getAuthority();
        UserRole role = UserRole.valueOf(roleName); 

        // 5. Return Result
        return new AuthenticationResult(jwt, userDetails.getId(), userDetails.getEmail(), role, "Login Successful");
    }

    @Override
    public String register(SignupRequest request) {
    	
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        User user = new User();
        user.setFirstName(request.getFirstName()); 
        user.setLastName(request.getLastName());   
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        user.setRole(request.getRole() != null ? request.getRole() : UserRole.ROLE_CUSTOMER);

        userRepository.save(user);
        return "User Registered Successfully!";
    }

}