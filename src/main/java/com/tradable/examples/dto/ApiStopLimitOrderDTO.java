package com.tradable.examples.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Represents a stop/limit order.
 *
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ApiStopLimitOrderDTO extends ApiOrderDto {
    /**
     * Flag to determine whether an order is guaranteed to trigger and fill at the associated trigger price.
     */
    private boolean Guaranteed;
    /**
     * Price at which the order should be triggered.
     */
    private double TriggerPrice;
    /**
     * The associated expiry DateTime for a pair of GoodTillDate If/Done orders.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant ExpiryDateTimeUTC;
    /**
     * Identifier which relates to the expiry of the order/trade, i.e. GoodTillDate (GTD), GoodTillCancelled (GTC) or GoodForDay (GFD).
     */
    private String Applicability;
    /**
     * This represents the OrderID of any orders that the current order is related to. If there is no parent order as the order in question is the parent order, then the value is null.
     */
    private Integer ParentOrderId;

    @Undocumented
    private Double TrailingDistance;


}
