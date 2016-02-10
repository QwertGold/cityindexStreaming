package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response containing the order. Only one of the two fields will be populated depending upon the type of order (Trade or Stop / Limit).
 * @author Klaus Groenbaek
 *         Created 14/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class GetOrderResponseDTO {
    /**
     * The details of the order if it is a trade / open position.
     */
    private ApiTradeOrderDTO TradeOrder;
    /**
     * The details of the order if it is a stop limit order.
     */
    private ApiStopLimitOrderDTO StopLimitOrder;

}
