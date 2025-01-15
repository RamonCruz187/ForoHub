package com.alura.forohub.mapper;

import com.alura.forohub.dto.AutorResponseDTO;
import com.alura.forohub.dto.topicos.RespuestasParaTopico;
import com.alura.forohub.dto.topicos.TopicoConRespuestasDTO;
import com.alura.forohub.dto.topicos.TopicoRequestDTO;
import com.alura.forohub.dto.topicos.TopicoResponseDTO;
import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Respuesta;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.User;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicoMapper {

    Topico toTopico(TopicoRequestDTO topicoRequestDTO);

    @Mapping(source = "curso", target = "curso", qualifiedByName = "CursoToString")
    @Mapping(source = "user", target = "autor", qualifiedByName = "stringToUser")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    TopicoResponseDTO toTopicoResponseDTO(Topico topico);

    @Mapping(source = "curso", target = "curso", qualifiedByName = "CursoToString")
    @Mapping(source = "user", target = "autor", qualifiedByName = "stringToUser")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    @Mapping(source = "respuestas", target = "respuestas", qualifiedByName = "respuestasParaTopico")
    TopicoConRespuestasDTO toTopicoConRespuestasDTO(Topico topico);

    @Named("CursoToString")
    default String CursoToString(Curso curso) {
        return curso.getNombre();
    }

    @Named("stringToUser")
    default Long stringToUser(User user) {
        return user.getId();
    }


    @Named("statusToString")
    default String stringToString(Boolean status) {
        if (status) {
            return "Tópico abierto";
        } else {
            return "Tópico cerrado";
        }
    }

    @Named("respuestasParaTopico")
    default List<RespuestasParaTopico> respuestasParaTopico(List<Respuesta> respuestas) {
        return respuestas.stream()
                .map(respuesta -> new RespuestasParaTopico(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getFechaCreacion(),
                        new AutorResponseDTO(respuesta.getUser())
                ))
                .collect(Collectors.toList());
    }
}
