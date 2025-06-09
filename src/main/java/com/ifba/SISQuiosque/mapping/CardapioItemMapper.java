package com.ifba.SISQuiosque.mapping;

import com.ifba.SISQuiosque.dto.CardapioItemDTO;
import com.ifba.SISQuiosque.entity.CardapioItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CardapioItemMapper {
    CardapioItemMapper INSTANCE = Mappers.getMapper(CardapioItemMapper.class);
    
    CardapioItemDTO toDTO(CardapioItem item);
    
    CardapioItem toEntity(CardapioItemDTO dto);
}