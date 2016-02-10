package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Represents a trade order.
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApiTradeOrderDTO extends ApiOrderDto {
    /**
     * The list of constituent trades for Trading Advisor managed positions (if applicable).
     */
    private List<ApiManagedTradeDTO> ManagedTrades;
}
