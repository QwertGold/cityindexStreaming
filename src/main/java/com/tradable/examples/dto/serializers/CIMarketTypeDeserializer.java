package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIMarketType;

/**
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public class CIMarketTypeDeserializer extends JsonDeserializer<CIMarketType> {
    @Override
    public CIMarketType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return CIMarketType.fromInt(value);
    }
}
