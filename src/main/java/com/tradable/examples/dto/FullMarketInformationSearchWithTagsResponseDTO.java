package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response from a full market information search with tags request. (Includes market information for each market.)
 *
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class FullMarketInformationSearchWithTagsResponseDTO {

    /**
     * The requested list of market information.
     * NOTE: incorrectly named 'Markets' in the documentation
     */
    private List<ApiMarketInformationDTO> MarketInformation;

    /**
     * The requested list of market tags.
     */
    private List<ApiMarketTagDTO> Tags;


}
