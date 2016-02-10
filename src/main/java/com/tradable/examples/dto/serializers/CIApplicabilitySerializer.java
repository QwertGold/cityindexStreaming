package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tradable.examples.dto.enums.CIApplicability;

/**
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
public class CIApplicabilitySerializer extends JsonSerializer<CIApplicability> {
    @Override
    public void serialize(CIApplicability value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.name());
    }
}
