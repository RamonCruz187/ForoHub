package com.alura.forohub.controller;


import com.alura.forohub.dto.RespuestaRequestDTO;
import com.alura.forohub.dto.RespuestaResponseDTO;
import com.alura.forohub.service.RespuestaService;
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
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @PostMapping("/nueva")
    public ResponseEntity<RespuestaResponseDTO> nuevaRespuesta(@RequestBody @Valid RespuestaRequestDTO respuestaRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        RespuestaResponseDTO respuestaResponseDTO = respuestaService.nuevaRespuesta(respuestaRequestDTO);
        URI uri = uriComponentsBuilder
                .path("/respuestas/{id}")
                .buildAndExpand(respuestaResponseDTO.id())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(respuestaResponseDTO);

    }

    @GetMapping("/listar")
    public ResponseEntity<Page<RespuestaResponseDTO>> listarRespuestas(Pageable pageable) {
        return ResponseEntity
                .status(200)
                        .body(respuestaService.listarRespuestas(pageable));

    }
}
