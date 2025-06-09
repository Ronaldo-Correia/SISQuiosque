package com.ifba.SISQuiosque.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.ifba.SISQuiosque.dto.UsuarioDTO;
import com.ifba.SISQuiosque.entity.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioEntity toEntity(UsuarioDTO dto);
}