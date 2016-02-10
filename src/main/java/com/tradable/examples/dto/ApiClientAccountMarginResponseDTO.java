package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 30/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiClientAccountMarginResponseDTO {

    /**
     * The cash balance expressed in the client account base currency.
     */
    private double Cash;
    /**
     * The client account total margin requirement expressed in base currency.
     */
    private double Margin;
    /**
     * The margin indicator expressed as a percentage.
     */
    private double MarginIndicator;
    /**
     * The net equity expressed in the client account base currency.
     */
    private double NetEquity;
    /**
     * The open trade equity (open or unrealised P&L) expressed in the client account base currency.
     */
    private double OpenTradeEquity;
    /**
     * The tradable funds in the client account base currency.
     */
    private double TradableFunds;
    /**
     * The amount in base currency that is still being processed for addition to the funds in the account.
     */
    private double PendingFunds;
    /**
     * The trading resource expressed in the client account base currency.
     */
    private double TradingResource;
    /**
     * The total margin requirement expressed in the client account base currency.
     */
    private double TotalMarginRequirement;
    /**
     * The ID code for the client account base currency. The table of lookup codes can be found at Lookup Values.
     */
    private int CurrencyId;
    /**
     * The base currency ISO code.
     */
    private String CurrencyIsoCode;


}
