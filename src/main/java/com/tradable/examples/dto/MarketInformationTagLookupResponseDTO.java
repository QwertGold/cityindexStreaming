package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response from a search request of market information tags. The CityIndex Tag structure is basically a tree, grouping instruments (called markets)
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class MarketInformationTagLookupResponseDTO {
    /**
     * The requested list of market tags
     */
    private List<ApiPrimaryMarketTagDTO> Tags;
}
