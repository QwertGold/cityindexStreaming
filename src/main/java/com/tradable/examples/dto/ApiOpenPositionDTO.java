package com.tradable.examples.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIOrderStatus;
import com.tradable.examples.dto.serializers.OrderSideDeserializer;
import com.tradable.examples.dto.serializers.OrderStatusDeserializer;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiOpenPositionDTO {
    /**
     * The order's unique identifier.
     */
    private int OrderId;
    /**
     * Flag to indicate whether the trade will automatically roll into the next market interval when the current market interval expires. Only applies to markets where the underlying is a futures contract.
     */
    private boolean AutoRollover;
    /**
     * The market's unique identifier.
     */
    private int MarketId;
    /**
     * The market's name.
     */
    private String MarketName;
    /**
     * The direction, buy or sell.
     */
    @JsonDeserialize(using = OrderSideDeserializer.class)
    private JSONOrderSide Direction;
    /**
     * The quantity of the order.
     */
    private double Quantity;
    /**
     * The price / rate that the trade was opened at.
     */
    private double Price;
    /**
     * The ID of the trading account that the order is on.
     */
    private int TradingAccountId;
    /**
     * The trade currency.
     */
    private String Currency;
    /**
     * The order status. The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderStatusDeserializer.class)
    private CIOrderStatus Status;
    /**
     * The stop order attached to this order. (Only some of the order information is here)
     */
    private ApiBasicStopLimitOrderDTO StopOrder;
    /**
     * The limit order attached to this order.
     */
    private ApiBasicStopLimitOrderDTO LimitOrder;
    /**
     * Represents the date and time when the trade/order was last edited. Note: does not include things such as the current market price.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant LastChangedDateTimeUTC;
    /**
     * An alternative trade reference.
     */
    private String TradeReference;
    /**
     * The list of constituent trades for Trading Advisor managed positions (if applicable).
     */
    private List<ApiManagedTradeDTO> ManagedTrades;

    @Undocumented
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant CreatedDateTimeUTC;

    @Undocumented
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant ExecutedDateTimeUTC;

    @Undocumented
    private Integer AllocationProfileId;

    @Undocumented
    private String AllocationProfileName;




}
