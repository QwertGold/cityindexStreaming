package com.tradable.examples.dto.serializers;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializer for WCF date
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
public class WCFDateSerializer extends JsonSerializer<Instant> {
    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString("/Date(" + Long.toString(value.toEpochMilli()) + ")/ ");
    }
}
