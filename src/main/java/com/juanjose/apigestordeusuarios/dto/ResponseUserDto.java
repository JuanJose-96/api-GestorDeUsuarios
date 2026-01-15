package com.juanjose.apigestordeusuarios.dto;

public record ResponseUserDto(
        Long id,
        String name,
        String surname,
        Integer age,
        String email

) {
}
