package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiMarketTagDTO {
    /**
     * A unique identifier for this market tag.
     */
    private int MarketTagId;
    /**
     * The market tag description. Can be localised if required.
     */
    private String Name;
    /**
     * Used to determine if the market tag is a primary (1) or secondary (2) tag.
     * Indicated the level in the tree, 1 is top level, 2 is child (leaf node)
     */
    private int Type;

    @Undocumented
    private int Weighting;

}
