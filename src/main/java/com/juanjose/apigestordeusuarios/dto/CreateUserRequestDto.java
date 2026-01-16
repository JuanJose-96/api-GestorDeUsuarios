package com.juanjose.apigestordeusuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateUserRequestDto(
        @Pattern (
                regexp = "^[A-aZ-z ]+$"
        )@NotBlank String name,
        @Pattern(
                regexp = "^[A-aZ-z ]+$"
        )@NotBlank String surname,
        @Positive Integer age,
        @NotBlank @Email String email,
        @NotBlank  String password
) {
}
