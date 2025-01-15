package com.alura.forohub.mapper;

import com.alura.forohub.dto.RespuestaRequestDTO;
import com.alura.forohub.dto.RespuestaResponseDTO;
import com.alura.forohub.model.Respuesta;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RespuestaMapper {

    Respuesta toRespuesta(RespuestaRequestDTO respuestaRequestDTO);

    @Mapping(source = "topico", target = "topico", qualifiedByName = "stringToTopico")
    @Mapping(source = "user", target = "autor", qualifiedByName = "stringToUser")
    RespuestaResponseDTO toRespuestaResponseDTO(Respuesta respuesta);

    @Named("stringToTopico")
    default Long stringToTopico(Topico topico) {
        return topico.getId();
    }

    @Named("stringToUser")
    default Long stringToUser(User user) {
        return user.getId();
    }
}
