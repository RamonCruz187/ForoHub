package com.alura.forohub.dto.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(

        @NotBlank(message = "El titulo es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El curso es obligatorio")
        Long cursoId,
        @NotNull(message = "El autor es obligatorio")
        Long autor
) {
}
