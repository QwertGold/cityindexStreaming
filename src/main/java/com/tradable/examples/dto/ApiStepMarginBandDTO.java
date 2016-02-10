package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents a single step margin band as expressed by the TradingApi.
 * @author Klaus Groenbaek
 *         Created 16/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiStepMarginBandDTO {
    /**
     * Minimum threshold for this band, expressed in terms of the client's trade quantity.
     */
    private double LowerBound;
    /**
     * The margin factor that is used for this band. It can be expressed as a percentage or in points based on the market's margin factor type.
     */
    private double MarginFactor;

}
