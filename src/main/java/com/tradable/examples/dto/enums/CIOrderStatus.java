package com.tradable.examples.dto.enums;

import com.tradable.examples.dto.JSONOrderStatus;

/**
 * Enum that models the CityIndex order status, and maps some of the status to the TOS api status.
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public enum  CIOrderStatus {
    ERROR_MESSAGE(null),
    PENDING(JSONOrderStatus.PENDING),
    ACCEPTED(JSONOrderStatus.PENDING),
    OPEN(JSONOrderStatus.EXECUTED),
    CANCELED(JSONOrderStatus.CANCELLED),    // when orders are rejected you actually gets Cancelled back, even though there is a rejected status
    REJECTED(null),
    SUSPENDED(null),
    YELLOW_CARD(null),
    CLOSED(null),
    RED_CARD(null),
    TRIGGERED(null);

    private final JSONOrderStatus apiStatue;

    CIOrderStatus(JSONOrderStatus apiStatue) {
        this.apiStatue = apiStatue;
    }

    public JSONOrderStatus getApiStatus() {
        if (apiStatue == null) {
            throw new IllegalStateException("CIOrderStatus " + this + " is not mapped to an API status.");
        }
        return apiStatue;
    }

    /**
     * The Streaming API sends the status as a string. We can't use the enum name, since some values have space
     * @param value the streaming api status
     * @return the status
     */
    public static CIOrderStatus fromString(String value) {
        switch (value) {
            case "Pending"      : return PENDING;
            case "Accepted"     : return ACCEPTED;
            case "Open"         : return OPEN;
            case "Cancelled"    : return CANCELED;
            case "Rejected"     : return REJECTED;
            case "Suspended"    : return SUSPENDED;
            case "Yellow Card"  : return YELLOW_CARD;
            case "Closed"       : return CLOSED;
            case "Red Card"     : return RED_CARD;
            case "Triggered"    : return TRIGGERED;
            default: throw new IllegalStateException("unmapped orderStatus " + value);
        }
    }

    /**
     * The REST API sends the status code an int
     * @param orderStatus the REST api status code
     * @return the status
     */
    public static CIOrderStatus fromInt(int orderStatus) {
        switch (orderStatus) {
            case -1: return CIOrderStatus.ERROR_MESSAGE; // this can be used in ApiTradeOrderResponseDTO
            case 1 : return CIOrderStatus.PENDING;
            case 2 : return CIOrderStatus.ACCEPTED;
            case 3 : return CIOrderStatus.OPEN;
            case 4 : return CIOrderStatus.CANCELED;
            case 5 : return CIOrderStatus.REJECTED;
            case 6 : return CIOrderStatus.SUSPENDED;
            case 8 : return CIOrderStatus.YELLOW_CARD;
            case 9 : return CIOrderStatus.CLOSED;
            case 10: return CIOrderStatus.RED_CARD;
            case 11: return CIOrderStatus.TRIGGERED;
            default: throw new IllegalStateException("unmapped orderStatus " + orderStatus);
        }
    }
}
