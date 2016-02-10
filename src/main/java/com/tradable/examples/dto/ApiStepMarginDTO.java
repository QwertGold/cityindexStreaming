package com.tradable.examples.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Klaus Groenbaek
 *         Created 16/12/15.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiStepMarginDTO {
    /**
     * Flag indicating whether this market is eligible for step margin.
     */
    private boolean EligibleForStepMargin;
    /**
     * Flag indicating whether this market has step margin configured.
     */
    private boolean StepMarginConfigured;
    /**
     * Flag indicating whether this market's step margin configuration is inherited from a parent account operator.
     */
    private boolean InheritedFromParentAccountOperator;
    /**
     * The step margining bands used for this market / account operator.
     */
    private List<ApiStepMarginBandDTO> Bands;

}
