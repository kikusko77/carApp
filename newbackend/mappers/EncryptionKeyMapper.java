package org.but.eloryksauthorization.newbackend.mappers;

import org.but.eloryksauthorization.newbackend.api.EncryptionKeyDTO;
import org.but.eloryksauthorization.newbackend.data.entity.EncryptionKey;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EncryptionKeyMapper {

    EncryptionKeyDTO toDTO(EncryptionKey entity);

    EncryptionKey toEntity(EncryptionKeyDTO dto);

    List<EncryptionKeyDTO> toDTOList(List<EncryptionKey> entities);

    List<EncryptionKey> toEntityList(List<EncryptionKeyDTO> dtos);
}
