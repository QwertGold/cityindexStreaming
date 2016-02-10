package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIApplicability;

/**
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
public class CIApplicabilityDeserializer extends JsonDeserializer<CIApplicability> {
    @Override
    public CIApplicability deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return CIApplicability.valueOf(p.getValueAsString());
    }
}
