package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 05/02/16.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UpdateTradeOrderRequestDTO extends NewTradeOrderRequestDTO {

    /**
     *  The identifier of the order to update.
     */
    private int OrderId;


    @Override
    public String toString() {
        return "UpdateTradeOrderRequestDTO{" +
                "OrderId=" + OrderId +
                " " + super.toString() +
                '}';
    }
}
