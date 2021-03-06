package ir.behzadnikbin.forster_fsk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.behzadnikbin.forster_fsk.dto.FskDto;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Test class for serialization/deserialization
 *
 * @author Behzad
 */
public class SerializationUtilsTest {
    @Test
    public void deserializeFromFile() {
        try {
            List<FskDto> list = TestUtils.loadFskListFromResource();
            //  Check list
            Assert.assertNotNull("Deserialized list is null", list);
            Assert.assertEquals("Invalid list size", 2, list.size());

            FskDto fsk0 = list.get(0);

            //  Check 1st item of list
            Assert.assertNotNull("Null asset_id for item[0]", fsk0.getAsset_id());
            Assert.assertEquals("Invalid asset_id for item[0]", 3443586L, (long) fsk0.getAsset_id());

            Assert.assertNotNull("Null title for item[0]", fsk0.getTitle());
            Assert.assertEquals("Invalid title for item[0]", "The Kennedys", fsk0.getTitle());

            Assert.assertNotNull("Null production_year for item[0]", fsk0.getProduction_year());
            Assert.assertEquals("Invalid production_year for item[0]", 2012, (int) fsk0.getProduction_year());

            List<String> fskLevelListFacet0 = fsk0.getFsk_level_list_facet();
            Assert.assertNotNull("Null fsk_level_list_facet for item[0]", fskLevelListFacet0);
            Assert.assertEquals("Invalid fsk_level_list_facet[] size for item[0]", 1, fskLevelListFacet0.size());
            Assert.assertEquals("Invalid fsk_level_list_facet[0] for item[0]", "FSF12", fskLevelListFacet0.get(0));

            FskDto fsk1 = list.get(1);

            //  Check 1st item of list
            Assert.assertNotNull("Null asset_id for item[1]", fsk1.getAsset_id());
            Assert.assertEquals("Invalid asset_id for item[1]", 9676465L, (long) fsk1.getAsset_id());

            Assert.assertNotNull("Null title for item[1]", fsk1.getTitle());
            Assert.assertEquals("Invalid title for item[1]", "Charmed - Zauberhafte Hexen", fsk1.getTitle());

            Assert.assertNotNull("Null production_year for item[1]", fsk1.getProduction_year());
            Assert.assertEquals("Invalid production_year for item[1]", 2005, (int) fsk1.getProduction_year());

            List<String> fskLevelListFacet1 = fsk1.getFsk_level_list_facet();
            Assert.assertNotNull("Null fsk_level_list_facet for item[1]", fskLevelListFacet1);
            Assert.assertEquals("Invalid fsk_level_list_facet[] size for item[1]", 2, fskLevelListFacet1.size());
            Assert.assertEquals("Invalid fsk_level_list_facet[0] for item[1]", "FSKfrei", fskLevelListFacet1.get(0));
            Assert.assertEquals("Invalid fsk_level_list_facet[1] for item[1]", "FSF12", fskLevelListFacet1.get(1));
        } catch (IOException e) {
            Assert.fail("Sample json file not found! " + e.toString());
        }
    }

    @Test
    public void serializeToStingTest() {
        try {
            String str = SerializationUtils.instance.serializeToSting(new TreeMap<String, List<FskDto>>() {{
                put("FSF12", Collections.singletonList(new FskDto(3443586L, null, "The Kennedys", 2012)));
            }});
            Assert.assertEquals(
                    "{\"FSF12\":[{\"asset_id\":3443586,\"title\":\"TheKennedys\",\"production_year\":2012}]}",
                    str.replaceAll("\\s+", "")      //  ignore whitespaces
            );
        } catch (JsonProcessingException e) {
            Assert.fail("Problem serializing map to string " + e.toString());
        }
    }
}
