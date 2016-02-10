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
public class ApiAccountHolderDTO {
    /**
     * Legal party identifier.
     */
    private int LegalPartyId;
    /**
     * Name of person associated with joint account.
     */
    private String Name;
}
