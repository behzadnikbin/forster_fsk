package logic;

import dto.FskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A singleton class to process FSK data ({@link FskDto})
 *
 * @author Behzad
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)        //  private default constructor
public class FskDataProcessor {

    public static final FskDataProcessor instance = new FskDataProcessor();     //  single instance

    /**
     * This function groups {@link List} of {@link FskDto} by its level facet
     *
     * @param list {@link List} of {@link FskDto}
     * @return {@link java.util.Map Map} of level facet to {@link List} of {@link FskDto} data related to the level facet.
     * It is sorted by keys
     */
    //TODO Map key can be changed to Enum instead of String, but I don't know all the enum items and the correct order
    public SortedMap<String, List<FskDto>> getFskGroupByLevelFacet(@NonNull List<FskDto> list) {
        SortedMap<String, List<FskDto>> res = new TreeMap<>();      //  result to return
        for (FskDto fskDto : list) {
            List<String> levels = fskDto.getFsk_level_list_facet();
            if (levels == null) {       //  ignore empty levels
                continue;
            }
            for (String level : levels) {
                /*
                  check if level exists in res, adds it to res if it does not exist,
                  and returns the value of the given level
                 */
                List<FskDto> levelFsks = res.computeIfAbsent(level, k -> new ArrayList<>());
                levelFsks.add(fskDto);
            }
            fskDto.setFsk_level_list_facet(null);       //  this field is not required any more
        }
        return res;
    }
}
