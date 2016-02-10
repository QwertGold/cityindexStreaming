package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tradable.examples.dto.enums.CIQuoteStatus;
import com.tradable.examples.dto.serializers.CIQuoteStatusDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 01/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiQuoteResponseDTO {
    /**
     * Quote ID.
     */
    private int QuoteId;
    /**
     * Quote status. The table of lookup codes can be found at Lookup Values.
     */
    @JsonDeserialize(using = CIQuoteStatusDeserializer.class)
    private CIQuoteStatus Status;
    /**
     * A reason code in case of failure
     */
    private int StatusReason;

}
