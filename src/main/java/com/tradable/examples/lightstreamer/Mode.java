package com.tradable.examples.lightstreamer;

/**
 * @author Klaus Groenbaek
 *         Created 16/02/16.
 */
public enum Mode {
    DISTINCT("DISTINCT"), MERGE("MERGE"), RAW("RAW");

    private final String mode;

    Mode(String mode) {
        this.mode = mode;
    }

    public String asString() {
        return mode;
    }
}
