package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.OrderTypeDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents a trade order or a stop/limit order.
 * This represents a pending order, typically it will be a limit/stop order, but it may be a Market order. The type of order depends on the TypeId field
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiActiveOrderDTO {
    /**
     * Represents a trade order.
     * A Trade order is basically a different view on a position.
     */
    private ApiTradeOrderDTO TradeOrder;
    /**
     * Represents a stop/limit order.
     */
    private ApiStopLimitOrderDTO StopLimitOrder;

    /**
     * Indicates the type of the order. 1 = Trade, 2 = Stop, 3 = Limit
     */
    @JsonDeserialize(using = OrderTypeDeserializer.class)
    private JSONOrderType TypeId;

}
