package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.JSONOrderType;

/**
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public class OrderTypeDeserializer extends JsonDeserializer<JSONOrderType> {

    @Override
    public JSONOrderType deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        int typeId = parser.getIntValue();
        switch (typeId) {
            // typeId 0, is possible when listing positions as part of the ActiveOrders response, but it not listed in the docs
            case 0 : return JSONOrderType.MARKET;
            case 1 : return JSONOrderType.MARKET;
            case 2 : return JSONOrderType.STOP;
            case 3 : return JSONOrderType.LIMIT;
            default: throw new IllegalStateException("Unmapped order type " + typeId);
        }

    }
}
