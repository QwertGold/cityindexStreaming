package com.tradable.examples.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A constituent order of a Trading Advisor managed position.
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiManagedTradeDTO {
    /**
     * The unique identifier for the order.
     */
    private int OrderId;
    /**
     * The order size or amount.
     */
    private double Quantity;
    /**
     * The unique identifer for the managed trading account that the order is being placed on.
     */
    private int TradingAccountId;
    /**
     * The code of the managed trading account that the order is being placed on.
     */
    private String TradingAccountCode;
    /**
     * The name of the managed trading account that the order is being placed on.
     */
    private String TradingAccountName;
    /**
     * Represents the date and time when the order was last changed. Note: does not include things such as the current market price.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant LastChangedDateTimeUTC;


}
