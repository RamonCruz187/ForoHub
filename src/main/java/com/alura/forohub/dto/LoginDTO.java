package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank(message = "El username es obligatorio")
        String username,

        @NotBlank(message = "El password es obligatorio")
        String password

) {
}
