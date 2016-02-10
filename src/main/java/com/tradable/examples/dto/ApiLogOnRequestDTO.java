package com.tradable.examples.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * @author Klaus Groenbaek
 *         Created 23/11/15.
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@Data
@Accessors(chain = true)
public class ApiLogOnRequestDTO {
    private String UserName;
    private String Password;
    private String AppKey;
    private String AppVersion;
    private String AppComments = "";
}
