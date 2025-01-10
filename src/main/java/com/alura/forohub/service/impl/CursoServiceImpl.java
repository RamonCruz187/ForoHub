package com.alura.forohub.service.impl;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import com.alura.forohub.mapper.CursoMapper;
import com.alura.forohub.model.Curso;
import com.alura.forohub.repository.CursoRepository;
import com.alura.forohub.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Override
    public CursoResponseDTO nuevoCurso(CursoRequestDTO cursoRequestDTO) {
        CursoMapper mapper = Mappers.getMapper(CursoMapper.class);
       Curso curso = mapper.toCurso(cursoRequestDTO);
        cursoRepository.save(curso);
       return mapper.toCursoResponseDTO(curso);
    }

    @Override
    public List<CursoResponseDTO> listarCursos() {
        CursoMapper mapper = Mappers.getMapper(CursoMapper.class);
        return cursoRepository.findAll().stream().map(mapper::toCursoResponseDTO).toList();
    }
}
