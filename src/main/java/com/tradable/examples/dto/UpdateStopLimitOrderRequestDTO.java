package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 18/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UpdateStopLimitOrderRequestDTO extends NewStopLimitOrderRequestDTO {
}
