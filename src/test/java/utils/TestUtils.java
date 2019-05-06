package utils;

import dto.FskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestUtils {
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
