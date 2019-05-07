package logic;

import dto.FskDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FskDataProcessor {
    public static final FskDataProcessor instance = new FskDataProcessor();

    public SortedMap<String, List<FskDto>> getFskGroupByLevelFacet(@NonNull List<FskDto> list) {
        SortedMap<String, List<FskDto>> res = new TreeMap<>();
        for (FskDto fskDto : list) {
            List<String> levels = fskDto.getFsk_level_list_facet();
            if (levels == null) {
                continue;
            }
            for (String level : levels) {
                List<FskDto> levelFsks = res.computeIfAbsent(level, k -> new ArrayList<>());
                levelFsks.add(fskDto);
            }
            fskDto.setFsk_level_list_facet(null);       //  this field is not required any more
        }
        return res;
    }
}
