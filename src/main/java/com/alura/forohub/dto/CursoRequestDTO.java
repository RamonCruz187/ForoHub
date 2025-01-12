package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "La categoría es obligatoria")
        String categoria) {
}
