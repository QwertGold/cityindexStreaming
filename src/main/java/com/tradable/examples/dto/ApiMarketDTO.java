package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Basic information about a Market.
 *
 * @author Klaus Groenbaek
 *         Created 30/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiMarketDTO {
    /**
     * A market's unique identifier.
     */
    private int MarketId;
    /**
     * The market name
     */
    private String Name;
    /**
     * Relays the priority of the market in the market search results. The higher the value the more prominent the market should appear.
     */
    private int Weighting;
}
