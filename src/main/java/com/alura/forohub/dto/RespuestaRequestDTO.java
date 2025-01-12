package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaRequestDTO(

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El topico es obligatorio")
        Long topicoId,
        @NotNull(message = "El autor es obligatorio")
        Long autor

) {
}
