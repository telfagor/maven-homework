package com.bolun.hotel.dto;

import com.bolun.hotel.entity.enums.Gender;
import com.bolun.hotel.entity.enums.Role;
import com.bolun.hotel.validation.PasswordMatcherValidator;
import com.bolun.hotel.validation.PasswordsMatcher;
import com.bolun.hotel.validation.UniqueEmail;
import com.bolun.hotel.validation.ValidPhoto;
import com.bolun.hotel.validation.group.CreateAction;
import com.bolun.hotel.validation.group.UpdateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@PasswordsMatcher(passwordField = "rawPassword", confirmPasswordField = "confirmPassword", groups = CreateAction.class)
public record UserCreateEditDto(@NotBlank(message = "First name is required")
                                @Size(min = 3, max = 64, message = "The first name should be between 3 and 64 characters")
                                String firstName,

                                @NotBlank(message = "Last name is required")
                                @Size(min = 3, max = 64, message = "The last name should be between 3 and 64 characters")
                                String lastName,

                                @NotBlank(message = "Email is required")
                                @Email(message = "Invalid email address")
                                @UniqueEmail(groups = CreateAction.class)
                                String email,

                                @NotBlank(message = "Password is required", groups = CreateAction.class)
                                @Size(min = 3, max = 64, message = "Password must be between 8 and 64 characters", groups = CreateAction.class)
                                String rawPassword,

                                String confirmPassword,

                                Role role,

                                @NotNull(message = "Gender is required")
                                Gender gender,

                                @NotBlank(message = "Phone number is required", groups = UpdateAction.class)
                                @Pattern(
                                        regexp = "^\\+\\d{3} \\d{8}$",
                                        message = "Phone number must match the format +373 67643434",
                                        groups = UpdateAction.class
                                )
                                String phoneNumber,

                                @NotNull(message = "Money is required", groups = UpdateAction.class)
                                Integer money,

                                @NotNull(message = "Birthdate is required", groups = UpdateAction.class)
                                @DateTimeFormat(pattern = "dd.MM.yyyy")
                                LocalDate birthdate,

                                @ValidPhoto(groups = UpdateAction.class)
                                MultipartFile photo) implements PasswordMatcherValidator {

    public UserCreateEditDto(String firstName,
                             String lastName,
                             String email,
                             String rawPassword,
                             String confirmPassword,
                             Role role,
                             Gender gender,
                             String phoneNumber,
                             Integer money,
                             LocalDate birthdate,
                             MultipartFile photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.rawPassword = rawPassword;
        this.confirmPassword = confirmPassword;
        this.role = role != null ? role : Role.USER;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.money = money;
        this.birthdate = birthdate;
        this.photo = photo;
    }

    @Override
    public String getPassword() {
        return rawPassword;
    }

    @Override
    public String getConfirmPassword() {
        return confirmPassword;
    }
}
