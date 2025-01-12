package com.alura.forohub.service;

import com.alura.forohub.dto.RespuestaRequestDTO;
import com.alura.forohub.dto.RespuestaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RespuestaService {

    RespuestaResponseDTO nuevaRespuesta(RespuestaRequestDTO request);

    Page<RespuestaResponseDTO> listarRespuestas(Pageable pageable);

}
