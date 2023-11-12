package org.but.eloryksauthorization.newbackend.mappers;

import org.but.eloryksauthorization.newbackend.api.PositionDTO;
import org.but.eloryksauthorization.newbackend.data.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    @Mapping(source = "id", target = "position_id")
    PositionDTO toDTO(Position entity);

    @Mapping(source = "position_id", target = "id")
    Position toEntity(PositionDTO dto);

    List<PositionDTO> toDTOList(List<Position> entities);

    List<Position> toEntityList(List<PositionDTO> dtos);
}

