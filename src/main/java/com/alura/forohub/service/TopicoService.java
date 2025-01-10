package com.alura.forohub.service;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;

import java.util.List;

public interface TopicoService {

    TopicoResponseDTO nuevoTopico(TopicoRequestDTO topicoRequestDTO);

    List<TopicoResponseDTO> listarTopicos();
}
