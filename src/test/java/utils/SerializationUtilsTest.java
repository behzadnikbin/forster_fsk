package utils;

import dto.FskDto;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SerializationUtilsTest {
    @Test
    public void deserializeFromFile() {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream stream = classLoader.getResourceAsStream("fsk_sample_file.json");
            List<FskDto> list = SerializationUtils.instance.deserializeFromFile(stream);
            if (stream != null) {
                stream.close();
            }
            Assert.assertNotNull("Deserialized list is null", list);
            Assert.assertEquals("Invalid list size", 1, list.size());
        } catch (IOException e) {
            Assert.fail("Sample json file not found! " + e.toString());
        }
    }
}
