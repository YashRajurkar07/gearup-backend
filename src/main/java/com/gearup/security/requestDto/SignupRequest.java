package com.gearup.security.requestDto;

import java.time.LocalDate;

import com.gearup.entities.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    
    // --- User Details ---
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String password;
    private String mobileNumber;
    private LocalDate dateOfBirth;
    private String gender; 
    private UserRole role; 

    // --- Address Details ---
    private String city;
    private String state;
    private String country;
    private String area;
    private int zipCode;

    // --- Specific Details ---
    private String licenseNumber;      // For Customer
    private String alternatePhone;     // For Owner
    private String registrationNumber; // For Owner
}