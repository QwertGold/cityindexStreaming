package com.tradable.examples.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tradable.examples.dto.enums.CIApplicability;
import com.tradable.examples.dto.serializers.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A request for a stop/limit order. Used when creating or modifying orders
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class NewStopLimitOrderRequestDTO {
    /**
     * The identifier of the order to update.
     */
    private int OrderId;
    /**
     * The unique identifier for the market.
     */
    private int MarketId;
    /**
     * Currency to place order in.
     */
    private String Currency;
    /**
     * Flag to indicate whether the trade will automatically roll into the next market when the current market expires. Only applies to markets where the underlying is a futures contract.
     */
    private boolean AutoRollover;

    /**
     * Direction identifier for trade, values supported are buy or sell.
     */
    @JsonDeserialize(using = OrderSideDeserializer.class)
    @JsonSerialize(using = OrderSideSerializer.class)
    protected JSONOrderSide Direction;
    /**
     * Indicates the position of the trade. 1 == LongOrShortOnly, 2 == LongAndShort.
     */
    private Integer PositionMethodId;
    /**
     * Size of the order/trade.
     */
    private double Quantity;
    /**
     * Market prices are quoted as a pair (buy/sell or bid/offer), the BidPrice is the lower of the two.
     */
    private double BidPrice;
    /**
     * Market prices are quoted as a pair (buy/sell or bid/offer), the OfferPrice is the higher of the market price pair.
     */
    private double OfferPrice;
    /**
     * Unique identifier for each price tick. Read this value from the prices stream. Treat it as a unique but random string.
     */
    private String AuditId;
    /**
     * The ID of the trading account associated with the trade/order request.
     */
    private int TradingAccountId;
    /**
     * List of If/Done Orders that will be filled when the initial trade/order is triggered.
     */
    private List<ApiIfDoneDTO> IfDone;
    /**
     * Corresponding OCO Order (One Cancels the Other) if one has been defined.
     */
    private NewStopLimitOrderRequestDTO OcoOrder;
    /**
     * Identifier which relates to the expiry of the order/trade, i.e. GoodTillDate (GTD), GoodTillCancelled (GTC) or GoodForDay (GFD).
     */
    @JsonDeserialize(using = CIApplicabilityDeserializer.class)
    @JsonSerialize(using = CIApplicabilitySerializer.class)
    private CIApplicability Applicability;
    /**
     * The associated expiry DateTime for a pair of GoodTillDate If/Done orders.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    @JsonSerialize(using = WCFDateSerializer.class)
    private Instant ExpiryDateTimeUTC;
    /**
     * Flag to determine whether an order is guaranteed to trigger and fill at the associated trigger price.
     */
    private boolean Guaranteed;
    /**
     * Price at which the order is intended to be triggered.
     */
    private double TriggerPrice;
    /**
     * Reference identifier for the instruction.
     */
    private String Reference;
    /**
     * ID of the allocation profile to use if the client is a Trading Advisor.
     */
    private int AllocationProfileId;


}
