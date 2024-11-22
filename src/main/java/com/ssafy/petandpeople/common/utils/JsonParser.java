package com.ssafy.petandpeople.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.petandpeople.application.dto.adoption.ErrorResponseDto;
import com.ssafy.petandpeople.common.exception.api.JsonToDtoMappingException;
import com.ssafy.petandpeople.common.exception.api.ExtractJsonFailException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ErrorResponseDto extractErrorCode(String response) {
        try {
            JsonNode resultNode = objectMapper.readTree(response).path("RESULT");

            if (!resultNode.isMissingNode()) {
                String errorCode = resultNode.path("CODE").asText();
                String errorMessage = resultNode.path("MESSAGE").asText();

                return new ErrorResponseDto(errorCode, errorMessage);
            }

            return null;
        } catch (Exception e) {
            throw new ExtractJsonFailException(e.getMessage());
        }
    }

    public String extractData(String response, String rootpath) {
        try {
            return objectMapper.readTree(response)
                    .at(rootpath)
                    .toString()
                    .toLowerCase();
        } catch (Exception e) {
            throw new ExtractJsonFailException(e.getMessage());
        }
    }

    public <T> List<T> mapToDtoList(String jsonResponse, TypeReference<List<T>> typeReference) {
        try {
            return objectMapper.readValue(jsonResponse, typeReference);
        } catch (Exception e) {
            throw new JsonToDtoMappingException(e.getMessage());
        }
    }

    public <T> T convertToType(Object value, TypeReference<T> typeReference) {
        return objectMapper.convertValue(value, typeReference);
    }

}
