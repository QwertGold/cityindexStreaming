package com.tradable.examples.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 16/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiMarketSpreadDTO {
    /**
     * The time and date in for the spread value in UTC, interchangeable to local time using local time offset.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant SpreadTimeUtc;
    /**
     * The market spread value.
     */
    private double Spread;
    /**
     * The market spread value's unit type, where Percentage == 26, and Points = 27.
     */
    private int SpreadUnits;

}
