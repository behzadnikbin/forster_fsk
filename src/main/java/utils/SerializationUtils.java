package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dto.FskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.SortedMap;


public class SerializationUtils {
    public static final SerializationUtils instance = new SerializationUtils();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private SerializationUtils() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<FskDto> deserializeFromFile(InputStream stream) throws IOException {
        return objectMapper.readValue(stream, new TypeReference<List<FskDto>>() {
        });
    }

    public String serializeToSting(SortedMap<String, List<FskDto>> map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

    public void serializeToStream(SortedMap<String, List<FskDto>> map, OutputStream outputStream) throws IOException {
        objectMapper.writeValue(outputStream, map);
    }
}
