package com.gearup.dtos;

import com.gearup.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResult {
    private String token;
    private Long id;
    private String email;
    private UserRole role;
    private String message;
}