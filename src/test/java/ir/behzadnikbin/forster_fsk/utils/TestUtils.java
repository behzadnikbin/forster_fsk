package ir.behzadnikbin.forster_fsk.utils;

import ir.behzadnikbin.forster_fsk.dto.FskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * This is a utility class for tests
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {

    /**
     * The function loads {@link List} of {@link FskDto} data from resource json file
     *
     * @return {@link List} of {@link FskDto}
     * @throws IOException if there is a problem loading or deserializing json file to {@link List} of {@link FskDto}
     */
    public static List<FskDto> loadFskListFromResource() throws IOException {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        InputStream stream = classLoader.getResourceAsStream("fsk_sample_file.json");
        List<FskDto> list = SerializationUtils.instance.deserializeFromFile(stream);
        if (stream != null) {
            stream.close();
        }
        return list;
    }
}
