package org.but.eloryksauthorization.newbackend.mappers;

import org.but.eloryksauthorization.newbackend.api.SignKeyDTO;
import org.but.eloryksauthorization.newbackend.data.entity.SignKey;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SignKeyMapper {

    SignKeyDTO toDTO(SignKey entity);

    SignKey toEntity(SignKeyDTO dto);

    List<SignKeyDTO> toDTOList(List<SignKey> entities);

    List<SignKey> toEntityList(List<SignKeyDTO> dtos);
}
