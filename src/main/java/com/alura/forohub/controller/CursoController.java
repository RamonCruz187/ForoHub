package com.alura.forohub.controller;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import com.alura.forohub.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Cursos", description = "Permite crear y listar cursos.")
@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;


    @Operation(
            summary = "Crear un nuevo curso.",
            description = "Permite a un usuario la creación de un nuevo curso. Se le puede poner cualquier nombre al curso, pero las categorías son predefinidas. Estas son frontend, backend, mobile, data_science, cloud y devops."
    )
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

    @Operation(
            summary = "Listar todos los cursos.",
            description = "Permite a un usuario listar todos los cursos de manera paginada."
    )
    @GetMapping("/listar")
    public ResponseEntity<Page<CursoResponseDTO>> listarCursos(Pageable pageable){
        return ResponseEntity
                .status(200)
                .body(cursoService.listarCursos(pageable));
    }
}
