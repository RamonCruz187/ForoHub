package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicoToUpdateDTO(

        String titulo,

        String mensaje,

        Boolean status
) {
}
