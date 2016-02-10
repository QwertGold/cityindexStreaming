package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Contains the result of a ListTradeHistory query.
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ListTradeHistoryResponseDTO {
    /**
     * A list of historical trades.
     */
    private List<ApiTradeHistoryDTO> TradeHistory;

    /**
     * A list of trades which are referenced as OpenOrderId's in the trade history list - but do not actually exist in that list.
     */
    private List<ApiTradeHistoryDTO> SupplementalOpenOrders;
}
