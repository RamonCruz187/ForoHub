package com.alura.forohub.dto;

import java.time.LocalDateTime;

public record RespuestaResponseDTO(Long id, String mensaje, LocalDateTime fechaCreacion, Long autor, Long topico) {
}
