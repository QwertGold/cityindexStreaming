package com.tradable.examples.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.serializers.WCFDateDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
@Undocumented
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class FxFinancingDTO {
    @JsonDeserialize(using = WCFDateDeserializer.class)
    private Instant CaptureDateTime;
    private int LongPoints;
    private int ShortPoints;
    private int LongCharge;
    private int ShortCharge;
    private int Quantity;
    private int ChargeCurrencyId;
    private int DaysToRoll;
}
