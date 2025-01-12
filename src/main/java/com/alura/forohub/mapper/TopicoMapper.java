package com.alura.forohub.mapper;

import com.alura.forohub.dto.TopicoRequestDTO;
import com.alura.forohub.dto.TopicoResponseDTO;
import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicoMapper {

    Topico toTopico(TopicoRequestDTO topicoRequestDTO);

    @Mapping(source = "curso", target = "curso", qualifiedByName = "CursoToString")
    @Mapping(source = "user", target = "autor", qualifiedByName = "stringToUser")
    TopicoResponseDTO toTopicoResponseDTO(Topico topico);

    @Named("CursoToString")
    default String CursoToString(Curso curso) {
        return curso.getNombre();
    }

    @Named("stringToUser")
    default Long stringToUser(User user) {
        return user.getId();
    }
}
