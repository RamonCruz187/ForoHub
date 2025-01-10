package com.alura.forohub.mapper;

import com.alura.forohub.dto.CursoRequestDTO;
import com.alura.forohub.dto.CursoResponseDTO;
import com.alura.forohub.model.Categoria;
import com.alura.forohub.model.Curso;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CursoMapper {

    @Mapping(source = "categoria", target = "categoria", qualifiedByName = "stringToCategoria")
    Curso toCurso(CursoRequestDTO cursoRequestDTO);

    @Named("stringToCategoria")
    default Categoria stringToCategoria(String categoria) {
        if (categoria == null) {
            return null;
        }
        return Categoria.valueOf(categoria.toUpperCase()); // Convierte el String a mayúsculas para coincidir con el enum
    }

    CursoResponseDTO toCursoResponseDTO(Curso curso);
}
