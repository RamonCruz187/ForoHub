package com.alura.forohub.controller;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import com.alura.forohub.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/nuevo")
    public ResponseEntity<CursoResponseDTO> nuevoCurso(@RequestBody @Valid CursoRequestDTO cursoRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        CursoResponseDTO cursoResponseDTO = cursoService.nuevoCurso(cursoRequestDTO);
        URI uri = uriComponentsBuilder
                .path("/cursos/{id}")
                .buildAndExpand(cursoResponseDTO.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(cursoResponseDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<CursoResponseDTO>> listarCursos(Pageable pageable){
        return ResponseEntity
                .status(200)
                .body(cursoService.listarCursos(pageable));
    }
}
