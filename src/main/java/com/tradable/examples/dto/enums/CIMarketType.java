package com.tradable.examples.dto.enums;

/**
 * MarketTypeId from ApiMarketInformationDTO.
 * Note: this is not the instrument type
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public enum  CIMarketType {
    OPTION, ORDINARY, BINARY;

    public static CIMarketType fromInt(int value) {
        switch (value) {
            case 1: return OPTION;
            case 2: return ORDINARY;
            case 4: return BINARY;
            default: throw new IllegalStateException("Unmapped marketTypeId " + value);
        }
    }
}
