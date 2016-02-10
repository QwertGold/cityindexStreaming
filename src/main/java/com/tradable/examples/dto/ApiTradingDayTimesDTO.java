package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Contains start and end time information for market specific events such as trading and pricing.
 * @author Klaus Groenbaek
 *         Created 16/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiTradingDayTimesDTO {
    /**
     * Day of the week at which the times are valid.
     */
    private int DayOfWeek;
    /**
     * Start of the market time in both UTC and local time (using Offset property).
     */
    private ApiDateTimeOffsetDTO StartTimeUtc;
    /**
     * End of the market time in both UTC and local time (using Offset property).
     */
    private ApiDateTimeOffsetDTO EndTimeUtc;

}
