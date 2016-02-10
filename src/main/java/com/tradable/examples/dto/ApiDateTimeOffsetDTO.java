package com.tradable.examples.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Contains DateTime represented as UTC time and time zone offset in minutes to convert back to local time.
 * @author Klaus Groenbaek
 *         Created 16/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiDateTimeOffsetDTO {
    /**
     * Gets the date and time in UTC.
     */
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant UtcDateTime;
    /**
     * Gets the time's offset from Coordinated Universal Time (UTC).
     */
    private int OffsetMinutes;

}
