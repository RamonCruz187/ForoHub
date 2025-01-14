package com.alura.forohub.controller;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.dto.TopicoToUpdateDTO;
import com.alura.forohub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

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

    @GetMapping("/listar")
    public ResponseEntity<Page<TopicoResponseDTO>> listarTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable pageable) {

        return ResponseEntity
                .status(200)
                .body(topicoService.listarTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> obtenerTopico(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(topicoService.obtenerTopico(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> editarTopico(@PathVariable Long id, @RequestBody @Valid TopicoToUpdateDTO topicoToUpdateDTO) {
        return ResponseEntity
                .status(200)
                .body(topicoService.editarTopico(id, topicoToUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(topicoService.eliminarTopico(id));
    }


}
