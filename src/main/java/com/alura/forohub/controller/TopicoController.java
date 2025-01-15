package com.alura.forohub.controller;

import com.alura.forohub.dto.topicos.TopicoConRespuestasDTO;
import com.alura.forohub.dto.topicos.TopicoRequestDTO;
import com.alura.forohub.dto.topicos.TopicoResponseDTO;
import com.alura.forohub.dto.topicos.TopicoToUpdateDTO;
import com.alura.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Tag(name = "Tópicos", description = "Permite crear, listar un tópico, listar un tópico con la lista de respuestas, listar todos los tópicos, editar y eliminar tópicos.")
@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @Operation(
            summary = "Crear un nuevo tópico.",
            description = "Permite a un usuario la creación de un nuevo tópico."
    )
    @PostMapping("/nuevo")
    public ResponseEntity<TopicoResponseDTO> nuevoTopico(@RequestBody @Valid TopicoRequestDTO topicoRequestDTO, UriComponentsBuilder uriComponentsBuilder) {
        TopicoResponseDTO topicoResponseDTO = topicoService.nuevoTopico(topicoRequestDTO);
        URI uri = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topicoResponseDTO.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(topicoResponseDTO);
    }

    @Operation(
            summary = "Listar todos los tópicos.",
            description = "Permite a un usuario listar todos los tópicos de manera paginada, ordenados por título y cada página contiene 10 tópicos ."
    )
    @GetMapping("/listar")
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable pageable) {

        return ResponseEntity
                .status(200)
                .body(topicoService.listarTopicos(pageable));
    }

    @Operation(
            summary = "Obtener un tópico.",
            description = "Permite a un usuario la obtención de un tópico especifico mediante su id."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> obtenerTopico(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(topicoService.obtenerTopico(id));
    }

    @Operation(
            summary = "Listar un tópico con la lista de respuestas.",
            description = "Permite a un usuario la obtención de un tópico especifico con la lista de respuestas mediante su id."
    )
    @GetMapping("/{id}/respuestas")
    public ResponseEntity<TopicoConRespuestasDTO> TopicoConRespuestas(@PathVariable Long id) {

        return ResponseEntity
                .status(200)
                .body(topicoService.TopicoConRespuestas(id));
    }

    @Operation(
            summary = "Editar un tópico.",
            description = "Permite a un usuario la edición de un tópico mediante su id."
    )
    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> editarTopico(@PathVariable Long id, @RequestBody @Valid TopicoToUpdateDTO topicoToUpdateDTO) {
        return ResponseEntity
                .status(200)
                .body(topicoService.editarTopico(id, topicoToUpdateDTO));
    }

    @Operation(
            summary = "Eliminar un tópico.",
            description = "Permite a un usuario la eliminación de un tópico mediante su id."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(topicoService.eliminarTopico(id));
    }
}
