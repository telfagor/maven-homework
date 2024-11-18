package com.bolun.hotel.dto;

import com.bolun.hotel.entity.enums.Gender;
import com.bolun.hotel.validation.PasswordsMatch;
import com.bolun.hotel.validation.group.CreateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordsMatch(groups = CreateAction.class)
public record UserCreateEditDto(
        @NotBlank(message = "First name is required")
        @Size(min = 3, max = 64, message = "The first name should be between 3 and 64 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 3, max = 64, message = "The last name should be between 3 and 64 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 3, max = 64, message = "Password must be between 8 and 64 characters")
        String password,

        String confirmPassword,

        @NotNull(message = "Gender is required")
        Gender gender) {
}
