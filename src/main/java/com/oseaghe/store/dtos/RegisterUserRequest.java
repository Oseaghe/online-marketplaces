package com.oseaghe.store.dtos;

import com.oseaghe.store.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(max=255, message = "Name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    @Lowercase(message="Email must be written in lowercase")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, max=25, message = "Password must be between 8 to 25 characters long.")
    private String password;
}
