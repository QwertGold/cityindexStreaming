package com.tradable.examples.dto.serializers;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Converts from the .NET WCF-date there are 3 possible formats: "/Date(1448275384407)/", "/Date(-1448275384407+1200)/" , "/Date(1448275384407-0330)/"
 * The timezone part is 2 digits for hour, and two digits for minutes
 * Note: this deserializer only works in the time is UTC, as it does not handle timezone offset.
 * @author Klaus Groenbaek
 *         Created 25/11/15.
 */
public class WCFDateDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        String valueAsString = parser.getValueAsString();
        return parseWCFDate(valueAsString);
    }

    protected static Instant parseWCFDate(String valueAsString) {
        String contents = valueAsString.replace("/Date(", "").replace(")/", "");

        int timeZoneStart = contents.indexOf("+");
        if (timeZoneStart == -1) {
            timeZoneStart = contents.lastIndexOf("-");
        }

        if (timeZoneStart == -1 || timeZoneStart == 0) {
            // -1 if there is no +/-, 0 if the only - is the start of the string
            return Instant.ofEpochMilli(Long.parseLong(contents));
        }

        long epocMillis = Long.parseLong(contents.substring(0, timeZoneStart));
        String zoneOffSet = contents.substring(timeZoneStart);

        int minutesStart = zoneOffSet.length() == 5 ? 3 : 2;

        int hours = Integer.parseInt(zoneOffSet.substring(0, minutesStart));
        int minutes = Integer.parseInt(zoneOffSet.substring(minutesStart));
        if (hours < 0) {
            minutes *= -1;  // if ofset is negative it applies to both hours and minutes
        }

        return Instant.ofEpochMilli(epocMillis).plus(hours, ChronoUnit.HOURS).plus(minutes, ChronoUnit.MINUTES);
    }
}
