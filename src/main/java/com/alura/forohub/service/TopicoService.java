package com.alura.forohub.service;

import com.alura.forohub.dto.topicos.TopicoConRespuestasDTO;
import com.alura.forohub.dto.topicos.TopicoRequestDTO;
import com.alura.forohub.dto.topicos.TopicoResponseDTO;
import com.alura.forohub.dto.topicos.TopicoToUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicoService {

    TopicoResponseDTO nuevoTopico(TopicoRequestDTO topicoRequestDTO);

    Page<TopicoResponseDTO> listarTopicos(Pageable pageable);

    TopicoConRespuestasDTO TopicoConRespuestas(Long id);

    TopicoResponseDTO obtenerTopico(Long id);

    TopicoResponseDTO editarTopico(Long id, TopicoToUpdateDTO topicoToUpdateDTO);

    String eliminarTopico(Long id);
}
