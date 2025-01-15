package com.alura.forohub.service.impl;

import com.alura.forohub.dto.RespuestaRequestDTO;
import com.alura.forohub.dto.RespuestaResponseDTO;
import com.alura.forohub.mapper.RespuestaMapper;
import com.alura.forohub.model.Respuesta;
import com.alura.forohub.repository.RespuestaRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UserRepository;
import com.alura.forohub.service.RespuestaService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RespuestaServiceImpl implements RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UserRepository userRepository;

    @Override
    public RespuestaResponseDTO nuevaRespuesta(RespuestaRequestDTO request) {
        Respuesta respuesta = Respuesta.builder()
                .mensaje(request.mensaje())
                .topico(topicoRepository.findById(request.topicoId()).orElse(null))
                .fechaCreacion(LocalDateTime.now())
                .user(userRepository.findById(request.autor()).orElse(null))
                .build();
        respuestaRepository.save(respuesta);
        RespuestaMapper mapper = Mappers.getMapper(RespuestaMapper.class);
        return mapper.toRespuestaResponseDTO(respuesta);
    }

    @Override
    public Page<RespuestaResponseDTO> listarRespuestas(Pageable pageable) {
        RespuestaMapper mapper = Mappers.getMapper(RespuestaMapper.class);

        Page<Respuesta> respuestas = respuestaRepository.findAll(pageable);

        return respuestas.map(mapper::toRespuestaResponseDTO);
    }
}
