package com.alura.forohub.dto.topicos;

import com.alura.forohub.dto.RespuestaResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoConRespuestasDTO(Long id, String titulo, String mensaje, String curso, Long autor, LocalDateTime fechaCreacion, String status, List<RespuestasParaTopico> respuestas) {
}
