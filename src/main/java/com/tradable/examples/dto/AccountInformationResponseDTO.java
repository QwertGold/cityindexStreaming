package com.tradable.examples.dto;

import java.util.List;

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
public class AccountInformationResponseDTO {

    /**
     * Logon user name.
     */
    private String LogonUserName;
    /**
     * Client account ID.
     */
    private int ClientAccountId;
    /**
     * Base currency of the client account.
     */
    private String ClientAccountCurrency;
    /**
     * Account Operator ID.
     */
    private int AccountOperatorId;
    /**
     * A list of trading accounts.
     */
    private List<ApiTradingAccountDTO> TradingAccounts;
    /**
     * The user's personal email address.
     */
    private String PersonalEmailAddress;
    /**
     * Flag indicating whether the user has more than one email address configured.
     */
    private boolean HasMultipleEmailAddresses;
    /**
     * Information about account holders.
     */
    private List<ApiAccountHolderDTO> AccountHolders;


}
