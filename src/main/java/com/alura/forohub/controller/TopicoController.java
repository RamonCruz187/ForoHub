package com.alura.forohub.controller;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.service.TopicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping("/nuevo")
    ResponseEntity<TopicoResponseDTO> nuevoTopico(@RequestBody TopicoRequestDTO topicoRequestDTO) {
        System.out.println(topicoRequestDTO);
        return ResponseEntity.ok(topicoService.nuevoTopico(topicoRequestDTO));
    }

    @GetMapping("/listar")
    ResponseEntity<List<TopicoResponseDTO>> listarTopicos() {

        return ResponseEntity.ok(topicoService.listarTopicos());
    }
}
