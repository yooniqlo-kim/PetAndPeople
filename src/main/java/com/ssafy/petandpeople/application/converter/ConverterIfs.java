package com.ssafy.petandpeople.application.converter;

public interface ConverterIfs<DTO, DOMAIN, ENTITY> {

    DOMAIN dtoToDomain(DTO dto);

    DTO entityToDto(ENTITY entity);

    ENTITY dtoToEntity(DTO dto);

    ENTITY domainToEntity(DOMAIN domain);

}
