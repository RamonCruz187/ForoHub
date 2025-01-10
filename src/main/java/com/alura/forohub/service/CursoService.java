package com.alura.forohub.service;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;

import java.util.List;

public interface CursoService {

    CursoResponseDTO nuevoCurso(CursoRequestDTO cursoRequestDTO);

    List<CursoResponseDTO> listarCursos();
}
