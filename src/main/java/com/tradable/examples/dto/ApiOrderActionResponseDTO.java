package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIOrderAction;
import com.tradable.examples.dto.serializers.CIOrderActionDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response to an order request.
 * @author Klaus Groenbaek
 *         Created 01/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiOrderActionResponseDTO {
    /**
     * Actioned Order ID.
     */
    private int ActionedOrderId;
    /**
     * Actioning Order ID.
     */
    private int ActioningOrderId;
    /**
     * Quantity.
     */
    private double Quantity;
    /**
     * Profit or Loss.
     */
    private double ProfitAndLoss;
    /**
     * Profit or Loss Currency.
     */
    private String ProfitAndLossCurrency;
    /**
     * Indicates the action performed by the order, for example: partial close, fully close and so on. The table of lookup codes can be found at API Code Lookup Values.
     */
    @JsonDeserialize(using = CIOrderActionDeserializer.class)
    private CIOrderAction OrderActionTypeId;

}
