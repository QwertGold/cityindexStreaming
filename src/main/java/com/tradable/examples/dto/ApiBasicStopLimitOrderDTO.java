package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Partial order information for a protection order. Intended to be enough to display in a grid. If you require the full order information
 * such as expiration you have to find it in the active orders
 *
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiBasicStopLimitOrderDTO {
    /**
     * Flag indicating if the stop order is a guaranteed order.
     */
    private boolean Guaranteed;
    /**
     * The order's unique identifier.
     */
    private int OrderId;
    /**
     * The order's trigger price.
     */
    private double TriggerPrice;
    /**
     * The quantity of the product.
     */
    private double Quantity;

    @Undocumented
    private Double TrailingDistance;

}
