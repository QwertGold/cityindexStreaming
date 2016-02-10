package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIOrderStatus;

/**
 * Deserializer from the CityIndex order status integer to an enum
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public class OrderStatusDeserializer extends JsonDeserializer<CIOrderStatus> {
    @Override
    public CIOrderStatus deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        int orderStatus = parser.getValueAsInt();
        return CIOrderStatus.fromInt(orderStatus);
    }
}
