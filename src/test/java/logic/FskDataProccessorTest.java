package logic;

import dto.FskDto;
import org.junit.Assert;
import org.junit.Test;
import utils.TestUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import static org.junit.Assert.*;

public class FskDataProccessorTest {

    @Test
    public void getFskGroupByLevelFacet() {
        try {
            List<FskDto> list = TestUtils.loadFskListFromResource();
            SortedMap<String, List<FskDto>> map = FskDataProccessor.instance.getFskGroupByLevelFacet(list);
            Assert.assertNotNull(map);
            Assert.assertEquals("Invalid result size", 2, map.size());

            List<FskDto> fsf12 = map.get("FSF12");
            Assert.assertNotNull("FSF12 not found in key set", fsf12);
            Assert.assertEquals("Invalid FSF12 grouped size", 2, fsf12.size());

            FskDto fsf12_0 = fsf12.get(0);
            Assert.assertEquals("Invalid asset_id for FSF12[0]", 3443586L, (long) fsf12_0.getAsset_id());
            Assert.assertEquals("Invalid title for FSF12[0]", "The Kennedys", fsf12_0.getTitle());
            Assert.assertEquals("Invalid production_year for FSF12[0]", 2012, (int) fsf12_0.getProduction_year());

            FskDto fsf12_1 = fsf12.get(1);
            Assert.assertEquals("Invalid asset_id for FSF12[1]", 9676465L, (long) fsf12_1.getAsset_id());
            Assert.assertEquals("Invalid title for FSF12[1]", "Charmed - Zauberhafte Hexen", fsf12_1.getTitle());
            Assert.assertEquals("Invalid production_year for FSF12[1]", 2005, (int) fsf12_1.getProduction_year());

            List<FskDto> fsKfrei = map.get("FSKfrei");

            FskDto fsKfrei_0 = fsKfrei.get(0);
            Assert.assertEquals("Invalid asset_id for FSKfrei[0]", 9676465L, (long) fsKfrei_0.getAsset_id());
            Assert.assertEquals("Invalid title for FSKfrei[0]", "Charmed - Zauberhafte Hexen", fsKfrei_0.getTitle());
            Assert.assertEquals("Invalid production_year for FSKfrei[0]", 2005, (int) fsKfrei_0.getProduction_year());

        } catch (IOException e) {
            Assert.fail("Sample json file not found! " + e.toString());
        }
    }
}