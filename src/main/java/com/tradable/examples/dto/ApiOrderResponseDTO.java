package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIOrderStatus;
import com.tradable.examples.dto.serializers.OrderStatusDeserializer;
import com.tradable.examples.dto.serializers.OrderTypeDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response to an order request.
 * @author Klaus Groenbaek
 *         Created 30/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiOrderResponseDTO {
    /**
     * Order ID.
     */
    private int OrderId;
    /**
     * Order status reason ID. The table of lookup codes can be found at Lookup Values.
     * There are ~160 different reasons
     */
    private int StatusReason;
    /**
     * Order status ID. The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderStatusDeserializer.class)
    private CIOrderStatus Status;
    /**
     * Order type ID. The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = OrderTypeDeserializer.class)
    private JSONOrderType OrderTypeId;
    /**
     * Order fill price.
     */
    private double Price;
    /**
     * Order quantity
     */
    private double Quantity;
    /**
     * Trigger price, if applicable
     */
    private double TriggerPrice;
    /**
     * Commission charge.
     */
    private double CommissionCharge;
    /**
     * List of If/Done orders.
     */
    private List<ApiIfDoneResponseDTO> IfDone;
    /**
     * Premium for guaranteed orders.
     */
    private double GuaranteedPremium;
    /**
     * An order in an OCO relationship with this order.
     */
    private ApiOrderResponseDTO OCO;

}
