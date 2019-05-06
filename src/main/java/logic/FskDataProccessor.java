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
public class FskDataProccessor {
    public static final FskDataProccessor instance = new FskDataProccessor();

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
        }
        return res;
    }
}
