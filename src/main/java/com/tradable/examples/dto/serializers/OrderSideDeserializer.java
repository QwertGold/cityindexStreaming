package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.JSONOrderSide;


/**
 * Converts the CityIndex Direction string to a JSONOrderSide
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public class OrderSideDeserializer extends JsonDeserializer<JSONOrderSide> {

    @Override
    public JSONOrderSide deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String direction = parser.getValueAsString();
        if (direction.equals("buy")) {
            return JSONOrderSide.BUY;
        }
        if (direction.equals("sell")) {
            return JSONOrderSide.SELL;
        }
        throw new IllegalStateException("Unknown order direction " + direction);
    }
}
