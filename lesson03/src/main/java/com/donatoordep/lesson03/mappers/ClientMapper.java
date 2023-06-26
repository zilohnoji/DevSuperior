package com.donatoordep.lesson03.mappers;

import com.donatoordep.lesson03.dto.ClientDTO;
import com.donatoordep.lesson03.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toEntity(ClientDTO dto);
    ClientDTO toDto(Client entity);
}
