package com.tradable.examples.dto;

/**
 * @author Klaus Groenbaek
 *         Created 19/06/15.
 */
public enum JSONOrderSide {

    BUY, SELL;
    public JSONOrderSide opposite() {
        if (this == BUY){
            return SELL;
        }
        return BUY;
    }
}
