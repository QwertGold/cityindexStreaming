package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiIfDoneDTO {
    /**
     * The price at which the stop order will be filled.
     */
    private ApiStopLimitOrderDTO Stop;
    /**
     * The price at which the limit order will be filled.
     */
    private ApiStopLimitOrderDTO Limit;
}
