package com.tradable.examples.dto.enums;

/**
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public enum  CIOptionType {
    PUT, CALL;

    public static CIOptionType fromInt(int value) {
        switch (value) {
            case 1: return PUT;
            case 2: return CALL;
            default: throw new IllegalStateException("Unmapped OptionTypeId " + value);
        }

    }
}
