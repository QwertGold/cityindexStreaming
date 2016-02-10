package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIMarketSettingsType;

/**
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public class CIMarketSettingsTypeDeserializer extends JsonDeserializer<CIMarketSettingsType> {
    @Override
    public CIMarketSettingsType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return CIMarketSettingsType.fromInt(value);
    }
}
