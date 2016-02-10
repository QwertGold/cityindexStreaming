package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 24/11/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiTradingAccountDTO {
    /**
     * Trading Account ID.
     */
    private int TradingAccountId;
    /**
     * Trading Account Code.
     */
    private String TradingAccountCode;
    /**
     * Trading account status with possible values (Open, Closed).
      */
    private String TradingAccountStatus;
    /**
     * Trading account type with possible values (Spread, CFD).
     */
    private String TradingAccountType;

}
