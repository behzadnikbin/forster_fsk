package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dto.FskDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.SortedMap;


/**
 * A singleton utility class to deserialize json file to DTOs and serialize DTOs to json file
 *
 * @author Behzad
 */
public class SerializationUtils {

    public static final SerializationUtils instance = new SerializationUtils();     //  single instance;

    private final ObjectMapper objectMapper = new ObjectMapper();       //  JSON serializer/deserializer

    /**
     * private default constructor
     */
    private SerializationUtils() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);        //  enable indent for json serialization
    }

    /**
     * This function is used to deserialize input stream to {@link List} of FSK data
     *
     * @param stream input stream as {@link InputStream} ({@link java.io.FileInputStream FileInputStream} usually)
     * @return {@link List} of {@link FskDto}
     * @throws IOException if there is a problem reading from stream, or deserializing JSON
     */
    public List<FskDto> deserializeFromFile(InputStream stream) throws IOException {
        return objectMapper.readValue(stream, new TypeReference<List<FskDto>>() {
        });
    }

    /**
     * This function is used to serialize grouped FSK data to json string
     *
     * @param map {@link java.util.Map Map} of level facet to {@link List} of related {@link FskDto}
     * @return JSON result as {@link String}
     * @throws JsonProcessingException if there is a problem serializing to string
     */
    public String serializeToSting(SortedMap<String, List<FskDto>> map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

    /**
     * This function is used to serialize grouped {@link FskDto} to json as {@link OutputStream}
     *
     * @param map          {@link java.util.Map Map} of level facet to {@link List} of related {@link FskDto}s
     * @param outputStream {@link OutputStream} to write JSON result (usually {@link java.io.FileOutputStream FileOutputStream})
     * @throws IOException if there is a problem writing to output stream or serializing JSON
     */
    public void serializeToStream(SortedMap<String, List<FskDto>> map, OutputStream outputStream) throws IOException {
        objectMapper.writeValue(outputStream, map);
    }
}
