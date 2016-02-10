package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIOrderStatus;
import com.tradable.examples.dto.serializers.OrderStatusDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 30/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiTradeOrderResponseDTO {
    /**
     * The status of the order (Pending, Accepted, Open, etc.). The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderStatusDeserializer.class)
    private CIOrderStatus Status;
    /**
     * The ID corresponding to a more descriptive reason for the order status. The table of lookup codes can be found at Lookup Values.
     */
    private int StatusReason;
    /**
     * The unique identifier associated to the order returned from the underlying trading system.
     */
    private int OrderId;
    /**
     * List of orders with their associated response.
     */
    private List<ApiOrderResponseDTO> Orders;
    /**
     * Quote response.
     */
    private ApiQuoteResponseDTO Quote;
    /**
     * List of order actions with their associated response.
     */
    private List<ApiOrderActionResponseDTO> Actions;
    /**
     * Contains the error message - this is only used when the Status = -1.
     */
    private String ErrorMessage;

}
