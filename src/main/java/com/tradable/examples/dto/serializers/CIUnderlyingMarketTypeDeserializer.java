package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIUnderlyingMarketType;

/**
 * Deserializer for the CIUnderlyingMarketType
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public class CIUnderlyingMarketTypeDeserializer extends JsonDeserializer<CIUnderlyingMarketType> {

    @Override
    public CIUnderlyingMarketType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        int marketUnderlyingTypeId = p.getIntValue();
        return CIUnderlyingMarketType.values()[marketUnderlyingTypeId -1];
    }
}
