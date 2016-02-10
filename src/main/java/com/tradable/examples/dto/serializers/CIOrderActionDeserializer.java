package com.tradable.examples.dto.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tradable.examples.dto.enums.CIOrderAction;

/**
 * @author Klaus Groenbaek
 *         Created 01/12/15.
 */
public class CIOrderActionDeserializer extends JsonDeserializer<CIOrderAction> {
    @Override
    public CIOrderAction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return CIOrderAction.values()[value -1];
    }
}
