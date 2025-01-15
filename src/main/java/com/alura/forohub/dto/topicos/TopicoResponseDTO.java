package com.alura.forohub.dto.topicos;

import java.time.LocalDateTime;

public record TopicoResponseDTO(Long id, String titulo, String mensaje, String curso, Long autor, LocalDateTime fechaCreacion, String status) {
}
