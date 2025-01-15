package com.alura.forohub.service.impl;

import com.alura.forohub.dto.topicos.TopicoConRespuestasDTO;
import com.alura.forohub.dto.topicos.TopicoRequestDTO;
import com.alura.forohub.dto.topicos.TopicoResponseDTO;
import com.alura.forohub.dto.topicos.TopicoToUpdateDTO;
import com.alura.forohub.mapper.TopicoMapper;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UserRepository;
import com.alura.forohub.service.TopicoService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UserRepository userRepository;

    @Override
    public TopicoResponseDTO nuevoTopico(TopicoRequestDTO topicoRequestDTO) {
        Boolean topicoBD = topicoRepository.existsByTituloAndMensaje(topicoRequestDTO.titulo(), topicoRequestDTO.mensaje());
        if (topicoBD) {
            throw new RuntimeException("El topico ya existe");
        }
        Topico topico = Topico.builder()
                .titulo(topicoRequestDTO.titulo())
                .mensaje(topicoRequestDTO.mensaje())
                .fechaCreacion(LocalDateTime.now())
                .status(true)
                .curso(cursoRepository.findById(topicoRequestDTO.cursoId()).orElseThrow(() -> new RuntimeException("No existe el curso con id " + topicoRequestDTO.cursoId())))
                .user(userRepository.findById(topicoRequestDTO.autor()).orElseThrow(() -> new RuntimeException("No existe el autor con id " + topicoRequestDTO.autor())))
                .build();

        topicoRepository.save(topico);
        TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
        return mapper.toTopicoResponseDTO(topico);
    }

    @Override
    public Page<TopicoResponseDTO> listarTopicos(Pageable pageable) {
        TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
        Page<Topico> topicos = topicoRepository.findAll(pageable);
        return topicos.map(mapper::toTopicoResponseDTO);
    }

    @Override
    public TopicoConRespuestasDTO TopicoConRespuestas(Long id) {
        TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el tópico con id " + id));
        return mapper.toTopicoConRespuestasDTO(topico);
    }

    @Override
    public TopicoResponseDTO obtenerTopico(Long id) {
        try {
            TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
            return topicoRepository.findById(id).map(mapper::toTopicoResponseDTO).get();
        } catch (Exception e) {
            throw new RuntimeException("No existe el tópico con id " + id);
        }
    }

    @Override
    public TopicoResponseDTO editarTopico(Long id, TopicoToUpdateDTO topicoToUpdateDTO) {
        try {
            Topico topico = topicoRepository.findById(id).get();
            if (topicoToUpdateDTO.titulo() != null) {
                topico.setTitulo(topicoToUpdateDTO.titulo());
            }
            if (topicoToUpdateDTO.mensaje() != null) {
                topico.setMensaje(topicoToUpdateDTO.mensaje());
            }
            if (topicoToUpdateDTO.status() != null) {
                topico.setStatus(topicoToUpdateDTO.status());
            }
            topicoRepository.save(topico);
            TopicoMapper mapper = Mappers.getMapper(TopicoMapper.class);
            return mapper.toTopicoResponseDTO(topico);
        } catch (Exception e) {
            throw new RuntimeException("No existe el tópico con id " + id);
        }
    }

    @Override
    public String eliminarTopico(Long id) {
        try {
            Topico topico = topicoRepository.findById(id).get();
            topicoRepository.deleteById(topico.getId());
            return "Tópico eliminado";
        } catch (Exception e) {
            throw new RuntimeException("No existe el tópico con id " + id);
        }
    }
}
