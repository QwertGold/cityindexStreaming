package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tradable.examples.dto.JSONOrderSide;

/**
 * @author Klaus Groenbaek
 *         Created 10/12/15.
 */
public class OrderSideSerializer extends JsonSerializer<JSONOrderSide> {
    @Override
    public void serialize(JSONOrderSide value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        switch (value) {
            case BUY:
                gen.writeString("buy");
                break;
            case SELL:
                gen.writeString("sell");
                break;
        }
    }
}
