package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tradable.examples.dto.serializers.OrderSideDeserializer;
import com.tradable.examples.dto.serializers.OrderSideSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A request to trade and open a new position with a market order. Crates Market orders
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class NewTradeOrderRequestDTO
{
    /*
    The unique identifier for a market.
     */
    private int MarketId;
    /**
     * Currency to place order in.
     */
    private String Currency;
    /**
     * Flag to indicate whether the trade will automatically roll into the next market interval when the current market interval expires. Only applies to markets where the underlying is a futures contract.
     */
    private boolean AutoRollover;
    /**
     * Direction identifier for trade, values supported are buy or sell.
     */
    @JsonDeserialize(using = OrderSideDeserializer.class)
    @JsonSerialize(using = OrderSideSerializer.class)
    protected JSONOrderSide Direction;
    /**
     * Size of the order/trade.
     */
    private double Quantity;
    /**
     * The unique quote identifier.
     */
    private Integer QuoteId;
    /**
     * Indicates the position of the trade. 1 == LongOrShortOnly, 2 == LongAndShort.
     */
    private Integer PositionMethodId;

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
     * List of existing open trade order IDs that require part or full closure.
     */
    private List<Integer> Close;
    /**
     * 	A reference code to identify the source origin of the trade order request. API calls should use the string: CIAPI.
     */
    private String Reference = "CIAPI";
    /**
     * ID of the allocation profile to use if this is a Trading Advisor trade.
     */
    private int AllocationProfileId;
    /**
     * The order reference for this new trade request - only applicable where source is set. (Optional).
     */
    private String OrderReference;
    /**
     * 	The source of the trade order request. (Optional).
     */
    private String Source;
    /**
     * Sets the amount of slippage you are willing to accept to get the trade executed. Values are in the range 0 - 1000.
     */
    private Integer PriceTolerance;
}
