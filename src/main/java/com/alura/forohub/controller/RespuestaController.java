package com.alura.forohub.controller;


import com.alura.forohub.dto.RespuestaRequestDTO;
import com.alura.forohub.dto.RespuestaResponseDTO;
import com.alura.forohub.service.RespuestaService;
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

@Tag(name = "Respuestas", description = "Permite crear y listar respuestas")
@RestController
@RequestMapping("/respuestas")
@RequiredArgsConstructor
public class RespuestaController {

    private final RespuestaService respuestaService;

    @Operation(
            summary = "Crear una nueva respuesta.",
            description = "Permite a un usuario la creación de una nueva respuesta."
    )
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

    @Operation(
            summary = "Listar todas las respuestas.",
            description = "Permite a un usuario la obtención de todas las respuestas de manera paginada."
    )
    @GetMapping("/listar")
    public ResponseEntity<Page<RespuestaResponseDTO>> listarRespuestas(Pageable pageable) {
        return ResponseEntity
                .status(200)
                        .body(respuestaService.listarRespuestas(pageable));
    }
}
