package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIQuoteStatus;

/**
 * @author Klaus Groenbaek
 *         Created 01/12/15.
 */
public class CIQuoteStatusDeserializer extends JsonDeserializer<CIQuoteStatus> {
    @Override
    public CIQuoteStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int statusCode = p.getIntValue();
        return CIQuoteStatus.values()[statusCode -1];
    }
}
