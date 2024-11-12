package com.ssafy.petandpeople.application.converter;

public interface RedisConverterIfs<DTO> {

    DTO jsonToDto(String jsonData);

}
