package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(

        @NotBlank(message = "El username es obligatorio")
        String username,

        @NotBlank(message = "La contraseña es obligatoria")
        String password,

        @NotBlank(message = "El email es obligatorio")
        String email) {
}
