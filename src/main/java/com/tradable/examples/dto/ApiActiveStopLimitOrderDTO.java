package com.tradable.examples.dto;

import java.time.Instant;

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
 *         Created 27/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)

public class ApiActiveStopLimitOrderDTO {
    /**
     * The order's unique identifier.
     */
    private int OrderId;
    /**
     * This represents the OrderID of any orders that the current order is related to. If there is no parent order as the order in question is the parent order, then the value is null.
     */
    private Integer ParentOrderId;
    /**
     * The markets unique identifier.
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
    JSONOrderSide Direction;
    /**
     * The quantity of the product.
     */
    private double Quantity;
    /**
     * The marked to market price at which the order will trigger at.
     */
    private double TriggerPrice;
    /**
     * The trading account that the order is on.
     */
    private int TradingAccountId;
    /**
     * The type of order, i.e. stop or limit.
     */
    private int Type;
    /**
     * The time period that the order remains in force.
     Good Till Date (GTD)	1
     Good Till Cancelled (GTC)	2
     Good For Day (GFD)	3
     */
    private int Applicability;
    /**
     * The associated expiry DateTime.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant ExpiryDateTimeUTC;
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
     * The if / done stop order.
     */
    private ApiBasicStopLimitOrderDTO StopOrder;
    /**
     * The if / done limit order.
     */
    private ApiBasicStopLimitOrderDTO LimitOrder;
    /**
     * The order on the other side of a One Cancels the Other relationship.
     */
    private ApiBasicStopLimitOrderDTO OcoOrder;
    /**
     * Represents the date and time when the trade/order was last edited. Note: does not include things such as the current market price.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant LastChangedDateTimeUTC;



}
