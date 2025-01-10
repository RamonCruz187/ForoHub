package com.alura.forohub.controller;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import com.alura.forohub.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping("/nuevo")
    public ResponseEntity<CursoResponseDTO> nuevoCurso(@RequestBody CursoRequestDTO cursoRequestDTO){
        return ResponseEntity.ok(cursoService.nuevoCurso(cursoRequestDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CursoResponseDTO>> listarCursos(){
        return ResponseEntity.ok(cursoService.listarCursos());
    }
}
