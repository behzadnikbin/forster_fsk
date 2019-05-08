package ir.behzadnikbin.forster_fsk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


/**
 * A DTO containing FSK data
 * It just contains necessary fields
 *
 * @author Behzad
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)      //  Do not serialize null fields
@JsonIgnoreProperties(ignoreUnknown = true)     //  Do not fail deserialization if there are unknown fields in json
@EqualsAndHashCode(of = "asset_id")             //  asset_id is the unique identifier of FskDto
public class FskDto {
    private Long asset_id;
    private List<String> fsk_level_list_facet;
    private String title;
    private Integer production_year;
}
