package com.alura.forohub.service;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CursoService {

    CursoResponseDTO nuevoCurso(CursoRequestDTO cursoRequestDTO);

    Page<CursoResponseDTO> listarCursos(Pageable pageable);
}
