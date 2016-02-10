package com.tradable.examples.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.OrderSideDeserializer;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Details of a previous trade for use in the Trade History.
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiTradeHistoryDTO {
    /**
     * The order ID.
     */
    private int OrderId;
    /**
     * The orders that are being closed / part closed by this order.
     */
    private List<Integer> OpeningOrderIds;
    /**
     * The market ID.
     */
    private int MarketId;
    /**
     * The name of the market.
     */
    private String MarketName;
    /**
     * The direction of the trade.
     */
    @JsonDeserialize(using = OrderSideDeserializer.class)
    private JSONOrderSide Direction;
    /**
     * The original quantity of the trade, before part closures.
     */
    private double OriginalQuantity;
    /**
     * The current quantity of the trade.
     */
    private double Quantity;
    /**
     * The open price of the trade.
     */
    private double Price;
    /**
     * The Trading Account ID that the order is on.
     */
    private int TradingAccountId;
    /**
     * The trade currency.
     */
    private String Currency;
    /**
     * The realised profit and loss (P&L).
     */
    private Double RealisedPnl;
    /**
     * The realised P&L currency.
     */
    private String RealisedPnlCurrency;
    /**
     * Represents the date and time when the trade/order was last edited. Note: does not include things such as the current market price.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant LastChangedDateTimeUtc;
    /**
     * The time that the order was executed.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant ExecutedDateTimeUtc;
    /**
     * An alternative trade reference.
     */
    private String TradeReference;
    /**
     * The list of constituent trades for Trading Advisor managed positions (if applicable).
     */
    private List<ApiManagedTradeDTO> ManagedTrades;

    @Undocumented
    private String OrderReference;

    @Undocumented
    private String Source;

    @Undocumented
    private boolean IsCloseBy;

}
