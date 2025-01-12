package com.alura.forohub.service;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicoService {

    TopicoResponseDTO nuevoTopico(TopicoRequestDTO topicoRequestDTO);

    Page<TopicoResponseDTO> listarTopicos(Pageable pageable);
}
