package com.tradable.examples.dto;

/**
 * Information annotation indicating that this property is missing from the broker api's public documentation
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public @interface Undocumented {
    String value() default "";
}
