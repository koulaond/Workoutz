package com.ondrejkoula.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.List;

@Slf4j
public class SingleIntArrayConverter implements AttributeConverter<List<Integer>, String> {

    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(integers);
        } catch (JsonProcessingException e) {
            log.error("Cannot serialize value: {}", integers);
            return null;
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String serialized) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(serialized, List.class);
        } catch (JsonProcessingException e) {
            log.error("Cannot deserialize value: {}", serialized);
            return null;
        }
    }
}
