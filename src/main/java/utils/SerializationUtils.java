package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.FskDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class SerializationUtils {
    public static final SerializationUtils instance = new SerializationUtils();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private SerializationUtils() {
    }

    public List<FskDto> deserializeFromFile(InputStream stream) throws IOException {
        return objectMapper.readValue(stream, new TypeReference<List<FskDto>>() {
        });
    }
}
