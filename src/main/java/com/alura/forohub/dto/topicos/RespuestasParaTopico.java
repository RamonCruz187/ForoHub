package com.alura.forohub.dto.topicos;

import com.alura.forohub.dto.AutorResponseDTO;

import java.time.LocalDateTime;

public record RespuestasParaTopico(Long id, String mensaje, LocalDateTime fechaCreacion, AutorResponseDTO autor) {
}
