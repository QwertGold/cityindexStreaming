package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Contains the response of a ListActiveStopLimitOrders query.
 * @author Klaus Groenbaek
 *         Created 27/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ListActiveStopLimitOrderResponseDTO {
    /**
     * The requested list of active stop / limit orders.
     */
   private List<ApiActiveStopLimitOrderDTO> ActiveStopLimitOrders;
}
