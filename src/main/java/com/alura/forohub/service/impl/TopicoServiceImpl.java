package com.alura.forohub.service.impl;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.mapper.CursoMapper;
import com.alura.forohub.mapper.TopicoMapper;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UserRepository;
import com.alura.forohub.service.TopicoService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UserRepository userRepository;

    @Override
    public TopicoResponseDTO nuevoTopico(TopicoRequestDTO topicoRequestDTO) {
        Topico topico = Topico.builder()
                .titulo(topicoRequestDTO.titulo())
                .mensaje(topicoRequestDTO.mensaje())
                .fechaCreacion(LocalDateTime.now())
                .status(true)
                .curso(cursoRepository.findById(topicoRequestDTO.cursoId()).orElse(null))
                .user(userRepository.findById(topicoRequestDTO.autor()).orElse(null))
                .build();

        topicoRepository.save(topico);
        TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
        return mapper.toTopicoResponseDTO(topico);
    }

    @Override
    public List<TopicoResponseDTO> listarTopicos() {
        TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
        return topicoRepository.findAll().stream().map(mapper::toTopicoResponseDTO).toList();
    }
}
