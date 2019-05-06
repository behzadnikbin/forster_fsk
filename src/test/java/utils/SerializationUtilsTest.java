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
            //  Check list
            Assert.assertNotNull("Deserialized list is null", list);
            Assert.assertEquals("Invalid list size", 1, list.size());

            FskDto fskDto = list.get(0);

            //  Check 1st item of list
            Assert.assertNotNull("Null asset_id", fskDto.getAsset_id());
            Assert.assertEquals("Invalid asset_id", 3443586L, (long) fskDto.getAsset_id());

            Assert.assertNotNull("Null title", fskDto.getTitle());
            Assert.assertEquals("Invalid title", "The Kennedys", fskDto.getTitle());

            Assert.assertNotNull("Null production_year", fskDto.getProduction_year());
            Assert.assertEquals("Invalid production_year", 2012, (int) fskDto.getProduction_year());

            List<String> fskLevelListFacet = fskDto.getFsk_level_list_facet();
            Assert.assertNotNull("Null fsk_level_list_facet", fskLevelListFacet);
            Assert.assertEquals("Invalid fsk_level_list_facet[] size", 1, fskLevelListFacet.size());
            Assert.assertEquals("Invalid fsk_level_list_facet[0]", "FSF12", fskLevelListFacet.get(0));
        } catch (IOException e) {
            Assert.fail("Sample json file not found! " + e.toString());
        }
    }
}
