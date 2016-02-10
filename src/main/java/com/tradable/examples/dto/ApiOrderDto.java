package com.tradable.examples.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIOrderStatus;
import com.tradable.examples.dto.serializers.OrderSideDeserializer;
import com.tradable.examples.dto.serializers.OrderStatusDeserializer;
import com.tradable.examples.dto.serializers.OrderTypeDeserializer;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents an order.
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiOrderDto {
    /**
     * The order identifier.
     */
    protected int OrderId;
    /**
     * Flag to indicate whether the trade will automatically roll into the next market interval when the current market interval expires. Only applies to markets where the underlying is a futures contract.
     */
    protected boolean AutoRollover;
    /**
     * A market's unique identifier
     */
    protected int MarketId;
    /**
     * Direction identifier for trade, values supported are buy or sell.
     */
    @JsonDeserialize(using = OrderSideDeserializer.class)
    protected JSONOrderSide Direction;
    /**
     * Size of the order.
     */
    protected double Quantity;
    /**
     * The price at which the order was filled.
     */
    protected Double Price;
    /**
     * The ID of the Trading Account associated with the order.
     */
    protected int TradingAccountId;
    /**
     * Currency ID for order (as represented in the trading system).
     */
    protected int CurrencyId;
    /**
     * Status ID of order (as represented in the trading system). The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderStatusDeserializer.class)
    protected CIOrderStatus StatusId;
    /**
     * The type of the order, Trade, stop or limit. The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderTypeDeserializer.class)
    protected JSONOrderType TypeId;
    /**
     * List of If/Done Orders which will be filled when the initial order is triggered.
     */
    protected List<ApiIfDoneDTO> IfDone;
    /**
     * Corresponding OCO Order (One Cancels the Other)
     */
    protected ApiStopLimitOrderDTO OcoOrder;

    @Undocumented
    private String Reference;

    @Undocumented
    private String OrderReference;

    @Undocumented
    private Double OriginalQuantity;

    @Undocumented
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant LastChangedDateTimeUTC;

    @Undocumented
    private Integer AllocationProfileId;

    @Undocumented
    private String AllocationProfileName;

    @Undocumented
    private String Source;

}
