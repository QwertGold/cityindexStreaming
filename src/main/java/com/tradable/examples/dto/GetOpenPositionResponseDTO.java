package com.tradable.examples.dto;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Response containing the open position information.
 * @author Klaus Groenbaek
 *         Created 14/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class GetOpenPositionResponseDTO {
    /**
     * The open position information. If it is null then the open position does not exist.
     */
    private ApiOpenPositionDTO OpenPosition;

}
