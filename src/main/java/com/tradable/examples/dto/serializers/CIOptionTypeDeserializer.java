package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIOptionType;

/**
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public class CIOptionTypeDeserializer extends JsonDeserializer<CIOptionType> {
    @Override
    public CIOptionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return CIOptionType.fromInt(value);
    }
}
