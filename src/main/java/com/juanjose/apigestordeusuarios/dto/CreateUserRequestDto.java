package com.juanjose.apigestordeusuarios.dto;

import jakarta.validation.constraints.*;

public record CreateUserRequestDto(
        @Pattern (
                regexp = "^[A-aZ-z ]+$"
        )@NotBlank String name,
        @Pattern(
                regexp = "^[A-aZ-z ]+$"
        )@NotBlank String surname,
        @Positive @Min(18) @Max(100) Integer age,
        @NotBlank @Email String email,
        @NotBlank  String password
) {
}
