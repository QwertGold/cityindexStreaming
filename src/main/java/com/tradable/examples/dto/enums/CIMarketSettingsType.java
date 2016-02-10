package com.tradable.examples.dto.enums;

/**
 *
 * Instrument type for CityIndex
 * @author Klaus Groenbaek
 *         Created 17/12/15.
 */
public enum CIMarketSettingsType {
    SPREAD, CFD, BINARY, FX, CASH, ALL;

    public static CIMarketSettingsType fromInt(int value) {
        switch (value) {
            case 1: return SPREAD;
            case 2: return CFD;
            case 3: return BINARY;
            case 4: return FX;
            case 5: return CASH;
            case 6: return ALL;
            default: throw new IllegalStateException("unmapped MarketSettingsTypeId " + value);
        }
    }

}
