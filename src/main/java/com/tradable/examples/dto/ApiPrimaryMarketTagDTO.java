package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Market tag information extended to include a list of child tags.
 * Top level node in the CityIndex Tag tree that groups instrument
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ApiPrimaryMarketTagDTO extends ApiMarketTagDTO {
    /**
     * The list of child tags associated with this market tag.
     */
    private List<ApiMarketTagDTO> Children;
}
